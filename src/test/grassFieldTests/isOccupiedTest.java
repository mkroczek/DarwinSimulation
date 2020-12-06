package grassFieldTests;

import agh.cs.lab7.*;
import agh.cs.lab7.interfaces.IEngine;
import agh.cs.lab7.interfaces.IWorldMap;
import agh.cs.lab7.world.GrassField;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class isOccupiedTest {
    private IEngine engine;
    private OptionsParser parser = new OptionsParser();;

    public void init(Vector2d[] positions, MoveDirection[] directions){
        IWorldMap map = new GrassField(10);
        this.engine = new SimulationEngine(directions, map, positions);
    }

    public boolean rightPositions(Vector2d[] pos){
        for (int i = 0; i < this.engine.getMap().getListOfAnimals().size(); i++){
            if (!this.engine.getMap().getListOfAnimals().get(i).getPosition().equals(pos[i]))
                return false;
        }
        return true;
    }

    @Test
    public void occupied(){
        init(new Vector2d[] {new Vector2d(2,2), new Vector2d(3,4)}, this.parser.parse(new String[]{}));
        this.engine.run();
        for (Animal animal: this.engine.getMap().getListOfAnimals()){
            assertTrue(this.engine.getMap().isOccupied(animal.getPosition()));
        }
    }

}
