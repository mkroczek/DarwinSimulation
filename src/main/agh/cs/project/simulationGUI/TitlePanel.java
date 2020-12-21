package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;

import javax.swing.*;

public class TitlePanel extends JPanel{

    private JLabel title;

    public TitlePanel(String title){
        this.title = new JLabel(title);
        initializeLayout();
    }

    private void initializeLayout(){
        setBackground(Constants.TITLE_COLOR);
        this.title.setVerticalTextPosition(JLabel.CENTER);
        this.title.setHorizontalTextPosition(JLabel.CENTER);
        setBounds(0,0,Constants.BUTTONS_PANEL_WIDTH, Constants.BUTTONS_PANEL_HEIGHT/2);
        title.setFont(Constants.TITLE_FONT);

        add(this.title);
    }
}
