package agh.cs.project.app;

import agh.cs.project.world.World;
import agh.cs.project.menu.MenuMainFrame;

import java.awt.*;
import java.util.ArrayList;

public class Application {

    private static ArrayList<World> worlds = new ArrayList<World>();
    private static String propertiesPath = "data/properties.json";

    public static void main(String[] args) {

        try {
            EventQueue.invokeLater(() -> {
                new MenuMainFrame();
            });
        }catch (IllegalArgumentException exception) {
            System.out.println(exception);
        }

    }

    public static void createWorld(){
        worlds.add(new World(Application.worlds.size(), Application.propertiesPath));
    }

}
