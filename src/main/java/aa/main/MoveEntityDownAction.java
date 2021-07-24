package aa.main;

import static java.util.Objects.requireNonNull;

import java.util.function.Supplier;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;

public class MoveEntityDownAction extends UserAction {

    private final Supplier<Entity> supplier;

    public static MoveEntityDownAction of(final Supplier<Entity> supplier) {
        requireNonNull(supplier);
        return new MoveEntityDownAction(supplier);
    }

    private MoveEntityDownAction(final Supplier<Entity> supplier) {
        super("Move Down");
        this.supplier = supplier;
    }

    @Override
    protected void onAction() {
        final Entity entity = supplier.get();
        final int y = entity.yProperty().intValue();
        final int height = 25;
        final int movement = 5;

        final int offset;
        if (y + height + movement < 800) {
            offset = movement;
        } else if (y + height < 800) {
            offset = 800 - (y + height);
        } else {
            offset = 0;
        }

        if (offset > 0) {
            entity.translateY(offset);
        }
    }
}
