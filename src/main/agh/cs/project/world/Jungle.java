package agh.cs.project.world;

import agh.cs.project.dataStructures.Vector2d;

public class Jungle extends RectangularArea {

    public Jungle(Vector2d frameLeftBottom, Vector2d frameRightTop, int jungleWidth, int jungleHeight){
        super(frameLeftBottom, frameRightTop);
        this.width = jungleWidth;
        this.height = jungleHeight;
        this.countBorderPoints(frameLeftBottom, frameRightTop);

    }

    private void countBorderPoints(Vector2d frameLeftBottom, Vector2d frameRightTop){
        int frameHeight = frameRightTop.y-frameLeftBottom.y+1;
        int frameWidth = frameRightTop.x-frameLeftBottom.x+1;
        int leftBottomX = frameLeftBottom.x+(frameWidth-this.width)/2;
        int leftBottomY = frameLeftBottom.y+(frameHeight-this.height)/2;
        int rightTopX = leftBottomX + this.width -1;
        int rightTopY = leftBottomY + this.height -1;
        this.leftBottom = new Vector2d(leftBottomX, leftBottomY);
        this.rightTop = new Vector2d(rightTopX, rightTopY);
    }

    public String toString() {
        return "j";
    }
}

