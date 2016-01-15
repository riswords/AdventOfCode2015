package dec06;

import java.util.ArrayList;

public class BrightnessLightGrid {
    private int[][] lights;

    public BrightnessLightGrid(int xDimension, int yDimension) {
        lights = new int[xDimension][yDimension];
    }

    public void executeInstructions(ArrayList<Instruction> instructions) {
        for(Instruction instruction : instructions) {
            executeInstruction(instruction);
        }
    }

    private void executeInstruction(Instruction instruction) {
        for(int row = instruction.getStart().getX(); row <= instruction.getEnd().getX(); row++) {
            for(int col = instruction.getStart().getY(); col <= instruction.getEnd().getY(); col++) {
                switch(instruction.getToDo()) {
                    case TURN_OFF:
                        lights[row][col] -= (lights[row][col] > 0) ? 1 : 0;
                        break;
                    case TURN_ON:
                        lights[row][col] += 1;
                        break;
                    case TOGGLE:
                        lights[row][col] += 2;
                        break;
                }
            }
        }
    }

    public int sumBrightness() {
        int sum = 0;
        for(int row = 0; row < lights.length; ++row) {
            for(int col = 0; col < lights[0].length; ++col) {
                sum += lights[row][col];
            }
        }
        return sum;
    }
}
