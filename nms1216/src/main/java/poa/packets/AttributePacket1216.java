package poa.packets;

import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundUpdateAttributesPacket;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.attribute.CraftAttribute;

import java.util.List;

public class AttributePacket1216 {

    public static Object packet(int entityId, Attribute attribute, double value){

        final Holder<net.minecraft.world.entity.ai.attributes.Attribute> atr = CraftAttribute.bukkitToMinecraftHolder(attribute);


        final AttributeInstance attributeInstance = new AttributeInstance(atr, (a) -> {});
        attributeInstance.setBaseValue(value);

        return new ClientboundUpdateAttributesPacket(entityId, List.of(attributeInstance));

    }

    @SuppressWarnings({"UnstableApiUsage", "removal"})
    public static Object packet(int entityId, String attribute, double value){
        return packet(entityId, Attribute.valueOf(attribute.toUpperCase()), value);
    }

}
