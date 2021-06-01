package com.johannes.game;

import com.badlogic.gdx.math.MathUtils;

public class RandomBinaryGenerator implements CommandLines{
    private int length;
    private String currentLine;

    public RandomBinaryGenerator(int length) {
        this.length = length;
        currentLine = generateNewLine();
    }

    private String generateNewLine(){
        String line = new String();

        for (int i = 1 ; i<=length ; i++){
            line += MathUtils.random(0, 1);
        }
        System.out.println(line);
        return line;
    }

    @Override
    public String getCurrentCommand() {
        return currentLine;
    }

    @Override
    public void iterateCommands() {
        currentLine = generateNewLine();
    }
}
