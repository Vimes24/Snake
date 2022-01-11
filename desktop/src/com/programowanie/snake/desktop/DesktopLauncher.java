package com.programowanie.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.programowanie.snake.SnakeGame;

/**
 * Podstawowa klasa wygenerowana przez użycie LibGDX
 * podstawowa konfiguracja okna gry (brak możliwości zmiany rozmiaru okna, zadany rozmiar pikseli i tytuł)
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 420;
		config.height = 225;
		config.title = "Snake!";
		new LwjglApplication(new SnakeGame(), config);
	}
}
