package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

enum Alignment{
    TOPLEFT,
    TOPRIGHT,
    BOTTOMLEFT,
    BOTTOMRIGHT,
}

public class TextBox extends Label {
    public TextBox(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
    }
    public TextBox(CharSequence text, Skin skin){
        super(text,skin);
    }
    public TextBox(CharSequence text, Skin skin, String fontName, String colorName){
        super(text,skin,fontName,colorName);
    }

    public TextBox(CharSequence text, Skin skin, String fontName, Color color, Widget parent, Alignment alignment){
        this(text,skin,fontName,color);

        switch (alignment){
            case TOPLEFT: setPosition(parent.getX(), parent.getY() + parent.getHeight() - this.getHeight()); break;
            case BOTTOMRIGHT: setPosition(parent.getX() + parent.getWidth() - this.getWidth(), parent.getY()); break;
        }

    }
}
