package agh.cs.project.interfaces;

import agh.cs.project.dataStructures.Vector2d;

public interface IRectangle {
    Vector2d getLeftBottom();
    Vector2d getRightTop();
    int getWidth();
    int getHeight();
}
