/**
 * The ShapeAnalyzer class provides utility methods for analyzing and parsing JSON-like strings
 * to create Machine objects.
 */
public class ShapeAnalyzer {

    // Represents an empty property when the value is null or empty.
    private static final Object EMPTY_PROPERTY = new Object();

    /**
     * Analyzes a JSON-like string and creates a Machine object with extracted information.
     *
     * @param json The JSON-like string representing the emergence.
     * @return A Machine object representing the analyzed information.
     */
    public static Machine analyze(String json) {
        // Parse entries, reify kind, reify properties, and check humanoid status.
        String[][] entries = parseEntries(json);
        Object kind = reifyKind(entries);
        Object[] properties = reifyProperties(entries);
        boolean humanConstrained = SystemWhole.isHumanoid(properties);
        return new Machine(kind, properties, humanConstrained);
    }

    /**
     * Parses a flat JSON-like string and returns an array of key-value pairs.
     *
     * @param flatJson The flat JSON-like string to be parsed.
     * @return A 2D array containing key-value pairs.
     */
    public static String[][] parseEntries(String flatJson) {
        // Remove curly braces and quotes, split into pairs, and trim whitespace.
        flatJson = flatJson.replaceAll("[{}\"]", "");
        String[] pairs = flatJson.split(",");
        String[][] entries = new String[pairs.length][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] keyValue = pairs[i].split(":");
            entries[i][0] = keyValue[0].trim();
            entries[i][1] = keyValue[1].trim();
        }
        return entries;
    }

    /**
     * Reifies the "kind" property from a set of key-value pairs.
     *
     * @param entries The key-value pairs to search for the "kind" property.
     * @return The value of the "kind" property, or null if not found.
     */
    public static String reifyKind(String[][] entries) {
        // Iterate through entries and return the value of the "kind" property.
        for (String[] entry : entries) {
            if (entry[0].equals("kind")) {
                return entry[1];
            }
        }
        return null;
    }

    /**
     * Reifies properties from a set of key-value pairs excluding the "kind" property.
     *
     * @param entries The key-value pairs from which properties are extracted.
     * @return An array of PartState objects representing the properties.
     */
    public static Object[] reifyProperties(String[][] entries) {
        // Create PartState objects for each property (excluding "kind").
        Object[] properties = new Object[entries.length - 1];
        int index = 0;
        for (String[] entry : entries) {
            if (!entry[0].equals("kind")) {
                properties[index] = new PartState(entry[0], entry[1]);
                index++;
            }
        }
        return properties;
    }

    /**
     * Infers the appropriate object type from a string value, considering numeric types.
     *
     * @param value The string value to be inferred.
     * @return The inferred object (String, Integer, Double), or EMPTY_PROPERTY for null/empty.
     */
    public static Object inferObject(String value) {
        // Handle null or empty values, non-numeric strings, and parse numeric strings.
        if (value == null || value.isEmpty()) {
            return EMPTY_PROPERTY;
        } else if (hasNonNumbers(value)) {
            return value;
        } else if (value.contains(".")) {
            return Double.parseDouble(value);
        } else {
            return Integer.parseInt(value);
        }
    }

    /**
     * Checks if a string has non-numeric characters (excluding '.', '_').
     *
     * @param value The string to be checked.
     * @return True if non-numeric characters are present, otherwise false.
     */
    public static boolean hasNonNumbers(String value) {
        // Check for non-numeric characters excluding '.', '_'.
        for (char c : value.toCharArray()) {
            if (!isDigit(c) && c != '.' && c != '_') {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a character is a digit.
     *
     * @param c The character to be checked.
     * @return True if the character is a digit, otherwise false.
     */
    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
