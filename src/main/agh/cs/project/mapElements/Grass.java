package agh.cs.project.mapElements;

import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.world.SteppeJungleMap;

import java.util.ArrayList;

public class Grass extends AbstractWorldMapElement {

    private final SteppeJungleMap map;
    private int energy;

    public Grass(SteppeJungleMap map, Vector2d position, int energy){
        this.position = position;
        this.map = map;
        this.energy = energy;
        this.addObserver(map);
    }

    public void feed(ArrayList<Animal> animals){
        int energyPerAnimal = (this.energy/animals.size());
        for (Animal animal : animals){
            if (animal.getPosition().equals(this.position))
                animal.eat(energyPerAnimal);
            else
                System.out.println("Animal must be on the same position as grass is!");
        }
        this.subtractEnergy(this.energy);
        this.die();
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void subtractEnergy(int energy){
        this.energy-=energy;
    }

    public int getEnergy(){return this.energy;}

    public String toString(){
        return "*";
    }

}
