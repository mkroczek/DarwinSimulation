import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TwoOnOnePositionTest {

    @Test

    public void twoPlacedOnOnePosition(){
        MoveDirection[] directions = new OptionsParser().parse(new String[]{});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        assertTrue(engine.getMap().getListOfAnimals().size() == 1);
    }

    @Test
    public void oneMovedOnOccupiedPosition(){
        //MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getMap().getListOfAnimals().get(0).getPosition() != engine.getMap().getListOfAnimals().get(1).getPosition());
    }
}
