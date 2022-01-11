package com.programowanie.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Fruit {
    private final GridPoint2 position;
    private final Texture texture;

    public Fruit(Texture texture){
        this.texture = texture;
        this.position = new GridPoint2();
    }

    public void randomLocation(){
        int xPosition = Gdx.graphics.getWidth() / texture.getWidth();
        int yPosition = Gdx.graphics.getHeight() / texture.getHeight();

        this.position.set((int) (Math.random() * xPosition) * texture.getWidth(), (int) (Math.random() * yPosition) * texture.getHeight());
    }

    public void draw(Batch batch){
        batch.draw(texture, position.x, position.y);
    }

    public GridPoint2 getPosition() {
        return this.position;
    }
}
