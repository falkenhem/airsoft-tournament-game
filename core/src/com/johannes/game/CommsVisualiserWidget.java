package com.johannes.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CommsVisualiserWidget extends Group {
    public CommsVisualiserWidget(Skin skin, Vector2 position) {
        super();
        TextureRegion textureRegion = new TextureRegion(DrawSineCurve.getInstance().getTexture());
        textureRegion.setRegion(0,0,textureRegion.getTexture().getWidth()*4,textureRegion.getTexture().getHeight());
        ScrollingTexture scrollingTexture = new ScrollingTexture(textureRegion);
        scrollingTexture.setSize(512,128);
        Border border = new Border(scrollingTexture, skin.getColor("green"), 5);
        setPosition(position.x, position.y);
        addActor(border);
        addActor(scrollingTexture);
        setScale(0.5f);
    }
}
