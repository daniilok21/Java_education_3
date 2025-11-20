package io.github.some_example_name.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.GameResources;

public class ProgressUpgrades extends View {
    private Texture texture;
    private Texture textureBar;
    private Texture textureIcon;
    private int upgradeLevel;
    public final static float barPadding = 3;
    float xBar;
    float yBar;
    float widthBar;
    float heightBar;
    float xIcon;
    float yIcon;


    public ProgressUpgrades(float x, float y, float width, float height, int upgradeLevel, float xIcon, float yIcon, String pathToTextureBar, String pathToTextureIcon) {
        super(x, y);
        texture = new Texture(GameResources.UPGRADES_BACK_BAR_IMG_PATH);
        textureBar = new Texture(pathToTextureBar);
        textureIcon = new Texture(pathToTextureIcon);
        this.width = width;
        this.height = height;
        widthBar = textureBar.getWidth();
        heightBar = textureBar.getHeight();
        this.upgradeLevel = 5;
        xBar = 84;
        yBar = 22; // священные числа
        this.xIcon = xIcon;
        this.yIcon = yIcon;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
        batch.draw(textureIcon, x + xIcon, y + yIcon, textureIcon.getWidth(), textureIcon.getHeight());
        if (upgradeLevel >= 1) batch.draw(textureBar, x + xBar, y + yBar, widthBar, heightBar);
        if (upgradeLevel >= 2) batch.draw(textureBar, x + xBar + (widthBar + barPadding), y + yBar, widthBar, heightBar);
        if (upgradeLevel >= 3) batch.draw(textureBar, x + xBar + 2 * (widthBar + barPadding), y + yBar, widthBar, heightBar);
        if (upgradeLevel >= 4) batch.draw(textureBar, x + xBar + 3 * (widthBar + barPadding), y + yBar, widthBar, heightBar);
        if (upgradeLevel >= 5) batch.draw(textureBar, x + xBar + 4 * (widthBar + barPadding), y + yBar, widthBar, heightBar);
    }

    @Override
    public void dispose() {
        texture.dispose();
        textureBar.dispose();
    }


}
