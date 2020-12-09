package agh.cs.lab7;

import java.util.Random;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NORTHEAST,
    SOUTHEAST,
    SOUTHWEST,
    NORTHWEST;
    public String toString(){
        switch (this){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            case NORTHEAST:
                return "NE";
            case NORTHWEST:
                return "NW";
            case SOUTHEAST:
                return "SE";
            case SOUTHWEST:
                return "SW";
        }
        return null;
    }
    public MapDirection next(){
        switch (this){
            case NORTH:
                return NORTHEAST;
            case NORTHEAST:
                return EAST;
            case EAST:
                return SOUTHEAST;
            case SOUTHEAST:
                return SOUTH;
            case SOUTH:
                return SOUTHWEST;
            case SOUTHWEST:
                return WEST;
            case WEST:
                return NORTHWEST;
            case NORTHWEST:
                return NORTH;
        }
        return null;
    }
    public MapDirection previous(){
        switch (this){
            case NORTH:
                return NORTHWEST;
            case NORTHWEST:
                return WEST;
            case WEST:
                return SOUTHWEST;
            case SOUTHWEST:
                return SOUTH;
            case SOUTH:
                return SOUTHEAST;
            case SOUTHEAST:
                return EAST;
            case EAST:
                return NORTHEAST;
            case NORTHEAST:
                return NORTH;
        }
        return null;
    }
    public Vector2d toUnitVector(){
        switch (this){
            case NORTH:
                return new Vector2d(0,1);
            case NORTHEAST:
                return new Vector2d(1,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            case SOUTH:
                return new Vector2d(0,-1);
            case SOUTHWEST:
                return new Vector2d(-1,-1);
            case WEST:
                return new Vector2d(-1,0);
            case NORTHWEST:
                return new Vector2d(-1,1);
        }
        return null;
    }
    public static MapDirection getRandom(){
        Random generator = new Random();
        MapDirection[] values = MapDirection.values();
        return values[generator.nextInt(values.length)];
    }
}
