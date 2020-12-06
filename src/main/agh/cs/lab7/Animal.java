package agh.cs.lab7;

import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IPositionChangeObserver;
import agh.cs.lab7.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Animal implements IMapElement {

    private IWorldMap map = null;
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private Energy energy;
    private Genes genes;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(){};

    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        addObserver((IPositionChangeObserver) map.getBoundary());
        this.position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, Genes genes){
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        addObserver((IPositionChangeObserver) map.getBoundary());
        this.position = initialPosition;
        this.genes = genes;
    }

    public String toString(){
        return this.orientation.toString();
    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        for(IPositionChangeObserver obs : this.observers){
            if (obs.equals(observer))
                this.observers.remove(obs);
        }
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public void changeOrientation(int direction){
        this.orientation = this.map.getParser().parseToDirection(direction, this.orientation);
    }

    private int drawDirection(){
        return this.genes.getRandomGen();
    }

    public void move(){
        this.changeOrientation(this.drawDirection());
        Vector2d oldPosition = this.position;
        Vector2d newPosition = this.position.add(orientation.toUnitVector());
        this.position = newPosition;
        positionChanged(oldPosition, newPosition);
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
