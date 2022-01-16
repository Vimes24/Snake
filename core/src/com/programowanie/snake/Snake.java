package com.programowanie.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

/**
 * enum - typ wyliczeniowy ktory umozliwia zadeklarowanie ograniczonej liczby mozliwych wartosci
 */
enum Direction {RIGHT, LEFT, UP, DOWN}

/**
 * Klasa reprezentuje na ekranie postac weza
 */
public class Snake {
    private final Texture texture;
    private final List<GridPoint2> snakeElement;
    private Direction snakeDirection;
    private float elapsedTime;
    private  boolean changeDirection;

    /**
     * Konstruktor tworzacy obiekt
     * @param texture wykorzystuje asset w formie zdjecia do zobrazowania weza
     */
    public Snake(Texture texture){
        this.texture = texture;
        snakeElement = new ArrayList<>();
    }

    /**
     * metoda resetujaca ustawienia po zakonczeniu i ponownym uruchomieniu gry
     */
    public void newGame(){
        elapsedTime = 0;
        snakeDirection = Direction.RIGHT;
        snakeElement.clear();
        snakeElement.add(new GridPoint2(90, 30));
        snakeElement.add(new GridPoint2(75, 30));
        snakeElement.add(new GridPoint2(60, 30));
        snakeElement.add(new GridPoint2(45, 30));
        snakeElement.add(new GridPoint2(30, 30));
    }

    /**
     * metoda odpowiedzialna za ruch weza w odpowiednim czasie
     * @param deltaTime okresla czas, jaki upłynał od jej poprzedniego wywolania
     */
    public void act(float deltaTime){
        if(changeDirection){
            directionChange();
        }
        elapsedTime += deltaTime;
        if(elapsedTime >= 0.1){
            elapsedTime = 0;
            changeDirection = true;
            move();
        }
    }

    /**
     * metoda sprawdzajaca czy glowa weza znalazla się na pozycji owocu
     * @param fruitPosition okresla aktualnie wygenerowana pozycje owocu
     * @return zwraca true gdy glowa jest na pozycji owocu
     */
    public boolean isFruitFound(GridPoint2 fruitPosition){
        return snakeElement.get(0).equals(fruitPosition);
    }

    /**
     * metoda powieksza weza o jeden element od ogona
     */
    public void extendSnake(){
        snakeElement.add(new GridPoint2(snakeElement.get(snakeElement.size() - 1)));
    }

    /**
     * metoda odpowedzialna za przemieszczanie sie weza
     */
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

    /**
     * metoda odpowiedzialna za zmiane kierunku ruchu weza
     */
    private void directionChange(){
        Direction nextDirection = snakeDirection;
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snakeDirection != Direction.RIGHT){
            nextDirection = Direction.LEFT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snakeDirection != Direction.LEFT){
            nextDirection = Direction.RIGHT;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && snakeDirection != Direction.DOWN){
            nextDirection = Direction.UP;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snakeDirection != Direction.UP){
            nextDirection = Direction.DOWN;
        }

        if(snakeDirection != nextDirection){
            snakeDirection = nextDirection;
            changeDirection = false;
        }
    }

    /**
     * metoda sprawdzajaca czy glowa weza nie zderzyla się z reszta ciala
     * @return zwraca true gdy glowa zderzy sie z reszta ciala co konczy gre
     */
    public boolean hasHit(){
        for (int i = 1; i < snakeElement.size(); i++) {
            if(snakeElement.get(i).equals(snakeElement.get(0))){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda rysuje kolejne kwadraty obrazujace weza
     * @param batch wykorzystany w petli for each do przejscia przez cala liste
     */
    public void draw(Batch batch){
        for(GridPoint2 pos : snakeElement){
            batch.draw(texture, pos.x, pos.y);
        }
    }
}
