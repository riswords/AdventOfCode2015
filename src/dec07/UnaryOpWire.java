package dec07;

import java.util.HashMap;
import java.util.Optional;

public class UnaryOpWire extends Wire {

    private Operator operator;
    private Wire rightWire;
    private Optional<Integer> cacheSignal = Optional.empty();

    public UnaryOpWire(String identifier, Wire rightInput, Operator operator) {
        super(identifier);
        rightWire = rightInput;
        this.operator = operator;
    }

    @Override
    public Optional<Integer> getSignal() {
        if(cacheSignal.isPresent())
            return cacheSignal;
        Optional<Integer> rightSignal = rightWire.getSignal();
        if(!rightSignal.isPresent())
            return Optional.empty();
        cacheSignal = Optional.of(performOperation(rightSignal.get(), operator));
        return cacheSignal;
    }

    @Override
    public void updateReferences(String identifier, Wire newWire) {
        if(rightWire.getId().equals(identifier))
            rightWire = newWire;
    }

    @Override
    public void replacePlaceholders(HashMap<String, Wire> wireMap) {
        if(rightWire.isPlaceholder())
            rightWire = wireMap.getOrDefault(rightWire.getId(), rightWire);
    }

    private int performOperation(int right, Operator op) {
        switch(op) {
            case COMPLEMENT:
                return ~right;
            default:
                throw new UnsupportedOperationException("Invalid unary operator: " + op);
        }
    }

}
