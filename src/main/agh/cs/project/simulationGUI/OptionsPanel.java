package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    private World world;
    private JPanel map;
    private JPanel titlePanel;
    private JPanel logPanel;

    public OptionsPanel(World world, JPanel map){
        this.map = map;
        this.world = world;
        initializeLayout();
    }

    private void initializeLayout(){
        setBounds(Constants.OPTIONS_PANEL_XSHIFT, Constants.OPTIONS_PANEL_YSHIFT, Constants.OPTIONS_PANEL_WIDTH, Constants.OPTIONS_PANEL_HEIGHT);
        setLayout(null);
        setBackground(Color.WHITE);
        this.titlePanel = new TitlePanel("OPTIONS");
        this.logPanel = new OptionsLogPanel(this.world, this.map);

        add(titlePanel);
        add(logPanel);

    }

}
