package agh.cs.lab7;

import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.SteppeJungleMap;

public class World {

    private WorldProperties properties;
    private IWorldMap map;
    private IEngine simulationEngine;

    public World(String filepath){
        this.properties = new WorldProperties(filepath);
        this.map = new SteppeJungleMap(this, this.properties.getMapWidth(), this.properties.getMapHeight(), this.properties.getJungleWidth(), this.properties.getJungleHeight());
        this.simulationEngine = new SimulationEngine(this.properties, this.map);
    }

    public void startSimulation(){
        this.simulationEngine.run();
    }

}
