import agh.cs.lab7.MoveDirection;
import agh.cs.lab7.OptionsParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataTest {
    @Test
    public void onlyDirections(){
        OptionsParser parser = new OptionsParser();
        String[] input = {"f", "b", "r", "l"};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertEquals(expected, parser.parse(input));
    }
    @Test
    public void notOnlyDirections(){
        OptionsParser parser = new OptionsParser();
        String[] input = {"f", "b", "h", "x", "r", "xd", "l", ""};
        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertEquals(expected, parser.parse(input));
    }
    @Test
    public void onlyNotDirections(){
        OptionsParser parser = new OptionsParser();
        String[] input = {"s", "p", "ssdsd", "fdgd"};
        MoveDirection[] expected = {};
        assertEquals(expected, parser.parse(input));
    }
}
