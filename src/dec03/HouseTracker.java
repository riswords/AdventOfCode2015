package dec03;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.Location;

public class HouseTracker {

    private String instructions = "";
    private HashMap<Integer, SortedSet<Integer>> houses;
    private Location currentLocation = new Location(0, 0);
    private Location robotLocation = new Location(0, 0);

    public HouseTracker(String instructions) {
        this.instructions = instructions;
        houses = new HashMap<Integer, SortedSet<Integer>>();
    }

    public void executeInstructions() {
        visit(currentLocation);
        for(int i = 0; i < instructions.length(); ++i) {
            move(instructions.charAt(i));
            visit(currentLocation);
        }
    }

    public void executeInstructionsWithRobot() {
        visit(currentLocation);
        for(int i = 0; i < instructions.length(); ++i) {
            Location locToUpdate = i % 2 == 0 ? currentLocation : robotLocation;
            move(instructions.charAt(i), locToUpdate);
            visit(locToUpdate);
        }
    }

    public int countHousesVisited() {
        Stream<SortedSet<Integer>> yCoords = houses.values().stream();
        int sum = yCoords.flatMapToInt(set -> IntStream.of(set.size())).sum();
        return sum;
    }

    private void move(char direction) {
        move(direction, currentLocation);
    }

    private void move(char direction, Location location) {
        switch(direction) {
            case '^':
                location.moveNorth();
                break;
            case 'v':
                location.moveSouth();
                break;
            case '>':
                location.moveEast();
                break;
            case '<':
                location.moveWest();
                break;
            default:
                break;
        }
    }

    private void visit(Location location) {
        int x = location.getX();
        if(!houses.containsKey(x)) {
            SortedSet<Integer> newYs = new TreeSet<Integer>();
            newYs.add(location.getY());
            houses.put(x, newYs);
        }
        else {
            SortedSet<Integer> oldYs = houses.get(x);
            oldYs.add(location.getY());
            // houses.put(x, oldYs);
        }
    }
}
