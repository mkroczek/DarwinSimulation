package agh.cs.lab7.interfaces;

import agh.cs.lab7.Vector2d;

public interface IPositionChangeObserver {
    /**
     *
     * @param oldPosition
     * @param newPosition
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
