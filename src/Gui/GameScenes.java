package Gui;

public enum GameScenes {
    MENU, PLAYING, OVERGAME;
    public static GameScenes gameScenes = GameScenes.MENU;
    public static void setGameScenes(GameScenes scene) {
        gameScenes = scene;
    }
}
