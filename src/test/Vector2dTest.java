import agh.cs.project.dataStructures.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Vector2dTest {
    Vector2d position1 = new Vector2d(1,2);
    Vector2d position2 = new Vector2d(-2,1);
    @Test
    public void testEquals(){
        assertEquals(position1.equals(position2), false);
        assertEquals(position1.equals(position1), true);
        assertEquals(position1.equals(new Vector2d(1, 2)), true);
    }
    @Test
    public void testToString(){
        assertEquals(position1.toString(), "(1,2)");
    }
    @Test
    public void testPrecedes(){
        assertEquals(position1.precedes(position2), false);
        assertEquals(position1.precedes(position1), true);
        assertEquals(position2.precedes(position1), true);
    }
    @Test
    public void testFollows(){
        assertEquals(position1.follows(position2), true);
        assertEquals(position1.follows(position1), true);
        assertEquals(position2.follows(position1), false);
    }
    @Test
    public void testUpperRight(){
        assertEquals(position1.upperRight(position2), new Vector2d(1,2));
        assertEquals(position2.upperRight(position2), new Vector2d(-2, 1));
    }
    @Test
    public void testLowerLeft(){
        assertEquals(position1.lowerLeft(position2), new Vector2d(-2,1));
        assertEquals(position1.lowerLeft(new Vector2d(0,5)), new Vector2d(0, 2));
    }
    @Test
    public void testAdd(){
        assertEquals(position1.add(position2), new Vector2d(-1,3));
        assertEquals(position1.add(position1), new Vector2d(2, 4));
    }
    @Test
    public void testSubtract(){
        assertEquals(position1.subtract(position2), new Vector2d(3,1));
        assertEquals(position1.subtract(position1), new Vector2d(0, 0));
    }
    @Test
    public void testOpposite(){
        assertEquals(position1.opposite(), new Vector2d(-1,-2));
        assertEquals(position2.opposite(), new Vector2d(2, -1));
    }
}
