package agh.cs.lab7;

import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private IWorldMap map;
    private ArrayList<Animal> animals = new ArrayList<>();

    public SimulationEngine(IWorldMap map, Vector2d[] positions){
        this.map = map;
        for (Vector2d position: positions){
            Animal animal = new Animal(this.map, position, new Genes(8,32));
            map.place(animal);
            animals.add(animal);
            System.out.println(this.map.toString());
        }
    }

    @Override
    public void run() {
        int moves_counter = 0;
        while (moves_counter < 20){
            for (int which_animal = 0; which_animal < animals.size() && moves_counter < 20; which_animal++, moves_counter++){
                this.animals.get(which_animal).move();
                System.out.println(this.map.toString());

            }
        }
    }

    public IWorldMap getMap(){
        return this.map;
    }
}
