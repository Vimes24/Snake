package com.programowanie.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

enum Direction {RIGHT, LEFT, UP, DOWN}

public class Snake {
    private final Texture texture;
    private final List<GridPoint2> snakeElement;
    private Direction snakeDirection;
    private float elapsedTime;

    public Snake(Texture texture){
        this.texture = texture;
        snakeDirection = Direction.RIGHT;
        snakeElement = new ArrayList<>();
        snakeElement.add(new GridPoint2(90, 30));
        snakeElement.add(new GridPoint2(75, 30));
        snakeElement.add(new GridPoint2(60, 30));
        snakeElement.add(new GridPoint2(45, 30));
        snakeElement.add(new GridPoint2(30, 30));
    }

    public void act(float deltaTime){
        directionChange();
        elapsedTime += deltaTime;

        if(elapsedTime >= 0.1){
            elapsedTime = 0;
            move();
        }
    }

    private void move(){
        for (int i = snakeElement.size() - 1; i > 0; i--) {
            snakeElement.get(i).set(snakeElement.get(i - 1));
        }

        int elementWidth = texture.getWidth();
        int elementHeight = texture.getWidth();

        int lastElementX = Gdx.graphics.getWidth() - elementWidth;
        int lastElementY = Gdx.graphics.getHeight() - elementHeight;

        GridPoint2 head = snakeElement.get(0);

        switch (snakeDirection){
            case RIGHT:
                head.x = (head.x == lastElementX) ? 0 : head.x + elementWidth;
                break;
            case LEFT:
                head.x = (head.x == 0) ? lastElementX : head.x - elementWidth;
                break;
            case UP:
                head.y = (head.y == lastElementY) ? 0 : head.y + elementHeight;
                break;
            case DOWN:
                head.y = (head.y == 0) ? lastElementY : head.y - elementHeight;
                break;
        }
    }

    private void directionChange(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snakeDirection != Direction.RIGHT){
            snakeDirection = Direction.LEFT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snakeDirection != Direction.LEFT){
            snakeDirection = Direction.RIGHT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && snakeDirection != Direction.DOWN){
            snakeDirection = Direction.UP;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snakeDirection != Direction.UP){
            snakeDirection = Direction.DOWN;
        }
    }

    public void draw(Batch batch){
        for(GridPoint2 pos : snakeElement){
            batch.draw(texture, pos.x, pos.y);
        }
    }
}
