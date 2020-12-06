package agh.cs.lab7;

public class DirectionsParser {

    public MapDirection parseToDirection(int direction, MapDirection orientation){
        if (direction <= 0)
            return orientation;
        else
            return parseToDirection(direction-1, orientation.next());
    }
}
