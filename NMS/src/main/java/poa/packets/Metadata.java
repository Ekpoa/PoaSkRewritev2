package poa.packets;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.block.data.BlockData;
import poa.util.BukkitVersion;

public class Metadata {


    public static Object basePacketForEntity(int id, boolean fire, boolean invisible, boolean glow, String name, boolean nameVisible, boolean silent, boolean gravity, String pose) {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" ->
                    Metadata1202.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1204" ->
                    Metadata1204.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1206" ->
                    Metadata1206.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "121" ->
                    Metadata121.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1211" ->
                    Metadata1211.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1213" ->
                    Metadata1213.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1214" ->
                    Metadata1214.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1215" ->
                    Metadata1215.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1216" ->
                    Metadata1216.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1217" ->
                    Metadata1217.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1218" ->
                    Metadata1218.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "1219" ->
                    Metadata1219.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "12110" ->
                    Metadata12110.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            case "12111" ->
                    Metadata12111.basePacketForEntity(id, fire, invisible, glow, name, nameVisible, silent, gravity, pose);
            default -> null;
        };
    }

    Metadata1202 metadata1202;
    Metadata1204 metadata1204;
    Metadata1206 metadata1206;
    Metadata121 metadata121;
    Metadata1211 metadata1211;
    Metadata1213 metadata1213;
    Metadata1214 metadata1214;
    Metadata1215 metadata1215;
    Metadata1216 metadata1216;
    Metadata1217 metadata1217;
    Metadata1218 metadata1218;
    Metadata1219 metadata1219;
    Metadata12110 metadata12110;
    Metadata12111 metadata12111;


    public Metadata(int id) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202 = new Metadata1202(id);
            case "1204" -> metadata1204 = new Metadata1204(id);
            case "1206" -> metadata1206 = new Metadata1206(id);
            case "121" -> metadata121 = new Metadata121(id);
            case "1211" -> metadata1211 = new Metadata1211(id);
            case "1213" -> metadata1213 = new Metadata1213(id);
            case "1214" -> metadata1214 = new Metadata1214(id);
            case "1215" -> metadata1215 = new Metadata1215(id);
            case "1216" -> metadata1216 = new Metadata1216(id);
            case "1217" -> metadata1217 = new Metadata1217(id);
            case "1218" -> metadata1218 = new Metadata1218(id);
            case "1219" -> metadata1219 = new Metadata1219(id);
            case "12110" -> metadata12110 = new Metadata12110(id);
            case "12111" -> metadata12111 = new Metadata12111(id);
        }
    }

    public void setOnFire(boolean onFire) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setOnFire(onFire);
            case "1204" -> metadata1204.setOnFire(onFire);
            case "1206" -> metadata1206.setOnFire(onFire);
            case "121" -> metadata121.setOnFire(onFire);
            case "1211" -> metadata1211.setOnFire(onFire);
            case "1213" -> metadata1213.setOnFire(onFire);
            case "1214" -> metadata1214.setOnFire(onFire);
            case "1215" -> metadata1215.setOnFire(onFire);
            case "1216" -> metadata1216.setOnFire(onFire);
            case "1217" -> metadata1217.setOnFire(onFire);
            case "1218" -> metadata1218.setOnFire(onFire);
            case "1219" -> metadata1219.setOnFire(onFire);
            case "12110" -> metadata12110.setOnFire(onFire);
            case "12111" -> metadata12111.setOnFire(onFire);
        }
    }

    public void setInvisible(boolean invisible) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInvisible(invisible);
            case "1204" -> metadata1204.setInvisible(invisible);
            case "1206" -> metadata1206.setInvisible(invisible);
            case "121" -> metadata121.setInvisible(invisible);
            case "1211" -> metadata1211.setInvisible(invisible);
            case "1213" -> metadata1213.setInvisible(invisible);
            case "1214" -> metadata1214.setInvisible(invisible);
            case "1215" -> metadata1215.setInvisible(invisible);
            case "1216" -> metadata1216.setInvisible(invisible);
            case "1217" -> metadata1217.setInvisible(invisible);
            case "1218" -> metadata1218.setInvisible(invisible);
            case "1219" -> metadata1219.setInvisible(invisible);
            case "12110" -> metadata12110.setInvisible(invisible);
            case "12111" -> metadata12111.setInvisible(invisible);
        }
    }

    public void setGlow(boolean glow) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGlow(glow);
            case "1204" -> metadata1204.setGlow(glow);
            case "1206" -> metadata1206.setGlow(glow);
            case "121" -> metadata121.setGlow(glow);
            case "1211" -> metadata1211.setGlow(glow);
            case "1213" -> metadata1213.setGlow(glow);
            case "1214" -> metadata1214.setGlow(glow);
            case "1215" -> metadata1215.setGlow(glow);
            case "1216" -> metadata1216.setGlow(glow);
            case "1217" -> metadata1217.setGlow(glow);
            case "1218" -> metadata1218.setGlow(glow);
            case "1219" -> metadata1219.setGlow(glow);
            case "12110" -> metadata12110.setGlow(glow);
            case "12111" -> metadata12111.setGlow(glow);
        }
    }

    public void setName(String name) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setName(name);
            case "1204" -> metadata1204.setName(name);
            case "1206" -> metadata1206.setName(name);
            case "121" -> metadata121.setName(name);
            case "1211" -> metadata1211.setName(name);
            case "1213" -> metadata1213.setName(name);
            case "1214" -> metadata1214.setName(name);
            case "1215" -> metadata1215.setName(name);
            case "1216" -> metadata1216.setName(name);
            case "1217" -> metadata1217.setName(name);
            case "1218" -> metadata1218.setName(name);
            case "1219" -> metadata1219.setName(name);
            case "12110" -> metadata12110.setName(name);
            case "12111" -> metadata12111.setName(name);
        }
    }

    public void setNameVisible(boolean nameVisible) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setNameVisible(nameVisible);
            case "1204" -> metadata1204.setNameVisible(nameVisible);
            case "1206" -> metadata1206.setNameVisible(nameVisible);
            case "121" -> metadata121.setNameVisible(nameVisible);
            case "1211" -> metadata1211.setNameVisible(nameVisible);
            case "1213" -> metadata1213.setNameVisible(nameVisible);
            case "1214" -> metadata1214.setNameVisible(nameVisible);
            case "1215" -> metadata1215.setNameVisible(nameVisible);
            case "1216" -> metadata1216.setNameVisible(nameVisible);
            case "1217" -> metadata1217.setNameVisible(nameVisible);
            case "1218" -> metadata1218.setNameVisible(nameVisible);
            case "1219" -> metadata1219.setNameVisible(nameVisible);
            case "12110" -> metadata12110.setNameVisible(nameVisible);
            case "12111" -> metadata12111.setNameVisible(nameVisible);
        }
    }


    public void setSilent(boolean silent) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setSilent(silent);
            case "1204" -> metadata1204.setSilent(silent);
            case "1206" -> metadata1206.setSilent(silent);
            case "121" -> metadata121.setSilent(silent);
            case "1211" -> metadata1211.setSilent(silent);
            case "1213" -> metadata1213.setSilent(silent);
            case "1214" -> metadata1214.setSilent(silent);
            case "1215" -> metadata1215.setSilent(silent);
            case "1216" -> metadata1216.setSilent(silent);
            case "1217" -> metadata1217.setSilent(silent);
            case "1218" -> metadata1218.setSilent(silent);
            case "1219" -> metadata1219.setSilent(silent);
            case "12110" -> metadata12110.setSilent(silent);
            case "12111" -> metadata12111.setSilent(silent);
        }
    }

    public void setGravity(boolean hasGravity) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGravity(hasGravity);
            case "1204" -> metadata1204.setGravity(hasGravity);
            case "1206" -> metadata1206.setGravity(hasGravity);
            case "121" -> metadata121.setGravity(hasGravity);
            case "1211" -> metadata1211.setGravity(hasGravity);
            case "1213" -> metadata1213.setGravity(hasGravity);
            case "1214" -> metadata1214.setGravity(hasGravity);
            case "1215" -> metadata1215.setGravity(hasGravity);
            case "1216" -> metadata1216.setGravity(hasGravity);
            case "1217" -> metadata1217.setGravity(hasGravity);
            case "1218" -> metadata1218.setGravity(hasGravity);
            case "1219" -> metadata1219.setGravity(hasGravity);
            case "12110" -> metadata12110.setGravity(hasGravity);
            case "12111" -> metadata12111.setGravity(hasGravity);
        }
    }

    public void setPose(String pose) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setPose(pose);
            case "1204" -> metadata1204.setPose(pose);
            case "1206" -> metadata1206.setPose(pose);
            case "121" -> metadata121.setPose(pose);
            case "1211" -> metadata1211.setPose(pose);
            case "1213" -> metadata1213.setPose(pose);
            case "1214" -> metadata1214.setPose(pose);
            case "1215" -> metadata1215.setPose(pose);
            case "1216" -> metadata1216.setPose(pose);
            case "1217" -> metadata1217.setPose(pose);
            case "1218" -> metadata1218.setPose(pose);
            case "1219" -> metadata1219.setPose(pose);
            case "12110" -> metadata12110.setPose(pose);
            case "12111" -> metadata12111.setPose(pose);
        }
    }

    public void setHealth(int health) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHealth(health);
            case "1204" -> metadata1204.setHealth(health);
            case "1206" -> metadata1206.setHealth(health);
            case "121" -> metadata121.setHealth(health);
            case "1211" -> metadata1211.setHealth(health);
            case "1213" -> metadata1213.setHealth(health);
            case "1214" -> metadata1214.setHealth(health);
            case "1215" -> metadata1215.setHealth(health);
            case "1216" -> metadata1216.setHealth(health);
            case "1217" -> metadata1217.setHealth(health);
            case "1218" -> metadata1218.setHealth(health);
            case "1219" -> metadata1219.setHealth(health);
            case "12110" -> metadata12110.setHealth(health);
            case "12111" -> metadata12111.setHealth(health);
        }
    }

    public void setRemainingAir(int air) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRemainingAir(air);
            case "1204" -> metadata1204.setRemainingAir(air);
            case "1206" -> metadata1206.setRemainingAir(air);
            case "121" -> metadata121.setRemainingAir(air);
            case "1211" -> metadata1211.setRemainingAir(air);
            case "1213" -> metadata1213.setRemainingAir(air);
            case "1214" -> metadata1214.setRemainingAir(air);
            case "1215" -> metadata1215.setRemainingAir(air);
            case "1216" -> metadata1216.setRemainingAir(air);
            case "1217" -> metadata1217.setRemainingAir(air);
            case "1218" -> metadata1218.setRemainingAir(air);
            case "1219" -> metadata1219.setRemainingAir(air);
            case "12110" -> metadata12110.setRemainingAir(air);
            case "12111" -> metadata12111.setRemainingAir(air);
        }
    }

    public void setItem(org.bukkit.inventory.ItemStack itemStack) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setItem(itemStack);
            case "1204" -> metadata1204.setItem(itemStack);
            case "1206" -> metadata1206.setItem(itemStack);
            case "121" -> metadata121.setItem(itemStack);
            case "1211" -> metadata1211.setItem(itemStack);
            case "1213" -> metadata1213.setItem(itemStack);
            case "1214" -> metadata1214.setItem(itemStack);
            case "1215" -> metadata1215.setItem(itemStack);
            case "1216" -> metadata1216.setItem(itemStack);
            case "1217" -> metadata1217.setItem(itemStack);
            case "1218" -> metadata1218.setItem(itemStack);
            case "1219" -> metadata1219.setItem(itemStack);
            case "12110" -> metadata12110.setItem(itemStack);
            case "12111" -> metadata12111.setItem(itemStack);
        }
    }

    public void setIsSmall(boolean isSmall) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setIsSmall(isSmall);
            case "1204" -> metadata1204.setIsSmall(isSmall);
            case "1206" -> metadata1206.setIsSmall(isSmall);
            case "121" -> metadata121.setIsSmall(isSmall);
            case "1211" -> metadata1211.setIsSmall(isSmall);
            case "1213" -> metadata1213.setIsSmall(isSmall);
            case "1214" -> metadata1214.setIsSmall(isSmall);
            case "1215" -> metadata1215.setIsSmall(isSmall);
            case "1216" -> metadata1216.setIsSmall(isSmall);
            case "1217" -> metadata1217.setIsSmall(isSmall);
            case "1218" -> metadata1218.setIsSmall(isSmall);
            case "1219" -> metadata1219.setIsSmall(isSmall);
            case "12110" -> metadata12110.setIsSmall(isSmall);
            case "12111" -> metadata12111.setIsSmall(isSmall);
        }
    }

    public void setHasArms(boolean hasArms) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHasArms(hasArms);
            case "1204" -> metadata1204.setHasArms(hasArms);
            case "1206" -> metadata1206.setHasArms(hasArms);
            case "121" -> metadata121.setHasArms(hasArms);
            case "1211" -> metadata1211.setHasArms(hasArms);
            case "1213" -> metadata1213.setHasArms(hasArms);
            case "1214" -> metadata1214.setHasArms(hasArms);
            case "1215" -> metadata1215.setHasArms(hasArms);
            case "1216" -> metadata1216.setHasArms(hasArms);
            case "1217" -> metadata1217.setHasArms(hasArms);
            case "1218" -> metadata1218.setHasArms(hasArms);
            case "1219" -> metadata1219.setHasArms(hasArms);
            case "12110" -> metadata12110.setHasArms(hasArms);
            case "12111" -> metadata12111.setHasArms(hasArms);
        }
    }

    public void setNoBase(boolean hasNoBase) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setNoBase(hasNoBase);
            case "1204" -> metadata1204.setNoBase(hasNoBase);
            case "1206" -> metadata1206.setNoBase(hasNoBase);
            case "121" -> metadata121.setNoBase(hasNoBase);
            case "1211" -> metadata1211.setNoBase(hasNoBase);
            case "1213" -> metadata1213.setNoBase(hasNoBase);
            case "1214" -> metadata1214.setNoBase(hasNoBase);
            case "1215" -> metadata1215.setNoBase(hasNoBase);
            case "1216" -> metadata1216.setNoBase(hasNoBase);
            case "1217" -> metadata1217.setNoBase(hasNoBase);
            case "1218" -> metadata1218.setNoBase(hasNoBase);
            case "1219" -> metadata1219.setNoBase(hasNoBase);
            case "12110" -> metadata12110.setNoBase(hasNoBase);
            case "12111" -> metadata12111.setNoBase(hasNoBase);
        }
    }

    public void setIsMarker(boolean isMarker) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setIsMarker(isMarker);
            case "1204" -> metadata1204.setIsMarker(isMarker);
            case "1206" -> metadata1206.setIsMarker(isMarker);
            case "121" -> metadata121.setIsMarker(isMarker);
            case "1211" -> metadata1211.setIsMarker(isMarker);
            case "1213" -> metadata1213.setIsMarker(isMarker);
            case "1214" -> metadata1214.setIsMarker(isMarker);
            case "1215" -> metadata1215.setIsMarker(isMarker);
            case "1216" -> metadata1216.setIsMarker(isMarker);
            case "1217" -> metadata1217.setIsMarker(isMarker);
            case "1218" -> metadata1218.setIsMarker(isMarker);
            case "1219" -> metadata1219.setIsMarker(isMarker);
            case "12110" -> metadata12110.setIsMarker(isMarker);
            case "12111" -> metadata12111.setIsMarker(isMarker);
        }
    }



    public void setHeadRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHeadRotation(x, y, z);
            case "1204" -> metadata1204.setHeadRotation(x, y, z);
            case "1206" -> metadata1206.setHeadRotation(x, y, z);
            case "121" -> metadata121.setHeadRotation(x, y, z);
            case "1211" -> metadata1211.setHeadRotation(x, y, z);
            case "1213" -> metadata1213.setHeadRotation(x, y, z);
            case "1214" -> metadata1214.setHeadRotation(x, y, z);
            case "1215" -> metadata1215.setHeadRotation(x, y, z);
            case "1216" -> metadata1216.setHeadRotation(x, y, z);
            case "1217" -> metadata1217.setHeadRotation(x, y, z);
            case "1218" -> metadata1218.setHeadRotation(x, y, z);
            case "1219" -> metadata1219.setHeadRotation(x, y, z);
            case "12110" -> metadata12110.setHeadRotation(x, y, z);
            case "12111" -> metadata12111.setHeadRotation(x, y, z);
        }
    }

    public void setBodyRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBodyRotation(x, y, z);
            case "1204" -> metadata1204.setBodyRotation(x, y, z);
            case "1206" -> metadata1206.setBodyRotation(x, y, z);
            case "121" -> metadata121.setHeadRotation(x, y, z);
            case "1211" -> metadata1211.setHeadRotation(x, y, z);
            case "1213" -> metadata1213.setHeadRotation(x, y, z);
            case "1214" -> metadata1214.setHeadRotation(x, y, z);
            case "1215" -> metadata1215.setHeadRotation(x, y, z);
            case "1216" -> metadata1216.setHeadRotation(x, y, z);
            case "1217" -> metadata1217.setHeadRotation(x, y, z);
            case "1218" -> metadata1218.setHeadRotation(x, y, z);
            case "1219" -> metadata1219.setHeadRotation(x, y, z);
            case "12110" -> metadata12110.setHeadRotation(x, y, z);
            case "12111" -> metadata12111.setHeadRotation(x, y, z);
        }
    }

    public void setLeftArmRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setLeftArmRotation(x, y, z);
            case "1204" -> metadata1204.setLeftArmRotation(x, y, z);
            case "1206" -> metadata1206.setLeftArmRotation(x, y, z);
            case "121" -> metadata121.setLeftArmRotation(x, y, z);
            case "1211" -> metadata1211.setLeftArmRotation(x, y, z);
            case "1213" -> metadata1213.setLeftArmRotation(x, y, z);
            case "1214" -> metadata1214.setLeftArmRotation(x, y, z);
            case "1215" -> metadata1215.setLeftArmRotation(x, y, z);
            case "1216" -> metadata1216.setLeftArmRotation(x, y, z);
            case "1217" -> metadata1217.setLeftArmRotation(x, y, z);
            case "1218" -> metadata1218.setLeftArmRotation(x, y, z);
            case "1219" -> metadata1219.setLeftArmRotation(x, y, z);
            case "12110" -> metadata12110.setLeftArmRotation(x, y, z);
            case "12111" -> metadata12111.setLeftArmRotation(x, y, z);
        }
    }

    public void setRightArmRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRightArmRotation(x, y, z);
            case "1204" -> metadata1204.setRightArmRotation(x, y, z);
            case "1206" -> metadata1206.setRightArmRotation(x, y, z);
            case "121" -> metadata121.setRightArmRotation(x, y, z);
            case "1211" -> metadata1211.setRightArmRotation(x, y, z);
            case "1213" -> metadata1213.setRightArmRotation(x, y, z);
            case "1214" -> metadata1214.setRightArmRotation(x, y, z);
            case "1215" -> metadata1215.setRightArmRotation(x, y, z);
            case "1216" -> metadata1216.setRightArmRotation(x, y, z);
            case "1217" -> metadata1217.setRightArmRotation(x, y, z);
            case "1218" -> metadata1218.setRightArmRotation(x, y, z);
            case "1219" -> metadata1219.setRightArmRotation(x, y, z);
            case "12110" -> metadata12110.setRightArmRotation(x, y, z);
            case "12111" -> metadata12111.setRightArmRotation(x, y, z);
        }
    }

    public void setLeftLegRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setLeftLegRotation(x, y, z);
            case "1204" -> metadata1204.setLeftLegRotation(x, y, z);
            case "1206" -> metadata1206.setLeftLegRotation(x, y, z);
            case "121" -> metadata121.setLeftLegRotation(x, y, z);
            case "1211" -> metadata1211.setLeftLegRotation(x, y, z);
            case "1213" -> metadata1213.setLeftLegRotation(x, y, z);
            case "1214" -> metadata1214.setLeftLegRotation(x, y, z);
            case "1215" -> metadata1215.setLeftLegRotation(x, y, z);
            case "1216" -> metadata1216.setLeftLegRotation(x, y, z);
            case "1217" -> metadata1217.setLeftLegRotation(x, y, z);
            case "1218" -> metadata1218.setLeftLegRotation(x, y, z);
            case "1219" -> metadata1219.setLeftLegRotation(x, y, z);
            case "12110" -> metadata12110.setLeftLegRotation(x, y, z);
            case "12111" -> metadata12111.setLeftLegRotation(x, y, z);
        }
    }

    public void setRightLegRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRightLegRotation(x, y, z);
            case "1204" -> metadata1204.setRightLegRotation(x, y, z);
            case "1206" -> metadata1206.setRightLegRotation(x, y, z);
            case "121" -> metadata121.setRightLegRotation(x, y, z);
            case "1211" -> metadata1211.setRightLegRotation(x, y, z);
            case "1213" -> metadata1213.setRightLegRotation(x, y, z);
            case "1214" -> metadata1214.setRightLegRotation(x, y, z);
            case "1215" -> metadata1215.setRightLegRotation(x, y, z);
            case "1216" -> metadata1216.setRightLegRotation(x, y, z);
            case "1217" -> metadata1217.setRightLegRotation(x, y, z);
            case "1218" -> metadata1218.setRightLegRotation(x, y, z);
            case "1219" -> metadata1219.setRightLegRotation(x, y, z);
            case "12110" -> metadata12110.setRightLegRotation(x, y, z);
            case "12111" -> metadata12111.setRightLegRotation(x, y, z);
        }
    }

    public void setDisplayItem(org.bukkit.inventory.ItemStack item) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setDisplayItem(item);
            case "1204" -> metadata1204.setDisplayItem(item);
            case "1206" -> metadata1206.setDisplayItem(item);
            case "121" -> metadata121.setDisplayItem(item);
            case "1211" -> metadata1211.setDisplayItem(item);
            case "1213" -> metadata1213.setDisplayItem(item);
            case "1214" -> metadata1214.setDisplayItem(item);
            case "1215" -> metadata1215.setDisplayItem(item);
            case "1216" -> metadata1216.setDisplayItem(item);
            case "1217" -> metadata1217.setDisplayItem(item);
            case "1218" -> metadata1218.setDisplayItem(item);
            case "1219" -> metadata1219.setDisplayItem(item);
            case "12110" -> metadata12110.setDisplayItem(item);
            case "12111" -> metadata12111.setDisplayItem(item);
        }
    }

    public void setDisplayBlock(BlockData blockData) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setDisplayBlock(blockData);
            case "1204" -> metadata1204.setDisplayBlock(blockData);
            case "1206" -> metadata1206.setDisplayBlock(blockData);
            case "121" -> metadata121.setDisplayBlock(blockData);
            case "1211" -> metadata1211.setDisplayBlock(blockData);
            case "1213" -> metadata1213.setDisplayBlock(blockData);
            case "1214" -> metadata1214.setDisplayBlock(blockData);
            case "1215" -> metadata1215.setDisplayBlock(blockData);
            case "1216" -> metadata1216.setDisplayBlock(blockData);
            case "1217" -> metadata1217.setDisplayBlock(blockData);
            case "1218" -> metadata1218.setDisplayBlock(blockData);
            case "1219" -> metadata1219.setDisplayBlock(blockData);
            case "12110" -> metadata12110.setDisplayBlock(blockData);
            case "12111" -> metadata12111.setDisplayBlock(blockData);
        }
    }

    public void setInterpolationDelay(int delay) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInterpolationDelay(delay);
            case "1204" -> metadata1204.setInterpolationDelay(delay);
            case "1206" -> metadata1206.setInterpolationDelay(delay);
            case "121" -> metadata121.setInterpolationDelay(delay);
            case "1211" -> metadata1211.setInterpolationDelay(delay);
            case "1213" -> metadata1213.setInterpolationDelay(delay);
            case "1214" -> metadata1214.setInterpolationDelay(delay);
            case "1215" -> metadata1215.setInterpolationDelay(delay);
            case "1216" -> metadata1216.setInterpolationDelay(delay);
            case "1217" -> metadata1217.setInterpolationDelay(delay);
            case "1218" -> metadata1218.setInterpolationDelay(delay);
            case "1219" -> metadata1219.setInterpolationDelay(delay);
            case "12110" -> metadata12110.setInterpolationDelay(delay);
            case "12111" -> metadata12111.setInterpolationDelay(delay);
        }
    }

    public void setTransformationDuration(int duration) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTransformationDuration(duration);
            case "1204" -> metadata1204.setTransformationDuration(duration);
            case "1206" -> metadata1206.setTransformationDuration(duration);
            case "121" -> metadata121.setTransformationDuration(duration);
            case "1211" -> metadata1211.setTransformationDuration(duration);
            case "1213" -> metadata1213.setTransformationDuration(duration);
            case "1214" -> metadata1214.setTransformationDuration(duration);
            case "1215" -> metadata1215.setTransformationDuration(duration);
            case "1216" -> metadata1216.setTransformationDuration(duration);
            case "1217" -> metadata1217.setTransformationDuration(duration);
            case "1218" -> metadata1218.setTransformationDuration(duration);
            case "1219" -> metadata1219.setTransformationDuration(duration);
            case "12110" -> metadata12110.setTransformationDuration(duration);
            case "12111" -> metadata12111.setTransformationDuration(duration);
        }
    }

    public void setPosRotDuration(int duration) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setPosRotDuration(duration);
            case "1204" -> metadata1204.setPosRotDuration(duration);
            case "1206" -> metadata1206.setPosRotDuration(duration);
            case "121" -> metadata121.setPosRotDuration(duration);
            case "1211" -> metadata1211.setPosRotDuration(duration);
            case "1213" -> metadata1213.setPosRotDuration(duration);
            case "1214" -> metadata1214.setPosRotDuration(duration);
            case "1215" -> metadata1215.setPosRotDuration(duration);
            case "1216" -> metadata1216.setPosRotDuration(duration);
            case "1217" -> metadata1217.setPosRotDuration(duration);
            case "1218" -> metadata1218.setPosRotDuration(duration);
            case "1219" -> metadata1219.setPosRotDuration(duration);
            case "12110" -> metadata12110.setPosRotDuration(duration);
            case "12111" -> metadata12111.setPosRotDuration(duration);
        }
    }

    public void setTranslation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTranslation(x, y, z);
            case "1204" -> metadata1204.setTranslation(x, y, z);
            case "1206" -> metadata1206.setTranslation(x, y, z);
            case "121" -> metadata121.setTranslation(x, y, z);
            case "1211" -> metadata1211.setTranslation(x, y, z);
            case "1213" -> metadata1213.setTranslation(x, y, z);
            case "1214" -> metadata1214.setTranslation(x, y, z);
            case "1215" -> metadata1215.setTranslation(x, y, z);
            case "1216" -> metadata1216.setTranslation(x, y, z);
            case "1217" -> metadata1217.setTranslation(x, y, z);
            case "1218" -> metadata1218.setTranslation(x, y, z);
            case "1219" -> metadata1219.setTranslation(x, y, z);
            case "12110" -> metadata12110.setTranslation(x, y, z);
            case "12111" -> metadata12111.setTranslation(x, y, z);
        }
    }

    public void setScale(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setScale(x, y, z);
            case "1204" -> metadata1204.setScale(x, y, z);
            case "1206" -> metadata1206.setScale(x, y, z);
            case "121" -> metadata121.setScale(x, y, z);
            case "1211" -> metadata1211.setScale(x, y, z);
            case "1213" -> metadata1213.setScale(x, y, z);
            case "1214" -> metadata1214.setScale(x, y, z);
            case "1215" -> metadata1215.setScale(x, y, z);
            case "1216" -> metadata1216.setScale(x, y, z);
            case "1217" -> metadata1217.setScale(x, y, z);
            case "1218" -> metadata1218.setScale(x, y, z);
            case "1219" -> metadata1219.setScale(x, y, z);
            case "12110" -> metadata12110.setScale(x, y, z);
            case "12111" -> metadata12111.setScale(x, y, z);
        }
    }

    public void setRotationLeft(double x, double y, double z, double w) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRotationLeft(x, y, z, w);
            case "1204" -> metadata1204.setRotationLeft(x, y, z, w);
            case "1206" -> metadata1206.setRotationLeft(x, y, z, w);
            case "121" -> metadata121.setRotationLeft(x, y, z, w);
            case "1211" -> metadata1211.setRotationLeft(x, y, z, w);
            case "1213" -> metadata1213.setRotationLeft(x, y, z, w);
            case "1214" -> metadata1214.setRotationLeft(x, y, z, w);
            case "1215" -> metadata1215.setRotationLeft(x, y, z, w);
            case "1216" -> metadata1216.setRotationLeft(x, y, z, w);
            case "1217" -> metadata1217.setRotationLeft(x, y, z, w);
            case "1218" -> metadata1218.setRotationLeft(x, y, z, w);
            case "1219" -> metadata1219.setRotationLeft(x, y, z, w);
            case "12110" -> metadata12110.setRotationLeft(x, y, z, w);
            case "12111" -> metadata12111.setRotationLeft(x, y, z, w);
        }
    }

    public void setRotationRight(double x, double y, double z, double w) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRotationRight(x, y, z, w);
            case "1204" -> metadata1204.setRotationRight(x, y, z, w);
            case "1206" -> metadata1206.setRotationRight(x, y, z, w);
            case "121" -> metadata121.setRotationRight(x, y, z, w);
            case "1211" -> metadata1211.setRotationRight(x, y, z, w);
            case "1213" -> metadata1213.setRotationRight(x, y, z, w);
            case "1214" -> metadata1214.setRotationRight(x, y, z, w);
            case "1215" -> metadata1215.setRotationRight(x, y, z, w);
            case "1216" -> metadata1216.setRotationRight(x, y, z, w);
            case "1217" -> metadata1217.setRotationRight(x, y, z, w);
            case "1218" -> metadata1218.setRotationRight(x, y, z, w);
            case "1219" -> metadata1219.setRotationRight(x, y, z, w);
            case "12110" -> metadata12110.setRotationRight(x, y, z, w);
            case "12111" -> metadata12111.setRotationRight(x, y, z, w);
        }
    }


    public void setBillboard(String string) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBillboard(string);
            case "1204" -> metadata1204.setBillboard(string);
            case "1206" -> metadata1206.setBillboard(string);
            case "121" -> metadata121.setBillboard(string);
            case "1211" -> metadata1211.setBillboard(string);
            case "1213" -> metadata1213.setBillboard(string);
            case "1214" -> metadata1214.setBillboard(string);
            case "1215" -> metadata1215.setBillboard(string);
            case "1216" -> metadata1216.setBillboard(string);
            case "1217" -> metadata1217.setBillboard(string);
            case "1218" -> metadata1218.setBillboard(string);
            case "1219" -> metadata1219.setBillboard(string);
            case "12110" -> metadata12110.setBillboard(string);
            case "12111" -> metadata12111.setBillboard(string);
        }
    }

    public void setBrightness(int blockLight, int skyLight) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBrightness(blockLight, skyLight);
            case "1204" -> metadata1204.setBrightness(blockLight, skyLight);
            case "1206" -> metadata1206.setBrightness(blockLight, skyLight);
            case "121" -> metadata121.setBrightness(blockLight, skyLight);
            case "1211" -> metadata1211.setBrightness(blockLight, skyLight);
            case "1213" -> metadata1213.setBrightness(blockLight, skyLight);
            case "1214" -> metadata1214.setBrightness(blockLight, skyLight);
            case "1215" -> metadata1215.setBrightness(blockLight, skyLight);
            case "1216" -> metadata1216.setBrightness(blockLight, skyLight);
            case "1217" -> metadata1217.setBrightness(blockLight, skyLight);
            case "1218" -> metadata1218.setBrightness(blockLight, skyLight);
            case "1219" -> metadata1219.setBrightness(blockLight, skyLight);
            case "12110" -> metadata12110.setBrightness(blockLight, skyLight);
            case "12111" -> metadata12111.setBrightness(blockLight, skyLight);
        }
    }

    public void setViewRange(float viewRange) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setViewRange(viewRange);
            case "1204" -> metadata1204.setViewRange(viewRange);
            case "1206" -> metadata1206.setViewRange(viewRange);
            case "121" -> metadata121.setViewRange(viewRange);
            case "1211" -> metadata1211.setViewRange(viewRange);
            case "1213" -> metadata1213.setViewRange(viewRange);
            case "1214" -> metadata1214.setViewRange(viewRange);
            case "1215" -> metadata1215.setViewRange(viewRange);
            case "1216" -> metadata1216.setViewRange(viewRange);
            case "1217" -> metadata1217.setViewRange(viewRange);
            case "1218" -> metadata1218.setViewRange(viewRange);
            case "1219" -> metadata1219.setViewRange(viewRange);
            case "12110" -> metadata12110.setViewRange(viewRange);
            case "12111" -> metadata12111.setViewRange(viewRange);
        }
    }

    public void setShadowRadius(float shadowRadius) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setShadowRadius(shadowRadius);
            case "1204" -> metadata1204.setShadowRadius(shadowRadius);
            case "1206" -> metadata1206.setShadowRadius(shadowRadius);
            case "121" -> metadata121.setShadowRadius(shadowRadius);
            case "1211" -> metadata1211.setShadowRadius(shadowRadius);
            case "1213" -> metadata1213.setShadowRadius(shadowRadius);
            case "1214" -> metadata1214.setShadowRadius(shadowRadius);
            case "1215" -> metadata1215.setShadowRadius(shadowRadius);
            case "1216" -> metadata1216.setShadowRadius(shadowRadius);
            case "1217" -> metadata1217.setShadowRadius(shadowRadius);
            case "1218" -> metadata1218.setShadowRadius(shadowRadius);
            case "1219" -> metadata1219.setShadowRadius(shadowRadius);
            case "12110" -> metadata12110.setShadowRadius(shadowRadius);
            case "12111" -> metadata12111.setShadowRadius(shadowRadius);
        }
    }

    public void setShadowStrength(float shadowStrength) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setShadowStrength(shadowStrength);
            case "1204" -> metadata1204.setShadowStrength(shadowStrength);
            case "1206" -> metadata1206.setShadowStrength(shadowStrength);
            case "121" -> metadata121.setShadowStrength(shadowStrength);
            case "1211" -> metadata1211.setShadowStrength(shadowStrength);
            case "1213" -> metadata1213.setShadowStrength(shadowStrength);
            case "1214" -> metadata1214.setShadowStrength(shadowStrength);
            case "1215" -> metadata1215.setShadowStrength(shadowStrength);
            case "1216" -> metadata1216.setShadowStrength(shadowStrength);
            case "1217" -> metadata1217.setShadowStrength(shadowStrength);
            case "1218" -> metadata1218.setShadowStrength(shadowStrength);
            case "1219" -> metadata1219.setShadowStrength(shadowStrength);
            case "12110" -> metadata12110.setShadowStrength(shadowStrength);
            case "12111" -> metadata12111.setShadowStrength(shadowStrength);
        }
    }

    public void setWidth(float width) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setWidth(width);
            case "1204" -> metadata1204.setWidth(width);
            case "1206" -> metadata1206.setWidth(width);
            case "121" -> metadata121.setWidth(width);
            case "1211" -> metadata1211.setWidth(width);
            case "1213" -> metadata1213.setWidth(width);
            case "1214" -> metadata1214.setWidth(width);
            case "1215" -> metadata1215.setWidth(width);
            case "1216" -> metadata1216.setWidth(width);
            case "1217" -> metadata1217.setWidth(width);
            case "1218" -> metadata1218.setWidth(width);
            case "1219" -> metadata1219.setWidth(width);
            case "12110" -> metadata12110.setWidth(width);
            case "12111" -> metadata12111.setWidth(width);
        }
    }

    public void setHeight(float height) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHeight(height);
            case "1204" -> metadata1204.setHeight(height);
            case "1206" -> metadata1206.setHeight(height);
            case "121" -> metadata121.setHeight(height);
            case "1211" -> metadata1211.setHeight(height);
            case "1213" -> metadata1213.setHeight(height);
            case "1214" -> metadata1214.setHeight(height);
            case "1215" -> metadata1215.setHeight(height);
            case "1216" -> metadata1216.setHeight(height);
            case "1217" -> metadata1217.setHeight(height);
            case "1218" -> metadata1218.setHeight(height);
            case "1219" -> metadata1219.setHeight(height);
            case "12110" -> metadata12110.setHeight(height);
            case "12111" -> metadata12111.setHeight(height);
        }
    }

    public void setGlowOverride(int glowOverride) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGlowOverride(glowOverride);
            case "1204" -> metadata1204.setGlowOverride(glowOverride);
            case "1206" -> metadata1206.setGlowOverride(glowOverride);
            case "121" -> metadata121.setGlowOverride(glowOverride);
            case "1211" -> metadata1211.setGlowOverride(glowOverride);
            case "1213" -> metadata1213.setGlowOverride(glowOverride);
            case "1214" -> metadata1214.setGlowOverride(glowOverride);
            case "1215" -> metadata1215.setGlowOverride(glowOverride);
            case "1216" -> metadata1216.setGlowOverride(glowOverride);
            case "1217" -> metadata1217.setGlowOverride(glowOverride);
            case "1218" -> metadata1218.setGlowOverride(glowOverride);
            case "1219" -> metadata1219.setGlowOverride(glowOverride);
            case "12110" -> metadata12110.setGlowOverride(glowOverride);
            case "12111" -> metadata12111.setGlowOverride(glowOverride);
        }
    }

    public void setText(Component component) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setText(component);
            case "1204" -> metadata1204.setText(component);
            case "1206" -> metadata1206.setText(component);
            case "121" -> metadata121.setText(component);
            case "1211" -> metadata1211.setText(component);
            case "1213" -> metadata1213.setText(component);
            case "1214" -> metadata1214.setText(component);
            case "1215" -> metadata1215.setText(component);
            case "1216" -> metadata1216.setText(component);
            case "1217" -> metadata1217.setText(component);
            case "1218" -> metadata1218.setText(component);
            case "1219" -> metadata1219.setText(component);
            case "12110" -> metadata12110.setText(component);
            case "12111" -> metadata12111.setText(component);
        }
    }

    public void setText(String miniMessageText) {
        setText(MiniMessage.miniMessage().deserialize(miniMessageText));
    }

    public void setLineWidth(int lineWidth) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setLineWidth(lineWidth);
            case "1204" -> metadata1204.setLineWidth(lineWidth);
            case "1206" -> metadata1206.setLineWidth(lineWidth);
            case "121" -> metadata121.setLineWidth(lineWidth);
            case "1211" -> metadata1211.setLineWidth(lineWidth);
            case "1213" -> metadata1213.setLineWidth(lineWidth);
            case "1214" -> metadata1214.setLineWidth(lineWidth);
            case "1215" -> metadata1215.setLineWidth(lineWidth);
            case "1216" -> metadata1216.setLineWidth(lineWidth);
            case "1217" -> metadata1217.setLineWidth(lineWidth);
            case "1218" -> metadata1218.setLineWidth(lineWidth);
            case "1219" -> metadata1219.setLineWidth(lineWidth);
            case "12110" -> metadata12110.setLineWidth(lineWidth);
            case "12111" -> metadata12111.setLineWidth(lineWidth);
        }
    }

    public void setBackground(int a, int r, int g, int b) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBackground(a, r, g, b);
            case "1204" -> metadata1204.setBackground(a, r, g, b);
            case "1206" -> metadata1206.setBackground(a, r, g, b);
            case "121" -> metadata121.setBackground(a, r, g, b);
            case "1211" -> metadata1211.setBackground(a, r, g, b);
            case "1213" -> metadata1213.setBackground(a, r, g, b);
            case "1214" -> metadata1214.setBackground(a, r, g, b);
            case "1215" -> metadata1215.setBackground(a, r, g, b);
            case "1216" -> metadata1216.setBackground(a, r, g, b);
            case "1217" -> metadata1217.setBackground(a, r, g, b);
            case "1218" -> metadata1218.setBackground(a, r, g, b);
            case "1219" -> metadata1219.setBackground(a, r, g, b);
            case "12110" -> metadata12110.setBackground(a, r, g, b);
            case "12111" -> metadata12111.setBackground(a, r, g, b);
        }
    }

    public void setTextOpacity(int opacityByte) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTextOpacity(opacityByte);
            case "1204" -> metadata1204.setTextOpacity(opacityByte);
            case "1206" -> metadata1206.setTextOpacity(opacityByte);
            case "121" -> metadata121.setTextOpacity(opacityByte);
            case "1211" -> metadata1211.setTextOpacity(opacityByte);
            case "1213" -> metadata1213.setTextOpacity(opacityByte);
            case "1214" -> metadata1214.setTextOpacity(opacityByte);
            case "1215" -> metadata1215.setTextOpacity(opacityByte);
            case "1216" -> metadata1216.setTextOpacity(opacityByte);
            case "1217" -> metadata1217.setTextOpacity(opacityByte);
            case "1218" -> metadata1218.setTextOpacity(opacityByte);
            case "1219" -> metadata1219.setTextOpacity(opacityByte);
            case "12110" -> metadata12110.setTextOpacity(opacityByte);
            case "12111" -> metadata12111.setTextOpacity(opacityByte);
        }
    }

    
    
    public void setHasShadow(boolean hasShadow) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setShadow(hasShadow);
            case "1204" -> metadata1204.setShadow(hasShadow);
            case "1206" -> metadata1206.setShadow(hasShadow);
            case "121" -> metadata121.setShadow(hasShadow);
            case "1211" -> metadata1211.setShadow(hasShadow);
            case "1213" -> metadata1213.setShadow(hasShadow);
            case "1214" -> metadata1214.setShadow(hasShadow);
            case "1215" -> metadata1215.setShadow(hasShadow);
            case "1216" -> metadata1216.setShadow(hasShadow);
            case "1217" -> metadata1217.setShadow(hasShadow);
            case "1218" -> metadata1218.setShadow(hasShadow);
            case "1219" -> metadata1219.setShadow(hasShadow);
            case "12110" -> metadata12110.setShadow(hasShadow);
            case "12111" -> metadata12111.setShadow(hasShadow);
        }
    }


    public void setSeeThrough(boolean seeThrough) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setSeeThrough(seeThrough);
            case "1204" -> metadata1204.setSeeThrough(seeThrough);
            case "1206" -> metadata1206.setSeeThrough(seeThrough);
            case "121" -> metadata121.setSeeThrough(seeThrough);
            case "1211" -> metadata1211.setSeeThrough(seeThrough);
            case "1213" -> metadata1213.setSeeThrough(seeThrough);
            case "1214" -> metadata1214.setSeeThrough(seeThrough);
            case "1215" -> metadata1215.setSeeThrough(seeThrough);
            case "1216" -> metadata1216.setSeeThrough(seeThrough);
            case "1217" -> metadata1217.setSeeThrough(seeThrough);
            case "1218" -> metadata1218.setSeeThrough(seeThrough);
            case "1219" -> metadata1219.setSeeThrough(seeThrough);
            case "12110" -> metadata12110.setSeeThrough(seeThrough);
            case "12111" -> metadata12111.setSeeThrough(seeThrough);
        }
    }

    public void setHasDefaultBackground(boolean hasDefaultBackground) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setDefaultBackground(hasDefaultBackground);
            case "1204" -> metadata1204.setDefaultBackground(hasDefaultBackground);
            case "1206" -> metadata1206.setDefaultBackground(hasDefaultBackground);
            case "121" -> metadata121.setDefaultBackground(hasDefaultBackground);
            case "1211" -> metadata1211.setDefaultBackground(hasDefaultBackground);
            case "1213" -> metadata1213.setDefaultBackground(hasDefaultBackground);
            case "1214" -> metadata1214.setDefaultBackground(hasDefaultBackground);
            case "1215" -> metadata1215.setDefaultBackground(hasDefaultBackground);
            case "1216" -> metadata1216.setDefaultBackground(hasDefaultBackground);
            case "1217" -> metadata1217.setDefaultBackground(hasDefaultBackground);
            case "1218" -> metadata1218.setDefaultBackground(hasDefaultBackground);
            case "1219" -> metadata1219.setDefaultBackground(hasDefaultBackground);
            case "12110" -> metadata12110.setDefaultBackground(hasDefaultBackground);
            case "12111" -> metadata12111.setDefaultBackground(hasDefaultBackground);
        }
    }

    public void setAlignment(String alignment) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setAlignment(alignment);
            case "1204" -> metadata1204.setAlignment(alignment);
            case "1206" -> metadata1206.setAlignment(alignment);
            case "121" -> metadata121.setAlignment(alignment);
            case "1211" -> metadata1211.setAlignment(alignment);
            case "1213" -> metadata1213.setAlignment(alignment);
            case "1214" -> metadata1214.setAlignment(alignment);
            case "1215" -> metadata1215.setAlignment(alignment);
            case "1216" -> metadata1216.setAlignment(alignment);
            case "1217" -> metadata1217.setAlignment(alignment);
            case "1218" -> metadata1218.setAlignment(alignment);
            case "1219" -> metadata1219.setAlignment(alignment);
            case "12110" -> metadata12110.setAlignment(alignment);
            case "12111" -> metadata12111.setAlignment(alignment);
        }
    }

    public void setInteractionWidth(Float width) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInteractionWidth(width);
            case "1204" -> metadata1204.setInteractionWidth(width);
            case "1206" -> metadata1206.setInteractionWidth(width);
            case "121" -> metadata121.setInteractionWidth(width);
            case "1211" -> metadata1211.setInteractionWidth(width);
            case "1213" -> metadata1213.setInteractionWidth(width);
            case "1214" -> metadata1214.setInteractionWidth(width);
            case "1215" -> metadata1215.setInteractionWidth(width);
            case "1216" -> metadata1216.setInteractionWidth(width);
            case "1217" -> metadata1217.setInteractionWidth(width);
            case "1218" -> metadata1218.setInteractionWidth(width);
            case "1219" -> metadata1219.setInteractionWidth(width);
            case "12110" -> metadata12110.setInteractionWidth(width);
            case "12111" -> metadata12111.setInteractionWidth(width);
        }
    }

    public void setInteractionHeight(Float height) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInteractionHeight(height);
            case "1204" -> metadata1204.setInteractionHeight(height);
            case "1206" -> metadata1206.setInteractionHeight(height);
            case "121" -> metadata121.setInteractionHeight(height);
            case "1211" -> metadata1211.setInteractionHeight(height);
            case "1213" -> metadata1213.setInteractionHeight(height);
            case "1214" -> metadata1214.setInteractionHeight(height);
            case "1215" -> metadata1215.setInteractionHeight(height);
            case "1216" -> metadata1216.setInteractionHeight(height);
            case "1217" -> metadata1217.setInteractionHeight(height);
            case "1218" -> metadata1218.setInteractionHeight(height);
            case "1219" -> metadata1219.setInteractionHeight(height);
            case "12110" -> metadata12110.setInteractionHeight(height);
            case "12111" -> metadata12111.setInteractionHeight(height);
        }
    }

    public Object build() {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.build();
            case "1204" -> metadata1204.build();
            case "1206" -> metadata1206.build();
            case "121" -> metadata121.build();
            case "1211" -> metadata1211.build();
            case "1213" -> metadata1213.build();
            case "1214" -> metadata1214.build();
            case "1215" -> metadata1215.build();
            case "1216" -> metadata1216.build();
            case "1217" -> metadata1217.build();
            case "1218" -> metadata1218.build();
            case "1219" -> metadata1219.build();
            case "12110" -> metadata12110.build();
            case "12111" -> metadata12111.build();
            default -> null;
        };
    }

}

