package poa.poaskrewritev2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.core.filter.Filterable;
import org.bukkit.Bukkit;
import poa.poaskrewritev2.events.bukkitevents.ConsoleLogEvent;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;

public final class ConsoleLogInjector {

    private static volatile boolean injected = false;

    private static volatile Filter globalFilter;
    private static volatile Filterable filterTarget;

    /*
     * Prevent logging performed from inside ConsoleLogEvent listeners
     * from recursively firing another ConsoleLogEvent.
     */
    private static final ThreadLocal<Boolean> FIRING_EVENT =
            ThreadLocal.withInitial(() -> false);

    private ConsoleLogInjector() {}

    public static synchronized void inject() {
        if (injected)
            return;

        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration configuration = context.getConfiguration();

        globalFilter = new AbstractFilter() {

            @Override
            public Result filter(LogEvent event) {
                if (FIRING_EVENT.get())
                    return Result.NEUTRAL;

                String message = formatEventMessage(event);

                if (message.isEmpty())
                    return Result.NEUTRAL;

                try {
                    FIRING_EVENT.set(true);

                    ConsoleLogEvent consoleLogEvent = new ConsoleLogEvent(
                            event,
                            message,
                            AnsiStripper.strip(message)
                    );

                    Bukkit.getPluginManager().callEvent(consoleLogEvent);

                    if (consoleLogEvent.isCancelled())
                        return Result.DENY;

                } finally {
                    FIRING_EVENT.set(false);
                }

                return Result.NEUTRAL;
            }
        };

        /*
         * Filters added after Log4j has already started should be
         * explicitly started.
         */
        globalFilter.start();

        /*
         * Paper's standard log4j2.xml sends the root logger into an
         * appender named "Async".
         */
        Appender asyncAppender = configuration.getAppender("Async");

        if (asyncAppender instanceof Filterable filterable) {
            filterTarget = filterable;
            filterTarget.addFilter(globalFilter);
        } else {
            /*
             * Fallback for a custom Log4j configuration.
             */
            filterTarget = configuration.getRootLogger();
            filterTarget.addFilter(globalFilter);

            context.updateLoggers();
        }

        injected = true;
    }

    public static synchronized void uninject() {
        if (!injected)
            return;

        injected = false;

        try {
            if (filterTarget != null && globalFilter != null)
                filterTarget.removeFilter(globalFilter);
        } finally {
            if (globalFilter != null) {
                try {
                    globalFilter.stop();
                } catch (Throwable ignored) {}
            }

            filterTarget = null;
            globalFilter = null;

            FIRING_EVENT.remove();
        }
    }

    private static String formatEventMessage(LogEvent event) {
        String message = "";

        if (event.getMessage() != null)
            message = event.getMessage().getFormattedMessage();

        Throwable thrown = event.getThrown();

        if (thrown != null) {
            StringWriter stringWriter = new StringWriter();

            thrown.printStackTrace(
                    new PrintWriter(stringWriter)
            );

            if (message == null || message.isEmpty())
                message = stringWriter.toString();
            else
                message += "\n" + stringWriter;
        }

        return message == null ? "" : message;
    }

    public static final class AnsiStripper {

        private static final Pattern ANSI = Pattern.compile(
                "(?:\\u001B\\[[0-9;?]*[ -/]*[@-~])"
                        + "|(?:\\u001B[@-Z\\\\-_])"
                        + "|(?:\\u009B[0-9;?]*[ -/]*[@-~])"
        );

        private AnsiStripper() {}

        public static String strip(String string) {
            if (string == null || string.isEmpty())
                return string;

            return ANSI.matcher(string).replaceAll("");
        }
    }
}