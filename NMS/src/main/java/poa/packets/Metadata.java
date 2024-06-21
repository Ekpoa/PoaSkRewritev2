package poa.packets;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
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
            default -> null;
        };
    }

    Metadata1202 metadata1202;
    Metadata1204 metadata1204;
    Metadata1206 metadata1206;


    public Metadata(int id) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202 = new Metadata1202(id);
            case "1204" -> metadata1204 = new Metadata1204(id);
            case "1206" -> metadata1206 = new Metadata1206(id);
        }
    }


    public void setOnFire(boolean onFire) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setOnFire(onFire);
            case "1204" -> metadata1204.setOnFire(onFire);
            case "1206" -> metadata1206.setOnFire(onFire);
        }
    }

    public void setInvisible(boolean invisible) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInvisible(invisible);
            case "1204" -> metadata1204.setInvisible(invisible);
            case "1206" -> metadata1206.setInvisible(invisible);
        }
    }

    public void setGlow(boolean glow) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGlow(glow);
            case "1204" -> metadata1204.setGlow(glow);
            case "1206" -> metadata1206.setGlow(glow);
        }
    }


    public void setName(String name) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setName(name);
            case "1204" -> metadata1204.setName(name);
            case "1206" -> metadata1206.setName(name);
        }
    }

    public void setNameVisible(boolean nameVisible) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setNameVisible(nameVisible);
            case "1204" -> metadata1204.setNameVisible(nameVisible);
            case "1206" -> metadata1206.setNameVisible(nameVisible);
        }
    }

    public void setSilent(boolean silent) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setSilent(silent);
            case "1204" -> metadata1204.setSilent(silent);
            case "1206" -> metadata1206.setSilent(silent);
        }
    }

    public void setGravity(boolean hasGravity) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGravity(hasGravity);
            case "1204" -> metadata1204.setGravity(hasGravity);
            case "1206" -> metadata1206.setGravity(hasGravity);
        }
    }

    public void setPose(String pose) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setPose(pose);
            case "1204" -> metadata1204.setPose(pose);
            case "1206" -> metadata1206.setPose(pose);
        }
    }


    public void setItem(org.bukkit.inventory.ItemStack itemStack) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setItem(itemStack);
            case "1204" -> metadata1204.setItem(itemStack);
            case "1206" -> metadata1206.setItem(itemStack);
        }
    }

    public void setIsSmall(boolean isSmall) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setIsSmall(isSmall);
            case "1204" -> metadata1204.setIsSmall(isSmall);
            case "1206" -> metadata1206.setIsSmall(isSmall);
        }
    }


    public void setHasArms(boolean hasArms) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHasArms(hasArms);
            case "1204" -> metadata1204.setHasArms(hasArms);
            case "1206" -> metadata1206.setHasArms(hasArms);
        }
    }


    public void setNoBase(boolean hasNoBase) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setNoBase(hasNoBase);
            case "1204" -> metadata1204.setNoBase(hasNoBase);
            case "1206" -> metadata1206.setNoBase(hasNoBase);
        }
    }


    public void setIsMarker(boolean isMarker) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setIsMarker(isMarker);
            case "1204" -> metadata1204.setIsMarker(isMarker);
            case "1206" -> metadata1206.setIsMarker(isMarker);
        }
    }


    public void setHeadRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHeadRotation(x, y, z);
            case "1204" -> metadata1204.setHeadRotation(x, y, z);
            case "1206" -> metadata1206.setHeadRotation(x, y, z);
        }
    }


    public void setBodyRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBodyRotation(x, y, z);
            case "1204" -> metadata1204.setBodyRotation(x, y, z);
            case "1206" -> metadata1206.setBodyRotation(x, y, z);
        }
    }


    public void setLeftArmRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setLeftArmRotation(x, y, z);
            case "1204" -> metadata1204.setLeftArmRotation(x, y, z);
            case "1206" -> metadata1206.setLeftArmRotation(x, y, z);
        }
    }


    public void setRightArmRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRightArmRotation(x, y, z);
            case "1204" -> metadata1204.setRightArmRotation(x, y, z);
            case "1206" -> metadata1206.setRightArmRotation(x, y, z);
        }
    }


    public void setLeftLegRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setLeftLegRotation(x, y, z);
            case "1204" -> metadata1204.setLeftLegRotation(x, y, z);
            case "1206" -> metadata1206.setLeftLegRotation(x, y, z);
        }
    }


    public void setRightLegRotation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRightLegRotation(x, y, z);
            case "1204" -> metadata1204.setRightLegRotation(x, y, z);
            case "1206" -> metadata1206.setRightLegRotation(x, y, z);
        }
    }


    public void setDisplayItem(org.bukkit.inventory.ItemStack item) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setDisplayItem(item);
            case "1204" -> metadata1204.setDisplayItem(item);
            case "1206" -> metadata1206.setDisplayItem(item);
        }
    }


    public void setDisplayBlock(BlockData blockData) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setDisplayBlock(blockData);
            case "1204" -> metadata1204.setDisplayBlock(blockData);
            case "1206" -> metadata1206.setDisplayBlock(blockData);
        }
    }


    public void setInterpolationDelay(int delay) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInterpolationDelay(delay);
            case "1204" -> metadata1204.setInterpolationDelay(delay);
            case "1206" -> metadata1206.setInterpolationDelay(delay);
        }
    }


    public void setTransformationDuration(int duration) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTransformationDuration(duration);
            case "1204" -> metadata1204.setTransformationDuration(duration);
            case "1206" -> metadata1206.setTransformationDuration(duration);
        }
    }


    public void setPosRotDuration(int duration) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setPosRotDuration(duration);
            case "1204" -> metadata1204.setPosRotDuration(duration);
            case "1206" -> metadata1206.setPosRotDuration(duration);
        }
    }


    public void setTranslation(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTranslation(x, y, z);
            case "1204" -> metadata1204.setTranslation(x, y, z);
            case "1206" -> metadata1206.setTranslation(x, y, z);
        }
    }

    public void setScale(float x, float y, float z) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setScale(x, y, z);
            case "1204" -> metadata1204.setScale(x, y, z);
            case "1206" -> metadata1206.setScale(x, y, z);
        }
    }

    public void setRotationLeft(double x, double y, double z, double w) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRotationLeft(x, y, z, w);
            case "1204" -> metadata1204.setRotationLeft(x, y, z, w);
            case "1206" -> metadata1206.setRotationLeft(x, y, z, w);
        }
    }


    public void setRotationRight(double x, double y, double z, double w) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setRotationRight(x, y, z, w);
            case "1204" -> metadata1204.setRotationRight(x, y, z, w);
            case "1206" -> metadata1206.setRotationRight(x, y, z, w);
        }
    }


    public void setBillboard(String string) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBillboard(string);
            case "1204" -> metadata1204.setBillboard(string);
            case "1206" -> metadata1206.setBillboard(string);
        }
    }


    public void setBrightness(int brightness) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBrightness(brightness);
            case "1204" -> metadata1204.setBrightness(brightness);
            case "1206" -> metadata1206.setBrightness(brightness);
        }
    }


    public void setViewRange(float viewRange) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setViewRange(viewRange);
            case "1204" -> metadata1204.setViewRange(viewRange);
            case "1206" -> metadata1206.setViewRange(viewRange);
        }
    }


    public void setShadowRadius(float shadowRadius) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1204.setShadowRadius(shadowRadius);
            case "1204" -> metadata1204.setShadowRadius(shadowRadius);
            case "1206" -> metadata1206.setShadowRadius(shadowRadius);
        }
    }


    public void setShadowStrength(float shadowStrength) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setShadowStrength(shadowStrength);
            case "1204" -> metadata1204.setShadowStrength(shadowStrength);
            case "1206" -> metadata1206.setShadowStrength(shadowStrength);
        }
    }


    public void setWidth(float width) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setWidth(width);
            case "1204" -> metadata1204.setWidth(width);
            case "1206" -> metadata1206.setWidth(width);
        }
    }


    public void setHeight(float height) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setHeight(height);
            case "1204" -> metadata1204.setHeight(height);
            case "1206" -> metadata1206.setHeight(height);
        }
    }


    public void setGlowOverride(int glowOverride) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setGlowOverride(glowOverride);
            case "1204" -> metadata1204.setGlowOverride(glowOverride);
            case "1206" -> metadata1206.setGlowOverride(glowOverride);
        }
    }


    public void setText(Component component) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setText(component);
            case "1204" -> metadata1204.setText(component);
            case "1206" -> metadata1206.setText(component);
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
        }
    }

    public void setBackground(int a, int r, int g, int b) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setBackground(a, r, g, b);
            case "1204" -> metadata1204.setBackground(a, r, g, b);
            case "1206" -> metadata1206.setBackground(a, r, g, b);
        }
    }

    public void setTextOpacity(int opacityByte) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setTextOpacity(opacityByte);
            case "1204" -> metadata1204.setTextOpacity(opacityByte);
            case "1206" -> metadata1206.setTextOpacity(opacityByte);
        }
    }

    public void index27(boolean hasShadow, boolean seeThru, boolean defaultBackgroundColor) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.index27(hasShadow, seeThru, defaultBackgroundColor);
            case "1204" -> metadata1204.index27(hasShadow, seeThru, defaultBackgroundColor);
            case "1206" -> metadata1206.index27(hasShadow, seeThru, defaultBackgroundColor);
        }
    }


    public void setInteractionWidth(Float width) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInteractionWidth(width);
            case "1204" -> metadata1204.setInteractionWidth(width);
            case "1206" -> metadata1206.setInteractionWidth(width);
        }
    }


    public void setInteractionHeight(Float height) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.setInteractionHeight(height);
            case "1204" -> metadata1204.setInteractionHeight(height);
            case "1206" -> metadata1206.setInteractionHeight(height);
        }
    }


    public Object build() {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> metadata1202.build();
            case "1204" -> metadata1204.build();
            case "1206" -> metadata1206.build();
            default -> null;
        };
    }


}

