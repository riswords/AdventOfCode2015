package dec07;

import java.util.HashMap;
import java.util.Optional;

public class ConstantWire extends Wire {

    private int signal;

    public ConstantWire(int signal) {
        super("constant" + signal);
        this.signal = signal;
    }

    public ConstantWire(String identifier, int signal) {
        super(identifier);
        this.signal = signal;
    }

    @Override
    public Optional<Integer> getSignal() {
        return Optional.of(signal);
    }

    @Override
    public void updateReferences(String identifier, Wire newWire) {
        return;
    }

    @Override
    public void replacePlaceholders(HashMap<String, Wire> wireMap) {
        return;
    }
}
