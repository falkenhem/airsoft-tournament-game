package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class EmptyBoxWithBorder extends Group {
    private Image body;
    private int width;
    private int height;

    public EmptyBoxWithBorder(int width, int height, int thicknessBorders, Color color, Color borderColor){
        body = new Image(ColoredTextureCreator.getInstance().createRectangularTexture(width,
                height,color));
        Border border = new Border(body,borderColor,thicknessBorders);
        addActor(border);
        addActor(body);
        this.width = width;
        this.height = height;
    }

    public EmptyBoxWithBorder(int width, int height, int thicknessBorders, Color color
            , Color borderColor, Widget parentWidget){
        this(width, height, thicknessBorders, color, borderColor);
        float posX = parentWidget.getX();
        float posY = parentWidget.getY();
        if (posX>=0) {
            posX -= thicknessBorders;
        } else posX += thicknessBorders;
        if (posY>0) {
            posY -= thicknessBorders;
        } else posY += thicknessBorders;
        setPosition(posX, posY);
    }

    public void changeColor(Color color){
        body.setDrawable(new TextureRegionDrawable(ColoredTextureCreator.getInstance().createRectangularTexture(width, height, color)));
    }
}
