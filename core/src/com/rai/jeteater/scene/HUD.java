package com.rai.jeteater.scene;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.badlogic.gdx.graphics.Color.*;
import static com.rai.jeteater.screen.PlayScreen.finalscore;

/**
 * Created by kumar on 30-09-2017.
 */

public class HUD {
    public Stage stage;
//    Viewport viewport;

    int score=0,life=3;
    float time=30;


    Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
    Label scorevar = new Label("0",style);
    Label lifevar = new Label("3-3",style);
    Label timevar = new Label("100",style);

    public HUD() {

        stage = new Stage();
        Table table = new Table();
        Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);

        Label scoreLabel = new Label("Score",style);
        Label lifeLabel = new Label("LIfe",style);
        Label timeLabel = new Label("Time",style);

        scoreLabel.setFontScale(2);
        lifeLabel.setFontScale(2);
         timeLabel.setFontScale(2);

        table.setFillParent(true);
        table.top();
        table.add(scoreLabel).expandX();
        table.add(lifeLabel).expandX();
        table.add(timeLabel).expandX();

        table.row();
        table.add(scorevar).expandX().size(2);
        table.add(lifevar).expandX();
        table.add(timevar).expandX();


        stage.addActor(table);



    }
    public int addScore(int i){
        score+=i;
        scorevar.setText(String.format("%d",score));
        return score;
    }
    public int kill(){
        life-=1;
        lifevar.setText(String.format("%d",life));
        return life;
    }
    public float timer(){
        time-=0.015;
        if(time>0)

            finalscore=score;
        timevar.setText(String.format("%f",time));
        return time;
    }
public  int returnScore(){
    return score;
}


}
