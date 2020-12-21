import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.world.World;
import agh.cs.project.mapElements.Animal;
import agh.cs.project.dataStructures.Genes;
import agh.cs.project.dataStructures.AnimalStorage;
import agh.cs.project.world.SteppeJungleMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AnimalStorageTest {
    SteppeJungleMap map;
    Vector2d position;
    Animal animal1;
    Animal animal2;
    Animal animal3;
    AnimalStorage testStorage;


    @Before
    public void init(){
        map = new SteppeJungleMap(new World(0,"data/properties.json"), 10,10,4,4);
        position = new Vector2d(2,2);
        animal1 = new Animal(map, position, new Genes(8,32), 20);
        animal2 = new Animal(map, position, new Genes(8,32), 30);
        animal3 = new Animal(map, position, new Genes(8,32), 10);
        testStorage = new AnimalStorage(animal1);
        testStorage.add(animal2);
        testStorage.add(animal3);
    }

    @Test
    public void testOrderWithOneStrongest(){

        System.out.println(animal1+", "+animal2+", "+animal3);
        ArrayList<Animal> strongest = testStorage.getStrongestAnimals();
        assertEquals(new Animal[] {animal2}, strongest.toArray());

    }

    @Test
    public void testOrderWithTwoStrongest(){
        Animal animal4 = new Animal(map,position,new Genes(8,32),30);
        this.testStorage.add(animal4);
        ArrayList<Animal> strongest = testStorage.getStrongestAnimals();
        assertEquals(new Animal[] {animal2,animal4}, strongest.toArray());
    }

}
