package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;
import agh.cs.project.interfaces.IDayChangeObserver;

import javax.swing.*;
import java.awt.*;

public class GameMainFrame extends JFrame {

        private JPanel map;
        private JPanel actionLog;
        private World world;

        public GameMainFrame(World world){
            this.world = world;
            initializeLayout();
        }

        private void initializeLayout(){
            setTitle(Constants.TITLE);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            setLayout(new FlowLayout());

            map = new MapPanel(this.world);
            actionLog = new ActionLogPanel(this.world, this.map);
            this.world.getSimulationEngine().addDayObserver((IDayChangeObserver)map);

            //init simulation
            this.world.getSimulationEngine().initSimulation();

            this.add(map);
            this.add(actionLog);

            this.pack();
            setVisible(true);
            setLocationRelativeTo(null);
        }

        @Override
        public void dispose(){
            this.world.getSimulationEngine().stop();
            super.dispose();
        }

}
