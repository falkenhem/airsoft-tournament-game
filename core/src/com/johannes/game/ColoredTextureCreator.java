package com.johannes.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import net.dermetfan.gdx.physics.box2d.RotationController;
import org.w3c.dom.Text;

public class ColoredTextureCreator {
    private  static ColoredTextureCreator instance;


    public static ColoredTextureCreator getInstance(){
        if (instance == null) {
            instance = new ColoredTextureCreator();
        }

        return instance;
    }

    public Texture createRectangularTexture(int width, int height, Color color){

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public Texture createRoundTexture(int radius, Color color){
        Pixmap pixmap = new Pixmap(2 * radius, 2 * radius, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillCircle(radius,radius,radius);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

}
