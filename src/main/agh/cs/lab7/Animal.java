package agh.cs.lab7;

import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IPositionChangeObserver;
import agh.cs.lab7.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {

    private IWorldMap map = null;
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private Energy energy;
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
        this.orientation = this.map.getParser().parseDirection(direction, this.orientation);
    }

    public void move(MoveDirection direction){
        Vector2d testPos;
        Vector2d oldPosition;
        Vector2d newPosition;
        switch (direction){
            case RIGHT:
                this.orientation = orientation.next();
                break;
            case LEFT:
                this.orientation = orientation.previous();
                break;
            case FORWARD:
                testPos = this.position.add(orientation.toUnitVector());
                if (map.canMoveTo(testPos)){
                    oldPosition = this.position;
                    newPosition = testPos;
                    this.position = testPos;
                    positionChanged(oldPosition, newPosition);
                }
                break;
            case BACKWARD:
                testPos = this.position.add(orientation.toUnitVector().opposite());
                if (map.canMoveTo(testPos)){
                    oldPosition = this.position;
                    newPosition = testPos;
                    this.position = testPos;
                    positionChanged(oldPosition, newPosition);
                }
                break;
        }
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
