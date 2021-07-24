package aa.main;

import static java.util.Objects.requireNonNull;

import java.util.function.Supplier;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public class MoveEntityRightAction extends UserAction {

    private final Supplier<Entity> supplier;

    public static MoveEntityRightAction of(final Supplier<Entity> supplier) {
        requireNonNull(supplier);
        return new MoveEntityRightAction(supplier);
    }

    private MoveEntityRightAction(final Supplier<Entity> supplier) {
        super("Move Right");
        this.supplier = supplier;
    }

    @Override
    protected void onAction() {
        final Entity entity = supplier.get();
        final int x = entity.xProperty().intValue();
        final int width = 25;
        final int movement = 5;

        final int offset;
        if (x + width + movement < 1024) {
            offset = movement;
        } else if (x + width < 1024) {
            offset = 1024 - (x + width);
        } else {
            offset = 0;
        }

        if (offset > 0) {
            entity.translateX(offset);
        }
    }
}
