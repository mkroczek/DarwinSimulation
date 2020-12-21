package agh.cs.project.dataStructures;

import agh.cs.project.tools.EnergyComparator;
import agh.cs.project.mapElements.Animal;

import java.util.*;

public class AnimalStorage{
    //class to store animals at given position
    private Comparator energyComparator = new EnergyComparator();
    private final ArrayList<Animal> animals = new ArrayList<>();

    public AnimalStorage(Animal animal){
        this.add(animal);
    }

    public void add(Animal animal){
        this.animals.add(animal);
        Collections.sort(this.animals, energyComparator);
    }

    public void remove(Animal animal){
        this.animals.remove(animal);
    }

    public boolean isEmpty(){
        return this.animals.isEmpty();
    }

    public ArrayList<Animal> getFullListOfAnimals(){
        return this.animals;
    }

    public ArrayList<Animal> getStrongestAnimals(){
        ArrayList<Animal> strongestAnimals = new ArrayList<>();
        Iterator iterator = this.animals.iterator();
        Animal firstAnimal = (Animal)iterator.next();
        strongestAnimals.add(firstAnimal);
        while(iterator.hasNext()){
            Animal nextAnimal = (Animal)iterator.next();
            if (nextAnimal.getEnergy() == firstAnimal.getEnergy())
                strongestAnimals.add(nextAnimal);
            else
                break;
        }
        return strongestAnimals;
    }

    private ArrayList<Animal> getNRandomAnimals(int n, ArrayList<Animal> animals){
        Random generator = new Random();
        ArrayList<Integer> drawnIndexes = new ArrayList<>();
        ArrayList<Animal> drawnAnimals = new ArrayList<>();
        int numberOfAnimals = animals.size();
        if (numberOfAnimals < n)
            throw new IllegalArgumentException("Can't pick "+n+" animals from list of length: "+numberOfAnimals);
        else {
            while (drawnIndexes.size() < n) {
                int pickedIndex = generator.nextInt(numberOfAnimals);
                if (!drawnIndexes.contains(pickedIndex))
                    drawnIndexes.add(pickedIndex);
            }
            for (int index : drawnIndexes)
                drawnAnimals.add(animals.get(index));
        }
        return drawnAnimals;
    }

    private ArrayList<Animal> getNFirstAnimals(int n, ArrayList<Animal> animals){
        ArrayList<Animal> result = new ArrayList<>();
        for (int i = 0; i < n; i++){
            result.add(animals.get(i));
        }
        return result;
    }

    public Animal getStrongestAnimal(){
        return  this.animals.get(0);
    }

    public ArrayList<Animal> getPotentialParents(){
        ArrayList<Animal> potentialParents = this.getStrongestAnimals();
        if (potentialParents.size() >= 2)
            potentialParents = getNRandomAnimals(2, potentialParents);
        else if (this.animals.size() >= 2){
            potentialParents = getNFirstAnimals(2, this.animals);
        }
        return potentialParents;
    }

    public int getNumberOfAnimals(){
        return this.animals.size();
    }

}
