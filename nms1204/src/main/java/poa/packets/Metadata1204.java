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
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R3.block.data.CraftBlockData;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import poa.util.Components1204;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Metadata1204 {


    public static Object basePacketForEntity(int id, boolean fire, boolean invisible, boolean glow, String name, boolean nameVisible, boolean silent, boolean gravity, String pose) {
        Metadata1204 entityMetadata = new Metadata1204(id);
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


    public Metadata1204(int id) {
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
        Optional<net.minecraft.network.chat.Component> optional = Optional.of(Components1204.nmsComponentActual(MiniMessage.miniMessage().deserialize(name)));
        dataList.add(new SynchedEntityData.DataValue<>(2, EntityDataSerializers.OPTIONAL_COMPONENT, optional));
    }

    public void setNameVisible(boolean nameVisible) {
        dataList.add(new SynchedEntityData.DataValue<>(3, EntityDataSerializers.BOOLEAN, nameVisible));
    }

    public void setSilent(boolean silent) {
        dataList.add(new SynchedEntityData.DataValue<>(4, EntityDataSerializers.BOOLEAN, silent));
    }

    public void setGravity(boolean hasGravity) {
        dataList.add(new SynchedEntityData.DataValue<>(5, EntityDataSerializers.BOOLEAN, hasGravity));
    }

    public void setPose(String pose) {
        Pose nmsPose = Pose.valueOf(pose);
        dataList.add(new SynchedEntityData.DataValue<>(6, EntityDataSerializers.POSE, nmsPose));
    }


    public void setItem(org.bukkit.inventory.ItemStack itemStack) {
        ItemStack item = ItemStack.fromBukkitCopy(itemStack);

        dataList.add(new SynchedEntityData.DataValue<>(8, EntityDataSerializers.ITEM_STACK, item));
    }


    boolean isSmall = false;
    boolean hasArms = false;
    boolean hasNoBase = false;
    boolean isMarker = false;

    
    public void setIsSmall(boolean isSmall) {
        this.isSmall = isSmall;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    
    public void setHasArms(boolean hasArms) {
        this.hasArms = hasArms;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    
    public void setNoBase(boolean hasNoBase) {
        this.hasNoBase = hasNoBase;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }

    
    public void setIsMarker(boolean isMarker) {
        this.isMarker = isMarker;
        dataList.add(new SynchedEntityData.DataValue<>(15, EntityDataSerializers.BYTE, (byte) index15(isSmall, hasArms, hasNoBase, isMarker))); //byte
    }


    
    public void setHeadRotation(float x, float y, float z) {

        dataList.add(new SynchedEntityData.DataValue<>(16, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    
    public void setBodyRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(17, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    
    public void setLeftArmRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(18, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    
    public void setRightArmRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(19, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    
    public void setLeftLegRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(20, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }

    
    public void setRightLegRotation(float x, float y, float z) {
        dataList.add(new SynchedEntityData.DataValue<>(21, EntityDataSerializers.ROTATIONS, rotation(x, y, z)));
    }


    
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





    
    public void setDisplayItem(org.bukkit.inventory.ItemStack item) {
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.ITEM_STACK, ItemStack.fromBukkitCopy(item)));
    }



    
    public void setDisplayBlock(BlockData blockData) {
        CraftBlockData data = (CraftBlockData) blockData;
        BlockState state = data.getState();
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.BLOCK_STATE, state));
    }

    
    public void setInterpolationDelay(int delay){
        dataList.add(new SynchedEntityData.DataValue<>(8, EntityDataSerializers.INT, delay));
    }

    
    public void setTransformationDuration(int duration){
        dataList.add(new SynchedEntityData.DataValue<>(9, EntityDataSerializers.INT, duration));
    }

    
    public void setPosRotDuration(int duration){
        dataList.add(new SynchedEntityData.DataValue<>(10, EntityDataSerializers.INT, duration));
    }

    
    public void setTranslation(float x, float y, float z){
        dataList.add(new SynchedEntityData.DataValue<>(11, EntityDataSerializers.VECTOR3, new Vector3f(x,y,z)));
    }
    
    public void setScale(float x, float y, float z){
        dataList.add(new SynchedEntityData.DataValue<>(12, EntityDataSerializers.VECTOR3, new Vector3f(x,y,z)));
    }
    
    public void setRotationLeft(double x, double y, double z, double w){
        dataList.add(new SynchedEntityData.DataValue<>(13, EntityDataSerializers.QUATERNION, new Quaternionf(x,y,z,w)));
    }

    
    public void setRotationRight(double x, double y, double z, double w){
        dataList.add(new SynchedEntityData.DataValue<>(14, EntityDataSerializers.QUATERNION, new Quaternionf(x,y,z,w)));
    }

    
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



    
    public void setBrightness(int brightness){
        dataList.add(new SynchedEntityData.DataValue<>(16, EntityDataSerializers.INT, brightness));
    }


    
    public void setViewRange(float viewRange){
        dataList.add(new SynchedEntityData.DataValue<>(17, EntityDataSerializers.FLOAT, viewRange));
    }

    
    public void setShadowRadius(float shadowRadius){
        dataList.add(new SynchedEntityData.DataValue<>(18, EntityDataSerializers.FLOAT, shadowRadius));
    }

    
    public void setShadowStrength(float shadowStrength){
        dataList.add(new SynchedEntityData.DataValue<>(19, EntityDataSerializers.FLOAT, shadowStrength));
    }

    
    public void setWidth(float width){
        dataList.add(new SynchedEntityData.DataValue<>(20, EntityDataSerializers.FLOAT, width));
    }

    
    public void setHeight(float height){
        dataList.add(new SynchedEntityData.DataValue<>(21, EntityDataSerializers.FLOAT, height));
    }

    
    public void setGlowOverride(int glowOverride){
        dataList.add(new SynchedEntityData.DataValue<>(22, EntityDataSerializers.INT, glowOverride));
    }


    public void setText(Component component){
        dataList.add(new SynchedEntityData.DataValue<>(23, EntityDataSerializers.COMPONENT, Components1204.nmsComponentActual(component)));
    }

    public void setText(String miniMessageText){
        setText(MiniMessage.miniMessage().deserialize(miniMessageText));
    }





    
    public void setInteractionWidth(Float width){
        dataList.add(new SynchedEntityData.DataValue<>(8,EntityDataSerializers.FLOAT, width));
    }

    
    public void setInteractionHeight(Float height){
        dataList.add(new SynchedEntityData.DataValue<>(9, EntityDataSerializers.FLOAT, height));
    }



    
    public Object build(){
        return new ClientboundSetEntityDataPacket(id, dataList);
    }



}

