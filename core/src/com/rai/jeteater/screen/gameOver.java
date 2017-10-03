package com.rai.jeteater.screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rai.jeteater.scene.HUD;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.rai.jeteater.screen.PlayScreen.finalscore;

public class gameOver  {
    SpriteBatch batch;
    HUD hud;
    public int option=0;
//1 replay, -1 exit

    public Stage stage;
    Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
    String message="Game over!!!!\nYour score is:";


    public gameOver() {
this.hud=hud;
        stage = new Stage();
        message="Game over!!!!\nYour score is:"+ finalscore + "\nREPLAY??";
        Table table = new Table();
        Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
        Label over = new Label(message,style);
        Label yes = new Label("TO PLAY AGAIN,\n PRESS Y",style);
        Label no = new Label("TO EXIT\n PRESS E",style);
        table.setFillParent(true);
        table.center();
        table.add(over ).expandX();

        table.add(yes).expandX();
        table.add(no).expandX();

        table.row();
        table.add(over).expandX();
        table.add(yes).expandX();
        table.add(no).expandX();
       stage.addActor(table);
    }


}
