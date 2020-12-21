package agh.cs.project.dataStructures;

import agh.cs.project.mapElements.Animal;

import java.util.*;

public class Offspring {
    private final Map<Integer, Animal> children = new LinkedHashMap<>();

    public void addChild(Animal animal){
        this.children.put(animal.getBirthDate(), animal);
    }

    public int getNumberOfChildren(){
        return this.children.size();
    }

    public Set<Animal> getChildren(int since){
        Set<Animal> children = new HashSet<>();
        for (Map.Entry<Integer, Animal> entry : this.children.entrySet()){
            if (entry.getKey() >= since)
                children.add(entry.getValue());
        }
        return children;
    }

    public Set<Animal> getOffspring(int since){
        Set<Animal> offset = getChildren(since);
        for (Animal children : this.children.values()){
            offset.addAll(children.getOffspring(since));
        }
        return offset;
    }

    public int getNumberOfOffspring(int since){
        return getOffspring(since).size();
    }

    public int getNumberOfChildren(int since){
        return getChildren(since).size();
    }

    public int getNumberOfOffspring(){
        return getNumberOfOffspring(0);
    }


}
