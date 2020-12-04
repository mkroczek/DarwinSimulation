package agh.cs.lab7;

public class DirectionsParser {

    public MapDirection parseDirection(int direction, MapDirection orientation){
        if (direction <= 0)
            return orientation;
        else
            return parseDirection(direction-1, orientation.next());
    }
}
