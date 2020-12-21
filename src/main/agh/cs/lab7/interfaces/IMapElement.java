package agh.cs.lab7.interfaces;

import agh.cs.lab7.Vector2d;

public interface IMapElement {
    /**
     * Return position of an element.
     *
     * @return Position of an element.
     */
    Vector2d getPosition();

    /**
     * Change object status into dead.
     */
    void die();

    /**
     *
     * @param observer
     */
    void addObserver(Object observer);

    void removeObserver(Object observer);

    /**
     * Return object representation as String.
     *
     * @return Object representation as String.
     */
    String toString();
    int getEnergy();
}
