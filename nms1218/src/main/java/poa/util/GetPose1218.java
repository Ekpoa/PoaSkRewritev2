package poa.util;

import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class GetPose1218 {

    public static Object getPose(Entity entity){
        return ((CraftEntity) entity).getHandle().getPose();
    }

    public static String getPoseString(Entity entity){
        return getPose(entity).toString();
    }

}
