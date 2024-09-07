package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import poa.packets.packetListener.events.ParticleEvent1202;
import poa.packets.packetListener.events.ParticleEvent1204;
import poa.packets.packetListener.events.ParticleEvent1206;
import poa.packets.packetListener.events.ParticleEvent121;
import poa.util.BukkitVersion;

public class RegisterParticle {

    public static void register(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                Skript.registerEvent("Particle Send", SimpleEvent.class, ParticleEvent1202.class,
                        "particle send");
                EventValues.registerEventValue(ParticleEvent1202.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1202 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1202.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1202 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1202.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1202 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1202.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1202 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1202.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1202 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }


            case "1204" -> {
                Skript.registerEvent("Particle Send", SimpleEvent.class, ParticleEvent1204.class,
                        "particle send");
                EventValues.registerEventValue(ParticleEvent1204.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1204 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1204.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1204 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1204.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1204 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1204.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1204 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1204.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1204 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);

            }


            case "1206" -> {
                Skript.registerEvent("Particle Send", SimpleEvent.class, ParticleEvent1206.class,
                        "particle send");
                EventValues.registerEventValue(ParticleEvent1206.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1206 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1206.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1206 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1206.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1206 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1206.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1206 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1206.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1206 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "121" -> {
                Skript.registerEvent("Particle Send", SimpleEvent.class, ParticleEvent121.class,
                        "particle send");
                EventValues.registerEventValue(ParticleEvent121.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent121 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent121.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent121 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent121.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent121 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent121.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent121 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent121.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent121 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
