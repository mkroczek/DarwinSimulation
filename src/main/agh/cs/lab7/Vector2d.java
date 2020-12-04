package agh.cs.lab7;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    public boolean precedes(Vector2d other){
        if (this.x <= other.x && this.y <= other.y)
            return true;
        else
            return false;
    }
    public boolean follows(Vector2d other){
        if (this.x >= other.x && this.y >= other.y)
            return true;
        else
            return false;
    }
    public Vector2d upperRight(Vector2d other){
        Vector2d result = new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
        return result;
    }
    public Vector2d lowerLeft(Vector2d other){
        Vector2d result = new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
        return result;
    }
    public Vector2d add(Vector2d other){
        Vector2d result = new Vector2d(this.x + other.x, this.y + other.y);
        return result;
    }
    public Vector2d subtract(Vector2d other){
        Vector2d result = new Vector2d(this.x - other.x, this.y - other.y);
        return result;
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public Vector2d opposite(){
        Vector2d result = new Vector2d(-this.x, -this.y);
        return result;
    }
}
