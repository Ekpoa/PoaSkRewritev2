package poa.poaskrewritev2.events.eventvalues;

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
            case "1214" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1214.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1214.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1214 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1214.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1214 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1214.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1214.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1214 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1214.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1214 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1215" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1215.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1215.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1215 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1215.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1215 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1215.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1215.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1215 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1215.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1215 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1216" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1216.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1216.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1216 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1216.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1216 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1216.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1216.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1216 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1216.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1216 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1217" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1217.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1217.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1217 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1217.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1217 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1217.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1217.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1217 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1217.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1217 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1218" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1218.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1218.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1218 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1218.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1218 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);


                //Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1218.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1218.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1218 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1218.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1218 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1219" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent1219.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent1219.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent1219 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent1219.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent1219 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);

                // Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent1219.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent1219.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent1219 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent1219.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent1219 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                Skript.registerEvent("System Chat Packet", SimpleEvent.class, SystemChatPacketEvent12110.class,
                        "system chat packet");

                EventValues.registerEventValue(SystemChatPacketEvent12110.class, String.class, new Getter<>() {
                    @Override
                    public String get(SystemChatPacketEvent12110 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(SystemChatPacketEvent12110.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(SystemChatPacketEvent12110 event) {
                        return event.isOverlay();
                    }
                }, EventValues.TIME_NOW);

                // Player
                Skript.registerEvent("Player Chat Packet", SimpleEvent.class, PlayerChatPacketEvent12110.class,
                        "player chat packet");

                EventValues.registerEventValue(PlayerChatPacketEvent12110.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerChatPacketEvent12110 event) {
                        return event.getString();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(PlayerChatPacketEvent12110.class, UUID.class, new Getter<>() {
                    @Override
                    public UUID get(PlayerChatPacketEvent12110 event) {
                        return event.getSender();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
