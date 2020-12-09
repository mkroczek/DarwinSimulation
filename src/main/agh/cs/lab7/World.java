package agh.cs.lab7;

import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.SteppeJungleMap;

public class World {
    public static void main(String[] args) {
        try {
            IWorldMap map = new SteppeJungleMap(10, 10, 4, 4);
            Vector2d[] positions = {new Vector2d(2, 8), new Vector2d(0, 4), new Vector2d(1,4), new Vector2d(0,3)};
            IEngine engine = new SimulationEngine(map, positions);
            engine.run();
        }catch (IllegalArgumentException exception){
            System.out.println(exception);
        }
    }
}
