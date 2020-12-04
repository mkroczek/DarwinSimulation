package agh.cs.lab7;

import java.util.Comparator;

public class YComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Vector2d v1 = (Vector2d) o1;
        Vector2d v2 = (Vector2d) o2;
        if (v1.y < v2.y)
            return -1;
        else if (v1.y == v2.y)
            return 0;
        else
            return 1;
    }
}