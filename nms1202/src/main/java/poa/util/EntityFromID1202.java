package poa.util;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.entity.Entity;

public class EntityFromID1202 {

    public static Entity getEntityFromId(int id){
        for (World world : Bukkit.getWorlds()) {
            final ServerLevel level = ((CraftWorld) world).getHandle();
            final net.minecraft.world.entity.Entity entity = level.getEntity(id);
            if(entity != null)
                return entity.getBukkitEntity();
        }
        return null;
    }

}
