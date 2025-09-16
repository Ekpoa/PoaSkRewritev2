package poa.packets;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.SneakyThrows;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class FakeEntity1206 {

    public static Object fakeEntityPacket(int id, Location location, String type, UUID uuid, int data) {
        if(List.of("text_display", "block_display", "item_display").contains(type.toLowerCase())){
            return new ClientboundAddEntityPacket(id,
                    uuid,
                    location.getX(),
                    location.getY(),
                    location.getZ(),
                    location.getPitch(),
                    location.getYaw(),
                    entityTypeFromString(type),
                    data,
                    new Vec3(0, 0, 0),
                    location.getYaw()
            );
        }
        return new ClientboundAddEntityPacket(id,
                uuid,
                location.getX(),
                location.getY(),
                location.z(),
                (location.getPitch() * 256.0F / 360.0F),
                (location.getYaw() * 256.0F / 360.0F),
                entityTypeFromString(type),
                data,
                new Vec3(0, 0, 0),
                location.getYaw()
        );

    }

    public static Object fakeEntityPacket(int id, Location location, String type, int data){
        return fakeEntityPacket(id, location, type, UUID.randomUUID(), data);
    }

    public static Object fakeEntityPacket(int id, Location location, String type, UUID uuid){
        return fakeEntityPacket(id, location, type, uuid, 0);
    }

    public static Object fakeEntityPacket(int id, Location location, String type){
        return fakeEntityPacket(id, location, type, 0);
    }

    @SneakyThrows
    private static EntityType<?> entityTypeFromString(String string) {
        return BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.tryParse("minecraft:" + string.toLowerCase()));
    }

//    @SneakyThrows
//    private static EntityType<?> entityTypeFromString(String string) {
//        Class<?> entityTypeClass = EntityType.class;
//
//        for (Field field : entityTypeClass.getDeclaredFields()) {
//            if (field.getName().equalsIgnoreCase(string))
//                return (EntityType<?>) field.get(null);
//        }
//
//
//        return EntityType.PIG;
//    }



    public static Object removeFakeEntityPacket(List<Integer> idList){
        IntList intList = new IntArrayList();
        intList.addAll(idList);

        return new ClientboundRemoveEntitiesPacket(intList);
    }

}
