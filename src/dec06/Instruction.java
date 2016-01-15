package dec06;

import common.Location;

public class Instruction {
    public enum ToDo {
        TURN_ON, TURN_OFF, TOGGLE
    };

    private ToDo todo;
    private Location start, end;

    public Instruction(String todo, String startRange, String endRange) {
        parseToDo(todo);
        start = parseRange(startRange);
        end = parseRange(endRange);
    }

    private void parseToDo(String string) {
        if(string.startsWith("turn on"))
            todo = ToDo.TURN_ON;
        else if(string.startsWith("turn off"))
            todo = ToDo.TURN_OFF;
        else
            todo = ToDo.TOGGLE;
    }

    private Location parseRange(String range) {
        String[] splitRange = range.split(",");
        try {
            int x = Integer.parseInt(splitRange[0]);
            int y = Integer.parseInt(splitRange[1]);
            return new Location(x, y);
        }
        catch(Exception e) {
            System.err.println("Error occurred while parsing range: " + range);
            e.printStackTrace();
        }
        return null;
    }

    public ToDo getToDo() {
        return todo;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }
}
