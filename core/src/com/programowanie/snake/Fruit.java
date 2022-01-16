package com.programowanie.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

/**
 * klasa generujaca obiekt owocu zjadanego przez weza
 */
public class Fruit {
    private final GridPoint2 position;
    private final Texture texture;

    /**
     * Konstruktor tworzacy obiekt
     * @param texture wykorzystuje asset w formie zdjecia do zobrazowania weza
     */
    public Fruit(Texture texture){
        this.texture = texture;
        this.position = new GridPoint2();
    }

    /**
     * metoda generujaca owoc w losowym miejscu planszy
     */
    public void randomLocation(){
        int xPosition = Gdx.graphics.getWidth() / texture.getWidth();
        int yPosition = Gdx.graphics.getHeight() / texture.getHeight();

        this.position.set((int) (Math.random() * xPosition) * texture.getWidth(), (int) (Math.random() * yPosition) * texture.getHeight());
    }

    /**
     * metoda rysuje obraz owocu na planszy
     * @param batch odpowiedzialny za wrysowanie tekstury
     */
    public void draw(Batch batch){
        batch.draw(texture, position.x, position.y);
    }

    /**
     * metoda zwraca pozycje owocu
     * @return zwraca miejsce na planszy gdzie jest umiejscowiony owoc
     */
    public GridPoint2 getPosition() {
        return this.position;
    }
}
