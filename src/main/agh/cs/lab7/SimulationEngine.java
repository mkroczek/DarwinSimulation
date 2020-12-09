package agh.cs.lab7;

import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.mapElements.Animal;
import agh.cs.lab7.mapElements.Genes;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {

    private IWorldMap map;

    public SimulationEngine(IWorldMap map, Vector2d[] positions){
        this.map = map;
        for (Vector2d position: positions){
            IMapElement animal = new Animal(this.map, position, new Genes(8,32), 10);
            map.place(animal);
        }
        this.map.updateMap();
        System.out.println(this.map.toString());
    }

    @Override
    public void run() {
        ArrayList<Animal> animals = this.map.getLivingAnimals();
        int moves_counter = 0;
        while (moves_counter < 100){
            for (int which_animal = 0; which_animal < animals.size() && moves_counter < 100; which_animal++, moves_counter++){
                animals.get(which_animal).move();
                this.map.updateMap();
                System.out.println(this.map.toString());

            }
        }
    }

    public IWorldMap getMap(){
        return this.map;
    }
}
