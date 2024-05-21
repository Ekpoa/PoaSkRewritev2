package poa.util;

import org.bukkit.craftbukkit.v1_20_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class GetPose1202 {

    public static Object getPose(Entity entity){
        return ((CraftEntity) entity).getHandle().getPose();
    }

    public static String getPoseString(Entity entity){
        return getPose(entity).toString();
    }

}
