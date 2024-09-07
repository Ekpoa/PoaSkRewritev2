package poa.util;

import lombok.SneakyThrows;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class EntityTypeFromString121 {
    @SneakyThrows
    public static EntityType<?> entityTypeFromString(String string) {
        return BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.tryParse("minecraft:" + string.toLowerCase()));
    }
}
