package io.github.some_example_name.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.GameResources;

public class ProgressUpgrades extends View {
    private Texture texture;
    private int upgradeLevel;
    public final static float upgradesPadding = 5;

    public ProgressUpgrades(float x, float y, float width, float height, int upgradeLevel) {
        super(x, y);
        texture = new Texture(GameResources.UPGRADES_BACK_BAR_IMG_PATH);
        this.width = width;
        this.height = height;
        this.upgradeLevel = upgradeLevel;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (upgradeLevel > 1) batch.draw(texture, x, y, width, height);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


}
