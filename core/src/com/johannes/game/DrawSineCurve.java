package com.johannes.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

//Make this a singleton?
public final class DrawSineCurve {
    private static DrawSineCurve instance;
    Pixmap pixmap;
    Texture pixmapTex;
    int[] points = new int[]{0,20,5,-50,38,-22,30,-40,0};
    float sine;

    public static DrawSineCurve getInstance() {
        if(instance == null) {
            instance = new DrawSineCurve();
        }
        return instance;
    }

    public Texture getTexture(){
        pixmap = new Pixmap(128,128, Pixmap.Format.RGBA8888);
        pixmap.setColor(0,0,0,1);
        pixmap.fillRectangle(0,0,128,128);
        pixmap.setColor(0,1,0,1);

        for (int i = 0; i < 8; i +=1){
            pixmap.drawLine(i*16,points[i]+64,(i+1)*16,points[i+1]+64);
        }

        pixmapTex = new Texture(pixmap);
        pixmapTex.setWrap(Texture.TextureWrap.MirroredRepeat,Texture.TextureWrap.MirroredRepeat);
        pixmap.dispose();
        return pixmapTex;
    }
}
