package dec07;

import java.util.HashMap;
import java.util.Optional;

public class StraightThroughWire extends Wire {

    private Wire otherWire;

    public StraightThroughWire(String identifier, Wire otherWire) {
        super(identifier);
        this.otherWire = otherWire;
    }

    @Override
    public Optional<Integer> getSignal() {
        return otherWire.getSignal();
    }

    @Override
    public void updateReferences(String identifier, Wire newWire) {
        if(otherWire.getId().equals(identifier))
            otherWire = newWire;
    }

    @Override
    public void replacePlaceholders(HashMap<String, Wire> wireMap) {
        if(otherWire.isPlaceholder())
            otherWire = wireMap.getOrDefault(otherWire.getId(), otherWire);
    }
}
