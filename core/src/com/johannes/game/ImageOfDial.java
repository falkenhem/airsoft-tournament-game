package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ImageOfDial extends Group {
    private float speedRotation;
    private boolean success;

    ImageOfDial(int radius, Color colorBody, Color colorMarker, Skin skin, float speedRotation){
        Image circleImage = new Image(ColoredTextureCreator.getInstance().createRoundTexture(radius, colorBody));
        Image markerImage = new Image(ColoredTextureCreator.getInstance().createRectangularTexture(
                radius, radius/10, colorMarker));
        addActor(circleImage);
        addActor(markerImage);
        markerImage.setPosition(+2,radius - markerImage.getHeight()/2);
        setOrigin(radius,radius);
        this.speedRotation = speedRotation;
        success = false;
    }

    public void setSpeedRotation(float speedRotation) {
        this.speedRotation = speedRotation;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getRotation() <= -360) setRotation(0);
        setRotation(getRotation() - speedRotation);
    }

    public boolean pointingAtTarget(){
        if (Math.abs(getRotation() + 180) <= 10){
            return true;
        } else return false;
    }
}
