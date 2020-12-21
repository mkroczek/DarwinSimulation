package agh.cs.project.constants;

import java.awt.*;

public class Constants {

    private Constants() {

    }
    public static final String TITLE = "Darwin World Simulation";

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 700;
    public static final int MAP_WIDTH = WINDOW_HEIGHT;
    public static final int MAP_HEIGHT = MAP_WIDTH;
    public static final int ACTION_LOG_WIDTH = WINDOW_WIDTH-MAP_WIDTH;
    public static final int ACTION_LOG_HEIGHT = WINDOW_HEIGHT;
    public static final int BUTTONS_PANEL_XSHIFT = 25;
    public static final int BUTTONS_PANEL_YSHIFT = 25;
    public static final int BUTTONS_PANEL_WIDTH = ACTION_LOG_WIDTH-2*BUTTONS_PANEL_XSHIFT;
    public static final int BUTTONS_PANEL_HEIGHT = ACTION_LOG_HEIGHT/10;
    public static final int STATISTICS_PANEL_YSHIFT = 2*BUTTONS_PANEL_YSHIFT+BUTTONS_PANEL_HEIGHT;
    public static final int STATISTICS_PANEL_WIDTH = BUTTONS_PANEL_WIDTH;
    public static final int STATISTICS_PANEL_HEIGHT = (ACTION_LOG_HEIGHT-(3*BUTTONS_PANEL_YSHIFT+BUTTONS_PANEL_HEIGHT))/2;
    public static final int TITLE_HEIGHT = BUTTONS_PANEL_HEIGHT/2;
    public static final int OPTIONS_PANEL_YSHIFT = STATISTICS_PANEL_YSHIFT+STATISTICS_PANEL_HEIGHT+BUTTONS_PANEL_YSHIFT;
    public static final int OPTIONS_PANEL_XSHIFT = BUTTONS_PANEL_XSHIFT;
    public static final int OPTIONS_PANEL_HEIGHT = ACTION_LOG_HEIGHT-OPTIONS_PANEL_YSHIFT-BUTTONS_PANEL_YSHIFT;
    public static final int OPTIONS_PANEL_WIDTH = BUTTONS_PANEL_WIDTH;
    public static final int OPTIONS_BUTTON_WIDTH = 80;
    public static final int OPTIONS_BUTTON_HEIGHT = 30;

    public static final int GAME_SPEED = 100;

    public static final Color STEPPE_COLOR = new Color(255, 248, 201);
    public static final Color JUNGLE_COLOR = new Color(68, 117, 61);
    public static final Color GRASS_COLOR = new Color(65, 204, 0);
    public static final Color TITLE_COLOR = new Color(186, 213, 255);
    public static final Color EMPHASIZED_ANIMAL_COLOR =  Color.CYAN;

    public static final Font TITLE_FONT = new Font("Comic Sans",Font.BOLD,15);
    public static final Font STATISTICS_FONT = new Font("Comic Sans",0,15);
    public static final Font OPTIONS_FONT = new Font("Comic Sans", 2, 13);
    public static final Font INPUT_FONT = new Font("Comic Sans", 0, 20);
    public static final Font MENU_FONT = new Font("Comic Sans", 0, 15);
    public static final Font OUTPUT_FONT = new Font("Comic Sans", 0, 13);

}
