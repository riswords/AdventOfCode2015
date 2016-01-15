package dec07;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import dec07.Wire.Operator;

public class Main {

    public static final Pattern UNARY_OP_PATTERN = Pattern.compile("NOT");
    public static final Pattern BINARY_OP_PATTERN = Pattern.compile("(AND)|(OR)|(LSHIFT)|(RSHIFT)");
    public static final Pattern ARROW_PATTERN = Pattern.compile("->");

    public static void main(String[] args) {
        HashMap<String, Wire> wires = parseArguments(args);
        Emulator emulator = new Emulator(wires);

        // part 2
        Wire bWire = new ConstantWire("b", 16076);
        wires.put("b", bWire);
        emulator.replaceWire("b", bWire);
        // end part 2 adjustment

        emulator.runEmulation();
        System.out.println(wires.get("a").getSignal().get());
    }

    public static HashMap<String, Wire> parseArguments(String[] args) {
        HashMap<String, Wire> wires = new HashMap<String, Wire>();
        Scanner scanner = new Scanner(String.join(" ", args));
        while(scanner.hasNext()) {
            if(scanner.hasNext(UNARY_OP_PATTERN))
                parseUnaryOp(wires, scanner);
            else if(scanner.hasNextInt())
                parseNumericStart(wires, scanner);
            else
                parseIdentifierStart(wires, scanner);
        }
        scanner.close();
        return wires;
    }

    public static void parseUnaryOp(HashMap<String, Wire> wires, Scanner scanner) {
        Optional<Operator> operator = Emulator.getOpString(scanner.next());
        String rightWireName = scanner.next();
        Wire rightWire = wires.getOrDefault(rightWireName, new PlaceholderWire(rightWireName));

        scanner.next(); // parse arrow

        String resultWireName = scanner.next();
        Wire newWire = new UnaryOpWire(resultWireName, rightWire, operator.get());
        wires.put(resultWireName, newWire);
    }

    public static void parseNumericStart(HashMap<String, Wire> wires, Scanner scanner) {
        int constant = scanner.nextInt();
        if(scanner.hasNext(ARROW_PATTERN))
            parseConstantWire(wires, constant, scanner);
        else if(scanner.hasNext(BINARY_OP_PATTERN)) {
            parseBinaryOp(wires, constant, scanner);
        }
    }

    public static void parseConstantWire(HashMap<String, Wire> wires, int input, Scanner scanner) {
        scanner.next(); // parse ->
        String resultWireName = scanner.next();
        Wire newWire = new ConstantWire(resultWireName, input);
        wires.put(resultWireName, newWire);
    }

    public static void parseBinaryOp(HashMap<String, Wire> wires, int leftConstant, Scanner scanner) {
        Wire leftWire = new ConstantWire(leftConstant);
        Operator operator = Emulator.getOpString(scanner.next()).get();
        Wire rightWire = null;

        // right operand is numeric
        if(scanner.hasNextInt()) {
            int rightConstant = scanner.nextInt();
            rightWire = new ConstantWire(rightConstant);

        }
        // right operand is an identifier
        else {
            String rightWireName = scanner.next();
            rightWire = wires.getOrDefault(rightWireName, new PlaceholderWire(rightWireName));
        }

        scanner.next(); // parse arrow
        String resultWireName = scanner.next();
        Wire resultWire = new BinaryOpWire(resultWireName, leftWire, rightWire, operator);
        wires.put(resultWireName, resultWire);
    }

    public static void parseIdentifierStart(HashMap<String, Wire> wires, Scanner scanner) {
        String leftWireName = scanner.next();
        Wire leftWire = wires.getOrDefault(leftWireName, new PlaceholderWire(leftWireName));
        if(scanner.hasNext(ARROW_PATTERN)) {
            scanner.next(); // parse arrow
            String resultWireName = scanner.next();
            Wire resultWire = new StraightThroughWire(resultWireName, leftWire);
            wires.put(resultWireName, resultWire);
        }
        else {
            Operator operator = Emulator.getOpString(scanner.next()).get();
            Wire rightWire = null;
            if(scanner.hasNextInt()) {
                int rightConstant = scanner.nextInt();
                rightWire = new ConstantWire(rightConstant);
            }
            else {
                String rightWireName = scanner.next();
                rightWire = wires.getOrDefault(rightWireName, new PlaceholderWire(rightWireName));
            }
            scanner.next(); // parse arrow
            String resultWireName = scanner.next();
            Wire resultWire = new BinaryOpWire(resultWireName, leftWire, rightWire, operator);
            wires.put(resultWireName, resultWire);
        }
    }
}
