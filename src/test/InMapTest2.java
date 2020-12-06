import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.RectangularMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InMapTest2 {
    private MoveDirection[] directions;
    private IWorldMap map;
    private Vector2d[] positions;

    @Before
    public void initialize(){
        this.map = new RectangularMap(10, 5);
        this.positions = new Vector2d[]{ new Vector2d(2,2)};
    }

    @Test
    public void leaveUp(){
        directions = new OptionsParser().parse(new String[]{"f", "f", "f", "f", "f", "f", "f", "f", "f"});
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getMap().getListOfAnimals().get(0).getPosition().equals(new Vector2d(2,5)));
    }

    @Test
    public void leaveDown(){
        directions = new OptionsParser().parse(new String[]{"b", "b", "b", "b", "b", "b", "b", "b", "b"});
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getMap().getListOfAnimals().get(0).getPosition().equals(new Vector2d(2,0)));
    }

    @Test
    public void leaveRight(){
        directions = new OptionsParser().parse(new String[]{"r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"});
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getMap().getListOfAnimals().get(0).getPosition().equals(new Vector2d(10,2)));
    }

    @Test
    public void leaveLeft(){
        directions = new OptionsParser().parse(new String[]{"l", "f", "f", "f", "f", "f", "f", "f", "f"});
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getMap().getListOfAnimals().get(0).getPosition().equals(new Vector2d(0,2)));
    }

}
