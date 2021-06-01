package com.johannes.game;

public class StateMachine {
    GameStates currentState;
    JonnesGame game;

    public StateMachine(GameStates initialState, JonnesGame game){
        this.currentState = initialState;
        this.game = game;
        initialState.enter(game);
    }

    public void changeState(GameStates newState){
        currentState.exit(game);
        newState.enter(game);
        currentState = newState;
    }

    public void update(){
        currentState.update(game);
    }
}
