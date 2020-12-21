package agh.cs.project.world;

import agh.cs.project.tools.DirectionsParser;
import agh.cs.project.tools.MapVisualizer;
import agh.cs.project.dataStructures.AnimalStorage;
import agh.cs.project.interfaces.IMapElement;
import agh.cs.project.interfaces.IObjectDiedObserver;
import agh.cs.project.interfaces.IPositionChangedObserver;
import agh.cs.project.interfaces.IWorldMap;
import agh.cs.project.mapElements.Animal;
import agh.cs.project.dataStructures.Genes;
import agh.cs.project.mapElements.Grass;
import agh.cs.project.dataStructures.Vector2d;

import java.util.*;

public class SteppeJungleMap implements IWorldMap, IObjectDiedObserver, IPositionChangedObserver {
    private final World world;
    private final RectangularArea jungle;
    private final RectangularArea steppe;
    private final RectangularArea map;
    private final DirectionsParser parser = new DirectionsParser();
    private final Random generator = new Random();
    private final Map<Vector2d, AnimalStorage> animals = new LinkedHashMap<>();
    private final Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    private final ArrayList<Animal> deadAnimals = new ArrayList<>();


    public SteppeJungleMap(World world, int width, int height, int jungleWidth, int jungleHeight){
        if (width > 0 && height > 0 && jungleWidth > 0 && jungleHeight > 0 && jungleWidth <= width && jungleHeight <= height){
            this.map = new RectangularArea(new Vector2d(0,0), new Vector2d(width-1, height-1));
            this.world = world;
            Vector2d leftBottom = new Vector2d(0,0);
            Vector2d rightTop = new Vector2d(width-1, height-1);
            this.jungle = new Jungle(leftBottom, rightTop, jungleWidth, jungleHeight);
            this.steppe = new Steppe(leftBottom, rightTop);
            this.steppe.excludeArea(this.jungle);
        }
        else
            throw new IllegalArgumentException("Map properties must be nonngeative!");
    }

    private void add(Vector2d position, Animal animal){
        if (this.animals.containsKey(position)) {
            this.animals.get(position).add(animal);
        }
        else {
            this.animals.put(position, new AnimalStorage(animal));
        }
    }

    private void remove(Vector2d position, Animal animal){
        this.animals.get(position).remove(animal);
        if (this.animals.get(position).isEmpty())
            this.animals.remove(position);
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
        else if (object instanceof Grass) {
            this.grasses.remove(position);
        }
    }

    //may change into placing only animal
    public void place(IMapElement object)
    {
        if (object instanceof Animal) {
            this.add(object.getPosition(), (Animal) object);
        }
    }

    public boolean isOccupied(Vector2d position){
        if (animals.containsKey(position) || this.grasses.containsKey(position))
            return true;
        return false;
    }

    public ArrayList<Animal> animalsAt(Vector2d position){
        if (this.animals.containsKey(position))
            return animals.get(position).getFullListOfAnimals();
        else
            return null;
    }

    public Grass grassAt(Vector2d position){
        return this.grasses.get(position);
    }

    public Object objectAt(Vector2d position){
        ArrayList<Animal> foundAnimals = animalsAt(position);
        if (foundAnimals != null)
            return foundAnimals.get(0);
        else
            return grasses.get(position);

    }

    public Vector2d countMovePosition(Vector2d position, Vector2d moveVector) {
        Vector2d estimatedPosition = position.add(moveVector);
        if (!this.map.getRightTop().follows(estimatedPosition))
            estimatedPosition = new Vector2d(estimatedPosition.x%(this.map.getWidth()), estimatedPosition.y%(this.map.getHeight()));
        if (!this.map.getLeftBottom().precedes(estimatedPosition))
            estimatedPosition = new Vector2d((estimatedPosition.x+this.map.getWidth())%(this.map.getWidth()),(estimatedPosition.y+this.map.getHeight())%(this.map.getHeight()));
        return estimatedPosition;
    }

    public Vector2d[] countBounds() {
        return new Vector2d[]{this.map.getLeftBottom(), this.map.getRightTop()};
    }

    public void spawnObjects(int energy, int amount){
        this.generateGrassFields(energy, amount);
    }

    private void generateGrassInArea(RectangularArea area, int amount, int energy){
        Vector2d position;
        for (int i = 0; i < amount; i++){
            position = this.getRandomFreePosition(area, area);
            if (position != null) {
                this.grasses.put(position, new Grass(this, position, energy));
            }
            else
                break;
        }
    }

    private void generateGrassFields(int energy, int amount){
        this.generateGrassInArea(jungle, amount/2, energy);
        this.generateGrassInArea(steppe, amount-amount/2, energy);
    }

    public Vector2d getRandomPosition(RectangularArea mainArea, RectangularArea subArea){
        RectangularArea innerArea = mainArea.jointArea(subArea);
        int xBound = innerArea.getWidth();
        int yBound = innerArea.getHeight();
        return new Vector2d(this.generator.nextInt(xBound)+innerArea.getLeftBottom().x, this.generator.nextInt(yBound)+innerArea.getLeftBottom().y);
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

    public void placeAnimalOnRandomPosition(int startEnergy){
        Vector2d position = this.getRandomFreePosition(this.map, this.map);
        if (position != null)
            this.place(new Animal(this, position, new Genes(8,32), startEnergy));
        else
            this.place(new Animal(this, new Vector2d(0,0), new Genes(8,32), startEnergy));

    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d[] bounds = countBounds();
        return visualizer.draw(bounds[0], new Vector2d(bounds[1].x, bounds[1].y));
    }

    public ArrayList<Animal> getStrongestAnimals(Vector2d position){
        return this.animals.get(position).getStrongestAnimals();
    }

    public ArrayList<Animal> getPotentialParents(Vector2d position){
        return this.animals.get(position).getPotentialParents();
    }

    public Animal getAnimal(Vector2d position){
        return this.animals.get(position).getStrongestAnimal();
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

    public int getAverageChildrenNumber(){
        int livingAnimalsNumber = this.getLivingAnimals().size();
        int sum = 0;
        for (Animal animal : this.getLivingAnimals())
            sum+=animal.getNumberOfChildren();
        if (livingAnimalsNumber > 0)
            return sum/livingAnimalsNumber;
        else
            return -1;
    }

    public ArrayList<Animal> getAnimalsWithDominantGen(){
        int dominantGen = this.getDominantGen();
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal:this.getLivingAnimals()){
            if (animal.getDominantGen() == dominantGen)
                resultList.add(animal);
        }
        return resultList;
    }

    public DirectionsParser getParser(){return this.parser;}

    public RectangularArea getMapArea(){return this.map;}

    public World getWorld(){return this.world;}

    public Vector2d getJungleLeftBottom() { return this.jungle.getLeftBottom();}

    public Set<Vector2d> getGrassesPositions(){return this.grasses.keySet();}

    public Vector2d getJungleRightTop() {
        return this.jungle.getRightTop();
    }

    public int getNumberOfGrassFields(){return this.grasses.size();}
}
