package com.johannes.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class JonnesGame extends ApplicationAdapter {

	private Stage stage;
	private Skin skin;
	private Commands commands;
	private StateMachine stateMachine;

	
	@Override
	public void create () {

		FileHandle fileHandle = Gdx.files.internal("console.txt");
		commands = new Commands(fileHandle);

		skin = new Skin(Gdx.files.internal("testSkin.json"));

		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		InputMultiplexer inputMultiplexer = new InputMultiplexer(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		stateMachine = new StateMachine(GameStates.INITIALISATION, this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateMachine.update();
		stage.act();
		stage.draw();
	}

	public Stage getStage() { return stage; }

	public Skin getSkin() { return skin; }

	public StateMachine getStateMachine() {
		return stateMachine;
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}

	public Commands getCommands() {
		return commands;
	}
}
