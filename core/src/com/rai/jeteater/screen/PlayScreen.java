package com.rai.jeteater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rai.jeteater.AdService;
import com.rai.jeteater.jeteater;
import com.rai.jeteater.scene.HUD;
import com.rai.jeteater.sprite.Bomb;
import com.rai.jeteater.sprite.Coins;
import com.rai.jeteater.sprite.Jet;
import java.util.LinkedList;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.rai.jeteater.jeteater.batch;

/**
 * Created by kumar on 30-09-2017.
 */

public class PlayScreen implements Screen {

    private Jet jet;
    private HUD hud;
    private gameOver gameover;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private boolean isActive = true;
    private LinkedList<Coins> coins = new LinkedList();
    private LinkedList<Bomb> bomb = new LinkedList();
    TextureRegion backgroundTexture;
    public static int finalscore;
    public static AdService some;

    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;

    public PlayScreen(AdService some) {
        this.some=some;
        jet = new Jet();
        hud = new HUD();
        gamecam = new OrthographicCamera(jeteater.WIDTH, jeteater.HEIGHT);
        gamecam.translate(jeteater.WIDTH / 2, jeteater.HEIGHT / 2);
        backgroundTexture = new TextureRegion(new Texture("wall.png"), 0, 0, 1024, 563);
        viewport = new StretchViewport(jeteater.WIDTH, jeteater.WIDTH, gamecam);

        for (int i = 0; i < 4; ++i) {
            coins.add(new Coins());
        }
        for (int i = 0; i < 6; ++i) {
            bomb.add(new Bomb());
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        some.toggleBanner();


        update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);    //clear screen
        //matrix for hud
        if(isActive){

        //matrix for content
        jeteater.batch.setProjectionMatrix(gamecam.combined);

        jeteater.batch.begin();
        batch.draw(backgroundTexture, 0, 0);

        for (Bomb bom : bomb) {
            bom.draw(jeteater.batch);
        }
        for (Coins coin : coins) {
            coin.draw(jeteater.batch);
        }
        jet.draw(jeteater.batch);

        jeteater.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();


        jeteater.batch.end();
    }else{
            gameover = new gameOver();
            jeteater.batch.setProjectionMatrix(gamecam.combined);
            jeteater.batch.begin();
            batch.draw(backgroundTexture, 0, 0);
            jet.draw(jeteater.batch);
            jeteater.batch.setProjectionMatrix(gameover.stage.getCamera().combined);
            gameover.stage.draw();
            jeteater.batch.end();
            some.toggleBanner();

            //adding button--------------------

//            Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//            Button button2 = new TextButton("Text Button",mySkin,"small");
//            button2.setSize(col_width*4,row_height);
//            button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
//            button2.addListener(new InputListener(){
//                @Override
//                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                    outputLabel.setText("Press a Button");
//                }
//                @Override
//                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                    outputLabel.setText("Pressed Text Button");
//                    return true;
//                }
//            });
//            stage.addActor(button2);

            //-----------------------Gdx.input.getY()==Gdx.graphics.getHeight()/2
            int shifty= (int) Gdx.input.getAccelerometerX();
            int shiftx= (int) Gdx.input.getAccelerometerY();
            if(shifty>0&&shiftx>0)
                {
                    some.ShowInterstetial();

                    hud=new HUD();
                isActive=true;

            }else if(shifty<0&&shiftx<0)
                {
                System.exit(1);
            }
        }

}




    private void update() {
        handleInput();
        handleEvent();
        gamecam.update();
    }

    private void handleEvent() {
        int kill=99,score;
        for(Bomb bom:bomb){

            if(bom.checkHit(jet)){
                for(Bomb bom1:bomb)
                    bom1.relocate();
                for(Coins coin1:coins)
                    coin1.relocate();
                kill=hud.kill();
            }
        }
        for(Coins coin:coins){
           if(coin.checkHit(jet)){
               for(Coins coin1:coins)
                   coin1.relocate();
               for(Bomb bom1:bomb)
                   bom1.relocate();
               score=hud.addScore(10);
           }
        }
       float time= hud.timer();

        if(time<=0||kill==0){
//            finalscore=hud.returnScore();
           isActive=false;
        }

    }

    private void exitScreen() {

    }

    private void handleInput() {
        Vector3 vector = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
    gamecam.unproject(vector);
        jet.setGoal((int)(vector.x),(int)(vector.y));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {
        isActive =false;

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
//
//    public class gameOver  {
//        SpriteBatch batch;
//        HUD hud;
//        int row_height = Gdx.graphics.getWidth() / 12;
//        int col_width = Gdx.graphics.getWidth() / 12;
//        private Label outputLabel;
//        public int option=0;
////1 replay, -1 exit
//
//        public Stage stage;
//        Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
//        String message="Game over!!!!\nYour score is:";
//
//
//        public gameOver() {
//            this.hud=hud;
//            stage = new Stage();
//            Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//            mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//            Button button2 = new TextButton("Text Button",mySkin,"small");
//            button2.setSize(col_width*4,row_height);
//            button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
//            button2.addListener(new InputListener(){
//                @Override
//                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//                    outputLabel.setText("Press a Button");
//                }
//                @Override
//                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                    outputLabel.setText("Pressed Text Button");
//                    return true;
//                }
//            });
//            stage.addActor(button2);
//
//            message="Game over!!!!\nYour score is:"+ finalscore + "\nREPLAY??";
//            Table table = new Table();
//            Label.LabelStyle style =new Label.LabelStyle(new BitmapFont(), WHITE);
//            Label over = new Label(message,style);
//            Label yes = new Label("TO PLAY AGAIN,\n PRESS Y",style);
//            Label no = new Label("TO EXIT\n PRESS E",style);
//            table.setFillParent(true);
//            table.center();
//            table.add(over ).expandX();
//
//            table.add(yes).expandX();
//            table.add(no).expandX();
//
//            table.row();
//            table.add(over).expandX();
//            table.add(yes).expandX();
//            table.add(no).expandX();
//            stage.addActor(table);
//        }
//
//
//    }

}
