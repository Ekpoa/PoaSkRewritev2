package poa.util;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Entity;

public class EntityFromID1219 {

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
