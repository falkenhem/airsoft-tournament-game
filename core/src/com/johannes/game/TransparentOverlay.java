package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TransparentOverlay extends Image {
    public TransparentOverlay(Color color){
        super(ColoredTextureCreator.getInstance().createRectangularTexture(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), color));
    }

}
