package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.ButtonView;
import io.github.some_example_name.components.ImageView;
import io.github.some_example_name.components.MovingBackgroundView;
import io.github.some_example_name.components.ProgressUpgrades;
import io.github.some_example_name.components.TextView;

public class UpgradesScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;

    ProgressUpgrades healthUpgrades;
    ProgressUpgrades damageUpgrades;
    ProgressUpgrades fireRateUpgrades;
    MovingBackgroundView backgroundView;
    TextView titleTextView;
    ImageView blackoutImageView;
    ButtonView returnButton;
    ButtonView plusButtonHealth;
    ButtonView plusButtonDamage;
    ButtonView plusButtonFireRate;

    public UpgradesScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        blackoutImageView = new ImageView(85, 345, 550, 580, GameResources.BLACKOUT_MIDDLE_IMG_PATH);

        titleTextView = new TextView(myGdxGame.largeWhiteFont, 180, 960, "Upgrades");

        returnButton = new ButtonView(
            280, 427,
            160, 70,
            myGdxGame.commonBlackFont,
            GameResources.BUTTON_SHORT_BG_IMG_PATH,
            "return"
        );
        healthUpgrades = new ProgressUpgrades(150, 780, 360, 84, 1, 16, 16, GameResources.UPGRADES_HEALTH_BAR_IMG_PATH, GameResources.UPGRADES_HEALTH_ICON_IMG_PATH);
        damageUpgrades = new ProgressUpgrades(150, 668, 360, 84, 1, 13, 16, GameResources.UPGRADES_DAMAGE_BAR_IMG_PATH, GameResources.UPGRADES_DAMAGE_ICON_IMG_PATH);
        fireRateUpgrades = new ProgressUpgrades(150, 556, 360, 84, 1, 16, 16, GameResources.UPGRADES_HEALTH_BAR_IMG_PATH, GameResources.UPGRADES_HEALTH_ICON_IMG_PATH);
        plusButtonHealth = new ButtonView(530, 792, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
        plusButtonDamage = new ButtonView(530, 680, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
        plusButtonFireRate = new ButtonView(530, 568, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
    }

    @Override
    public void render(float delta) {

        handleInput();

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        blackoutImageView.draw(myGdxGame.batch);

        healthUpgrades.draw(myGdxGame.batch);
        damageUpgrades.draw(myGdxGame.batch);
        fireRateUpgrades.draw(myGdxGame.batch);
        titleTextView.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);
        plusButtonHealth.draw(myGdxGame.batch);
        plusButtonDamage.draw(myGdxGame.batch);
        plusButtonFireRate.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
            if (plusButtonHealth.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                System.out.println("Health hit");
            }
            if (plusButtonDamage.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                System.out.println("Damage hit");
            }
            if (plusButtonFireRate.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                System.out.println("FireRate hit");
            }
        }
    }
}
