package poa.poaskrewritev2.events.eventvalues;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterParticle {

    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
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
            case "1211" -> {
                EventValues.registerEventValue(ParticleEvent1211.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1211 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1211.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1211 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1211.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1211 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1211.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1211 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1211.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1211 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1213" -> {
                EventValues.registerEventValue(ParticleEvent1213.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1213 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1213.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1213 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1213.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1213 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1213.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1213 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1213.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1213 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1214" -> {
                EventValues.registerEventValue(ParticleEvent1214.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1214 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1214.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1214 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1214.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1214 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1214.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1214 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1214.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1214 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1215" -> {
                EventValues.registerEventValue(ParticleEvent1215.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1215 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1215.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1215 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1215.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1215 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1215.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1215 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1215.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1215 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1216" -> {
                EventValues.registerEventValue(ParticleEvent1216.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1216 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1216.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1216 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1216.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1216 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1216.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1216 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1216.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1216 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1217" -> {
                EventValues.registerEventValue(ParticleEvent1217.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1217 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1217.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1217 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1217.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1217 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1217.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1217 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1217.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1217 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1218" -> {
                EventValues.registerEventValue(ParticleEvent1218.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1218 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1218.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1218 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1218.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1218 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1218.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1218 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1218.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1218 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1219" -> {
                EventValues.registerEventValue(ParticleEvent1219.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent1219 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1219.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent1219 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1219.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent1219 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent1219.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent1219 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent1219.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent1219 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                EventValues.registerEventValue(ParticleEvent12110.class, Particle.class, new Getter<>() {
                    @Override
                    public Particle get(ParticleEvent12110 event) {
                        return event.getParticle();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent12110.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(ParticleEvent12110 event) {
                        return event.getCount();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent12110.class, Float.class, new Getter<>() {
                    @Override
                    public Float get(ParticleEvent12110 event) {
                        return event.getMaxSpeed();
                    }
                }, EventValues.TIME_NOW);

                EventValues.registerEventValue(ParticleEvent12110.class, Vector.class, new Getter<>() {
                    @Override
                    public Vector get(ParticleEvent12110 event) {
                        return event.getOffset();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(ParticleEvent12110.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(ParticleEvent12110 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
