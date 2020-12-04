package agh.cs.lab7;

import agh.cs.lab7.interfaces.IMapElement;
import agh.cs.lab7.interfaces.IPositionChangeObserver;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private Comparator xComp = new XComparator();
    private Comparator yComp = new YComparator();

    private SortedSet<Vector2d> xSorted = new TreeSet<Vector2d>(xComp);
    private SortedSet<Vector2d> ySorted = new TreeSet<Vector2d>(yComp);

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.xSorted.remove(oldPosition);
        this.ySorted.remove(oldPosition);
        this.xSorted.add(newPosition);
        this.ySorted.add(newPosition);
    }

    public void add(Vector2d position){
        this.xSorted.add(position);
        this.ySorted.add(position);
    }

    public int[] getBoundaries(){
        return new int[] {this.xSorted.first().x, this.xSorted.last().x, this.ySorted.first().y, this.ySorted.last().y};
    }
}
