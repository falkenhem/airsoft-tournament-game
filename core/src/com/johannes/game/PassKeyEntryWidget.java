package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class PassKeyEntryWidget extends Group {
    public TextField getPasskeyInputField() {
        return passkeyInputField;
    }

    public String getPassKey() {
        return passKey;
    }

    private TextField passkeyInputField;
    private int width = 256;
    private int height = 64;

    private String passKey;

    public PassKeyEntryWidget(Skin skin, Vector2 position, String passKey){
        passkeyInputField = new TextField("", skin, "password");
        passkeyInputField.setSize(width,height);
        passkeyInputField.selectAll();
        passkeyInputField.setColor(Color.BLACK);
        this.passKey = passKey;
        Border border = new Border(passkeyInputField,skin.getColor("green"),5);
        addActor(border);
        addActor(passkeyInputField);
        setPosition(position.x - width/2, position.y - height/2);
    }

    public PassKeyEntryWidget(Skin skin, Vector2 position, String passKey, String userInformation){
        this(skin,position,passKey);
        TextBox infoBox = new TextBox(userInformation,skin,"sans-mono-password",skin.getColor("green"));
        infoBox.setPosition(-infoBox.getWidth()/3,100f);
        addActor(infoBox);
    }
}
