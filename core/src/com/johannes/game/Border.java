package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class Border extends Image {
    private Widget widget;
    private Color color;

    public Border(Widget widget, Color color, int thicknessBorders){
        super(ColoredTextureCreator.getInstance().createRectangularTexture((int)widget.getWidth() + 2*thicknessBorders,
                (int)widget.getHeight() + 2*thicknessBorders,color));
        this.widget = widget;
        this.color = color;
        setPosition(-thicknessBorders + widget.getX(),-thicknessBorders + widget.getY());

    }


    public Widget getWidget() {
        return widget;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
