package agh.cs.project.mapElements;

import agh.cs.project.enums.MapDirection;
import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.dataStructures.Genes;
import agh.cs.project.dataStructures.Offspring;
import agh.cs.project.world.World;
import agh.cs.project.interfaces.IPositionChangedObserver;
import agh.cs.project.world.RectangularArea;
import agh.cs.project.world.SteppeJungleMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Animal extends AbstractWorldMapElement {

    private final SteppeJungleMap map;
    private final World world;
    private MapDirection orientation = MapDirection.getRandom();
    private final int startEnergy;
    private int currentEnergy;
    private final Genes genes;
    private final Offspring offspring = new Offspring();
    private final int birthDate;
    private int deathDate = -1;
    private final List<IPositionChangedObserver> positionChangedObservers = new ArrayList<>();

    public Animal(SteppeJungleMap map, Vector2d initialPosition, Genes genes, int startEnergy){
        this.map = map;
        addObserver(map);
        this.world = this.map.getWorld();
        this.position = initialPosition;
        this.startEnergy = startEnergy;
        this.currentEnergy = startEnergy;
        this.genes = genes;
        this.birthDate = this.world.getDay();
    }

    public String toString(){
        return this.orientation.toString();
    }

    public void addObserver(Object observer){
        super.addObserver(observer);
        if (observer instanceof IPositionChangedObserver)
            positionChangedObservers.add((IPositionChangedObserver) observer);
    }

    public void removeObserver(Object observer){
        super.removeObserver(observer);
        if (observer instanceof IPositionChangedObserver)
            this.positionChangedObservers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        for (IPositionChangedObserver observer : this.positionChangedObservers){
            observer.positionChanged(oldPosition, newPosition ,animal);
        }
    }

    public void changeOrientation(int direction){
        this.orientation = this.map.getParser().parseToDirection(direction, this.orientation);
    }

    private int drawDirection(){
        return this.genes.getRandomGen();
    }

    public void eat(int energy){
        this.currentEnergy += energy;
    }

    public boolean canBeParent(int requiredEnergy){
        return this.currentEnergy >= requiredEnergy;
    }

    public void multiply(Animal partner){
        Genes childGenes = this.genes.multiply(partner.getGenotype());
        int motherEnergy = this.currentEnergy/4;
        int fatherEnergy = partner.currentEnergy/4;
        int childEnergy = motherEnergy+fatherEnergy;
        this.subtractEnergy(motherEnergy);
        partner.subtractEnergy(fatherEnergy);
        Vector2d leftBottom = new Vector2d(this.position.x-1, this.position.y-1);
        Vector2d rightTop = new Vector2d(this.position.x+1, this.position.y+1);
        RectangularArea childArea = new RectangularArea(leftBottom, rightTop);
        Vector2d childPosition = this.map.getRandomFreePosition(this.map.getMapArea(), childArea);
        if (childPosition == null)
            childPosition = leftBottom;
        Animal child = new Animal(this.map, childPosition, childGenes, childEnergy);
        this.addChild(child);
        partner.addChild(child);
        this.map.place(child);
    }

    public void move(){
        this.changeOrientation(this.drawDirection());
        Vector2d oldPosition = this.position;
        Vector2d newPosition = map.countMovePosition(oldPosition, orientation.toUnitVector());
        this.position = newPosition;
        positionChanged(oldPosition, newPosition, this);
    }

    public void subtractEnergy(int energy){
        this.currentEnergy -= energy;
        if (this.currentEnergy < 0)
            this.die();
    }

    public void die(){
        this.deathDate = this.world.getDay();
        super.die();
    }

    public void addChild(Animal animal){
        this.offspring.addChild(animal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Animal animal = (Animal) o;
        return startEnergy == animal.startEnergy &&
                currentEnergy == animal.currentEnergy &&
                orientation == animal.orientation &&
                position.equals(animal.position) &&
                genes.equals(animal.genes)&&
                birthDate == animal.birthDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orientation, this.position, this.startEnergy, this.currentEnergy, this.genes, this.birthDate);
    }

    public int getDominantGen(){return this.genes.getDominantGen();}

    public int[] getGenotype(){
        return this.genes.getGenotype();
    }

    public Set<Animal> getChildren(int since){return this.offspring.getChildren(since);}

    public Set<Animal> getOffspring(int since){return this.offspring.getOffspring(since);}

    public int getNumberOfChildren(){return this.offspring.getNumberOfChildren();}

    public int getNumberOfChildren(int since){return this.offspring.getNumberOfChildren(since);}

    public int getNumberOfOffspring(){return this.offspring.getNumberOfOffspring();}

    public int getNumberOfOffspring(int since){return this.offspring.getNumberOfOffspring(since);}

    public int getEnergy() {return this.currentEnergy;}

    public int getStartEnergy(){return this.startEnergy;}

    public int getDeathDate() {return this.deathDate;}

    public int getBirthDate() {return this.birthDate;}
}
