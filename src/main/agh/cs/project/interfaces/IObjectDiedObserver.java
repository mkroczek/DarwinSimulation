package agh.cs.project.interfaces;

import agh.cs.project.dataStructures.Vector2d;

public interface IObjectDiedObserver {
    /**
     *
     * @param object
     */
    void objectDied(IMapElement object, Vector2d position);
}
