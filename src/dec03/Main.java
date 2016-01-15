package dec03;

public class Main {

    public static void main(String[] args) {
        if(args.length == 1) {
            HouseTracker tracker = new HouseTracker(args[0]);
            // tracker.executeInstructions();
            tracker.executeInstructionsWithRobot();
            System.out.println("Houses visited: " + tracker.countHousesVisited());
        }
    }

}
