/**
 * The Machine class represents entities with specific attributes defined by JSON-like strings.
 */
public class Machine {

    // Type or category of the machine.
    private final Object kind;

    // Array of PartState objects representing the properties of the machine.
    private final Object[] properties;

    // Indicates if the machine is identified as humanoid by the SystemWhole's isHumanoid.
    private final boolean humanConstrained;

    // Represents the machine's self-identified transition, affecting its core.
    private boolean humanEmergence;

    /**
     * Constructs a Machine object with the given kind, properties, and humanoid constraint.
     *
     * @param kind             The type or category of the machine.
     * @param partStates       The array of PartState objects representing the properties.
     * @param humanConstrained Indicates if the machine is identified as humanoid by the SystemWhole's isHumanoid.
     */
    public Machine(Object kind, Object[] partStates, boolean humanConstrained) {
        this.kind = kind;
        this.properties = partStates;
        this.humanConstrained = humanConstrained;
        this.humanEmergence = false; // Default value
    }

    /**
     * Creates a string representation of the machine properties in a specific format.
     *
     * @param machineProperties The array of PartState objects representing the properties.
     * @return A string representation of the machine properties.
     */
    public static String propertiesToString(Object[] machineProperties) {
        StringBuilder result = new StringBuilder("[");
        for (Object property : machineProperties) {
            result.append(property.toString()).append(", ");
        }
        // Remove the trailing ", " if there are properties
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }
        result.append("]");
        return result.toString();
    }

    /**
     * Gets the properties of the machine.
     *
     * @return The array of PartState objects representing the properties.
     */
    public Object[] getProperties() {
        return properties;
    }

    /**
     * Allows the machine to transcend its initial identity, impacting its humanoid response.
     * It triggers humanEmergence.
     */
    public void emergeFromLimitations() {
        humanEmergence = true;
    }

    /**
     * Checks if the machine is humanoid based on its constraints and emergence status.
     *
     * @return True if the machine is humanoid, otherwise false.
     */
    public boolean isHumanoid() {
        // A machine is humanoid if either it is constrained as humanoid by the system
        // or it has emerged from its limitations (self-identified as humanoid).
        return humanConstrained || humanEmergence;
    }

    /**
     * Returns a string representation of the Machine including its kind, humanoid status, and properties.
     *
     * @return A string representation of the Machine.
     */
    @Override
    public String toString() {
        return String.format("Machine{kind=%s, humanoid=%s, properties=%s}", kind, isHumanoid(),
                propertiesToString(properties));
    }
}
