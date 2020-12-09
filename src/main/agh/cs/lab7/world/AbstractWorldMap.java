package agh.cs.lab7.world;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IMapArea;
import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IMapElementObserver;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.mapElements.Animal;
import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IMapElementObserver {
    protected Map<Vector2d, AnimalStorage> animals = new LinkedHashMap<>();
    protected DirectionsParser parser = new DirectionsParser();

    protected void remove(Vector2d position, Animal animal){
        this.animals.get(position).remove(animal);
        if (this.animals.get(position).isEmpty())
            this.animals.remove(position);
    }

    protected void add(Vector2d position, Animal animal){
        if (this.animals.containsKey(position)) {
            this.animals.get(position).add(animal);
        }
        else {
            this.animals.put(position, new AnimalStorage(animal));
        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        this.remove(oldPosition, animal);
        this.add(newPosition, animal);
    }

    public void objectDied(IMapElement object){
        if (object instanceof Animal) {
            Animal animal = (Animal) object;
            this.remove(animal.getPosition(), animal);
        }
    }

    public void place(IMapElement object)
    {
        if (object instanceof Animal)
            this.add(object.getPosition(), (Animal)object);
    }

    public boolean isOccupied(Vector2d position){
        if (animals.containsKey(position))
            return true;
        return false;
    }

    public ArrayList<Animal> animalsAt(Vector2d position){
        if (this.animals.containsKey(position))
            return animals.get(position).getFullListOfAnimals();
        else
            return null;
    }

    public void updateMap(){
    }

    public Object objectAt(Vector2d position){
        ArrayList<Animal> foundAnimals = animalsAt(position);
        if (foundAnimals == null)
            return null;
        else
            return foundAnimals.get(0);
    }

    public abstract Vector2d countMovePosition(Vector2d position, Vector2d moveVector);

    public abstract Vector2d[] countBounds();

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d[] bounds = countBounds();
        return visualizer.draw(bounds[0], new Vector2d(bounds[1].x, bounds[1].y));
    }

    public Vector2d getFreePosition(IMapArea area, Vector2d from, Vector2d to){
        for (int x = from.x; x <= to.x; x++){
            for (int y = from.y; y <= to.y; y++){
                Vector2d position = new Vector2d(x,y);
                if (objectAt(position) == null && area.includePosition(position))
                    return position;
            }
        }
        return null;
    }

    public ArrayList<Animal> getLivingAnimals(){
        ArrayList<Animal> livingAnimals = new ArrayList<Animal>();
        for (Vector2d position : this.animals.keySet())
            livingAnimals.addAll(animalsAt(position));
        return livingAnimals;
    }

    public DirectionsParser getParser(){return this.parser;}

}
