package agh.cs.project.mapElements;

import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.interfaces.IMapElement;
import agh.cs.project.interfaces.IObjectDiedObserver;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMapElement implements IMapElement {
    protected Vector2d position;
    protected List<IObjectDiedObserver> objectDiedObservers = new ArrayList<>();

    public void addObserver(Object observer){
        if (observer instanceof IObjectDiedObserver)
            this.objectDiedObservers.add((IObjectDiedObserver)observer);
    }

    public void removeObserver(Object observer){
        if (observer instanceof IObjectDiedObserver)
            this.objectDiedObservers.remove(observer);
    }

    public void die(){
        this.objectDied();
    }

    public void objectDied(){
        for (IObjectDiedObserver observer : this.objectDiedObservers){
            observer.objectDied(this, this.getPosition());
        }
    }

    public abstract int getEnergy();

    public abstract void subtractEnergy(int energy);

    public Vector2d getPosition(){
        return this.position;
    }
}
