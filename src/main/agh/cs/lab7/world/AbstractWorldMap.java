package agh.cs.lab7.world;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.*;
import agh.cs.lab7.mapElements.Animal;
import agh.cs.lab7.mapElements.Genes;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IMapElementObserver {
    protected Map<Vector2d, AnimalStorage> animals = new LinkedHashMap<>();
    protected DirectionsParser parser = new DirectionsParser();
    protected World world;
    protected IMapArea map;
    protected Random generator = new Random();

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

    public Vector2d getRandomFreePosition(IMapArea area, Vector2d leftBottom, Vector2d rightTop){
        int xBound = rightTop.x-leftBottom.x+1;
        int yBound = rightTop.y-leftBottom.y+1;
        int searchingLimit = xBound*yBound+1;
        int checked = 0;
        Vector2d position = null;
        for (checked = 0; checked < searchingLimit; checked++){
            position = new Vector2d(this.generator.nextInt(xBound)+leftBottom.x, this.generator.nextInt(yBound)+ leftBottom.y);
            if (!isOccupied(position) && area.includePosition(position)){
                return position;
            }
        }
        position = this.getFreePosition(area, leftBottom, rightTop);
        return position;
    }

    public void placeAnimalOnRandomPosition(int startEnergy){
        Vector2d position = this.getFreePosition(this.map, ((IRectangle)map).getLeftBottom(), ((IRectangle)map).getRightTop());
        if (position != null)
            this.place(new Animal(this, position, new Genes(8,32), startEnergy));
        else
            this.place(new Animal(this, new Vector2d(0,0), new Genes(8,32), startEnergy));

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
