package agh.cs.lab7;

import java.util.Arrays;

public class OptionsParser {

    public MoveDirection[] parse(String[] args){
        MoveDirection[] directions = new MoveDirection[args.length];
        for (int j = 0; j < args.length; j++)
            directions[j] = null;
        int i = 0;
        for (String direction : args){
            switch (direction){
                case "f":
                case "forward":
                    directions[i] = MoveDirection.FORWARD;
                    i+=1;
                    break;
                case "b":
                case "backward":
                    directions[i] = MoveDirection.BACKWARD;
                    i+=1;
                    break;
                case "r":
                case "right":
                    directions[i] = MoveDirection.RIGHT;
                    i+=1;
                    break;
                case "l":
                case "left":
                    directions[i] = MoveDirection.LEFT;
                    i+=1;
                    break;
                default:
                    throw new IllegalArgumentException(direction+" is not legal move specification");
            }
        }
        return Arrays.copyOf(directions, i);
    }
}
