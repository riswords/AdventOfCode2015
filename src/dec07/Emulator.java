package dec07;

import java.util.HashMap;
import java.util.Optional;

import dec07.Wire.Operator;

public class Emulator {

    private HashMap<String, Wire> wireMap;

    public Emulator(HashMap<String, Wire> wires) {
        wireMap = wires;
    }

    public void replaceWire(String identifier, Wire newWire) {
        for(Wire wire : wireMap.values())
            wire.updateReferences(identifier, newWire);
    }

    public void runEmulation() {
        for(Wire wire : wireMap.values())
            wire.replacePlaceholders(wireMap);
    }

    public static Optional<Operator> getOpString(String string) {
        switch(string) {
            case "AND":
                return Optional.of(Operator.AND);
            case "OR":
                return Optional.of(Operator.OR);
            case "LSHIFT":
                return Optional.of(Operator.LSHIFT);
            case "RSHIFT":
                return Optional.of(Operator.RSHIFT);
            case "NOT":
                return Optional.of(Operator.COMPLEMENT);
            default:
                return Optional.empty();
        }
    }

    public static Optional<Integer> getIntegerString(String string) {
        try {
            int value = Integer.parseInt(string);
            return Optional.of(value);
        }
        catch(NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<String> getIdentifier(String string) {
        return Optional.of(string);
    }
}
