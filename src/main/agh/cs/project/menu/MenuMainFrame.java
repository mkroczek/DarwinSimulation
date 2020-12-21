package agh.cs.project.menu;

import agh.cs.project.app.Application;
import agh.cs.project.constants.Constants;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuMainFrame extends JFrame implements ActionListener {

    private JButton start = new JButton("Start simulation");
    private JTextArea introduction = new JTextArea();

    public MenuMainFrame(){
        initializeLayout();
    }

    private void initializeLayout(){

        setTitle("Simulation Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(300,200));
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER,20,20));

        introduction.setText("Welcome to the Darwin Simulation! Click the button below to initialize new world.");
        introduction.setSize(new Dimension(250, 100));
        introduction.setBackground(null);
        introduction.setWrapStyleWord(true);
        introduction.setLineWrap(true);
        introduction.setEditable(false);
        introduction.setFont(Constants.MENU_FONT);

        start.setAlignmentY(Component.CENTER_ALIGNMENT);
        start.addActionListener(this);
        add(introduction);
        add(start);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application.createWorld();
    }
}
