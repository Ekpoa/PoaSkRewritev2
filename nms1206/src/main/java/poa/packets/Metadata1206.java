package poa.packets;

import lombok.SneakyThrows;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.core.Rotations;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import poa.util.Components1206;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Metadata1206 {


    public static Object basePacketForEntity(int id, boolean fire, boolean invisible, boolean glow, String name, boolean nameVisible, boolean silent, boolean gravity, String pose) {
        Metadata1206 entityMetadata = new Metadata1206(id);
        entityMetadata.setOnFire(fire);
        entityMetadata.setInvisible(invisible);
        entityMetadata.setGlow(glow);
        if (name != null)
            entityMetadata.setName(name);
        entityMetadata.setNameVisible(nameVisible);
        entityMetadata.setSilent(silent);
        entityMetadata.setGravity(!gravity); //cos packet is "has no gravity"
        entityMetadata.setPose(pose);
        return entityMetadata.build();
    }

    List<SynchedEntityData.DataValue<?>> dataList = new ArrayList<>();
    int id;


    boolean onFire = false;
    boolean invisible = false;
    boolean glow = false;


    public Metadata1206(int id) {
        this.id = id;
    }

    public void setOnFire(boolean onFire) {
        this.onFire = onFire;
        dataList.add(new SynchedEntityData.DataValue<>(0, EntityDataSerializers.BYTE, (byte) index0(onFire, invisible, glow)));
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
        dataList.add(new SynchedEntityData.DataValue<>(0, EntityDataSerializers.BYTE, (byte) index0(onFire, invisible, glow)));
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
        dataList.add(new SynchedEntityData.DataValue<>(0, EntityDataSerializers.BYTE, (byte) index0(onFire, invisible, glow)));
    }

    private static int index0(boolean isOnFire, boolean invisible, boolean glow) {
        byte b = 0;
        if (isOnFire)
            b = 0x01;
        if (invisible)
            b = (byte) (b + 0x20);
        if (glow)
            b = (byte) (b + 0x40);

        return b;
    }


    public void setName(String name) {
        name = name.replaceAll("§", "&");
        dataList.add(new SynchedEntityData.DataValue<>(2, EntityDataSerializers.OPTIONAL_COMPONENT, Optional.of(Components1206.nmsComponentActual(MiniMessage.miniMessage().deserialize(name)))));
    }

    public void setNameVisible(boolean nameVisible) {
        dataList.add(new SynchedEntityData.DataValue<>(3, EntityDataSerializers.BOOLEAN, nameVisible));
    }

    public void setSilent(boolean silent) {
        dataList.add(new SynchedEntityData.DataValue<>(4, EntityDataSerializers.BOOLEAN, silent));
    }

    public void setGravity(boolean hasGravity) {
        dataList.add(new SynchedEntityData.DataValue<>(5, EntityDataSerializers.BOOLEAN, !hasGravity));
    }

    public void setPose(String pose) {
        Pose nmsPose = Pose.valueOf(pose);
        dataList.add(new SynchedEntityData.DataValue<>(6, EntityDataSerializers.POSE, nmsPose));
    }

    public void setHealth(int health){
        float h = health/10F;
        dataList.add(new SynchedEntityData.DataValue<>(9, EntityDataSerializers.FLOAT, h));
    }




    public void setItem(org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = ItemStack.fromBukkitCopy(itemStack);

        dataList.add(new SynchedEntityData.DataValue<>(8, EntityDataSerializers.ITEM_STACK, item));
    }


    boolean isSmall = false;
    boolean hasArms = false;
    boolean hasNoBase = false;
    boolean isMarker = false;

    @SneakyThrows
    public void setIsSmall(boolean isSmall) {
        this.isSmall = isSmall;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    @SneakyThrows
    public void setHasArms(boolean hasArms) {
        this.hasArms = hasArms;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    @SneakyThrows
    public void setNoBase(boolean hasNoBase) {
        this.hasNoBase = hasNoBase;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    @SneakyThrows
    public void setIsMarker(boolean isMarker) {
        this.isMarker = isMarker;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }


    @SneakyThrows
    public void setHeadRotation(float x, float y, float z) {

        dataList.add(new SynchedEntityData.DataValue<>(16, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    @SneakyThrows
    public void setBodyRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(17, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    @SneakyThrows
    public void setLeftArmRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(18, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    @SneakyThrows
    public void setRightArmRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(19, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    @SneakyThrows
    public void setLeftLegRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(20, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    @SneakyThrows
    public void setRightLegRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(21, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }


    @SneakyThrows
    private static Rotations rotation(float x, float y, float z) {
        return new Rotations(x, y, z);
    }

    private static int index15(boolean isSmall, boolean hasArms, boolean hasNoBase, boolean isMarker) {
        byte b = 0;
        if (isSmall)
            b = 0x01;
        if (hasArms)
            b = (byte) (b + 0x04);
        if (hasNoBase)
            b = (byte) (b + 0x08);
        if (isMarker)
            b = (byte) (b + 0x10);

        return b;
    }





    @SneakyThrows
    public void setDisplayItem(org.bukkit.inventory.ItemStack item) {
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.ITEM_STACK, ItemStack.fromBukkitCopy(item)));
    }



    @SneakyThrows
    public void setDisplayBlock(BlockData blockData) {
        CraftBlockData data = (CraftBlockData) blockData;
        BlockState state = data.getState();
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.BLOCK_STATE, state));
    }

    @SneakyThrows
    public void setInterpolationDelay(int delay){
        dataList.add(new SynchedEntityData.DataValue<>(8, EntityDataSerializers.INT, delay));
    }

    @SneakyThrows
    public void setTransformationDuration(int duration){
        dataList.add(new SynchedEntityData.DataValue<>(9, EntityDataSerializers.INT, duration));
    }

    @SneakyThrows
    public void setPosRotDuration(int duration){
        dataList.add(new SynchedEntityData.DataValue<>(10, EntityDataSerializers.INT, duration));
    }

    @SneakyThrows
    public void setTranslation(float x, float y, float z){
        dataList.add(new SynchedEntityData.DataValue<>(11, EntityDataSerializers.VECTOR3, new Vector3f(x,y,z)));
    }
    @SneakyThrows
    public void setScale(float x, float y, float z){
        dataList.add(new SynchedEntityData.DataValue<>(12, EntityDataSerializers.VECTOR3, new Vector3f(x,y,z)));
    }
    @SneakyThrows
    public void setRotationLeft(double x, double y, double z, double w){
        dataList.add(new SynchedEntityData.DataValue<>(13, EntityDataSerializers.QUATERNION, new Quaternionf(x,y,z,w)));
    }

    @SneakyThrows
    public void setRotationRight(double x, double y, double z, double w){
        dataList.add(new SynchedEntityData.DataValue<>(14, EntityDataSerializers.QUATERNION, new Quaternionf(x,y,z,w)));
    }

    @SneakyThrows
    public void setBillboard(String string){
        byte b = 0;
        switch (string.toLowerCase()){
            case "fixed" -> b = (byte) 0;
            case "vertical" -> b = (byte) 1;
            case "horizontal" -> b = (byte) 2;
            case "center" -> b = (byte) 3;
        }
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, b));
    }



    public void setBrightness(int blockLight, int skyLight){
        int brightness = (blockLight << 4 | skyLight << 20);
        dataList.add(new SynchedEntityData.DataValue<>(16, EntityDataSerializers.INT, brightness));
    }


    @SneakyThrows
    public void setViewRange(float viewRange){
        dataList.add(new SynchedEntityData.DataValue<>(17, EntityDataSerializers.FLOAT, viewRange));
    }

    @SneakyThrows
    public void setShadowRadius(float shadowRadius){
        dataList.add(new SynchedEntityData.DataValue<>(18, EntityDataSerializers.FLOAT, shadowRadius));
    }

    @SneakyThrows
    public void setShadowStrength(float shadowStrength){
        dataList.add(new SynchedEntityData.DataValue<>(19, EntityDataSerializers.FLOAT, shadowStrength));
    }

    @SneakyThrows
    public void setWidth(float width){
        dataList.add(new SynchedEntityData.DataValue<>(20, EntityDataSerializers.FLOAT, width));
    }

    @SneakyThrows
    public void setHeight(float height){
        dataList.add(new SynchedEntityData.DataValue<>(21, EntityDataSerializers.FLOAT, height));
    }

    @SneakyThrows
    public void setGlowOverride(int glowOverride){
        dataList.add(new SynchedEntityData.DataValue<>(22, EntityDataSerializers.INT, glowOverride));
    }

    @SneakyThrows
    public void setText(Component component){
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.COMPONENT, Components1206.nmsComponentActual(component)));
    }

    public void setText(String miniMessageText){
        setText(MiniMessage.miniMessage().deserialize(miniMessageText));
    }


    public void setLineWidth(int lineWidth){
        dataList.add(new SynchedEntityData.DataValue<>(24, EntityDataSerializers.INT, lineWidth));
    }

    public void setBackground(int a, int r, int g, int b){
        dataList.add(new SynchedEntityData.DataValue<>(25, EntityDataSerializers.INT, Color.fromARGB(a, r, g, b).asARGB()));
    }

    public void setTextOpacity(int opacity){
        dataList.add(new SynchedEntityData.DataValue<>(26, EntityDataSerializers.BYTE, (byte) opacity));

    }
    public void index27(boolean hasShadow, boolean seeThru, boolean defaultBackgroundColor){
        dataList.add(new SynchedEntityData.DataValue<>(27, EntityDataSerializers.BYTE, index27asByte(hasShadow, seeThru, defaultBackgroundColor)));
    }

    private static byte index27asByte(boolean hasShadow, boolean seeThru, boolean defaultBackgroundColor) {
        byte b = 0;
        if (hasShadow)
            b = 0x01;
        if (seeThru)
            b = (byte) (b + 0x02);
        if (defaultBackgroundColor)
            b = (byte) (b + 0x04);
//        if (alignment)
//            b = (byte) (b + 0x08);

        return b;
    }



    @SneakyThrows
    public void setInteractionWidth(Float width){
        dataList.add(new SynchedEntityData.DataValue<>(8,EntityDataSerializers.FLOAT, width));
    }

    @SneakyThrows
    public void setInteractionHeight(Float height){
        dataList.add(new SynchedEntityData.DataValue<>(9, EntityDataSerializers.FLOAT, height));
    }



    @SneakyThrows
    public void setGuardianSpikes(boolean spikesShown){
        dataList.add(new SynchedEntityData.DataValue<>(16, EntityDataSerializers.BOOLEAN, !spikesShown));
    }
    @SneakyThrows
    public void setGuardianTarget(int entityId){
        dataList.add(new SynchedEntityData.DataValue<>(17, EntityDataSerializers.INT, entityId));
    }





    @SneakyThrows
    public Object build(){
        return new ClientboundSetEntityDataPacket(id, dataList);
    }



}

