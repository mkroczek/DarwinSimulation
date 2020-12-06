package agh.cs.lab7;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Genes {
    private int[] genotype;
    private int[] genesOccurrencies;
    private int domain;
    private int genotypeLength;

    public Genes(int domain, int numberOfGenes){
        this.domain = domain;
        this.genotypeLength = numberOfGenes;
        this.genesOccurrencies = new int[this.domain];
        Arrays.fill(this.genesOccurrencies, 0);
        this.genotype = generateRandomGenotype();
        this.validateGenotype();
        Arrays.sort(this.genotype);
    }

    public Genes(int domain, int numberOfGenes, Genes motherGenes, Genes fatherGenes){
        this.domain = domain;
        this.genotypeLength = numberOfGenes;
        this.genesOccurrencies = new int[this.domain];
        Arrays.fill(this.genesOccurrencies, 0);
        this.genotype = generateMultipliedGenotype(motherGenes, fatherGenes);
        this.validateGenotype();
        Arrays.sort(this.genotype);
    }

    private int[] generateRandomGenotype(){
        Random generator = new Random();
        int[] genotype = new int[this.genotypeLength];
        for (int i = 0; i < this.genotypeLength; i+=1){
            int gen = generator.nextInt(this.domain);
            genotype[i] = gen;
            this.genesOccurrencies[gen]+=1;
        }
        return genotype;
    }

    private void fulfillGenotype(int[] genotype, int startIndex, int[] genotypePart){
        for (int i = 0; i < genotypePart.length; i++){
            genotype[i+startIndex] = genotypePart[i];
        }
    }

    private int[] generateMultipliedGenotype(Genes motherGenes, Genes fatherGenes){
        int[] motherGenotype = motherGenes.getGenotype();
        int[] fatherGenotype = motherGenes.getGenotype();
        Random generator = new Random();
        int splitIndex1 = generator.nextInt(this.genotypeLength-2);
        int splitIndex2 = generator.nextInt(this.genotypeLength-(splitIndex1+2)) + splitIndex1 + 1;
        int[] reproductedGenotype = new int[this.genotypeLength];
        fulfillGenotype(reproductedGenotype, 0, Arrays.copyOfRange(motherGenotype, 0, splitIndex1+1));
        fulfillGenotype(reproductedGenotype, splitIndex1+1, Arrays.copyOfRange(motherGenotype, splitIndex1+1, splitIndex2+1));
        fulfillGenotype(reproductedGenotype, splitIndex2+1, Arrays.copyOfRange(motherGenotype, splitIndex2+1, this.genotypeLength));
        return reproductedGenotype;
    }

    private int findMissingGen(){
        for (int i = 0; i < this.domain; i += 1){
            if (this.genesOccurrencies[i] == 0)
                return i;
        }
        return -1;
    }

    private void validateGenotype(){
        Random generator = new Random();
        int missingGen = findMissingGen();
        while (missingGen != -1) {
            int genIndexToReplace = generator.nextInt(this.genotypeLength);
            int genToReplace = this.genotype[genIndexToReplace];
            this.genesOccurrencies[genToReplace] -= 1;
            this.genotype[genIndexToReplace] = missingGen;
            this.genesOccurrencies[missingGen] += 1;
            missingGen = findMissingGen();
        }
    }

    public int getRandomGen(){
        Random generator = new Random();
        int genIndex = generator.nextInt(this.genotypeLength);
        return this.genotype[genIndex];
    }

    public int[] getGenotype() {
        return this.genotype;
    }

    public Genes multiply(Genes partnerGenes){
        return new Genes(this.domain, this.genotypeLength, this, partnerGenes);
    }
}
