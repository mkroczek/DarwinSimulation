package grassFieldTests;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.GrassField;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class placeTest {
    private MoveDirection[] directions = {};
    private IWorldMap map;
    private Vector2d[] positions = {};
    private IEngine engine;

    public boolean rightNumberOfElements(int requiredNum){
        return this.engine.getMap().getListOfAnimals().size() == requiredNum;
    }

    public boolean rightPositions(Vector2d[] pos){
        for (int i = 0; i < this.engine.getMap().getListOfAnimals().size(); i++){
            if (!this.engine.getMap().getListOfAnimals().get(i).getPosition().equals(pos[i]))
                return false;
        }
        return true;
    }

    @Before
    public void initialize(){
        this.map = new GrassField(10);
    }

    @Test
    public void differentPositions(){
        this.positions = new Vector2d[] {new Vector2d(2,2), new Vector2d(3,4)};
        this.engine = new SimulationEngine(directions, map, positions);

        //is there as many animals on the map as we want to
        assertTrue(rightNumberOfElements(positions.length));

        //are the animals placed on the right position
        assertTrue(rightPositions(this.positions));

    }

    @Test
    public void samePositions(){
        this.positions = new Vector2d[] {new Vector2d(2,2), new Vector2d(2,2)};
        this.engine = new SimulationEngine(directions, map, positions);

        //are there as many animals on the map as we want to
        assertTrue(rightNumberOfElements(1));

        //are the animals placed on the right position
        assertTrue(rightPositions(Arrays.copyOf(this.positions,1)));

    }

}
