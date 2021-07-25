package aa.main;

import static org.assertj.core.api.Assertions.assertThat;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Disabled
@DisplayName("Move Entity Up Action")
class MoveEntityUpActionTest {

    @Test
    @DisplayName("should move the entity by 5")
    void shouldMoveTheEntityBy5() {
        /* Given */
        final Entity entity = FXGL.entityBuilder()
                .at(0, 10)
                .build();
        final MoveEntityUpAction action = MoveEntityUpAction.of(() -> entity);

        /* When */
        action.onAction();

        /* Then */
        assertThat(entity.yProperty().intValue())
                .describedAs("The entity should be moved up")
                .isEqualTo(5);
    }

    @Test
    @DisplayName("should not move the entity out of the board")
    void shouldNotMoveTheEntityOutOfTheBoard() {
        /* Given */
        final Entity entity = FXGL.entityBuilder()
                .at(0, 0)
                .build();
        final MoveEntityUpAction action = MoveEntityUpAction.of(() -> entity);

        /* When */
        action.onAction();

        /* Then */
        assertThat(entity.yProperty().intValue())
                .describedAs("The entity should not be moved up")
                .isEqualTo(0);
    }
}
