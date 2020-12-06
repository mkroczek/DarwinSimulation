package agh.cs.lab7.world;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IPositionChangeObserver;
import agh.cs.lab7.interfaces.IWorldMap;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    protected MapBoundary boundary = new MapBoundary();
    protected DirectionsParser parser = new DirectionsParser();

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition, animal);
        animals.put(newPosition, animal);
    }

    public void place(Animal animal){
        this.animals.put(animal.getPosition(), animal);
        this.boundary.add(animal.getPosition());
    }

    public boolean isOccupied(Vector2d position){
        if (animals.get(position) != null)
            return true;
        return false;
    }

    public Object objectAt(Vector2d position){
        return animals.get(position);
    }

    public abstract Vector2d countMovePosition(Vector2d position, Vector2d moveVector);

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        int[] bounds = this.boundary.getBoundaries();
        return visualizer.draw(new Vector2d(bounds[0],bounds[2]), new Vector2d(bounds[1], bounds[3]));
    }

    public List<Animal> getListOfAnimals(){
        return new ArrayList<>(this.animals.values());
    }

    public MapBoundary getBoundary(){return this.boundary;}

    public DirectionsParser getParser(){return this.parser;}

}
