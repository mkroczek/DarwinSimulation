package agh.cs.project.tools;


import agh.cs.project.enums.StatisticsNames;
import agh.cs.project.interfaces.IDayChangeObserver;
import agh.cs.project.world.World;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class AverageStatisticsGenerator implements IDayChangeObserver {
    private final World world;
    private final Map<StatisticsNames,Integer> statistics = new LinkedHashMap<>();
    private final int startEra;
    private final int ereasNumber;
    private int currentEra;
    private final int[] dominantGenes = new int[8];

    public AverageStatisticsGenerator(int n, World world){
        this.world = world;
        this.world.getSimulationEngine().addDayObserver(this);
        this.ereasNumber = n;
        Arrays.fill(dominantGenes, 0);
        this.startEra = this.world.getDay();
        this.currentEra = this.world.getDay();
        this.init();
        this.updateStatistics(this.world.getStatistics());
    }

    private void init(){
        this.statistics.put(StatisticsNames.NUMBER_OF_ANIMALS, -1);
        this.statistics.put(StatisticsNames.NUMBER_OF_PLANTS, -1);
        this.statistics.put(StatisticsNames.DOMINANT_GEN, -1);
        this.statistics.put(StatisticsNames.AVERAGE_ENERGY, -1);
        this.statistics.put(StatisticsNames.AVERAGE_LENGTH_OF_LIFE, -1);
        this.statistics.put(StatisticsNames.AVERAGE_CHILDREN_NUMBER, -1);
    }

    @Override
    public void nextDay() {
        this.currentEra += 1;
        if (this.currentEra-this.startEra == ereasNumber){
            generateStatistics();
            this.world.getSimulationEngine().removeDayObserver(this);
        }
        else
            updateStatistics(this.world.getStatistics());

    }

    private void generateStatistics(){
        //Generates statistics from startEra (included) to startEra+erasNumber (not included)

        try{
            PrintWriter fileWriter = new PrintWriter("data/world_"+this.world.getId()+"_"+this.startEra+"-"+(this.currentEra-1)+".txt");
            fileWriter.println("-----------------------------------------------");
            fileWriter.println("Average statistics from world "+this.world.getId()+", eras "+this.startEra+" to "+(this.currentEra-1));
            fileWriter.println("-----------------------------------------------");
            for (Map.Entry<StatisticsNames, Integer> entry : this.statistics.entrySet()){
                if (entry.getKey() == StatisticsNames.DOMINANT_GEN)
                {
                    int maxIndex = 0;
                    for (int i = 1; i<dominantGenes.length; i++){
                        if (dominantGenes[i] > dominantGenes[maxIndex])
                            maxIndex = i;
                    }
                    fileWriter.println(entry.getKey().toString()+": "+maxIndex);
                }
                else
                    fileWriter.println(entry.getKey().toString()+": "+entry.getValue()/ereasNumber);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error with writing to file occurred");
            e.printStackTrace();
        }
    }

    private void updateStatistics(Map<StatisticsNames,Integer> statistics){
        for (StatisticsNames statistic : this.statistics.keySet())
        {
            if (statistic == StatisticsNames.DOMINANT_GEN){
                this.dominantGenes[statistics.get(StatisticsNames.DOMINANT_GEN)]+=1;
            }
            else if (currentEra-startEra == 0)
                this.statistics.put(statistic, this.statistics.get(statistic) + statistics.get(statistic));
            else
                this.statistics.put(statistic, this.statistics.get(statistic)+statistics.get(statistic));
        }
    }
}
