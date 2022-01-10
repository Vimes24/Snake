package com.programowanie.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.programowanie.snake.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// podstawowa konfiguracja okna gry (brak możliwości zmiany rozmiaru okna, rozmiar 840x450 pikseli i tytuł)
		config.resizable = false;
		config.width = 840;
		config.height = 450;
		config.title = "Snake!";
		new LwjglApplication(new SnakeGame(), config);
	}
}
