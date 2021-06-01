package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import org.w3c.dom.Text;

public enum GameStates implements State {

    INITIALISATION() {
        @Override
        public void enter(JonnesGame game) {
            ScrollingTerminal scrollingTerminal = new ScrollingTerminal(game.getSkin(), game.getCommands(),
                    Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight(),0.05f,1000);
            game.getStage().addActor(scrollingTerminal);
        }

        @Override
        public void update(JonnesGame game) {
            if (game.getCommands().getCurrentCommand().equals("<R>SECTION 1 END"))  {
                game.getStateMachine().changeState(ENTERFIRSTCODE);
            }

        }

        @Override
        public void exit(JonnesGame game) {

        }
    },

    ENTERFIRSTCODE() {
        PassKeyEntryWidget passKeyEntryWidget;
        TransparentOverlay transparentOverlay;

        @Override
        public void enter(JonnesGame game) {
            Array<Actor> actors = game.getStage().getActors();
            for (Actor actor : actors){
                if (actor instanceof Pausable) ((Pausable) actor).setPause(true);
            }
            passKeyEntryWidget = new PassKeyEntryWidget(game.getSkin(),
                    new Vector2(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f),"999",
                    "Enter passkey ALPHA to access camera feed");
            transparentOverlay = new TransparentOverlay(game.getSkin().getColor("transparentBlack"));
            game.getStage().addActor(transparentOverlay);
            game.getStage().addActor(passKeyEntryWidget);
            game.getStage().setKeyboardFocus(passKeyEntryWidget.getPasskeyInputField());

        }

        @Override
        public void update(JonnesGame game) {
            if (passKeyEntryWidget.getPasskeyInputField().getText().equals(passKeyEntryWidget.getPassKey())) {
                game.getStateMachine().changeState(LAUNCHTIMINGGAME);
            }

        }

        @Override
        public void exit(JonnesGame game) {
            passKeyEntryWidget.remove();
            transparentOverlay.remove();
        }
    },

    ENTERSECONDCODE() {
        PassKeyEntryWidget passKeyEntryWidget;
        TransparentOverlay transparentOverlay;

        @Override
        public void enter(JonnesGame game) {
            Array<Actor> actors = game.getStage().getActors();
            for (Actor actor : actors){
                if (actor instanceof Pausable) ((Pausable) actor).setPause(true);
            }
            passKeyEntryWidget = new PassKeyEntryWidget(game.getSkin(),
                    new Vector2(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f),"999",
                    "Enter passkey BETA to re-route communication");
            transparentOverlay = new TransparentOverlay(game.getSkin().getColor("transparentBlack"));
            game.getStage().addActor(transparentOverlay);
            game.getStage().addActor(passKeyEntryWidget);
            game.getStage().setKeyboardFocus(passKeyEntryWidget.getPasskeyInputField());

        }

        @Override
        public void update(JonnesGame game) {
            if (passKeyEntryWidget.getPasskeyInputField().getText().equals(passKeyEntryWidget.getPassKey())) {
                game.getStateMachine().changeState(ROTATINGTIMINGGAME);
            }

        }

        @Override
        public void exit(JonnesGame game) {
            passKeyEntryWidget.remove();
            transparentOverlay.remove();
        }
    },

    LAUNCHTIMINGGAME(){
        TimingGameWidget timingGameWidget;
        TransparentOverlay transparentOverlay;
        TextBox userInformation;

        @Override
        public void enter(JonnesGame game) {
            transparentOverlay = new TransparentOverlay(game.getSkin().getColor("transparentBlack"));
            timingGameWidget = new TimingGameWidget(512,128,
                    64,128,5,game.getSkin());
            userInformation = new TextBox("Manual timing-optimization needed", game.getSkin(),
                    "sans-mono-password",game.getSkin().getColor("red"));
            userInformation.setPosition(300,Gdx.graphics.getHeight()/1.6f);
            game.getStage().addActor(transparentOverlay);
            game.getStage().addActor(userInformation);
            game.getStage().addActor(timingGameWidget);
        }

        @Override
        public void update(JonnesGame game) {
            if (timingGameWidget.gameCleared()) game.getStateMachine().changeState(STARTCAMERAFEED);
        }

        @Override
        public void exit(JonnesGame game) {
            timingGameWidget.remove();
            transparentOverlay.remove();
            userInformation.remove();

        }
    },

    STARTCAMERAFEED(){
        @Override
        public void enter(JonnesGame game) {
            Array<Actor> actors = game.getStage().getActors();
            for (Actor actor : actors){
                if (actor instanceof Pausable) ((Pausable) actor).setPause(false);
            }

            Table table = new Table();
            table.setPosition(Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/2);
            for (int i = 1 ; i <= 2; i++) {
                CameraWidget camera = new CameraWidget(game.getSkin(), "Camera " + i,
                        new Texture(Gdx.files.internal("camera" + i + ".jpg")));
                table.add(camera).width(camera.getWidth() + 20);
            }
            /*CameraWidget camera = new CameraWidget(game.getSkin(), "Camera 3",
                    new Texture(Gdx.files.internal("camera3" + ".jpg")));
            table.row();
            table.add(camera);*/

            game.getStage().addActor(table);
            game.getCommands().iterateCommands();
        }

        @Override
        public void update(JonnesGame game) {
            if (game.getCommands().getCurrentCommand().equals("<R>SECTION 1 END"))  {
                game.getStateMachine().changeState(ENTERSECONDCODE);
            }
        }

        @Override
        public void exit(JonnesGame game) {

        }
    },

    ROTATINGTIMINGGAME(){
    private RotatingTimingGame rotatingTimingGame;
    private TransparentOverlay transparentOverlay;
    private TextBox userInformation;

        @Override
        public void enter(JonnesGame game) {
            transparentOverlay = new TransparentOverlay(game.getSkin().getColor("transparentBlack"));
            rotatingTimingGame = new RotatingTimingGame(game.getSkin(), 60);
            userInformation = new TextBox("Biometrics manual override", game.getSkin(),
                    "sans-mono-password",game.getSkin().getColor("red"));
            userInformation.setPosition(30,Gdx.graphics.getHeight()/1.4f);
            game.getStage().addActor(transparentOverlay);
            game.getStage().addActor(userInformation);
            game.getStage().addActor(rotatingTimingGame);
        }

        @Override
        public void update(JonnesGame game) {
            if (rotatingTimingGame.gameCleared()) game.getStateMachine().changeState(STARTCAMERAFEED);
        }

        @Override
        public void exit(JonnesGame game) {
            rotatingTimingGame.remove();
            transparentOverlay.remove();
            userInformation.remove();
        }
    }

}
