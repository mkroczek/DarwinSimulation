package agh.cs.lab7;

import agh.cs.lab7.interfaces.IDayChangeObserver;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.mapElements.Animal;
import agh.cs.lab7.mapElements.Genes;
import agh.cs.lab7.mapElements.Grass;
import agh.cs.lab7.world.SteppeJungleMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class SimulationEngine implements IEngine, ActionListener{

    private IWorldMap map;
    private WorldProperties worldProperties;
    private World world;
    private SimulationState state = SimulationState.NOT_STARTED;
    private Timer timer;
    private ArrayList<IDayChangeObserver> observers = new ArrayList<>();
    private ArrayList<IDayChangeObserver> observersToRemove = new ArrayList<>();
    private int day = -1;

    public SimulationEngine(World world, IWorldMap map){
        this.map = map;
        this.world = world;
        this.worldProperties = this.world.getProperties();
        this.timer = new Timer(Constants.GAME_SPEED, this);
    }

    public void initSimulation(){
        int numberOfAnimals = this.worldProperties.getStartNumberOfAnimals();
        int startEnergy = this.worldProperties.getStartEnergy();
        for (int i = 0; i < numberOfAnimals; i++){
            this.map.placeAnimalOnRandomPosition(startEnergy);
        }
        this.map.spawnObjects(this.worldProperties.getPlantEnergy(), this.worldProperties.getStartNumberOfGrass());
        this.showMap();
        this.nextDay();
    }

    public void subtractMoveEnergy(){
        ArrayList<Animal> animals = this.map.getLivingAnimals();
        for (Animal animal : animals){
            animal.subtractEnergy(this.worldProperties.getMoveEnergy());
        }
    }

    public void moveAnimals(){
        ArrayList<Animal> livingAnimals = this.map.getLivingAnimals();
        for (Animal animal : livingAnimals){
            animal.move();
        }
    }

    public void spawnObjects(){
        this.map.spawnObjects(this.worldProperties.getPlantEnergy(), this.worldProperties.getPlantsPerDay());
    }

    public void feedAnimals(){
        Set<Vector2d> positions = this.map.getAnimalsPositions();
        for (Vector2d position : positions){
            ArrayList<Animal> strongestAnimals = this.map.getStrongestAnimals(position);
            if (strongestAnimals.size() == 0)
                System.out.println("There is no animals!");
            Grass grass = ((SteppeJungleMap)this.map).grassAt(position);
            if (strongestAnimals.size() > 0 && grass != null)
                grass.feed(strongestAnimals);
        }
    }

    public void multiplyAnimals(){
        Set<Vector2d> positions = this.map.getAnimalsPositions();
        for (Vector2d position : positions){
            ArrayList<Animal> potentialParents = this.map.getPotentialParents(position);
            if (potentialParents.size() >= 2){
                System.out.println("Potential parents found on position: "+ position);
                Animal mother = potentialParents.get(0);
                Animal father = potentialParents.get(1);
                if (mother.canBeParent(this.worldProperties.getStartEnergy()/2) && father.canBeParent(this.worldProperties.getStartEnergy()/2)) {
                    mother.multiply(father);
                    System.out.println("child borned properly");
                }
                else
                    System.out.println("Mother energy: "+mother.getEnergy()+" father energy: "+father.getEnergy());
            }
        }
    }

    public void showMap(){
        System.out.println("Map status at the end of the day "+this.world.getDay()+"\n" + "Number of living animals: "+this.map.getNumberOfAnimals()+"\n"+"Number of dead animals: "+this.map.getNumberOfDeadAnimals()+"\n");
    }

    private void update(){
        this.subtractMoveEnergy();
        this.moveAnimals();
        this.feedAnimals();
        this.multiplyAnimals();
        this.spawnObjects();
        this.showMap();
        this.nextDay();
        cleanObservers();
    }

    public void addDayObserver(IDayChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeDayObserver(IDayChangeObserver observer){
        this.observersToRemove.add(observer);
    }

    public void cleanObservers(){
        for (IDayChangeObserver observer : this.observersToRemove)
            this.observers.remove(observer);
        observersToRemove.clear();
    }

    public int getDay(){return this.day;}

    private void nextDay(){
        this.day +=1;
        this.world.updateStatistics();
        for (IDayChangeObserver observer : this.observers){
            observer.nextDay();
        }
    }

    @Override
    public void run(){
        this.state = SimulationState.RUNNING;
        this.timer.start();

    }

    public void stop(){
        this.state = SimulationState.STOPPED;
        this.timer.stop();
    }

    public IWorldMap getMap(){
        return this.map;
    }

    public boolean isRunning(){
        return (this.state == SimulationState.RUNNING);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }
}
