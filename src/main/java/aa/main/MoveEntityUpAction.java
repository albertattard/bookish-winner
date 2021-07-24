package aa.main;

import static java.util.Objects.requireNonNull;

import java.util.function.Supplier;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public class MoveEntityUpAction extends UserAction {

    private final Supplier<Entity> supplier;

    public static MoveEntityUpAction of(final Supplier<Entity> supplier) {
        requireNonNull(supplier);
        return new MoveEntityUpAction(supplier);
    }

    private MoveEntityUpAction(final Supplier<Entity> supplier) {
        super("Move Up");
        this.supplier = supplier;
    }

    @Override
    protected void onAction() {
        final Entity entity = supplier.get();
        final int y = entity.yProperty().intValue();
        final int movement = 5;
        final int offset;

        if (y > movement) {
            offset = -movement;
        } else if (y > 0) {
            offset = -y;
        } else {
            offset = 0;
        }

        entity.translateY(offset);
    }
}
