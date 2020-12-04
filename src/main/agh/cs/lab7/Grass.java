package agh.cs.lab7;

import agh.cs.lab7.interfaces.IMapElement;

public class Grass implements IMapElement {

    private Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public String toString(){
        return "*";
    }

}
