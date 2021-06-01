package com.johannes.game;

import com.badlogic.gdx.Game;

public interface State {
    public void enter(JonnesGame game);
    public void update(JonnesGame game);
    public void exit(JonnesGame game);
}
