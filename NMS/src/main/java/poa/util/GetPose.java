package poa.util;

import org.bukkit.entity.Entity;
import poa.util.BukkitVersion;
import poa.util.GetPose1202;
import poa.util.GetPose1204;
import poa.util.GetPose1206;

public class GetPose {

    public static Object getPose(Entity entity){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> GetPose1202.getPose(entity);
            case "1204" -> GetPose1204.getPose(entity);
            case "1206" -> GetPose1206.getPose(entity);
            case "121" -> GetPose121.getPose(entity);
            case "1211" -> GetPose121.getPose(entity);
            default -> null;
        };
    }

    public static String getPoseString(Entity entity){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> GetPose1202.getPoseString(entity);
            case "1204" -> GetPose1204.getPoseString(entity);
            case "1206" -> GetPose1206.getPoseString(entity);
            case "121" -> GetPose121.getPoseString(entity);
            case "1211" -> GetPose121.getPoseString(entity);
            default -> null;
        };
    }

}
