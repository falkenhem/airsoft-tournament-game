package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

enum Direction{
    LEFT,
    RIGHT
}

public class TimingGameWidget extends Group implements InputProcessor {
    private int height;
    private int width;
    private int middle;
    private int widthTarget;
    private int heightTarget;
    private int speedMovingLine;
    private Skin skin;
    private final int thicknessBorders = 2;
    private final int lineWidth = 10;
    private Image line;
    private Direction direction;
    private boolean success;


    public TimingGameWidget(int width, int height, int widthTarget, int heightTarget, int speedMovingLine, Skin skin) {
        ((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(this);
        this.height = height;
        this.width = width;
        middle = width/2;
        this.widthTarget = widthTarget;
        this.heightTarget = heightTarget;
        this.speedMovingLine = speedMovingLine;
        this.skin = skin;

        EmptyBoxWithBorder boundingBox = new EmptyBoxWithBorder(width, height,thicknessBorders, Color.BLACK,
                skin.getColor("green"));
        EmptyBoxWithBorder target = new EmptyBoxWithBorder(widthTarget, heightTarget,
                thicknessBorders,Color.BLACK, skin.getColor("green"));
        line = new Image(ColoredTextureCreator.getInstance().createRectangularTexture(lineWidth
                ,height,skin.getColor("green")));
        target.setPosition(width/2 - widthTarget/2, target.getY());
        addActor(boundingBox);
        addActor(target);
        addActor(line);
        setPosition(Gdx.graphics.getWidth()/2 - width/2,Gdx.graphics.getHeight()/2 - height/2);
        direction = Direction.RIGHT;
    }

    public boolean gameCleared(){
        return success;
    }

    private boolean isLineWithinTarget(){
        int lowerBounds = middle - widthTarget/2;
        int upperBounds = middle + widthTarget/2;

        if (line.getX() >= lowerBounds && line.getX() <= upperBounds) {
            return true;
        } else return false;
    }

    @Override
    public void act(float delta) {

        switch (direction){
            case LEFT: line.setX(line.getX() - speedMovingLine); break;
            case RIGHT: line.setX(line.getX() + speedMovingLine); break;
        }

        if (line.getX() >= width - line.getImageWidth()) direction = Direction.LEFT;
        if (line.getX() <= 0.0f) direction = Direction.RIGHT;

        getStage().getKeyboardFocus();

        super.act(delta);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE)
        {
            if (isLineWithinTarget()){
                success = true;
                System.out.println("Success!!");
            } else System.out.println("Fail");
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
