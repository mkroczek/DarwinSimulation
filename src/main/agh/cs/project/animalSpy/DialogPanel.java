package agh.cs.project.animalSpy;

import agh.cs.project.world.World;
import agh.cs.project.constants.Constants;
import agh.cs.project.mapElements.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DialogPanel extends JDialog implements ActionListener {
    private Animal animal;
    private JButton spy;
    private World world;
    private JDialog dialog;

    public DialogPanel(Animal animal, World world){
        this.world = world;
        this.animal = animal;
        initializeLayout();
    }
    private void initializeLayout(){
        setTitle("Follow animal");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(600,100));
        setLocationRelativeTo(null);

        JLabel info = new JLabel("Animal chosen at position: "+animal.getPosition());
        info.setFont(Constants.OUTPUT_FONT);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel genotype = new JLabel("Genotype: "+ Arrays.toString(this.animal.getGenotype()));
        genotype.setFont(Constants.OUTPUT_FONT);
        genotype.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.spy = new JButton("SPY");
        spy.setAlignmentX(Component.CENTER_ALIGNMENT);
        spy.addActionListener(this);

        this.add(info);
        this.add(genotype);
        this.add(spy);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.spy){
            dialog = new SpyPanel(animal, world);
            dialog.setVisible(true);
            super.dispose();
        }
    }
}
