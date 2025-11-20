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
    MovingBackgroundView backgroundView;
    TextView titleTextView;
    ImageView blackoutImageView;
    ButtonView returnButton;

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
        healthUpgrades = new ProgressUpgrades(150, 780, 360, 84, 1, GameResources.UPGRADES_HEALTH_BAR_IMG_PATH, GameResources.UPGRADES_HEALTH_ICON_IMG_PATH);
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
        titleTextView.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        }
    }
}
