package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FingerPrintScanner extends Group {
    private final int numberOfCircles = 15;
    private final int ringRadius = 10;
    private float timeBetweenUpdates = 0.3f;
    private float timeSinceUpdate;
    private Image scanningPattern;
    private int width;
    private int height;
    private Color colorPattern;

    public FingerPrintScanner(int width, int height, Color color) {
        Image fingerPrintImage = new Image(new Texture(Gdx.files.internal("Fingerprint.png")));
        scanningPattern = new Image(ScanningPattern.getInstance().createScanningPattern(width,
                height, color,numberOfCircles,ringRadius));
        scanningPattern.setPosition(fingerPrintImage.getWidth()/2 - width/2,
                fingerPrintImage.getHeight()/2 - height/2);
        addActor(fingerPrintImage);
        addActor(scanningPattern);
        this.width = width;
        this.height = height;
        colorPattern = color;
        timeSinceUpdate = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timeSinceUpdate += delta;
        if (timeSinceUpdate >= timeBetweenUpdates) {
            scanningPattern.setDrawable(new TextureRegionDrawable(ScanningPattern.getInstance()
                    .createScanningPattern(width, height, colorPattern, numberOfCircles, ringRadius)));
            timeSinceUpdate = 0;
        }
    }
}
