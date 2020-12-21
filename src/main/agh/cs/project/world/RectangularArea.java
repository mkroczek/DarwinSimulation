package agh.cs.project.world;

import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.interfaces.IMapArea;
import agh.cs.project.interfaces.IRectangle;

import java.util.HashSet;
import java.util.Set;

public class RectangularArea implements IMapArea, IRectangle {
    protected int width;
    protected int height;
    protected Vector2d leftBottom;
    protected Vector2d rightTop;
    protected Set<IMapArea> excludedAreas = new HashSet<>();

    public RectangularArea(Vector2d frameLeftBottom, Vector2d frameRightTop){
        this.width = frameRightTop.x-frameLeftBottom.x+1;
        this.height = frameRightTop.y- frameLeftBottom.y+1;
        this.leftBottom = frameLeftBottom;
        this.rightTop = frameRightTop;
    }

    public boolean comprisePosition (Vector2d position) {
        return (this.leftBottom.precedes(position) && this.rightTop.follows(position));
    }

    public boolean includePosition (Vector2d position){
        if (!comprisePosition(position))
            return false;
        for (IMapArea area : this.excludedAreas) {
            if (area.comprisePosition(position))
                return false;
        }
        return true;
    }

    public void excludeArea(IMapArea area) {
        this.excludedAreas.add(area);
    }

    public boolean removeExcludedArea(IMapArea area){
        return this.excludedAreas.remove(area);
    }

    public Vector2d getLeftBottom() {
        return this.leftBottom;
    }

    public Vector2d getRightTop(){
        return this.rightTop;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    public int getArea(){
        return this.height*this.width;
    }

    public RectangularArea jointArea(RectangularArea subArea){
        Vector2d leftBottom = this.leftBottom.upperRight(subArea.leftBottom);
        Vector2d rightTop = this.rightTop.lowerLeft(subArea.rightTop);
        return new RectangularArea(leftBottom, rightTop);
    }

}

