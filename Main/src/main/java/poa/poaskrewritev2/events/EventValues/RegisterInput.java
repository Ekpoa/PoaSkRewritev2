package poa.poaskrewritev2.events.EventValues;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Input;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterInput {

    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                EventValues.registerEventValue(PlayerInputEvent1202.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(PlayerInputEvent1202 event) {
                        return event.getVector();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1202.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(PlayerInputEvent1202 event) {
                        return event.isJumping();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1202.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerInputEvent1202 event) {
                        return event.getKey();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1204" -> {
                EventValues.registerEventValue(PlayerInputEvent1204.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(PlayerInputEvent1204 event) {
                        return event.getVector();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1204.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(PlayerInputEvent1204 event) {
                        return event.isJumping();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1204.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerInputEvent1204 event) {
                        return event.getKey();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1206" -> {
                EventValues.registerEventValue(PlayerInputEvent1206.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(PlayerInputEvent1206 event) {
                        return event.getVector();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1206.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(PlayerInputEvent1206 event) {
                        return event.isJumping();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1206.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerInputEvent1206 event) {
                        return event.getKey();
                    }
                }, EventValues.TIME_NOW);
            }
            case "121" -> {
                EventValues.registerEventValue(PlayerInputEvent121.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(PlayerInputEvent121 event) {
                        return event.getVector();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent121.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(PlayerInputEvent121 event) {
                        return event.isJumping();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent121.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerInputEvent121 event) {
                        return event.getKey();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1211" -> {
                EventValues.registerEventValue(PlayerInputEvent1211.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(PlayerInputEvent1211 event) {
                        return event.getVector();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1211.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(PlayerInputEvent1211 event) {
                        return event.isJumping();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerInputEvent1211.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerInputEvent1211 event) {
                        return event.getKey();
                    }
                }, EventValues.TIME_NOW);
            }
            default -> {
                EventValues.registerEventValue(PlayerInputEvent.class, String.class, new Getter<>() {
                    @Override
                    public @Nullable String get(PlayerInputEvent event) {
                        final Input input = event.getInput();
                        String s = "";
                        if (input.isBackward())
                            s = s + "S ";
                        if (input.isForward())
                            s = s + "W ";
                        if (input.isLeft())
                            s = s + "A ";
                        if (input.isRight())
                            s = s + "D ";
                        if (input.isJump())
                            s = s + "SPACE ";
                        if (input.isSneak())
                            s = s + "SNEAK ";
                        if (input.isSprint())
                            s = s + "SPRINT ";

                        return s.strip();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
