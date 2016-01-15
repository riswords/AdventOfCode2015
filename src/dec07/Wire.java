package dec07;

import java.util.HashMap;
import java.util.Optional;

public abstract class Wire {

    public enum Operator {
        AND, OR, LSHIFT, RSHIFT, COMPLEMENT
    }

    private String id;

    public Wire(String identifier) {
        id = identifier;
    }

    public abstract Optional<Integer> getSignal();

    public abstract void updateReferences(String identifier, Wire newWire);

    public abstract void replacePlaceholders(HashMap<String, Wire> wireMap);

    public boolean isPlaceholder() {
        return false;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Wire) { return ((Wire) o).getId().equals(this.getId()); }
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
