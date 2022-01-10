package com.programowanie.snake;

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
        elapsedTime += deltaTime;

        if(elapsedTime >= 0.1){
            elapsedTime = 0;
        }

        for (int i = snakeElement.size() - 1; i > 0; i--) {
            snakeElement.get(i).set(snakeElement.get(i - 1));
        }

        GridPoint2 head = snakeElement.get(0);

        switch (snakeDirection){
            case RIGHT:
                head.x += texture.getWidth();
                break;
            case LEFT:
                head.x -= texture.getWidth();
                break;
            case UP:
                head.y += texture.getHeight();
                break;
            case DOWN:
                head.y -= texture.getHeight();
                break;
        }
    }

    public void draw(Batch batch){
        for(GridPoint2 pos : snakeElement){
            batch.draw(texture, pos.x, pos.y);
        }
    }

}
