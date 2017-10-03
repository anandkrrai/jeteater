package com.rai.jeteater.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
            if(Gdx.input.isKeyPressed(Input.Keys.Y))
                {
                    some.ShowInterstetial();
                hud=new HUD();
                isActive=true;
            }else if(Gdx.input.isKeyPressed(Input.Keys.E))
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
                kill=hud.kill();
            }
        }
        for(Coins coin:coins){
           if(coin.checkHit(jet)){
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
}
