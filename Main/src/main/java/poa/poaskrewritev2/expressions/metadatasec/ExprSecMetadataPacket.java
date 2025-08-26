package poa.poaskrewritev2.expressions.metadatasec;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.expressions.base.SectionExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.skript.lang.VariableString;
import ch.njol.skript.lang.util.SimpleLiteral;
import ch.njol.skript.util.Color; // Skript's Color (NOT Bukkit)
import ch.njol.util.Kleenean;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;
import org.skriptlang.skript.lang.entry.EntryContainer;
import org.skriptlang.skript.lang.entry.EntryValidator;
import org.skriptlang.skript.lang.entry.util.ExpressionEntryData;
import poa.packets.Metadata;
import poa.util.Messages;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Usage:
 * set {_m} to new metadata packet with id {_id}:
 *   glowing: true
 *   name: "&aHello"
 *   name visible: true
 *   pose: STANDING
 *   health: 20
 *   air: 300
 *
 *   # ArmorStand bits
 *   stand small: false
 *   stand arms: true
 *   stand nobaseplate: true
 *   stand marker: false
 *   stand head: vector(0, 25, 0)
 *   stand left arm: vector(-10, 0, 0)
 *
 *   # Display
 *   display item: diamond
 *   display block: stone as block
 *   display translation: vector(0, 1, 0)
 *   display scale: vector(2, 2, 2)
 *   display rotation left: 0, 0, 0, 1
 *   display rotation right: 0, 0, 0, 1
 *   display billboard: center    # unquoted works
 *   display brightness: 15, 15
 *   display background color: rgb(0, 0, 0)
 *   display background alpha: 128
 *   display text: "&cHello"
 *   display text opacity: 255
 *   display glow: rgb(255, 128, 64)
 *
 *   interaction width: 1.5
 *   interaction height: 2
 */
public class ExprSecMetadataPacket extends SectionExpression<Metadata> {

