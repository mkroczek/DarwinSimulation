package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.dataStructures.Vector2d;
import agh.cs.project.world.World;
import agh.cs.project.interfaces.IDayChangeObserver;
import agh.cs.project.mapElements.Animal;
import agh.cs.project.animalSpy.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

public class MapPanel extends JPanel implements IDayChangeObserver, MouseListener {

    public boolean emphasizeAnimals = false;
    private final int objWidth, objHeight, jungleWidth, jungleHeight;
    private final World world;
    private JDialog dialog;

    public MapPanel(World world){
        this.world = world;
        this.objWidth = Constants.MAP_WIDTH/this.world.getProperties().getMapWidth();
        this.objHeight = Constants.MAP_HEIGHT/this.world.getProperties().getMapHeight();
        this.jungleWidth = objWidth*this.world.getProperties().getJungleWidth();
        this.jungleHeight = objHeight*this.world.getProperties().getJungleHeight();
        initializeLayout();
    }
    private void initializeLayout(){
        addMouseListener(this);
        setPreferredSize(new Dimension(Constants.MAP_WIDTH, Constants.MAP_HEIGHT));
        setBackground(Constants.STEPPE_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //paint jungle
        g.setColor(Constants.JUNGLE_COLOR);
        g.fillRect(this.world.getMap().getJungleLeftBottom().x*objWidth,
                   reverseY(this.world.getMap().getJungleRightTop().y)*objHeight,
                      this.jungleWidth, this.jungleHeight);

        drawAnimals(g);
        drawGrasses(g);
        this.emphasizeAnimals = false;
    }

    private void drawAnimals(Graphics g){
        ArrayList<Animal> animals = this.world.getMap().getLivingAnimals();
        ArrayList<Animal> dominantAnimals = new ArrayList<>();
        if(this.emphasizeAnimals) {
            dominantAnimals = this.world.getMap().getAnimalsWithDominantGen();
        }
        for (Animal animal : animals){
            if (dominantAnimals.contains(animal))
                this.drawAnimal(animal,g,true);
            else
                this.drawAnimal(animal, g, false);
        }
    }

    private void drawAnimal(Animal animal, Graphics g, boolean emphasized){

        if (!emphasized) {
            int green = 0;
            int blue = 0;
            int red = 255;
            int colorComp = Math.min(255 * animal.getEnergy() / animal.getStartEnergy(), 255);
            int reversedY = this.reverseY(animal.getPosition().y);
            Color animalColor = new Color(colorComp, green, blue);
            g.setColor(animalColor);
            g.fillRect(animal.getPosition().x * objWidth, reversedY * objHeight, objWidth, objHeight);
        }
        else {
            int reversedY = this.reverseY(animal.getPosition().y);
            g.setColor(Constants.EMPHASIZED_ANIMAL_COLOR);
            g.fillRect(animal.getPosition().x * objWidth, reversedY * objHeight, objWidth, objHeight);
        }
    }

    private void drawGrasses(Graphics g){
        Set<Vector2d> grasses = this.world.getMap().getGrassesPositions();
        g.setColor(Constants.GRASS_COLOR);
        for (Vector2d position : grasses){
            g.fillRect(position.x*objWidth, this.reverseY(position.y)*objHeight, objWidth, objHeight);
        }
    }

    private int reverseY(int y){
        return this.world.getProperties().getMapHeight()-1-y;
    }

    @Override
    public void nextDay() {
        repaint();
    }

    public void showDominant(){
        this.emphasizeAnimals = true;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!this.world.getSimulationEngine().isRunning()){
            int x = e.getX()/objWidth;
            int y = e.getY()/objHeight;
            y = reverseY(y);
            Vector2d position = new Vector2d(x,y);
            Animal chosenAnimal = this.world.getMap().getAnimal(position);
            dialog = new DialogPanel(chosenAnimal, this.world);
            dialog.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
