package agh.cs.lab7;

import java.util.Random;

public class Genes {
    private int[] genotype;

    public Genes(int domain, int numberOfGenes){
        this.genotype = generateRandom(domain, numberOfGenes);
    }

    private int[] generateRandom(int domain, int numberOfGenes){
        Random generator = new Random();
        int[] result = new int[numberOfGenes];
        for (int i = 0; i < numberOfGenes; i+=1){
            result[i] = generator.nextInt(domain);
        }
        return result;
    }
}
