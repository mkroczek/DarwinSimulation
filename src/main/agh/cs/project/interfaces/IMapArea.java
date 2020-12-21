package agh.cs.project.interfaces;

import agh.cs.project.dataStructures.Vector2d;

public interface IMapArea {
    boolean includePosition(Vector2d position); //true if area comprise position and excluded areas doesn't
    boolean comprisePosition(Vector2d position); //true if position fits in area in geomethric way
    void excludeArea(IMapArea area);
    boolean removeExcludedArea(IMapArea area);
    String toString();
}
