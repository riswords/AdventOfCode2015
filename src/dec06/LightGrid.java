package dec06;

import java.util.ArrayList;

public class LightGrid {

    private boolean[][] lights;

    public LightGrid(int xDimension, int yDimension) {
        lights = new boolean[xDimension][yDimension];
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
                        lights[row][col] = false;
                        break;
                    case TURN_ON:
                        lights[row][col] = true;
                        break;
                    case TOGGLE:
                        lights[row][col] = !lights[row][col];
                        break;
                }
            }
        }
    }

    public int countLitLights() {
        int count = 0;
        for(int row = 0; row < lights.length; ++row) {
            for(int col = 0; col < lights[0].length; ++col) {
                count += lights[row][col] ? 1 : 0;
            }
        }
        return count;
    }
}
