package agh.cs.lab7;

import agh.cs.lab7.engine.SimulationEngine;
import agh.cs.lab7.simulationGUI.GameMainFrame;
import agh.cs.lab7.world.SteppeJungleMap;
import agh.cs.lab7.world.WorldProperties;

import java.awt.*;
import java.util.Map;

public class World {

    private final int id;
    private final WorldProperties properties;
    private final SteppeJungleMap map;
    private final SimulationEngine simulationEngine;
    private final Statistics statistics;

    public World(int id, String propertiesPath){
        this.id = id;
        this.properties = new WorldProperties(propertiesPath);
        this.map = new SteppeJungleMap(this, this.properties.getMapWidth(), this.properties.getMapHeight(), this.properties.getJungleWidth(), this.properties.getJungleHeight());
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
