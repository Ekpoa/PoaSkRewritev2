package poa.poaskrewritev2.expressions.metadatasec;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.expressions.base.SectionExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;
import poa.packets.Metadata;
import poa.util.Messages;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ExprSecMetadataPacket extends SectionExpression<Metadata> {

    private static final EntryValidator VALIDATOR;

    static {
        final EntryValidator.EntryValidatorBuilder b = EntryValidator.builder();

        VALIDATOR = b
                // General
                .addEntryData(new ExpressionEntryData<>("glowing", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("invisible", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("fire", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("silent", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("gravity", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("name", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("name visible", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("pose", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("health", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("air", null, true, Number.class))

                // Stand
                .addEntryData(new ExpressionEntryData<>("stand small", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand arms", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand nobaseplate", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand marker", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand left arm", null, true, String.class))   
                .addEntryData(new ExpressionEntryData<>("stand right arm", null, true, String.class)) 
                .addEntryData(new ExpressionEntryData<>("stand left leg", null, true, String.class))  
                .addEntryData(new ExpressionEntryData<>("stand right leg", null, true, String.class))  
                .addEntryData(new ExpressionEntryData<>("stand head", null, true, String.class))       
                .addEntryData(new ExpressionEntryData<>("stand body", null, true, String.class))       

                // Display
                .addEntryData(new ExpressionEntryData<>("display item", null, true, String.class))     
                .addEntryData(new ExpressionEntryData<>("display block", null, true, String.class))    
                .addEntryData(new ExpressionEntryData<>("display interpolation", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display transformation", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display teleportduration", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display translation", null, true, String.class)) 
                .addEntryData(new ExpressionEntryData<>("display scale", null, true, String.class))       
                .addEntryData(new ExpressionEntryData<>("display rotation left", null, true, String.class))  
                .addEntryData(new ExpressionEntryData<>("display rotation right", null, true, String.class)) 
                .addEntryData(new ExpressionEntryData<>("display billboard", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("display brightness", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("display view", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display shadow radius", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display shadow strength", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display width", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display height", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display line", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display background", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("display text", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("display text opacity", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display index27", null, true, String.class))
                .addEntryData(new ExpressionEntryData<>("display glow", null, true, String.class))

                // Interaction
                .addEntryData(new ExpressionEntryData<>("interaction width", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("interaction height", null, true, Number.class))

                .build();

        Skript.registerExpression(
                ExprSecMetadataPacket.class,
                Metadata.class,
                ExpressionType.SIMPLE,
                "new metadata packet with id %number%"
        );
    }

    private Expression<Number> idExpr;

    // General
    private Expression<Boolean> glowingExpr, invisibleExpr, fireExpr, silentExpr, gravityExpr, nameVisibleExpr;
    private Expression<String> nameExpr, poseExpr;
    private Expression<Number> healthExpr, airExpr;

    // Stand
    private Expression<Boolean> standSmallExpr, standArmsExpr, standNoBaseExpr, standMarkerExpr;
    private Expression<String> standLeftArmExpr, standRightArmExpr, standLeftLegExpr, standRightLegExpr, standHeadExpr, standBodyExpr;

    // Display
    private Expression<String> displayItemExpr, displayBlockExpr;
    private Expression<Number> displayInterpolationExpr, displayTransformationExpr, displayTeleportDurationExpr, displayViewExpr,
            displayShadowRadiusExpr, displayShadowStrengthExpr, displayWidthExpr, displayHeightExpr, displayLineExpr, displayTextOpacityExpr;
    private Expression<String> displayTranslationExpr, displayScaleExpr, displayRotationLeftExpr, displayRotationRightExpr,
            displayBillboardExpr, displayBrightnessExpr, displayBackgroundExpr, displayTextExpr, displayIndex27Expr, displayGlowExpr;

    // Interaction
    private Expression<Number> interactionWidthExpr, interactionHeightExpr;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions,
                        int pattern,
                        Kleenean delayed,
                        SkriptParser.ParseResult result,
                        @Nullable SectionNode node,
                        @Nullable List<TriggerItem> triggerItems) {

        idExpr = (Expression<Number>) expressions[0];

        if (node != null) {
            EntryContainer c = VALIDATOR.validate(node);
            if (c == null) return false;

            glowingExpr = (Expression<Boolean>) c.getOptional("glowing", false);
            invisibleExpr = (Expression<Boolean>) c.getOptional("invisible", false);
            fireExpr = (Expression<Boolean>) c.getOptional("fire", false);
            silentExpr = (Expression<Boolean>) c.getOptional("silent", false);
            gravityExpr = (Expression<Boolean>) c.getOptional("gravity", false);
            nameExpr = (Expression<String>) c.getOptional("name", false);
            nameVisibleExpr = (Expression<Boolean>) c.getOptional("name visible", false);
            poseExpr = (Expression<String>) c.getOptional("pose", false);
            healthExpr = (Expression<Number>) c.getOptional("health", false);
            airExpr = (Expression<Number>) c.getOptional("air", false);

            standSmallExpr = (Expression<Boolean>) c.getOptional("stand small", false);
            standArmsExpr = (Expression<Boolean>) c.getOptional("stand arms", false);
            standNoBaseExpr = (Expression<Boolean>) c.getOptional("stand nobaseplate", false);
            standMarkerExpr = (Expression<Boolean>) c.getOptional("stand marker", false);
            standLeftArmExpr = (Expression<String>) c.getOptional("stand left arm", false);
            standRightArmExpr = (Expression<String>) c.getOptional("stand right arm", false);
            standLeftLegExpr = (Expression<String>) c.getOptional("stand left leg", false);
            standRightLegExpr = (Expression<String>) c.getOptional("stand right leg", false);
            standHeadExpr = (Expression<String>) c.getOptional("stand head", false);
            standBodyExpr = (Expression<String>) c.getOptional("stand body", false);

            displayItemExpr = (Expression<String>) c.getOptional("display item", false);
            displayBlockExpr = (Expression<String>) c.getOptional("display block", false);
            displayInterpolationExpr = (Expression<Number>) c.getOptional("display interpolation", false);
            displayTransformationExpr = (Expression<Number>) c.getOptional("display transformation", false);
            displayTeleportDurationExpr = (Expression<Number>) c.getOptional("display teleportduration", false);
            displayTranslationExpr = (Expression<String>) c.getOptional("display translation", false);
            displayScaleExpr = (Expression<String>) c.getOptional("display scale", false);
            displayRotationLeftExpr = (Expression<String>) c.getOptional("display rotation left", false);
            displayRotationRightExpr = (Expression<String>) c.getOptional("display rotation right", false);
            displayBillboardExpr = (Expression<String>) c.getOptional("display billboard", false);
            displayBrightnessExpr = (Expression<String>) c.getOptional("display brightness", false);
            displayViewExpr = (Expression<Number>) c.getOptional("display view", false);
            displayShadowRadiusExpr = (Expression<Number>) c.getOptional("display shadow radius", false);
            displayShadowStrengthExpr = (Expression<Number>) c.getOptional("display shadow strength", false);
            displayWidthExpr = (Expression<Number>) c.getOptional("display width", false);
            displayHeightExpr = (Expression<Number>) c.getOptional("display height", false);
            displayLineExpr = (Expression<Number>) c.getOptional("display line", false);
            displayBackgroundExpr = (Expression<String>) c.getOptional("display background", false);
            displayTextExpr = (Expression<String>) c.getOptional("display text", false);
            displayTextOpacityExpr = (Expression<Number>) c.getOptional("display text opacity", false);
            displayIndex27Expr = (Expression<String>) c.getOptional("display index27", false);
            displayGlowExpr = (Expression<String>) c.getOptional("display glow", false);

            interactionWidthExpr = (Expression<Number>) c.getOptional("interaction width", false);
            interactionHeightExpr = (Expression<Number>) c.getOptional("interaction height", false);
        }

        return true;
    }

    @Override
    protected Metadata[] get(Event e) {
        Number id = val(idExpr, e);
        if (id == null) return new Metadata[0];

        Metadata meta = new Metadata(id.intValue());

        // General
        final Boolean val = val(glowingExpr, e);
        set(meta::setGlow, val);
        set(meta::setInvisible, val(invisibleExpr, e));
        set(meta::setOnFire, val(fireExpr, e));
        set(meta::setSilent, val(silentExpr, e));
        set(meta::setGravity, val(gravityExpr, e));
        String name = val(nameExpr, e);
        if (name != null) meta.setName(Messages.essentialsToMinimessage(name));
        set(meta::setNameVisible, val(nameVisibleExpr, e));
        String pose = val(poseExpr, e);
        if (pose != null) meta.setPose(pose.toUpperCase(Locale.ROOT));
        num(healthExpr, e, v -> meta.setHealth(v.intValue()));
        num(airExpr, e, v -> meta.setRemainingAir(v.intValue()));

        // Stand
        set(meta::setIsSmall, val(standSmallExpr, e));
        set(meta::setHasArms, val(standArmsExpr, e));
        set(meta::setNoBase, val(standNoBaseExpr, e));
        set(meta::setIsMarker, val(standMarkerExpr, e));
        vec3(val(standLeftArmExpr, e), meta::setLeftArmRotation);
        vec3(val(standRightArmExpr, e), meta::setRightArmRotation);
        vec3(val(standLeftLegExpr, e), meta::setLeftLegRotation);
        vec3(val(standRightLegExpr, e), meta::setRightLegRotation);
        vec3(val(standHeadExpr, e), meta::setHeadRotation);
        vec3(val(standBodyExpr, e), meta::setBodyRotation);

        // Display
        mat(val(displayItemExpr, e), m -> meta.setDisplayItem(new ItemStack(m)));
        mat(val(displayBlockExpr, e), m -> meta.setDisplayBlock(m.createBlockData()));
        num(displayInterpolationExpr, e, v -> meta.setInterpolationDelay(v.intValue()));
        num(displayTransformationExpr, e, v -> meta.setTransformationDuration(v.intValue()));
        num(displayTeleportDurationExpr, e, v -> meta.setPosRotDuration(v.intValue()));
        vec3(val(displayTranslationExpr, e), meta::setTranslation);
        vec3(val(displayScaleExpr, e), meta::setScale);
        quat(val(displayRotationLeftExpr, e), meta::setRotationLeft);
        quat(val(displayRotationRightExpr, e), meta::setRotationRight);
        set(meta::setBillboard, val(displayBillboardExpr, e));
        ints2(val(displayBrightnessExpr, e), (a, b) -> meta.setBrightness(a, b));
        num(displayViewExpr, e, v -> meta.setViewRange(v.floatValue()));
        num(displayShadowRadiusExpr, e, v -> meta.setShadowRadius(v.floatValue()));
        num(displayShadowStrengthExpr, e, v -> meta.setShadowStrength(v.floatValue()));
        num(displayWidthExpr, e, v -> meta.setWidth(v.floatValue()));
        num(displayHeightExpr, e, v -> meta.setHeight(v.floatValue()));
        num(displayLineExpr, e, v -> meta.setLineWidth(v.intValue()));
        ints4(val(displayBackgroundExpr, e), (r, g, b, a) -> meta.setBackground(r, g, b, a));
        String txt = val(displayTextExpr, e);
        if (txt != null) meta.setText(Messages.essentialsToMinimessage(txt));
        num(displayTextOpacityExpr, e, v -> meta.setTextOpacity(v.intValue()));
        bools3(val(displayIndex27Expr, e), (a, b, c) -> meta.index27(a, b, c));
        ints3(val(displayGlowExpr, e), (r, g, b) -> meta.setGlowOverride(Color.fromRGB(r, g, b).asRGB()));

        // Interaction
        num(interactionWidthExpr, e, v -> meta.setInteractionWidth(v.floatValue()));
        num(interactionHeightExpr, e, v -> meta.setInteractionHeight(v.floatValue()));

        return new Metadata[]{meta};
    }

    private static <T> T val(Expression<T> expr, Event e) {
        return expr == null ? null : expr.getSingle(e);
    }

    private static <N extends Number> void num(Expression<N> expr, Event e, java.util.function.Consumer<N> fn) {
        N v = val(expr, e);
        if (v != null) fn.accept(v);
    }

    private static void set(java.util.function.Consumer<Boolean> setter, Boolean v) {
        if (v != null) setter.accept(v);
    }

    private static void set(java.util.function.Consumer<String> setter, String v) {
        if (v != null) setter.accept(v);
    }

    private interface Vec3 {
        void apply(float x, float y, float z);
    }

    private interface Quat {
        void apply(float x, float y, float z, float w);
    }

    private interface Int2 {
        void apply(int a, int b);
    }

    private interface Int3 {
        void apply(int a, int b, int c);
    }

    private interface Int4 {
        void apply(int a, int b, int c, int d);
    }

    private interface Bool3 {
        void apply(boolean a, boolean b, boolean c);
    }

    private static void vec3(String s, Vec3 fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 3) {
            Skript.warning("Expected 3 numbers: " + s);
            return;
        }
        try {
            fn.apply(Float.parseFloat(p[0]), Float.parseFloat(p[1]), Float.parseFloat(p[2]));
        } catch (NumberFormatException ex) {
            Skript.warning("Invalid vec3: " + s);
        }
    }

    private static void quat(String s, Quat fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 4) {
            Skript.warning("Expected 4 numbers: " + s);
            return;
        }
        try {
            fn.apply(Float.parseFloat(p[0]), Float.parseFloat(p[1]), Float.parseFloat(p[2]), Float.parseFloat(p[3]));
        } catch (NumberFormatException ex) {
            Skript.warning("Invalid quaternion: " + s);
        }
    }

    private static void ints2(String s, Int2 fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 2) {
            Skript.warning("Expected 2 integers: " + s);
            return;
        }
        try {
            fn.apply(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
        } catch (NumberFormatException ex) {
            Skript.warning("Invalid integers: " + s);
        }
    }

    private static void ints3(String s, Int3 fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 3) {
            Skript.warning("Expected 3 integers: " + s);
            return;
        }
        try {
            fn.apply(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]));
        } catch (NumberFormatException ex) {
            Skript.warning("Invalid integers: " + s);
        }
    }

    private static void ints4(String s, Int4 fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 4) {
            Skript.warning("Expected 4 integers: " + s);
            return;
        }
        try {
            fn.apply(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3]));
        } catch (NumberFormatException ex) {
            Skript.warning("Invalid integers: " + s);
        }
    }

    private static void bools3(String s, Bool3 fn) {
        if (s == null) return;
        String[] p = s.trim().split("[,\\s]+");
        if (p.length < 3) {
            Skript.warning("Expected 3 booleans: " + s);
            return;
        }
        fn.apply(Boolean.parseBoolean(p[0]), Boolean.parseBoolean(p[1]), Boolean.parseBoolean(p[2]));
    }

    private static void mat(String s, java.util.function.Consumer<Material> fn) {
        if (s == null) return;
        try {
            fn.accept(Material.valueOf(s.trim().toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException ex) {
            Skript.warning("Invalid material: " + s);
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Metadata> getReturnType() {
        return Metadata.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "section metadata packet with id " + (idExpr == null ? "?" : Objects.toString(idExpr.toString(event, debug)));
    }
}
