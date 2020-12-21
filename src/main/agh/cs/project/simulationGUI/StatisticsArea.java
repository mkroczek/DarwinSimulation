package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.enums.StatisticsNames;
import agh.cs.project.world.World;
import agh.cs.project.interfaces.IDayChangeObserver;

import javax.swing.*;
import java.util.Map;

public class StatisticsArea extends JTextArea implements IDayChangeObserver {

    private World world;

    public StatisticsArea(World world){
        this.world = world;
        initializeLayout();
    }
    private void initializeLayout(){
        setBounds(0, Constants.TITLE_HEIGHT, Constants.STATISTICS_PANEL_WIDTH, Constants.STATISTICS_PANEL_HEIGHT-Constants.TITLE_HEIGHT);
        setEditable(false);
        setAutoscrolls(true);
        setFont(Constants.STATISTICS_FONT);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createLoweredBevelBorder()));

    }

    private void updateStatistics(){
        //clear old statistics
        setText(null);

        //print updated statistics
        for (Map.Entry<StatisticsNames, Integer> entry : this.world.getStatistics().entrySet()){
            append(entry.getKey().toString()+": "+entry.getValue()+"\n");
        }
    }
    @Override
    public void nextDay() {
        updateStatistics();
    }
}
