package poa.poaskrewritev2.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprEntityID extends SimplePropertyExpression<Entity, Number> {

    static {
        register(ExprEntityID.class, Number.class, "entity id", "entities");
    }

    @Override
    public @Nullable Number convert(Entity entity) {
        return entity.getEntityId();
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "entity id";
    }

}
