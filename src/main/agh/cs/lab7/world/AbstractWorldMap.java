package agh.cs.lab7.world;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.*;
import agh.cs.lab7.mapElements.Animal;
import agh.cs.lab7.mapElements.Genes;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IObjectDiedObserver, IPositionChangedObserver {
    protected Map<Vector2d, AnimalStorage> animals = new LinkedHashMap<>();
    protected Set<Animal> deadAnimals = new HashSet<Animal>();
    protected DirectionsParser parser = new DirectionsParser();
    protected World world;
    protected RectangularArea map;
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

    public void objectDied(IMapElement object, Vector2d position){
        if (object instanceof Animal) {
            Animal animal = (Animal) object;
            this.remove(position, animal);
            this.deadAnimals.add(animal);
        }
    }

    public void place(IMapElement object)
    {
        if (object instanceof Animal) {
            this.add(object.getPosition(), (Animal) object);
        }
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

    public abstract void spawnObjects(int energy);

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

    public Vector2d getRandomFreePosition(RectangularArea mainArea, RectangularArea subArea){
        RectangularArea innerArea = mainArea.jointArea(subArea);
        int searchingLimit = innerArea.getArea() + 1;
        int checked = 0;
        Vector2d position = null;
        for (checked = 0; checked < searchingLimit; checked++){
            position = this.getRandomPosition(mainArea, innerArea);
            if (!isOccupied(position) && mainArea.includePosition(position)){
                return position;
            }
        }
        position = this.getFreePosition(mainArea, innerArea);
        return position;
    }

    public Vector2d getRandomPosition(RectangularArea mainArea, RectangularArea subArea){
        RectangularArea innerArea = mainArea.jointArea(subArea);
        int xBound = innerArea.getWidth();
        int yBound = innerArea.getHeight();
        return new Vector2d(this.generator.nextInt(xBound)+innerArea.getLeftBottom().x, this.generator.nextInt(yBound)+innerArea.getLeftBottom().y);
    }

    public void placeAnimalOnRandomPosition(int startEnergy){
        Vector2d position = this.getRandomFreePosition(this.map, this.map);
        if (position != null)
            this.place(new Animal(this, position, new Genes(8,32), startEnergy));
        else
            this.place(new Animal(this, new Vector2d(0,0), new Genes(8,32), startEnergy));

    }

    public Vector2d getFreePosition(RectangularArea mainArea, RectangularArea subArea){
        RectangularArea innerArea = mainArea.jointArea(subArea);
        for (int x = innerArea.getLeftBottom().x; x <= innerArea.getRightTop().x; x++){
            for (int y = innerArea.getLeftBottom().y; y <= innerArea.getRightTop().y; y++){
                Vector2d position = new Vector2d(x,y);
                if (objectAt(position) == null && mainArea.includePosition(position))
                    return position;
            }
        }
        return null;
    }

    public ArrayList<Animal> getStrongestAnimals(Vector2d position){
        return this.animals.get(position).getStrongestAnimals();
    }

    public ArrayList<Animal> getPotentialParents(Vector2d position){
        return this.animals.get(position).getPotentialParents();
    }

    public Set<Vector2d> getAnimalsPositions(){
        Set<Vector2d> actualSet = new HashSet<>();
        actualSet.addAll(this.animals.keySet());
        return actualSet;
    }

    public ArrayList<Animal> getLivingAnimals(){
        ArrayList<Animal> livingAnimals = new ArrayList<Animal>();
        for (Vector2d position : this.animals.keySet())
            livingAnimals.addAll(animalsAt(position));
        return livingAnimals;
    }

    public int getNumberOfAnimals(){
        return getLivingAnimals().size();
    }

    public int getNumberOfDeadAnimals(){return this.deadAnimals.size();}

    public int getDominantGen(){
        int mostCommonGen = 0;
        int[] genesOccurrences = new int[8];
        Arrays.fill(genesOccurrences, 0);
        for (Animal animal : this.getLivingAnimals()){
            int animalGen = animal.getDominantGen();
            genesOccurrences[animalGen]+=1;
            if (genesOccurrences[animalGen] > genesOccurrences[mostCommonGen])
                mostCommonGen = animalGen;
        }
        return mostCommonGen;
    }

    public int getAverageEnergy(){
        int sum = 0;
        ArrayList<Animal> animals = this.getLivingAnimals();
        for (Animal animal : animals) {
            sum += animal.getEnergy();
        }
        if(animals.size() > 0)
            return sum/animals.size();
        else
            return -1;
    }

    public int getAverageLengthOfLife(){
        int sum = 0;
        for (Animal deadAnimal : this.deadAnimals){
            sum+=deadAnimal.getDeathDate();
        }
        if (deadAnimals.size() > 0)
            return sum/deadAnimals.size();
        else
            return -1;
    }

    public DirectionsParser getParser(){return this.parser;}

    public RectangularArea getMapArea(){return this.map;}

    public World getWorld(){return this.world;}
}
