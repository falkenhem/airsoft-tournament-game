package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;


public class RotatingTimingGame extends Group implements InputProcessor {
    private Array<ImageOfDial> dials = new Array<>();
    private HashMap<ImageOfDial,EmptyBoxWithBorder> dialsAndTargets = new HashMap<>();
    private int activeDial;
    private boolean success;
    private Skin skin;
    private final float spacing = 2.4f;

    public RotatingTimingGame(Skin skin, int radius){
        ((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(this);
        FingerPrintScanner fingerPrintScanner = new FingerPrintScanner(260,
                500, skin.getColor("red"));
        fingerPrintScanner.setPosition(3.5f*radius,0);
        addActor(fingerPrintScanner);
        for (int i = 0; i<3 ; i++){
            ImageOfDial imageOfDial = new ImageOfDial(radius, skin.getColor("transparent"),
                    skin.getColor("green"), skin, i*2 + 1);
            imageOfDial.setPosition(0,spacing*radius*i);
            addActor(imageOfDial);
            dials.add(imageOfDial);
            EmptyBoxWithBorder imageTarget = new EmptyBoxWithBorder(radius, radius/10, 0,
                    skin.getColor("red"), skin.getColor("black"));
            imageTarget.setPosition(2 *radius + 10, spacing*radius*i + radius -
                    imageOfDial.getHeight()/2 - radius/20);
            addActor(imageTarget);
            dialsAndTargets.put(imageOfDial, imageTarget);
            setPosition(Gdx.graphics.getWidth()/2 - radius, radius);
            activeDial = 0;
            success = false;
        }
        this.skin = skin;
    }

    public boolean gameCleared(){return success;}

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            if (activeDial < dials.size) {
                if (dials.get(activeDial).pointingAtTarget()) {
                    dials.get(activeDial).setSpeedRotation(0);
                    dials.get(activeDial).setRotation(180);
                    EmptyBoxWithBorder target = dialsAndTargets.get(dials.get(activeDial));
                    target.changeColor(skin.getColor("green"));
                    activeDial += 1;
                    if (activeDial==dials.size) success = true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
