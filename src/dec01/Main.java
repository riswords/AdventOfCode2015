package dec01;

public class Main {

    public static void main(String[] args) {
        if(args.length >= 1) {
            FloorTracker tracker = new FloorTracker(args[0]);
            System.out.println("First basement entry instruction: " + tracker.calculateFirstBasementEntry());
            System.out.println("Final Floor: " + tracker.calculateFloor());

        }
    }

}
