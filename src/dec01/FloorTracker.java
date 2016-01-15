package dec01;

public class FloorTracker {
    private String instructions = "";
    private int currentFloor = 0;

    public FloorTracker(String instructions) {
        this.instructions = instructions;
    }

    public int calculateFirstBasementEntry() {
        for(int i = 0; i < instructions.length(); ++i) {
            move(instructions.charAt(i));
            if(currentFloor == -1)
                return i + 1;
        }
        return -1;
    }

    public int calculateFloor() {
        for(int i = 0; i < instructions.length(); ++i) {
            move(instructions.charAt(i));
        }
        return currentFloor;
    }

    private void move(char direction) {
        switch(direction) {
            case '(':
                goUp();
                break;
            case ')':
                goDown();
                break;
            default:
                break;
        }
    }

    private void goUp() {
        currentFloor += 1;
    }

    private void goDown() {
        currentFloor -= 1;
    }
}
