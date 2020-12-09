import agh.cs.lab7.MapDirection;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {

    @Test
    public void testNext(){
        assertEquals(MapDirection.SOUTH.next(), MapDirection.SOUTHWEST);
        assertEquals(MapDirection.SOUTHWEST.next(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.next(), MapDirection.NORTHWEST);
        assertEquals(MapDirection.NORTHWEST.next(), MapDirection.NORTH);
        assertEquals(MapDirection.NORTH.next(), MapDirection.NORTHEAST);
        assertEquals(MapDirection.NORTHEAST.next(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.next(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.SOUTHEAST.next(), MapDirection.SOUTH);
    }
    @Test
    public void testPrevious(){
        assertEquals(MapDirection.SOUTH.previous(), MapDirection.SOUTHEAST);
        assertEquals(MapDirection.SOUTHEAST.previous(), MapDirection.EAST);
        assertEquals(MapDirection.EAST.previous(), MapDirection.NORTHEAST);
        assertEquals(MapDirection.NORTHEAST.previous(), MapDirection.NORTH);
        assertEquals(MapDirection.NORTH.previous(), MapDirection.NORTHWEST);
        assertEquals(MapDirection.NORTHWEST.previous(), MapDirection.WEST);
        assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTHWEST);
        assertEquals(MapDirection.SOUTHWEST.previous(), MapDirection.SOUTH);
    }
}
