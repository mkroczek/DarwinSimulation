package agh.cs.project.simulationGUI;

import agh.cs.project.constants.Constants;
import agh.cs.project.world.World;
import agh.cs.project.tools.AverageStatisticsGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsLogPanel extends JPanel implements ActionListener{
    private World world;
    private JPanel map;
    private JButton emphasizeButton;
    private JButton generateButton;
    private JTextArea n_eras;
    private int shift = 5;

    public OptionsLogPanel(World world, JPanel map){
        this.map = map;
        this.world = world;
        initializeLayout();
    }
    private void initializeLayout(){
        setBounds(0, Constants.TITLE_HEIGHT, Constants.OPTIONS_PANEL_WIDTH, Constants.OPTIONS_PANEL_HEIGHT-Constants.TITLE_HEIGHT);
        setLayout(null);

        JLabel showDominant = new JLabel("Click to show all animals with dominant gen");
        showDominant.setBounds(0,shift,Constants.OPTIONS_PANEL_WIDTH, 20);
        showDominant.setFont(Constants.OPTIONS_FONT);

        emphasizeButton = new JButton("SHOW");
        emphasizeButton.setBounds((Constants.OPTIONS_PANEL_WIDTH-Constants.OPTIONS_BUTTON_WIDTH)/2, 2*shift+20, Constants.OPTIONS_BUTTON_WIDTH, Constants.OPTIONS_BUTTON_HEIGHT);
        emphasizeButton.addActionListener(this);

        JTextArea stats = new JTextArea("Insert number of days and click generate, to generate statistics after n days");
        stats.setFont(Constants.OPTIONS_FONT);
        stats.setLineWrap(true);
        stats.setWrapStyleWord(true);
        stats.setEditable(false);
        stats.setBackground(null);
        stats.setBounds(0,4*shift+20+Constants.OPTIONS_BUTTON_HEIGHT, Constants.OPTIONS_PANEL_WIDTH, 40);

        n_eras = new JTextArea();
        n_eras.setFont(Constants.INPUT_FONT);
        n_eras.setBounds(shift, 5*shift+60+Constants.OPTIONS_BUTTON_HEIGHT, (Constants.OPTIONS_PANEL_WIDTH-3*shift)/2, Constants.OPTIONS_BUTTON_HEIGHT);

        generateButton = new JButton("GENERATE");
        generateButton.setBounds(2*shift+(Constants.OPTIONS_PANEL_WIDTH-3*shift)/2, 5*shift+60+Constants.OPTIONS_BUTTON_HEIGHT, (Constants.OPTIONS_PANEL_WIDTH-3*shift)/2, Constants.OPTIONS_BUTTON_HEIGHT);
        generateButton.addActionListener(this);

        add(showDominant);
        add(emphasizeButton);
        add(stats);
        add(n_eras);
        add(generateButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.emphasizeButton) {
            ((MapPanel) this.map).showDominant();
        }

        else if (e.getSource() == this.generateButton) {
            try{
                int n = Integer.parseInt(this.n_eras.getText());
                if (n > 0) {
                    AverageStatisticsGenerator generator = new AverageStatisticsGenerator(n, this.world);
                }
            }catch (NumberFormatException exception) {
                System.out.println("Value of days must be positive Integer");
            }
        }
    }
}
