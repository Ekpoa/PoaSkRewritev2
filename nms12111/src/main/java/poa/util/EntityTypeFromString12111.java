package poa.util;

import lombok.SneakyThrows;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;

public class EntityTypeFromString12111 {
    @SneakyThrows
    public static EntityType<?> entityTypeFromString(String string) {
        return BuiltInRegistries.ENTITY_TYPE.get(Identifier.tryParse("minecraft:" + string.toLowerCase())).get().value();
    }
}
