package poa.poaskrewritev2.util.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

import java.util.UUID;

public class UUIDType {

    public static void register(){}

    static {
        if(Classes.getExactClassInfo(UUID.class) == null){
            Classes.registerClass(new ClassInfo(UUID.class, "uuid")
                    .user("uuid")
                    .name("UUID")
                    .description("uuid")
                    .since("0.0")
                    .parser(new Parser<>() {
                        public boolean canParse(ParseContext context) {
                            return false;
                        }

                        @Override
                        public String toString(Object o, int flags) {
                            return o.toString();
                        }

                        @Override
                        public String toVariableNameString(Object o) {
                            return o.toString();
                        }
                    }));
        }

    }

}
