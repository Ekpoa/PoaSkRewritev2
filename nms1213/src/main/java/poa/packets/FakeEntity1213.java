package poa.packets;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;
import poa.util.EntityTypeFromString1213;

import java.util.List;
import java.util.UUID;

public class FakeEntity1213 {

    public static Object fakeEntityPacket(int id, Location location, String type, UUID uuid, int data) {
        return new ClientboundAddEntityPacket(id,
                uuid,
                location.getX(),
                location.getY(),
                location.z(),
                location.getPitch(),
                location.getYaw(),
                EntityTypeFromString1213.entityTypeFromString(type),
                data,
                new Vec3(0, 0, 0),
                0
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
