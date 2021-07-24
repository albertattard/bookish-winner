package aa.main;

import static java.util.Objects.requireNonNull;

import java.util.function.Supplier;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public class MoveEntityLeftAction extends UserAction {

    private final Supplier<Entity> supplier;

    public static MoveEntityLeftAction of(final Supplier<Entity> supplier) {
        requireNonNull(supplier);
        return new MoveEntityLeftAction(supplier);
    }

    private MoveEntityLeftAction(final Supplier<Entity> supplier) {
        super("Move Left");
        this.supplier = supplier;
    }

    @Override
    protected void onAction() {
        final Entity entity = supplier.get();
        final int x = entity.xProperty().intValue();
        final int movement = 5;

        final int offset;
        if (x > movement) {
            offset = -movement;
        } else if (x > 0) {
            offset = -x;
        } else {
            offset = 0;
        }

        if (offset < 0) {
            entity.translateX(offset);
        }
    }
}
