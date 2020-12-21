package agh.cs.project.tools;

import agh.cs.project.enums.MapDirection;

public class DirectionsParser {

    public MapDirection parseToDirection(int direction, MapDirection orientation){
        if (direction <= 0)
            return orientation;
        else
            return parseToDirection(direction-1, orientation.next());
    }
}
