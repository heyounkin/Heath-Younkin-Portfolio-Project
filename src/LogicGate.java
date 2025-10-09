/**
 * Enumeration for basic types.
 */
enum GateType {

    /**
     * AND, OR, and NOT, follow their respective logic.
     *
     * AND: All inputs must be true for output to be true
     *
     * OR: At least one input must be true for output to be true
     *
     * NOT: Makes the output the opposite of the input
     */
    AND, OR, NOT;
}

/**
 * Class LogicGate.
 */
public class LogicGate {

    /**
     * boolean array for inputs.
     */
    private boolean[] inputs;

    /**
     * Uses GateType enum for type.
     */
    private GateType type;

    // ---Constructors---

    /**
     * Default constructor. Creates an empty gate. Type and inputs can be set
     * later.
     */
    public LogicGate() {
        this.inputs = null;
        this.type = null;
    }

    /**
     * Constructor that sets the type only.
     *
     * @param type
     *            GateType (AND, OR, NOT)
     */
    public LogicGate(GateType type) {
        this.type = type;
        this.inputs = null;
    }

    /**
     * Constructor that sets both the type and the inputs.
     *
     * @param type
     *            GateType (AND, OR, NOT)
     * @param inputs
     *            boolean array of input values
     */
    public LogicGate(GateType type, boolean[] inputs) {
        this.type = type;
        this.inputs = inputs.clone();
    }

    // ---Kernel Methods---

    /**
     * Sets the type of the logic gate.
     *
     * @param gate
     *            GateType (AND, OR, NOT)
     */
    public void setType(GateType gate) {
        this.type = gate;
    }

    /**
     * Returns the current type of the gate.
     *
     * @return GateType (AND, OR, NOT)
     */
    public GateType getType() {
        return this.type;
    }

    /**
     * Sets the inputs for the gate.
     *
     * @param arr
     *            boolean array of input values
     */
    public void setInputs(boolean[] arr) {
        this.inputs = arr.clone();
    }

    /**
     * Prints the current input values.
     */
    public void getInput() {
        System.out.print("Inputs: ");
        if (this.inputs == null) {
            System.out.println("No inputs set.");
        } else {
            for (boolean input : this.inputs) {
                System.out.print(input + " ");
            }
            System.out.println();
        }
    }

    /**
     * Returns the output given the current inputs and type.
     *
     * @return boolean output
     */
    public boolean getOutput() {
        if (this.type == null || this.inputs == null) {
            throw new IllegalStateException("Gate type or inputs not set.");
        }

        switch (this.type) {
            case AND:
                for (boolean input : this.inputs) {
                    if (!input) {
                        return false;
                    }
                }
                return true;

            case OR:
                for (boolean input : this.inputs) {
                    if (input) {
                        return true;
                    }
                }
                return false;

            case NOT:
                if (this.inputs.length != 1) {
                    throw new IllegalArgumentException(
                            "NOT gate requires exactly one input.");
                }
                return !this.inputs[0];

            default:
                throw new UnsupportedOperationException(
                        "Unknown gate type: " + this.type);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            Command-line arguments passed to the program. In this example,
     *            no specific arguments are expected or processed.
     */
    public static void main(String[] args) {
        // Default constructor
        LogicGate gate1 = new LogicGate();
        gate1.setType(GateType.OR);
        gate1.setInputs(new boolean[] { true, false, false });
        System.out.println("OR output: " + gate1.getOutput());

        // Type-only constructor
        LogicGate gate2 = new LogicGate(GateType.NOT);
        gate2.setInputs(new boolean[] { true });
        System.out.println("NOT output: " + gate2.getOutput());

        // Full constructor
        LogicGate gate3 = new LogicGate(GateType.AND,
                new boolean[] { true, true, true });
        System.out.println("AND output: " + gate3.getOutput());
    }

}