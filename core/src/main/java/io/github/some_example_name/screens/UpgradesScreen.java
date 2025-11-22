package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.ButtonView;
import io.github.some_example_name.components.ImageView;
import io.github.some_example_name.components.MovingBackgroundView;
import io.github.some_example_name.components.ProgressUpgrades;
import io.github.some_example_name.components.TextView;
import io.github.some_example_name.managers.MemoryManager;

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
    TextView scoreText;

    public UpgradesScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        blackoutImageView = new ImageView(85, 345, 550, 580, GameResources.BLACKOUT_MIDDLE_IMG_PATH);
        titleTextView = new TextView(myGdxGame.largeWhiteFont, GameSettings.SCREEN_WIDTH / 2, 980, "Upgrades");
        titleTextView.setPos(titleTextView.getX() - titleTextView.getWidth() / 2, titleTextView.getY());
        returnButton = new ButtonView(
            280, 407,
            160, 70,
            myGdxGame.commonBlackFont,
            GameResources.BUTTON_SHORT_BG_IMG_PATH,
            "return"
        );
        damageUpgrades = new ProgressUpgrades(150, 760, 360, 84, MemoryManager.getDamageLevel(), 11, 13, GameResources.UPGRADES_DAMAGE_BAR_IMG_PATH, GameResources.UPGRADES_DAMAGE_ICON_IMG_PATH);
        fireRateUpgrades = new ProgressUpgrades(150, 648, 360, 84, MemoryManager.getFireRateLevel(), 13.5f, 10, GameResources.UPGRADES_FIRE_RATE_BAR_IMG_PATH, GameResources.UPGRADES_FIRE_RATE_ICON_IMG_PATH);
        healthUpgrades = new ProgressUpgrades(150, 536, 360, 84, MemoryManager.getHealthLevel(), 16, 16, GameResources.UPGRADES_HEALTH_BAR_IMG_PATH, GameResources.UPGRADES_HEALTH_ICON_IMG_PATH);
        plusButtonDamage = new ButtonView(530, 772, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
        plusButtonFireRate = new ButtonView(530, 660, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
        plusButtonHealth = new ButtonView(530, 548, 64, 64, GameResources.UPGRADES_PLUS_ICON_IMG_PATH);
        scoreText = new TextView(myGdxGame.commonWhiteFont, 360, 870, "Score: " + MemoryManager.getTotalScore());
    }

    @Override
    public void render(float delta) {
        handleInput();

        plusButtonHealth.setEnabled(canBuyHealthUpgrade());
        plusButtonDamage.setEnabled(canBuyDamageUpgrade());
        plusButtonFireRate.setEnabled(canBuyFireRateUpgrade());
        scoreText.setText("Score: " + MemoryManager.getTotalScore());
        scoreText.setPos(GameSettings.SCREEN_WIDTH / 2 - scoreText.getWidth() / 2f, scoreText.getY());

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
        scoreText.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }
    private boolean canBuyHealthUpgrade() {
        return MemoryManager.getHealthLevel() < 5 && GameSettings.UPGRADES_KOFF_COST_UP * healthUpgrades.getUpgradeLevel() < MemoryManager.getTotalScore();
    }

    private boolean canBuyDamageUpgrade() {
        return MemoryManager.getDamageLevel() < 5 && GameSettings.UPGRADES_KOFF_COST_UP * damageUpgrades.getUpgradeLevel() < MemoryManager.getTotalScore();
    }

    private boolean canBuyFireRateUpgrade() {
        return MemoryManager.getFireRateLevel() < 5 && GameSettings.UPGRADES_KOFF_COST_UP * fireRateUpgrades.getUpgradeLevel() < MemoryManager.getTotalScore();
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
            if (plusButtonHealth.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyHealthUpgrade()) {
                    MemoryManager.putHealthLevel();
                    MemoryManager.pickUpTotalScore(GameSettings.UPGRADES_KOFF_COST_UP * healthUpgrades.getUpgradeLevel());
                    healthUpgrades.upgradeLevelUp();
                    if (MemoryManager.getHealthLevel() == 5) {
                        plusButtonHealth.setEnabled(false);
                    }
                }
            }
            if (plusButtonDamage.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyDamageUpgrade()) {
                    MemoryManager.putDamageLevel();
                    MemoryManager.pickUpTotalScore(GameSettings.UPGRADES_KOFF_COST_UP * damageUpgrades.getUpgradeLevel());
                    damageUpgrades.upgradeLevelUp();
                    if (MemoryManager.getDamageLevel() == 5) {
                        plusButtonDamage.setEnabled(false);
                    }
                }
            }
            if (plusButtonFireRate.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyFireRateUpgrade()) {
                    MemoryManager.putFireRateLevel();
                    MemoryManager.pickUpTotalScore(GameSettings.UPGRADES_KOFF_COST_UP * fireRateUpgrades.getUpgradeLevel());
                    fireRateUpgrades.upgradeLevelUp();
                    if (MemoryManager.getFireRateLevel() == 5) {
                        plusButtonFireRate.setEnabled(false);
                    }
                }
            }
        }
    }
    @Override
    public void dispose() {
        backgroundView.dispose();
        blackoutImageView.dispose();
        healthUpgrades.dispose();
        damageUpgrades.dispose();
        fireRateUpgrades.dispose();
        returnButton.dispose();
        plusButtonHealth.dispose();
        plusButtonDamage.dispose();
        plusButtonFireRate.dispose();
        scoreText.dispose();
    }
}
