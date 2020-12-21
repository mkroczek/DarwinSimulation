package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;
import agh.cs.project.interfaces.IDayChangeObserver;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {

    private JPanel title;
    private JTextArea statistics;
    private World world;

    public StatisticsPanel(World world){
        this.world = world;
        initializeLayout();
    }

    private void initializeLayout(){
        setBounds(Constants.BUTTONS_PANEL_XSHIFT, Constants.STATISTICS_PANEL_YSHIFT, Constants.STATISTICS_PANEL_WIDTH, Constants.STATISTICS_PANEL_HEIGHT);
        setBackground(Color.YELLOW);
        setLayout(null);

        title = new TitlePanel("STATISTICS");
        statistics = new StatisticsArea(this.world);
        this.world.getSimulationEngine().addDayObserver((IDayChangeObserver)statistics);

        add(title);
        add(statistics);
    }

}
