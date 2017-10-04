package com.rai.jeteater.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.rai.jeteater.scene.HUD;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.rai.jeteater.screen.PlayScreen.finalscore;

public class gameOver  {
    SpriteBatch batch;
    HUD hud;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;
    public int option=0;
//1 replay, -1 exit

    public Stage stage;
    Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
    String message="Game over!!!!\nYour score is:";


    public gameOver() {
this.hud=hud;
        stage = new Stage();
//        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//        Button button2 = new TextButton("Text Button",mySkin,"small");
//        button2.setSize(col_width*4,row_height);
//        button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
//        button2.addListener(new InputListener(){
//            @Override
//            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                outputLabel.setText("Press a Button");
//            }
//            @Override
//            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                outputLabel.setText("Pressed Text Button");
//                return true;
//            }
//        });
//        stage.addActor(button2);

        message="Game over!!!!\nYour score is:"+ finalscore + "\nREPLAY??";
        Table table = new Table();
        Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
        Label over = new Label(message,style);
        Label yes = new Label("TILT BOTTOM RIGHT \n PLAY AGAIN,\n ",style);
        Label no = new Label("TILT TOP LEFT \n  TO EXIT\n",style);
        no.setFontScale(5);
        yes.setFontScale(5);
        over.setFontScale(5);
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
