package agh.cs.project.interfaces;

import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.mapElements.Animal;

public interface IPositionChangedObserver {
    /**
     *
     * @param oldPosition
     * @param newPosition
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
