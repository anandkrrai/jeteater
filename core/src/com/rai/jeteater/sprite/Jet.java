package com.rai.jeteater.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kumar on 30-09-2017.
 */

public class Jet extends Sprite {
    public Jet() {
        super(new Texture("jet.png"));
        //dispose ko super call kr rha h

        setRotation(90);
    }

    public void setGoal(int x, int y) {
        Vector2 source = new Vector2(getX(),getY());
        //getx se sprite ki location & get.inputx se mouse ki
        Vector2 dest = new Vector2(x,y);
        Vector2 path= dest.sub(source);

        int pathx= (int)(getX()+path.x/50);
        int pathy= (int)(getY()+path.y/50);
        setPosition(pathx,pathy);
        setRotation(path.angle());
    }
}
