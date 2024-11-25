package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

import java.util.UUID;

public class RegisterChat {

    public static void register(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1202.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1202.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1202 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1202.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1202 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1202.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1202.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1202 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1202.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1202 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }


            case "1204" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1202.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1204.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1204 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1204.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1204 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1204.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1204.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1204 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1204.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1204 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);

            }


            case "1206" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1206.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1206.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1206 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1206.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1206 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1206.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1206.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1206 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1206.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1206 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "121" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent121.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent121.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent121 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent121.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent121 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent121.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent121.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent121 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent121.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent121 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1211" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1211.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1211.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1211 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1211.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1211 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1211.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1211.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1211 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1211.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1211 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1213" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1213.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1213.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1213 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1213.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1213 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1213.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1213.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1213 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1213.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1213 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
