package aa.main;

import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;

import java.util.Random;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameEntityFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(final SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.PLAYER)
                .at(487, 770)
                .viewWithBBox(new Rectangle(25, 25, Color.BLUE))
                .with(new CollidableComponent(true))
                // .with(new AnimationComponent())
                .buildAndAttach();
    }

    @Spawns("coin")
    public Entity newCoin(final SpawnData data) {
        final Random random = new Random();
        final int x = random.nextInt(1024 - 50) + 25;
        final int y = random.nextInt(800 - 50) + 25;

        return FXGL.entityBuilder(data)
                .type(EntityType.COIN)
                .at(x, y)
                .viewWithBBox(new Circle(25, 25, 25, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}
