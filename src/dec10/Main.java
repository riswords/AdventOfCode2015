package dec10;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0) {
            LookAndSay lookAndSay = new LookAndSay(args[0]);
            System.out.println(lookAndSay.lookAndSay(40));
        }
    }

}
