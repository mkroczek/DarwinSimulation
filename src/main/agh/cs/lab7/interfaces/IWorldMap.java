package agh.cs.lab7.interfaces;

import agh.cs.lab7.Animal;
import agh.cs.lab7.DirectionsParser;
import agh.cs.lab7.MapBoundary;
import agh.cs.lab7.Vector2d;

import java.util.List;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Count position where will animal be after move
     *
     * @param position
     *              Position of object, Vector representing move shift
     * @return Position where object should be located after move.
     */
    Vector2d countMovePosition(Vector2d position, Vector2d moveVector);

    /**
     * Place a animal on the map.
     *
     * @param animal
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    void place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */
    Object objectAt(Vector2d position);

    List<Animal> getListOfAnimals();

    MapBoundary getBoundary();

    DirectionsParser getParser();

}