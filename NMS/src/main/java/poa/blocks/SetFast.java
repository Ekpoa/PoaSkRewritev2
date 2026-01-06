package poa.blocks;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import poa.util.BukkitVersion;

public class SetFast {

    public static void setFast(Location[] locations, BlockData blockData){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetFast1202.setFast(locations, blockData);
            case "1204" -> SetFast1204.setFast(locations, blockData);
            case "1206" -> SetFast1206.setFast(locations, blockData);
            case "121" -> SetFast121.setFast(locations, blockData);
            case "1211" -> SetFast1211.setFast(locations, blockData);
            case "1213" -> SetFast1213.setFast(locations, blockData);
            case "1214" -> SetFast1214.setFast(locations, blockData);
            case "1215" -> SetFast1215.setFast(locations, blockData);
            case "1216" -> SetFast1216.setFast(locations, blockData);
            case "1217" -> SetFast1217.setFast(locations, blockData);
            case "1218" -> SetFast1218.setFast(locations, blockData);
            case "1219" -> SetFast1219.setFast(locations, blockData);
            case "12110" -> SetFast12110.setFast(locations, blockData);
            case "12111" -> SetFast12111.setFast(locations, blockData);
        }
    }

    public static void setFaster(Location location, Location location2, BlockData blockData){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetFast1202.setFaster(location, location2, blockData);
            case "1204" -> SetFast1204.setFaster(location, location2, blockData);
            case "1206" -> SetFast1206.setFaster(location, location2, blockData);
            case "121" -> SetFast121.setFaster(location, location2, blockData);
            case "1211" -> SetFast1211.setFaster(location, location2, blockData);
            case "1213" -> SetFast1213.setFaster(location, location2, blockData);
            case "1214" -> SetFast1214.setFaster(location, location2, blockData);
            case "1215" -> SetFast1215.setFaster(location, location2, blockData);
            case "1216" -> SetFast1216.setFaster(location, location2, blockData);
            case "1217" -> SetFast1217.setFaster(location, location2, blockData);
            case "1218" -> SetFast1218.setFaster(location, location2, blockData);
            case "1219" -> SetFast1219.setFaster(location, location2, blockData);
            case "12110" -> SetFast12110.setFaster(location, location2, blockData);
            case "12111" -> SetFast12111.setFaster(location, location2, blockData);
        }
    }

    public static void replaceFast(Location location, Location location2, BlockData from, BlockData to){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetFast1202.replaceFast(location, location2, from, to);
            case "1204" -> SetFast1204.replaceFast(location, location2, from, to);
            case "1206" -> SetFast1206.replaceFast(location, location2, from, to);
            case "121" -> SetFast121.replaceFast(location, location2, from, to);
            case "1211" -> SetFast1211.replaceFast(location, location2, from, to);
            case "1213" -> SetFast1213.replaceFast(location, location2, from, to);
            case "1214" -> SetFast1214.replaceFast(location, location2, from, to);
            case "1215" -> SetFast1215.replaceFast(location, location2, from, to);
            case "1216" -> SetFast1216.replaceFast(location, location2, from, to);
            case "1217" -> SetFast1217.replaceFast(location, location2, from, to);
            case "1218" -> SetFast1218.replaceFast(location, location2, from, to);
            case "1219" -> SetFast1219.replaceFast(location, location2, from, to);
            case "12110" -> SetFast12110.replaceFast(location, location2, from, to);
            case "12111" -> SetFast12111.replaceFast(location, location2, from, to);
        }
    }



}
