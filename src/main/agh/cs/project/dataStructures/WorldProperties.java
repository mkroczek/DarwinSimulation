package agh.cs.project.dataStructures;

import agh.cs.project.tools.JSONPropertiesLoader;
import agh.cs.project.interfaces.IPropertiesLoader;

public class WorldProperties {
    private int mapWidth;
    private int mapHeight;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private int jungleHeight;
    private int jungleWidth;
    private int plantsPerDay;
    private int startNumberOfAnimals;
    private int startNumberOfGrass;
    private IPropertiesLoader loader;

    public WorldProperties(String filepath){
        this.loader = new JSONPropertiesLoader(this);
        this.loader.loadProperties(filepath);
    }

    //setter
    public void setMapWidth(int width){this.mapWidth = width;}
    public void setMapHeight(int height){this.mapHeight = height;}
    public void setStartEnergy(int startEnergy){this.startEnergy = startEnergy;}
    public void setMoveEnergy(int moveEnergy){this.moveEnergy = moveEnergy;}
    public void setPlantEnergy(int plantEnergy){this.plantEnergy = plantEnergy;}
    public void setJungleHeight(int jungleHeight){this.jungleHeight = jungleHeight;}
    public void setJungleWidth(int jungleWidth){this.jungleWidth = jungleWidth;}
    public void setPlantsPerDay(int plantsPerDay){this.plantsPerDay = plantsPerDay;}
    public void setStartNumberOfAnimals(int startNumberOfAnimals){this.startNumberOfAnimals = startNumberOfAnimals;}
    public void setStartNumberOfGrass(int startNumberOfGrass){this.startNumberOfGrass = startNumberOfGrass;}

    //getter
    public int getMapWidth(){return this.mapWidth;}
    public int getMapHeight(){return this.mapHeight;}
    public int getStartEnergy(){return this.startEnergy;}
    public int getMoveEnergy(){return this.moveEnergy;}
    public int getPlantEnergy(){return this.plantEnergy;}
    public int getJungleHeight(){return this.jungleHeight;}
    public int getJungleWidth(){return this.jungleWidth;}
    public int getPlantsPerDay(){return this.plantsPerDay;}
    public int getStartNumberOfAnimals(){return this.startNumberOfAnimals;}
    public int getStartNumberOfGrass(){return this.startNumberOfGrass;}

}
