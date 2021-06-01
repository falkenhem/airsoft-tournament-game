package com.johannes.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import java.util.Scanner;

public class Commands implements CommandLines{
    Array<String> commandStructure;
    int currentLine;

    Commands(FileHandle fileHandle){
        commandStructure = new Array<>();
        currentLine = 0;
        String text = fileHandle.readString();
        commandStructure = stringToArray(text);
    }

    public String getCurrentCommand(){
        return commandStructure.get(currentLine);
    }

    public void iterateCommands(){
        if (currentLine < commandStructure.size - 1) {
            currentLine += 1;
        } else currentLine = 1;
    }

    private Array<String> stringToArray(String string) {
        Scanner scanner = new Scanner(string);
        Array<String> array = new Array<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            array.add(line);
        }
        return array;
    }
}