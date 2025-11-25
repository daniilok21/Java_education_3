package io.github.some_example_name.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import io.github.some_example_name.GameSettings;
import io.github.some_example_name.screens.GameScreen;


public class AsteroidObject extends GameObject {

    private static final int paddingHorizontal = 20;

    private int livesLeft;
    GameScreen gameScreen;

    public AsteroidObject(int width, int height, int livesLeft, String texturePath, World world, GameScreen gameScreen) {
        super(
            texturePath,
            width / 2 + paddingHorizontal + (new Random()).nextInt((GameSettings.SCREEN_WIDTH - 2 * paddingHorizontal - width)),
            GameSettings.SCREEN_HEIGHT + height / 2,
            width, height,
            GameSettings.TRASH_BIT,
            world
        );

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
        this.livesLeft = livesLeft;
        this.gameScreen = gameScreen;
    }

    public AsteroidObject(int x, int y, int width, int height, int livesLeft, String texturePath, World world, GameScreen gameScreen) {
        super(
            texturePath, x, y,
            width, height,
            GameSettings.TRASH_BIT,
            world
        );

        body.setLinearVelocity(new Vector2(0, -GameSettings.TRASH_VELOCITY));
        this.livesLeft = livesLeft;
        this.gameScreen = gameScreen;
    }

    public boolean isAlive() {
        return livesLeft > 0;
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }
    public int getLivesLeft() {
        return livesLeft;
    }
    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }
    @Override
    public void hit() {
        livesLeft -= 1;
        if (livesLeft > 0) {
            System.out.println(123);
            gameScreen.spawnAsteroid(this);
        }
    }
}
