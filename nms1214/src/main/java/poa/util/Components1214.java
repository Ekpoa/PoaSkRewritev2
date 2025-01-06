package poa.util;

import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;

public class Components1214 {

    public static Component component(Object nms) {
        return PaperAdventure.WRAPPER_AWARE_SERIALIZER.deserialize((net.minecraft.network.chat.Component) nms);
    }

    public static Object nmsComponent(final Component adventure) {
        return PaperAdventure.WRAPPER_AWARE_SERIALIZER.serialize(adventure);
    }


    public static Component componentActual(net.minecraft.network.chat.Component nms) {
        return PaperAdventure.WRAPPER_AWARE_SERIALIZER.deserialize(nms);
    }

    public static net.minecraft.network.chat.Component nmsComponentActual(final Component adventure) {
        return PaperAdventure.WRAPPER_AWARE_SERIALIZER.serialize(adventure);
    }
}
