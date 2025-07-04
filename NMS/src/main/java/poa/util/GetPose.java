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
            case "1211" -> GetPose1211.getPose(entity);
            case "1213" -> GetPose1213.getPose(entity);
            case "1214" -> GetPose1214.getPose(entity);
            case "1215" -> GetPose1215.getPose(entity);
            case "1216" -> GetPose1216.getPose(entity);
            case "1217" -> GetPose1217.getPose(entity);
            default -> null;
        };
    }

    public static String getPoseString(Entity entity){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> GetPose1202.getPoseString(entity);
            case "1204" -> GetPose1204.getPoseString(entity);
            case "1206" -> GetPose1206.getPoseString(entity);
            case "121" -> GetPose121.getPoseString(entity);
            case "1211" -> GetPose1211.getPoseString(entity);
            case "1213" -> GetPose1213.getPoseString(entity);
            case "1214" -> GetPose1214.getPoseString(entity);
            case "1215" -> GetPose1215.getPoseString(entity);
            case "1216" -> GetPose1216.getPoseString(entity);
            case "1217" -> GetPose1217.getPoseString(entity);
            default -> null;
        };
    }

}
