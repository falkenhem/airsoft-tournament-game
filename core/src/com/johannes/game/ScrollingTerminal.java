package com.johannes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class ScrollingTerminal extends TextBox implements Pausable{
    private float timeSinceLastCommand;
    private float  timeBetweenCommands;
    private int maxNumberOfLines;
    private String text;
    private CommandLines commands;
    private boolean pause;
    private Array<String> allCommandsDisplayed = new Array<>();


    public ScrollingTerminal(Skin skin, CommandLines commands, int width, int height) {
        this(skin, commands, width, height,1,1000);
    }

    public ScrollingTerminal(Skin skin, CommandLines commands,
                             int width, int height, float timeBetweenCommands, int maxNumberOfLines) {
        super("1", skin, "default-font", "green");
        this.timeBetweenCommands = timeBetweenCommands;
        this.maxNumberOfLines = maxNumberOfLines;
        setHeight(height);
        setWidth(width);
        setAlignment(Align.bottomLeft);
        timeSinceLastCommand = 0;
        this.commands = commands;
        pause = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        String newText = "";

        if (!pause) {
            timeSinceLastCommand += delta;

            if (timeSinceLastCommand >= timeBetweenCommands) {
                commands.iterateCommands();
                allCommandsDisplayed.add(commands.getCurrentCommand());
                if (allCommandsDisplayed.size > maxNumberOfLines) allCommandsDisplayed.removeIndex(0);
                for (String string : allCommandsDisplayed) {
                    newText += System.lineSeparator() + string;
                }
                text = newText;

                setText(text);
                timeSinceLastCommand = 0;
            }
        }
    }


    public void setPause(boolean pause) {
        this.pause = pause;
    }

}
