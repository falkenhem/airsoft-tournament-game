package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ScanningPattern {
    private static ScanningPattern instance;

    public static ScanningPattern getInstance(){
        if (instance == null) {
            instance = new ScanningPattern();
        }
        return instance;
    }

    public Texture createScanningPattern(int width, int height, Color color, int numberOfCircles, int radius){
        int prevPosX = 0;
        int prevPosY = 0;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);

        for (int i = 0; i<numberOfCircles; i++){
            int posX = MathUtils.random(0,width - radius);
            int posY = MathUtils.random(0,height - radius);
            pixmap.fillCircle(posX, posY, radius);
            if (i>0) pixmap.drawLine(posX, posY, prevPosX, prevPosY);
            prevPosX = posX;
            prevPosY = posY;
        }
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

}
