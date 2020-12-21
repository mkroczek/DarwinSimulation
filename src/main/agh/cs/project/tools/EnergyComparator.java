package agh.cs.project.tools;

import agh.cs.project.mapElements.Animal;

import java.util.Comparator;

public class EnergyComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal animal1, Animal animal2) {
        int energy1 = animal1.getEnergy();
        int energy2 = animal2.getEnergy();
        if (energy1 > energy2)
            return -1;
        else if (energy1 == energy2)
            return 0;
        else
            return 1;
    }
}
