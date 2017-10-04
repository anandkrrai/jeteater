package com.rai.jeteater.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.rai.jeteater.jeteater;

/**
 * Created by kumar on 30-09-2017.
 */

public class Bomb extends Sprite {
    public Bomb() {
        super(new Texture("bomb.png"));
        //dispose ko super call kr rha h

            relocate();
    }

    public void relocate() {
        int x= (int)(Math.random() * jeteater.WIDTH);
        int y= (int)(Math.random() * jeteater.HEIGHT);
        setPosition(x,y);
    }

        public boolean checkHit(Jet jet){
            Rectangle jRect = jet.getBoundingRectangle();
            Rectangle bRect = this.getBoundingRectangle();

            if(jRect.overlaps(bRect)) {
                relocate();
                return true;

            }
                else
                    return false;
        }

}
