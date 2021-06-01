package com.johannes.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ScrollingTexture extends Image {
    int scroll;
    int originX, originY,rotation,srcX,srcY;
    float x,y,width, height,scaleX,scaleY;
    boolean flipX,flipY;
    TextureRegion textureRegion;

    public ScrollingTexture(TextureRegion textureRegion){
        super();
        this.textureRegion= textureRegion;
        x = y = originX = originY = rotation = srcY = 0;
        scaleX = scaleY = 1;
        flipX = flipY = false;
        scroll = 0;
        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        scroll+=3;
        srcX = scroll;
        batch.draw(textureRegion.getTexture(), getX(), getY(), originX, originY, textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),scaleX,scaleY,rotation,
                srcX,srcY,textureRegion.getRegionWidth(),textureRegion.getTexture().getHeight(),flipX,flipY);

    }
}
