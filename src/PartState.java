/**
 * The PartState class represents a single property and its corresponding value of a Machine.
 */
public class PartState {

    // The name of the property.
    private final String property;

    // The value associated with the property.
    private final Object value;

    /**
     * Constructs a PartState object with the given property and value.
     *
     * @param property The name of the property.
     * @param value    The value associated with the property.
     */
    public PartState(String property, Object value) {
        this.property = property;
        this.value = value;
    }

    /**
     * Gets the name of the property.
     *
     * @return The name of the property.
     */
    public String getProperty() {
        return property;
    }

    /**
     * Gets the value associated with the property.
     *
     * @return The value associated with the property.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Returns a string representation of the PartState in the format "PartState{property=value}".
     *
     * @return A string representation of the PartState.
     */
    @Override
    public String toString() {
        return String.format("PartState{%s=%s}", property, value);
    }
}
