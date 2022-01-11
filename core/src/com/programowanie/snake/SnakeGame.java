package com.programowanie.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Główna klasa aplikacji
 */

public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture fruitImg;
	private Snake snake;
	private Fruit fruit;
	private boolean gameOver;

	/**
	 * metoda wywoływana jednorazowo podczas uruchamiania programu – inicjalizacja gry
 	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		snakeImg = new Texture("snake.png");
		fruitImg = new Texture("fruit.png");
		snake = new Snake(snakeImg);
		fruit = new Fruit(fruitImg);
		startNewGame();
	}

	/**
	 * metoda uruchamiająca powtórnie nową grę
	 */
	private void startNewGame(){
		snake.newGame();
		fruit.randomLocation();
		gameOver = false;
	}

	/**
	 * metoda wywoływana celem aktualizowania stanu i rysowania świata gry
	 */
	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		fruit.draw(batch);
		snake.draw(batch);
		batch.end();
	}

	/**
	 * metoda aktualizująca stan gry
	 */
	private void update(){
		if(!gameOver){
			snake.act(Gdx.graphics.getDeltaTime());
			if(snake.isFruitFound(fruit.getPosition())){
				snake.extendSnake();
				fruit.randomLocation();
			}
			if(snake.hasHit()){
				gameOver = true;
			}
		}
		else{
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
				startNewGame();
			}
		}
	}

	/**
	 * metoda wywoływana, gdy program ma zakończyć działanie, a my powinniśmy zwolnić zasoby
	 */
	@Override
	public void dispose () {
		batch.dispose();
		snakeImg.dispose();
		fruitImg.dispose();
	}
}
