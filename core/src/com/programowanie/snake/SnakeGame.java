package com.programowanie.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
/**
 * Glowna klasa aplikacji
 */
public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture fruitImg;
	private Snake snake;
	private Fruit fruit;
	private boolean gameOver;
	/**
	 * metoda wywolywana jednorazowo podczas uruchamiania programu – inicjalizacja gry
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
	 * metoda uruchamiajaca powtornie nowa gre
	 */
	private void startNewGame(){
		snake.newGame();
		fruit.randomLocation();
		gameOver = false;
	}
	/**
	 * metoda wywolywana celem aktualizowania stanu i rysowania swiata gry
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
	 * metoda aktualizujaca stan gry
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
	 * metoda wywolywana, gdy program ma zakonczyc dzialanie, a my powinnismy zwolnic zasoby
	 */
	@Override
	public void dispose () {
		batch.dispose();
		snakeImg.dispose();
		fruitImg.dispose();
	}
}
