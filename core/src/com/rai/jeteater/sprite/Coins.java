package com.rai.jeteater.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.rai.jeteater.jeteater;

/**
 * Created by kumar on 30-09-2017.
 */

public class Coins extends Sprite {
    public Coins() {
        super(new Texture("coins.png"));
        //dispose ko super call kr rha h

            relocate();
    }

    public void relocate() {
        int x= (int)(Math.random() * (jeteater.WIDTH-50));
        int y= (int)(Math.random() * (jeteater.HEIGHT-50));
        setPosition(x,y);
    }

        public boolean checkHit(Jet jet){
            Rectangle jRect = jet.getBoundingRectangle();
            Rectangle cRect = this.getBoundingRectangle();

            if(jRect.overlaps(cRect)) {
                relocate();
                return true;

            }
                else
                    return false;
        }

}
