package dec06;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Instruction> instructions = parseInstructions(String.join(" ", args));

        // Part 1
        LightGrid lights = new LightGrid(1000, 1000);
        lights.executeInstructions(instructions);
        System.out.println("Lit lights: " + lights.countLitLights());

        // Part 2
        BrightnessLightGrid brightnessLight = new BrightnessLightGrid(1000, 1000);
        brightnessLight.executeInstructions(instructions);
        System.out.println("Brightness: " + brightnessLight.sumBrightness());
    }

    public static ArrayList<Instruction> parseInstructions(String instructionString) {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        Scanner scanner = new Scanner(instructionString);
        while(scanner.hasNext()) {
            String todo;
            String startRange, endRange;
            String next = scanner.next();

            // turn on/off
            if(next.equals("turn"))
                todo = next + " " + scanner.next();

            // toggle
            else
                todo = next;

            startRange = scanner.next();
            scanner.next();
            endRange = scanner.next();
            instructions.add(new Instruction(todo, startRange, endRange));
        }
        scanner.close();
        return instructions;
    }
}
