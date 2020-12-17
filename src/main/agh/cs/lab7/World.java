package agh.cs.lab7;

import agh.cs.lab7.sumulationGUI.GameMainFrame;
import agh.cs.lab7.interfaces.IDayChangeObserver;
import agh.cs.lab7.world.SteppeJungleMap;

import java.awt.*;
import java.util.Map;

public class World implements IDayChangeObserver {

    private int day = -1;
    private WorldProperties properties;
    private SteppeJungleMap map;
    private SimulationEngine simulationEngine;
    private Statistics statistics;

    public World(String filepath){
        this.properties = new WorldProperties(filepath);
        this.map = new SteppeJungleMap(this, this.properties.getMapWidth(), this.properties.getMapHeight(), this.properties.getJungleWidth(), this.properties.getJungleHeight());
        this.statistics = new Statistics(map);
        this.simulationEngine = new SimulationEngine(this, this.map);
        EventQueue.invokeLater( () -> {
            new GameMainFrame(this);
        } );
    }

    public void startSimulation(){
        this.simulationEngine.run();
    }

    public void pauseSimulation(){this.simulationEngine.stop();}

    public void nextDay(){
        this.day ++;
        this.statistics.updateStatistics();
    }

    public int getDay(){return this.day;}

    public SimulationEngine getSimulationEngine(){return this.simulationEngine;}
    public Map<StatisticsNames, Integer> getStatistics(){return this.statistics.getStatistics();}
    public WorldProperties getProperties(){return this.properties;}
    public SteppeJungleMap getMap(){return this.map;}
}
