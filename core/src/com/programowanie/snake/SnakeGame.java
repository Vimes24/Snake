package com.programowanie.snake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture fruitImg;
	private Snake snake;
	private Fruit fruit;
	private boolean gameOver;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		snakeImg = new Texture("snake.png");
		fruitImg = new Texture("fruit.png");
		snake = new Snake(snakeImg);
		fruit = new Fruit(fruitImg);
		startNewGame();
	}

	private void startNewGame(){
		snake.newGame();
		fruit.randomLocation();
		gameOver = false;
	}

	@Override
	public void render () {
		update();
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		fruit.draw(batch);
		snake.draw(batch);
		batch.end();
	}

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
	
	@Override
	public void dispose () {
		batch.dispose();
		snakeImg.dispose();
		fruitImg.dispose();
	}
}
