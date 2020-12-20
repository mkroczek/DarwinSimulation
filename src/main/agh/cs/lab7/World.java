package agh.cs.lab7;

import agh.cs.lab7.simulationGUI.GameMainFrame;
import agh.cs.lab7.world.SteppeJungleMap;

import java.awt.*;
import java.util.Map;

public class World {

    private final int id;
    private WorldProperties properties;
    private SteppeJungleMap map;
    private SimulationEngine simulationEngine;
    private Statistics statistics;

    public World(int id, String filepath){
        this.id = id;
        this.properties = new WorldProperties(filepath);
        this.map = new SteppeJungleMap(this, this.properties.getMapWidth(), this.properties.getMapHeight(), this.properties.getJungleWidth(), this.properties.getJungleHeight(), this.properties.getPlantsPerDay());
        this.statistics = new Statistics(this, map);
        this.simulationEngine = new SimulationEngine(this, this.map);
        this.simulationEngine.addDayObserver(this.statistics);
        EventQueue.invokeLater( () -> {
            new GameMainFrame(this);
        } );
    }

    public void startSimulation(){
        this.simulationEngine.run();
    }

    public void pauseSimulation(){this.simulationEngine.stop();}

    public void updateStatistics(){
        this.statistics.updateStatistics();
    }

    public int getDay(){return this.simulationEngine.getDay();}

    public int getId(){return this.id;}

    public SimulationEngine getSimulationEngine(){return this.simulationEngine;}
    public Map<StatisticsNames, Integer> getStatistics(){return this.statistics.getStatistics();}
    public WorldProperties getProperties(){return this.properties;}
    public SteppeJungleMap getMap(){return this.map;}
}