    private static final EntryValidator VALIDATOR;
    static {
        final EntryValidator.EntryValidatorBuilder b = EntryValidator.builder();

        VALIDATOR = b
                // ===== General =====
                .addEntryData(new ExpressionEntryData<>("glowing", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("invisible", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("fire", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("silent", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("gravity", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("name", null, true, Object.class)) // toStringExpr
                .addEntryData(new ExpressionEntryData<>("name visible", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("pose", null, true, Object.class)) // toStringExpr
                .addEntryData(new ExpressionEntryData<>("health", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("air", null, true, Number.class))

                // ===== Stand (ArmorStand) =====
                .addEntryData(new ExpressionEntryData<>("stand small", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand arms", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand nobaseplate", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand no base", null, true, Boolean.class)) // alias
                .addEntryData(new ExpressionEntryData<>("stand noplate", null, true, Boolean.class)) // alias
                .addEntryData(new ExpressionEntryData<>("stand marker", null, true, Boolean.class))
                .addEntryData(new ExpressionEntryData<>("stand left arm", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("stand right arm", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("stand left leg", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("stand right leg", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("stand head", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("stand body", null, true, Vector.class))

                // ===== Display (Display/TextDisplay/ItemDisplay/BlockDisplay) =====
                .addEntryData(new ExpressionEntryData<>("display item", null, true, ItemType.class))
                .addEntryData(new ExpressionEntryData<>("display block", null, true, BlockData.class))
                .addEntryData(new ExpressionEntryData<>("display interpolation", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display transformation", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display teleportduration", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display translation", null, true, Vector.class))
                .addEntryData(new ExpressionEntryData<>("display scale", null, true, Vector.class))
                // Quaternions: 4 numbers (typed)
                .addEntryData(new ExpressionEntryData<>("display rotation left", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display rotation right", null, true, Number.class))
                // Billboard as TEXT (Object -> String), we map keywords ourselves so unquoted works
                .addEntryData(new ExpressionEntryData<>("display billboard", null, true, Object.class))
                // Brightness: 2 ints in one Number expr
                .addEntryData(new ExpressionEntryData<>("display brightness", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display view", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display shadow radius", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display shadow strength", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display width", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display height", null, true, Number.class))
                .addEntryData(new ExpressionEntryData<>("display line", null, true, Number.class))
                // Background: Skript Color + optional alpha override
                .addEntryData(new ExpressionEntryData<>("display background color", null, true, Color.class))
                .addEntryData(new ExpressionEntryData<>("display background alpha", null, true, Number.class))
                // Text + opacity
                .addEntryData(new ExpressionEntryData<>("display text", null, true, Object.class)) // toStringExpr
                .addEntryData(new ExpressionEntryData<>("display text opacity", null, true, Number.class))
                // Glow color as Skript Color (we convert to RGB int)
                .addEntryData(new ExpressionEntryData<>("display glow", null, true, Color.class))

                // ===== Interaction =====
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

    // === Inputs ===
    private Expression<Number> idExpr;

    // General
    private Expression<Boolean> glowingExpr, invisibleExpr, fireExpr, silentExpr, gravityExpr, nameVisibleExpr;
    private Expression<String> nameExpr, poseExpr;
    private Expression<Number> healthExpr, airExpr;

    // Stand
    private Expression<Boolean> standSmallExpr, standArmsExpr, standNoBaseExpr, standNoBase2Expr, standNoPlateExpr, standMarkerExpr;
    private Expression<Vector> standLeftArmExpr, standRightArmExpr, standLeftLegExpr, standRightLegExpr, standHeadExpr, standBodyExpr;

    // Display
    private Expression<ItemType>  displayItemExpr;
    private Expression<BlockData> displayBlockExpr;
    private Expression<Number> displayInterpolationExpr, displayTransformationExpr, displayTeleportDurationExpr, displayViewExpr,
            displayShadowRadiusExpr, displayShadowStrengthExpr, displayWidthExpr, displayHeightExpr, displayLineExpr, displayTextOpacityExpr;

    private Expression<Vector> displayTranslationExpr, displayScaleExpr;
    private Expression<Number> displayRotationLeftExpr, displayRotationRightExpr; // 4 numbers each
    private Expression<String> displayBillboardExpr; // mapped keywords
    private Expression<Number> displayBrightnessExpr; // 2 ints
    private Expression<Color>  displayBackgroundColorExpr, displayGlowColorExpr; // Skript Color
    private Expression<Number> displayBackgroundAlphaExpr;
    private Expression<String> displayTextExpr;

    // Interaction
    private Expression<Number> interactionWidthExpr, interactionHeightExpr;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int pattern, Kleenean delayed,
                        SkriptParser.ParseResult result, @Nullable SectionNode node,
                        @Nullable List<TriggerItem> triggerItems) {

        idExpr = (Expression<Number>) expressions[0];

        if (node != null) {
            EntryContainer c = VALIDATOR.validate(node);
            if (c == null) return false;

            // General
            glowingExpr     = (Expression<Boolean>) c.getOptional("glowing", false);
            invisibleExpr   = (Expression<Boolean>) c.getOptional("invisible", false);
            fireExpr        = (Expression<Boolean>) c.getOptional("fire", false);
            silentExpr      = (Expression<Boolean>) c.getOptional("silent", false);
            gravityExpr     = (Expression<Boolean>) c.getOptional("gravity", false);
            nameExpr        = toStringExpr((Expression<?>) c.getOptional("name", false));
            nameVisibleExpr = (Expression<Boolean>) c.getOptional("name visible", false);
            poseExpr        = toStringExpr((Expression<?>) c.getOptional("pose", false));
            healthExpr      = (Expression<Number>)  c.getOptional("health", false);
            airExpr         = (Expression<Number>)  c.getOptional("air", false);

            // Stand
            standSmallExpr   = (Expression<Boolean>) c.getOptional("stand small", false);
            standArmsExpr    = (Expression<Boolean>) c.getOptional("stand arms", false);
            standNoBaseExpr  = (Expression<Boolean>) c.getOptional("stand nobaseplate", false);
            standNoBase2Expr = (Expression<Boolean>) c.getOptional("stand no base", false);
            standNoPlateExpr = (Expression<Boolean>) c.getOptional("stand noplate", false);
            standMarkerExpr  = (Expression<Boolean>) c.getOptional("stand marker", false);
            standLeftArmExpr = (Expression<Vector>)  c.getOptional("stand left arm", false);
            standRightArmExpr= (Expression<Vector>)  c.getOptional("stand right arm", false);
            standLeftLegExpr = (Expression<Vector>)  c.getOptional("stand left leg", false);
            standRightLegExpr= (Expression<Vector>)  c.getOptional("stand right leg", false);
            standHeadExpr    = (Expression<Vector>)  c.getOptional("stand head", false);
            standBodyExpr    = (Expression<Vector>)  c.getOptional("stand body", false);

            // Display
            displayItemExpr             = (Expression<ItemType>)  c.getOptional("display item", false);
            displayBlockExpr            = (Expression<BlockData>) c.getOptional("display block", false);
            displayInterpolationExpr    = (Expression<Number>)    c.getOptional("display interpolation", false);
            displayTransformationExpr   = (Expression<Number>)    c.getOptional("display transformation", false);
            displayTeleportDurationExpr = (Expression<Number>)    c.getOptional("display teleportduration", false);

            displayTranslationExpr      = (Expression<Vector>)    c.getOptional("display translation", false);
            displayScaleExpr            = (Expression<Vector>)    c.getOptional("display scale", false);

            displayRotationLeftExpr     = (Expression<Number>)    c.getOptional("display rotation left", false);
            displayRotationRightExpr    = (Expression<Number>)    c.getOptional("display rotation right", false);

            displayBillboardExpr        = toStringExpr((Expression<?>) c.getOptional("display billboard", false));

            displayBrightnessExpr       = (Expression<Number>)    c.getOptional("display brightness", false);
            displayViewExpr             = (Expression<Number>)    c.getOptional("display view", false);
            displayShadowRadiusExpr     = (Expression<Number>)    c.getOptional("display shadow radius", false);
            displayShadowStrengthExpr   = (Expression<Number>)    c.getOptional("display shadow strength", false);
            displayWidthExpr            = (Expression<Number>)    c.getOptional("display width", false);
            displayHeightExpr           = (Expression<Number>)    c.getOptional("display height", false);
            displayLineExpr             = (Expression<Number>)    c.getOptional("display line", false);

            displayBackgroundColorExpr  = (Expression<Color>)     c.getOptional("display background color", false);
            displayBackgroundAlphaExpr  = (Expression<Number>)    c.getOptional("display background alpha", false);

            displayTextExpr             = toStringExpr((Expression<?>) c.getOptional("display text", false));
            displayTextOpacityExpr      = (Expression<Number>)    c.getOptional("display text opacity", false);

            displayGlowColorExpr        = (Expression<Color>)     c.getOptional("display glow", false);

            // Interaction
            interactionWidthExpr        = (Expression<Number>)    c.getOptional("interaction width", false);
            interactionHeightExpr       = (Expression<Number>)    c.getOptional("interaction height", false);
        }

        return true;
    }

    @Override
    protected Metadata[] get(Event e) {
        Number id = val(idExpr, e);
        if (id == null) return new Metadata[0];

        Metadata meta = new Metadata(id.intValue());

        // ===== General =====
        set(meta::setGlow,      val(glowingExpr, e));
        set(meta::setInvisible, val(invisibleExpr, e));
        set(meta::setOnFire,    val(fireExpr, e));
        set(meta::setSilent,    val(silentExpr, e));
        set(meta::setGravity,   val(gravityExpr, e));

        String name = stringify(nameExpr, e);
        if (name != null) meta.setName(Messages.essentialsToMinimessage(name));
        set(meta::setNameVisible, val(nameVisibleExpr, e));

        String pose = stringify(poseExpr, e);
        if (pose != null) meta.setPose(pose.toUpperCase(Locale.ROOT));

        num(healthExpr, e, v -> meta.setHealth(Math.max(0, v.intValue())));
        num(airExpr,    e, v -> meta.setRemainingAir(Math.max(0, v.intValue())));

        // ===== Stand =====
        set(meta::setIsSmall, val(standSmallExpr, e));
        set(meta::setHasArms, val(standArmsExpr, e));
        Boolean noBase = coalesce(val(standNoBaseExpr, e), val(standNoBase2Expr, e), val(standNoPlateExpr, e));
        if (noBase != null) meta.setNoBase(noBase);
        set(meta::setIsMarker, val(standMarkerExpr, e));

        vec3(standLeftArmExpr,  e, meta::setLeftArmRotation);
        vec3(standRightArmExpr, e, meta::setRightArmRotation);
        vec3(standLeftLegExpr,  e, meta::setLeftLegRotation);
        vec3(standRightLegExpr, e, meta::setRightLegRotation);
        vec3(standHeadExpr,     e, meta::setHeadRotation);
        vec3(standBodyExpr,     e, meta::setBodyRotation);

        // ===== Display =====
        ItemType it = val(displayItemExpr, e);
        if (it != null) {
            ItemStack stack = it.getRandom();
            if (stack != null) meta.setDisplayItem(stack);
        }
        BlockData bd = val(displayBlockExpr, e);
        if (bd != null) meta.setDisplayBlock(bd);

        num(displayInterpolationExpr,    e, v -> meta.setInterpolationDelay(v.intValue()));
        num(displayTransformationExpr,   e, v -> meta.setTransformationDuration(v.intValue()));
        num(displayTeleportDurationExpr, e, v -> meta.setPosRotDuration(v.intValue()));

        vec3(displayTranslationExpr, e, meta::setTranslation);
        vec3(displayScaleExpr,       e, meta::setScale);

        quat(displayRotationLeftExpr,  e, meta::setRotationLeft);
        quat(displayRotationRightExpr, e, meta::setRotationRight);

        // billboard keywords (unquoted support)
        String bbRaw = stringify(displayBillboardExpr, e);
        String bb = normalizeKeyword(bbRaw);
        if (bb != null && !bb.isEmpty()) {
            switch (bb) {
                case "center", "centre", "centred" -> meta.setBillboard("center");
                case "fixed"                       -> meta.setBillboard("fixed");
                case "vertical"                    -> meta.setBillboard("vertical");
                case "horizontal"                  -> meta.setBillboard("horizontal");
                default -> Skript.warning("Unknown billboard mode: " + bbRaw + " (expected center/fixed/vertical/horizontal)");
            }
        }

        ints2(displayBrightnessExpr, e, (a, b2) -> meta.setBrightness(clamp(a, 0, 15), clamp(b2, 0, 15)));

        num(displayViewExpr,           e, v -> meta.setViewRange(Math.max(0f, v.floatValue())));
        num(displayShadowRadiusExpr,   e, v -> meta.setShadowRadius(Math.max(0f, v.floatValue())));
        num(displayShadowStrengthExpr, e, v -> meta.setShadowStrength(Math.max(0f, v.floatValue())));
        num(displayWidthExpr,          e, v -> meta.setWidth(Math.max(0f, v.floatValue())));
        num(displayHeightExpr,         e, v -> meta.setHeight(Math.max(0f, v.floatValue())));
        num(displayLineExpr,           e, v -> meta.setLineWidth(Math.max(0, v.intValue())));

        // Background via Skript Color + optional alpha override
        Color bg = val(displayBackgroundColorExpr, e);
        Integer alphaOverride = numOrNull(displayBackgroundAlphaExpr, e);
        if (bg != null) {
            int r = bg.getRed(), g = bg.getGreen(), b3 = bg.getBlue();
            int a = (alphaOverride != null) ? clamp(alphaOverride, 0, 255) : clamp(bg.getAlpha(), 0, 255);
            meta.setBackground(r, g, b3, a);
        }

        String txt = stringify(displayTextExpr, e);
        if (txt != null && !txt.isEmpty()) meta.setText(Messages.essentialsToMinimessage(txt));
        num(displayTextOpacityExpr, e, v -> meta.setTextOpacity(clamp(v.intValue(), 0, 255)));

        // Glow via Skript Color
        Color glow = val(displayGlowColorExpr, e);
        if (glow != null) {
            int rgb = (glow.getRed() << 16) | (glow.getGreen() << 8) | glow.getBlue();
            meta.setGlowOverride(rgb);
        }

        // ===== Interaction =====
        num(interactionWidthExpr,  e, v -> meta.setInteractionWidth(Math.max(0f, v.floatValue())));
        num(interactionHeightExpr, e, v -> meta.setInteractionHeight(Math.max(0f, v.floatValue())));

        return new Metadata[]{meta};
    }

    // ---------- helpers ----------
    @SuppressWarnings("unchecked")
    private static Expression<String> toStringExpr(Expression<?> raw) {
        if (raw == null) return null;
        Expression<String> conv = (Expression<String>) raw.getConvertedExpression(String.class);
        if (conv != null) return conv;
        if (raw instanceof VariableString) return (Expression<String>) raw;
        String fallback = raw.toString(null, false);
        if (fallback == null) fallback = raw.toString(null, true);
        return new SimpleLiteral<>(fallback, false);
    }

    private static <T> T val(Expression<T> expr, Event e) { return expr == null ? null : expr.getSingle(e); }

    private static String stringify(Expression<String> expr, Event e) {
        if (expr == null) return null;
        if (expr instanceof VariableString vs) return vs.toUnformattedString(e).trim();
        String s = expr.getSingle(e);
        return s == null ? null : s.trim();
    }

    @SafeVarargs
    private static <T> T coalesce(T... vals) { if (vals != null) for (T v : vals) if (v != null) return v; return null; }

    private static <N extends Number> void num(Expression<N> expr, Event e, java.util.function.Consumer<N> fn) {
        N v = val(expr, e); if (v != null) fn.accept(v);
    }

    private static Integer numOrNull(Expression<Number> expr, Event e) {
        Number n = val(expr, e); return n == null ? null : n.intValue();
    }

    private static void set(java.util.function.Consumer<Boolean> setter, Boolean v) { if (v != null) setter.accept(v); }
    private static void set(java.util.function.Consumer<String> setter, String v)   { if (v != null) setter.accept(v); }

    private static int clamp(int v, int min, int max){ return Math.max(min, Math.min(max, v)); }

    private interface Vec3 { void apply(float x, float y, float z); }
    private interface Quat { void apply(float x, float y, float z, float w); }
    private interface Int2 { void apply(int a, int b); }

    private static void vec3(Expression<Vector> expr, Event e, Vec3 fn) {
        if (expr == null) return;
        Vector v = expr.getSingle(e);
        if (v == null) return;
        fn.apply((float) v.getX(), (float) v.getY(), (float) v.getZ());
    }

    private static void quat(Expression<Number> expr, Event e, Quat fn) {
        if (expr == null) return;
        Number[] arr = expr.getArray(e);
        if (arr.length < 4) return;
        Number x = arr[0], y = arr[1], z = arr[2], w = arr[3];
        if (x == null || y == null || z == null || w == null) return;
        fn.apply(x.floatValue(), y.floatValue(), z.floatValue(), w.floatValue());
    }

    private static void ints2(Expression<Number> expr, Event e, Int2 fn) {
        if (expr == null) return;
        Number[] arr = expr.getArray(e);
        if (arr.length < 2) return;
        Number a = arr[0], b = arr[1];
        if (a == null || b == null) return;
        fn.apply(a.intValue(), b.intValue());
    }

    private static String normalizeKeyword(String s) {
        if (s == null) return null;
        String t = s.trim();
        // strip single/double quotes if present
        if ((t.length() >= 2) &&
                ((t.startsWith("\"") && t.endsWith("\"")) || (t.startsWith("'") && t.endsWith("'")))) {
            t = t.substring(1, t.length() - 1).trim();
        }
        return t.toLowerCase(java.util.Locale.ROOT);
    }


    @Override public boolean isSingle() { return true; }
    @Override public Class<? extends Metadata> getReturnType() { return Metadata.class; }
    @Override public String toString(@Nullable Event event, boolean debug) {
        return "new metadata packet with id " + (idExpr == null ? "?" : Objects.toString(idExpr.toString(event, debug)));
    }
}
