package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;

import javax.swing.*;
import java.awt.*;

public class ActionLogPanel extends JPanel {

    private World world;
    private JPanel map;
    private JPanel buttonsPanel;
    private JPanel statisticsPanel;
    private JPanel optionsPanel;

    public ActionLogPanel(World world, JPanel map){
        this.map = map;
        this.world = world;
        initializeLayout();
    }
    private void initializeLayout(){

        setLayout(null);
        setPreferredSize(new Dimension(Constants.ACTION_LOG_WIDTH, Constants.ACTION_LOG_HEIGHT));
        buttonsPanel = new ButtonsPanel(this.world);
        statisticsPanel = new StatisticsPanel(this.world);
        optionsPanel = new OptionsPanel(this.world, this.map);

        add(buttonsPanel);
        add(statisticsPanel);
        add(optionsPanel);
    }

}
