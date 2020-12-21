package agh.cs.lab7.interfaces;

import agh.cs.lab7.World;
import agh.cs.lab7.mapElements.Animal;
import agh.cs.lab7.DirectionsParser;
import agh.cs.lab7.Vector2d;
import agh.cs.lab7.world.RectangularArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     * @param object
     *            The object to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    void place(IMapElement object);

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

}