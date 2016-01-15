package dec07;

import java.util.HashMap;
import java.util.Optional;

public class BinaryOpWire extends Wire {
    private Operator operator;
    private Wire leftWire, rightWire;
    private Optional<Integer> cacheSignal = Optional.empty();

    public BinaryOpWire(String identifier, Wire leftInput, Wire rightInput, Operator operator) {
        super(identifier);
        leftWire = leftInput;
        rightWire = rightInput;
        this.operator = operator;
    }

    @Override
    public Optional<Integer> getSignal() {
        if(cacheSignal.isPresent())
            return cacheSignal;
        Optional<Integer> leftSignal = leftWire.getSignal();
        if(leftSignal.isPresent()) {
            Optional<Integer> rightSignal = rightWire.getSignal();
            if(rightSignal.isPresent()) {
                cacheSignal = Optional.of(performOperation(leftSignal.get(), rightSignal.get(), operator));
                return cacheSignal;
            }
        }
        return Optional.empty();
    }

    @Override
    public void updateReferences(String identifier, Wire newWire) {
        if(leftWire.getId().equals(identifier))
            leftWire = newWire;
        if(rightWire.getId().equals(identifier))
            rightWire = newWire;
    }

    @Override
    public void replacePlaceholders(HashMap<String, Wire> wireMap) {
        if(leftWire.isPlaceholder())
            leftWire = wireMap.getOrDefault(leftWire.getId(), leftWire);
        if(rightWire.isPlaceholder())
            rightWire = wireMap.getOrDefault(rightWire.getId(), rightWire);
    }

    private int performOperation(int left, int right, Operator op) {
        switch(op) {
            case AND:
                return left & right;
            case OR:
                return left | right;
            case LSHIFT:
                return left << right;
            case RSHIFT:
                return left >> right;
            default:
                throw new UnsupportedOperationException("Invalid binary operator: " + op);
        }
    }

}
