package agh.cs.project.animalSpy;

import agh.cs.project.world.World;
import agh.cs.project.constants.Constants;
import agh.cs.project.mapElements.Animal;
import agh.cs.project.interfaces.*;

import javax.swing.*;
import java.awt.*;

public class SpyPanel extends JDialog implements IDayChangeObserver{
    private Animal animal;
    private World world;
    private int startDate;
    private JLabel death;
    private JLabel date;
    private JLabel children;
    private JLabel offspring;
    private JLabel position;

    public SpyPanel(Animal animal, World world){
        this.startDate = world.getDay();
        this.world = world;
        this.animal = animal;
        initializeLayout();
    }
    private void initializeLayout(){

        this.world.getSimulationEngine().addDayObserver(this);
        setTitle("Animal information");
        setMinimumSize(new Dimension(300,150));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        date = new JLabel("Spied from: "+this.startDate+", current day: "+this.world.getDay());
        date.setFont(Constants.OUTPUT_FONT);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        position = new JLabel("Current position: "+animal.getPosition());
        position.setFont(Constants.OUTPUT_FONT);
        position.setAlignmentX(Component.CENTER_ALIGNMENT);

        children = new JLabel("Number of children: "+animal.getNumberOfChildren(startDate));
        children.setFont(Constants.OUTPUT_FONT);
        children.setAlignmentX(Component.CENTER_ALIGNMENT);

        offspring = new JLabel("Total offspring: "+ animal.getNumberOfOffspring(startDate));
        offspring.setFont(Constants.OUTPUT_FONT);
        offspring.setAlignmentX(Component.CENTER_ALIGNMENT);

        death = new JLabel("Death date: ");
        death.setFont(Constants.OUTPUT_FONT);
        death.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(date);
        this.add(position);
        this.add(children);
        this.add(offspring);
        this.add(death);

    }

    @Override
    public void nextDay() {
        this.date.setText("Spied from: "+this.startDate+", current day: "+this.world.getDay());
        this.children.setText("Number of children: "+animal.getNumberOfChildren(startDate));
        this.offspring.setText("Total offspring: "+ animal.getNumberOfOffspring(startDate));
        if (animal.getDeathDate() >= 0) {
            this.death.setText("Death date: " + animal.getDeathDate());
            this.position.setText("Died at position: "+ animal.getPosition());
        }
        else {
            this.death.setText("Death date: ");
            this.position.setText("Current position: "+ animal.getPosition());
        }
    }

    @Override
    public void dispose(){
        this.world.getSimulationEngine().removeDayObserver(this);
        super.dispose();
    }

}
