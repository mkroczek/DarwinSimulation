//package agh.cs.lab7.world;
//
//import agh.cs.lab7.mapElements.Animal;
//import agh.cs.lab7.mapElements.Grass;
//import agh.cs.lab7.Vector2d;
//
//import java.util.*;
//
//public class GrassField extends AbstractWorldMap {
//    private Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
//    private int n;
//    private int grassBound;
//
//    public GrassField(int n){
//        this.n = n;
//        this.grassBound = (int)Math.sqrt((double)n*10);
//        generateGrassFields();
//    }
//
//    private void generateGrassFields(){
//        Random generator = new Random();
//        for (int i = 0; i < this.n; i++){
//            Vector2d position = new Vector2d(generator.nextInt(this.grassBound), generator.nextInt(this.grassBound));
//            while (isOccupied(position)){
//                position = new Vector2d(generator.nextInt(this.grassBound), generator.nextInt(this.grassBound));
//            }
//            grasses.put(position, new Grass(position));
//            this.boundary.add(position);
//        }
//    }
//
//    public boolean canMoveTo(Vector2d position){
//            return !(objectAt(position) instanceof Animal);
//    }
//
//    @Override
//    public boolean isOccupied(Vector2d position){
//        if(super.isOccupied(position))
//            return true;
//        if (grasses.get(position) != null)
//            return true;
//        return false;
//    }
//
//    @Override
//    public Object objectAt(Vector2d position){
//        Object animal = super.objectAt(position);
//        if (animal != null)
//            return animal;
//        return grasses.get(position);
//    }
//
//}
