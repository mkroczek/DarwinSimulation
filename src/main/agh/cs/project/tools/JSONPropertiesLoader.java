package agh.cs.project.tools;

import agh.cs.project.interfaces.IPropertiesLoader;
import agh.cs.project.dataStructures.WorldProperties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONPropertiesLoader implements IPropertiesLoader {
    private JSONParser parser = new JSONParser();
    private WorldProperties properties;

    public JSONPropertiesLoader(WorldProperties properties){
        this.properties = properties;
    }

    public void loadProperties(String filepath){

        try{
            Object obj = this.parser.parse(new FileReader(filepath));
            JSONObject jsonObject = (JSONObject) obj;
            this.properties.setMapWidth(((Long)jsonObject.get("width")).intValue());
            this.properties.setMapHeight(((Long)jsonObject.get("height")).intValue());
            this.properties.setStartEnergy(((Long)jsonObject.get("startEnergy")).intValue());
            this.properties.setMoveEnergy(((Long)jsonObject.get("moveEnergy")).intValue());
            this.properties.setPlantEnergy(((Long)jsonObject.get("plantEnergy")).intValue());
            this.properties.setJungleWidth(((Long)jsonObject.get("jungleWidth")).intValue());
            this.properties.setJungleHeight(((Long)jsonObject.get("jungleHeight")).intValue());
            this.properties.setPlantsPerDay(((Long)jsonObject.get("plantsPerDay")).intValue());
            this.properties.setStartNumberOfAnimals(((Long)jsonObject.get("startNumberOfAnimals")).intValue());
            this.properties.setStartNumberOfGrass(((Long)jsonObject.get("startNumberOfGrass")).intValue());
        }
        catch(FileNotFoundException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
        catch(ParseException e){e.printStackTrace();}
        catch(Exception e){e.printStackTrace();}
    }
}
