package agh.cs.project.dataStructures;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Genes {
    private final int[] genotype;
    private final int[] genesOccurrences;
    private final int domain;
    private final int genotypeLength;

    public Genes(int domain, int numberOfGenes){
        this.domain = domain;
        this.genotypeLength = numberOfGenes;
        this.genesOccurrences = new int[this.domain];
        Arrays.fill(this.genesOccurrences, 0);
        this.genotype = generateRandomGenotype();
        this.validateGenotype();
        Arrays.sort(this.genotype);
    }

    public Genes(int domain, int numberOfGenes, int[] motherGenotype, int[] fatherGenotype){
        this.domain = domain;
        this.genotypeLength = numberOfGenes;
        this.genesOccurrences = new int[this.domain];
        Arrays.fill(this.genesOccurrences, 0);
        this.genotype = generateMultipliedGenotype(motherGenotype, fatherGenotype);
        this.validateGenotype();
        Arrays.sort(this.genotype);
    }

    private int[] generateRandomGenotype(){
        Random generator = new Random();
        int[] genotype = new int[this.genotypeLength];
        for (int i = 0; i < this.genotypeLength; i+=1){
            int gen = generator.nextInt(this.domain);
            genotype[i] = gen;
            this.genesOccurrences[gen]+=1;
        }
        return genotype;
    }

    private void fulfillGenotype(int[] genotype, int startIndex, int[] genotypePart){
        for (int i = 0; i < genotypePart.length; i++){
            genotype[i+startIndex] = genotypePart[i];
            this.genesOccurrences[genotypePart[i]] += 1;
        }
    }

    private int[] generateMultipliedGenotype(int[] motherGenotype, int[] fatherGenotype){
        Random generator = new Random();
        int splitIndex1 = generator.nextInt(this.genotypeLength-2);
        int splitIndex2 = generator.nextInt(this.genotypeLength-(splitIndex1+2)) + splitIndex1 + 1;
        int[] reproductedGenotype = new int[this.genotypeLength];
        fulfillGenotype(reproductedGenotype, 0, Arrays.copyOfRange(motherGenotype, 0, splitIndex1+1));
        fulfillGenotype(reproductedGenotype, splitIndex1+1, Arrays.copyOfRange(fatherGenotype, splitIndex1+1, splitIndex2+1));
        fulfillGenotype(reproductedGenotype, splitIndex2+1, Arrays.copyOfRange(motherGenotype, splitIndex2+1, this.genotypeLength));
        return reproductedGenotype;
    }

    private int findMissingGen(){
        for (int i = 0; i < this.domain; i += 1){
            if (this.genesOccurrences[i] == 0)
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
            this.genesOccurrences[genToReplace] -= 1;
            this.genotype[genIndexToReplace] = missingGen;
            this.genesOccurrences[missingGen] += 1;
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

    public Genes multiply(int[] partnerGenotype){
        return new Genes(this.domain, this.genotypeLength, this.genotype , partnerGenotype);
    }

    public int getDominantGen(){
        int gen = 0;
        for (int i = 1; i < this.domain; i++){
            if (this.genesOccurrences[i] > this.genesOccurrences[gen])
                gen = i;
        }
        return gen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genes genes = (Genes) o;
        return domain == genes.domain &&
                genotypeLength == genes.genotypeLength &&
                Arrays.equals(genotype, genes.genotype) &&
                Arrays.equals(genesOccurrences, genes.genesOccurrences);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(domain, genotypeLength);
        result = 31 * result + Arrays.hashCode(genotype);
        result = 31 * result + Arrays.hashCode(genesOccurrences);
        return result;
    }
}
