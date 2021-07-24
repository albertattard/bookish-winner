package aa.main;

import static aa.main.BookishWinner.GAME_VARS.COINS_PICKED;
import static aa.main.BookishWinner.GAME_VARS.HITS;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;
import static com.almasb.fxgl.dsl.FXGLForKtKt.inc;

import java.util.Map;
import java.util.Random;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BookishWinner extends GameApplication {

    public enum GAME_VARS {
        COINS_PICKED,
        HITS;
    }

    private Entity player;

    private Entity coin;

    @Override
    protected void initGame() {
        initPlayer();
        spanCoin();
    }

    private void initPlayer() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(487, 770)
                .viewWithBBox(new Rectangle(25, 25, Color.BLUE))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    private void spanCoin() {
        final Random random = new Random();
        final int x = random.nextInt(1024 - 50) + 25;
        final int y = random.nextInt(800 - 50) + 25;

        coin = FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(x, y)
                .viewWithBBox(new Circle(25, 25, 25, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put(COINS_PICKED.name(), 0);
        vars.put(HITS.name(), 0);
    }

    @Override
    protected void initInput() {
        final Input input = FXGL.getInput();
        input.addAction(MoveEntityRightAction.of(() -> player), KeyCode.D);
        input.addAction(MoveEntityLeftAction.of(() -> player), KeyCode.A);
        input.addAction(MoveEntityUpAction.of(() -> player), KeyCode.W);
        input.addAction(MoveEntityDownAction.of(() -> player), KeyCode.S);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(final Entity player, final Entity coin) {
                inc(COINS_PICKED.name(), 1);
                FXGL.play("pick-coin.wav");
                coin.removeFromWorld();
                spanCoin();
            }
        });
    }

    @Override
    protected void initUI() {
        final Text coinsPicked = new Text();
        coinsPicked.setTranslateX(5);
        coinsPicked.setTranslateY(25);
        coinsPicked.setFont(Font.font("Arial", 24.0));
        coinsPicked.textProperty().bind(getWorldProperties()
                .intProperty(COINS_PICKED.name())
                .asString("Coins Picked: %d"));
        getGameScene().addUINode(coinsPicked);

        final Text hits = new Text();
        hits.setTranslateX(200);
        hits.setTranslateY(25);
        hits.setFont(Font.font("Arial", 24.0));
        hits.textProperty().bind(getWorldProperties()
                .intProperty(HITS.name())
                .asString("Hits: %d"));
        getGameScene().addUINode(hits);
    }

    @Override
    protected void initSettings(final GameSettings settings) {
        settings.setTitle("Bookish Winner");
        settings.setVersion("v0.8");
        settings.setWidth(1024);
        settings.setHeight(800);
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
