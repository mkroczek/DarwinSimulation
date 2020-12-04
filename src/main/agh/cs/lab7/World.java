package agh.cs.lab7;

import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(4, 2)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }catch (IllegalArgumentException exception){
            System.out.println(exception);
        }
    }
}
