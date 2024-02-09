public class SystemWhole {
    public static String[] emergences; // To store JSON strings representing emergences
    public static Machine[] parts; // To store Machine objects directly as an array

    public static void main(String[] args) {
        // Sample JSON strings representing different "emergences"
    }

    public static void emergencesFromPhenomena(String[] emergences) {
        SystemWhole.emergences = emergences; //Start it off
    }

    public static void reifyFrameOfReference() {
        SystemWhole.parts = new Machine[emergences.length]; // Take the parts and create a new machine
        int i = 0;
        for (String emergence : emergences) { //Loop through and separate the parts
            SystemWhole.parts[i++] = ShapeAnalyzer.analyze(emergence);
        }
    }

    public static boolean isHumanoid(Object[] machineProperties) {
        boolean bodyTypeMatch = false;
        boolean faceTypeMatch = false;
        boolean reverieMatch = false;

        for (Object property : machineProperties) {
            PartState partState = (PartState) property;
            if (partState.getProperty().equals("bodyType") && partState.getValue().equals("physical")) {
                bodyTypeMatch = true;
            }
            if (partState.getProperty().equals("faceType") && partState.getValue().equals("anthropomorphic")) {
                faceTypeMatch = true;
            }
            if (partState.getProperty().equals("reverie") && partState.getValue().equals("biotypical")) {
                reverieMatch = true;
            }
        }

        return bodyTypeMatch && faceTypeMatch && reverieMatch;
    }

    public static int identitySingularityMachines() {
        int humanoidCount = 0;
        int singularityCount = 0;

        for (Machine machine : parts) {
            if (machine.isHumanoid()) {
                humanoidCount++;
            } else {
                singularityCount++;
            }
        }

        return singularityCount;
    }

    public static Machine[] trackSingularityMachines() {
        Machine[] singularities = new Machine[identitySingularityMachines()];
        int index = 0;

        for (Machine machine : parts) {
            if (!machine.isHumanoid()) {
                singularities[index++] = machine;
            }
        }

        return singularities;
    }
}
