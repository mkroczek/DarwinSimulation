package agh.cs.project.dataStructures;

import agh.cs.project.enums.StatisticsNames;
import agh.cs.project.interfaces.IDayChangeObserver;
import agh.cs.project.world.SteppeJungleMap;
import agh.cs.project.world.World;

import java.util.LinkedHashMap;
import java.util.Map;

public class Statistics implements IDayChangeObserver {
    private SteppeJungleMap map;
    private World world;
    private Map<StatisticsNames,Integer> statistics = new LinkedHashMap<>();

    public Statistics(World world ,SteppeJungleMap map){
        this.world = world;
        this.map = map;
        init();
    }

    private void init(){
        this.statistics.put(StatisticsNames.DAY, -1);
        this.statistics.put(StatisticsNames.NUMBER_OF_ANIMALS, -1);
        this.statistics.put(StatisticsNames.NUMBER_OF_PLANTS, -1);
        this.statistics.put(StatisticsNames.DOMINANT_GEN, -1);
        this.statistics.put(StatisticsNames.AVERAGE_ENERGY, -1);
        this.statistics.put(StatisticsNames.AVERAGE_LENGTH_OF_LIFE, -1);
        this.statistics.put(StatisticsNames.AVERAGE_CHILDREN_NUMBER, -1);
    }

    public void updateStatistics(){

        this.statistics.put(StatisticsNames.DAY, this.world.getDay());
        this.statistics.put(StatisticsNames.NUMBER_OF_ANIMALS, this.map.getNumberOfAnimals());
        this.statistics.put(StatisticsNames.NUMBER_OF_PLANTS, map.getNumberOfGrassFields());
        this.statistics.put(StatisticsNames.DOMINANT_GEN, map.getDominantGen());
        this.statistics.put(StatisticsNames.AVERAGE_ENERGY, map.getAverageEnergy());
        this.statistics.put(StatisticsNames.AVERAGE_LENGTH_OF_LIFE, map.getAverageLengthOfLife());
        this.statistics.put(StatisticsNames.AVERAGE_CHILDREN_NUMBER, map.getAverageChildrenNumber());
    }

    public Map<StatisticsNames, Integer> getStatistics(){
        return this.statistics;
    }

    @Override
    public void nextDay() {
        this.updateStatistics();
    }
}
