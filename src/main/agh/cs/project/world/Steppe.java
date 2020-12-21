package agh.cs.project.world;

import agh.cs.project.dataStructures.Vector2d;

public class Steppe extends RectangularArea {
    public Steppe(Vector2d frameLeftBottom, Vector2d frameRightTop){
        super(frameLeftBottom, frameRightTop);
    }

    public String toString() {
        return "s";
    }
}
