package dec04;

public class Main {

    public static void main(String[] args) {
        String key = "yzbqklnj";
        HashFinder finder = new HashFinder(key);
        System.out.println("Result: " + finder.findIntToHashWithLeading0s());
    }

}
