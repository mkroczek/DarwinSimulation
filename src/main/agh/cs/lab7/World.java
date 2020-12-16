package agh.cs.lab7;

import agh.cs.lab7.gui.GameMainFrame;
import agh.cs.lab7.interfaces.IDayChangeObserver;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.SteppeJungleMap;

import java.awt.*;

public class World implements IDayChangeObserver {

    private int day = 0;
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

    public void nextDay(){
        this.day ++;
        this.statistics.updateStatistics();
    }

    public int getDay(){return this.day;}

    public SimulationEngine getSimulationEngine(){return this.simulationEngine;}
    public Statistics getStatistics(){return  this.statistics;}
    public WorldProperties getProperties(){return this.properties;}
    public SteppeJungleMap getMap(){return this.map;}
}
