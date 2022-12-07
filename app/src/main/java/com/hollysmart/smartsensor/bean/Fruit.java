package com.hollysmart.smartsensor.bean;

public class Fruit {
    private String fruitName;

    private int fruitImageID;

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getFruitImageID() {
        return fruitImageID;
    }

    public void setFruitImageID(int fruitImageID) {
        this.fruitImageID = fruitImageID;
    }

    public Fruit(String fruitName, int fruitImageID) {
        this.fruitName = fruitName;
        this.fruitImageID = fruitImageID;
    }
}
