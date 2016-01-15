package dec07;

import java.util.HashMap;
import java.util.Optional;

public class PlaceholderWire extends Wire {

    public PlaceholderWire(String identifier) {
        super(identifier);
    }

    @Override
    public Optional<Integer> getSignal() {
        return Optional.empty();
    }

    @Override
    public void updateReferences(String identifier, Wire newWire) {
        return;
    }

    @Override
    public boolean isPlaceholder() {
        return true;
    }

    @Override
    public void replacePlaceholders(HashMap<String, Wire> wireMap) {
        return;
    }

}
