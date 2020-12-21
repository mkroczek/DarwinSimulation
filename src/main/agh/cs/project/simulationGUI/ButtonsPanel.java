package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel implements ActionListener {

    private World world;
    private JButton pause_button;
    private JButton start_button;
    private JPanel titlePanel;

    public ButtonsPanel(World world){
        this.world = world;
        initializeLayout();
    }

    private void initializeLayout(){
        setBounds(Constants.BUTTONS_PANEL_XSHIFT, Constants.BUTTONS_PANEL_YSHIFT, Constants.BUTTONS_PANEL_WIDTH, Constants.BUTTONS_PANEL_HEIGHT);
        setBackground(Color.BLUE);
        setLayout(null);

        this.titlePanel = new TitlePanel("CONTROL CENTER");

        this.pause_button = new JButton("PAUSE");
        pause_button.setBounds(0,Constants.BUTTONS_PANEL_HEIGHT/2, Constants.BUTTONS_PANEL_WIDTH/2, Constants.BUTTONS_PANEL_HEIGHT/2);
        this.start_button = new JButton("START");
        start_button.setBounds(Constants.BUTTONS_PANEL_WIDTH/2,Constants.BUTTONS_PANEL_HEIGHT/2, Constants.BUTTONS_PANEL_WIDTH/2, Constants.BUTTONS_PANEL_HEIGHT/2);
        pause_button.addActionListener(this);
        start_button.addActionListener(this);

        add(titlePanel);
        add(pause_button);
        add(start_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pause_button) {
            this.world.pauseSimulation();
        }
        else if(e.getSource() == this.start_button) {
            this.world.startSimulation();
        }
    }
}
