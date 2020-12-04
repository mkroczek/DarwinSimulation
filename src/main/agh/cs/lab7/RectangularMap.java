package agh.cs.lab7;

public class RectangularMap extends AbstractWorldMap {
    private int width;
    private int heigth;

    public RectangularMap(int width, int height){
        if (width > 0 && height > 0) {
            this.width = width;
            this.heigth = height;
        }
    }

    public boolean canMoveTo(Vector2d position){
        if (position.x <= width && position.x >= 0 && position.y <= heigth && position.y >=0)
            return !isOccupied(position);
        else
            return false;
    }
}
