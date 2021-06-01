package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CameraWidget extends Group{
    @Override
    public float getWidth() {
        return width;
    }

    float width;

    public CameraWidget(Skin skin, String name, Texture cameraTexture){
        super();

        Image cameraImage = new Image(cameraTexture);
        width = cameraTexture.getWidth();
        Border borderCameraImage = new Border(cameraImage,skin.getColor("green"),5);
        ScrollingTerminal scrollingTerminal = new ScrollingTerminal(skin,new RandomBinaryGenerator(30),
                (int)cameraImage.getWidth(),128,0.3f,6);
        scrollingTerminal.setPosition(0,0);
        TextBox recordingTextBox = new TextBox("REC",skin,"default-font"
                , skin.getColor("red"), cameraImage,Alignment.BOTTOMRIGHT);
        TextBox cameraNameTextBox = new TextBox(name,skin,"default-font",
                skin.getColor("green"), cameraImage, Alignment.TOPLEFT);
        addActor(borderCameraImage);
        addActor(cameraImage);
        addActor(scrollingTerminal);
        addActor(recordingTextBox);
        addActor(cameraNameTextBox);
    }

}
