/**
 * Class LogicGate.
 *
 * Representation:
 * <pre>
 * private boolean[] inputs;
 * private GateType type;
 * </pre>
 *
 * Convention:
 * <pre>
 * - {@code inputs} may be {@code null}, meaning "no inputs set" yet.
 * - {@code type} may be {@code null}, meaning "no type set" yet.
 * - If {@code type == GateType.NOT} and {@code inputs != null}, then
 *       {@code inputs.length == 1}.
 * </pre>
 *
 * Correspondence:
 * <pre>
 * - {@code this.inputs} corresponds to the abstract input string
 *   {@code this.inputs} in the model, in left-to-right index order.
 * - If {@code inputs == null}, then the abstract input string is empty
 *       (no inputs set).
 * - {@code this.type} corresponds exactly to the abstract {@code this.type}
 *       in the model.
 * </pre>
 */
public class LogicGate1 extends LogicGateSecondary implements LogicGateKernel {

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
     * Initializes a new, empty representation.
     */
    private void createNewRep() {
        this.inputs = null;
        this.type = null;
    }

    /**
     * Default constructor. Creates an empty gate. Type and inputs can be set
     * later.
     */
    public LogicGate1() {
        this.createNewRep();
    }

    /**
     * Constructor that sets the type only.
     *
     * @param type
     *            GateType (AND, OR, NOT)
     */
    public LogicGate1(GateType type) {
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
    public LogicGate1(GateType type, boolean[] inputs) {
        this.type = type;
        this.inputs = inputs.clone();
    }

    // -- Standard<LogicGate1> methods --
    @Override
    public LogicGate1 newInstance() {
        return new LogicGate1();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(LogicGate1 source) {
        if (source == this) {
            return;
        }
        assert source != null : "source must not be null";

        this.inputs = source.inputs;
        this.type = source.type;

        source.createNewRep();
    }

    // ---Kernel Methods---

    /**
     * Sets the type of the logic gate.
     *
     * @param gate
     *            GateType (AND, OR, NOT)
     */
    @Override
    public void setType(GateType gate) {
        this.type = gate;
    }

    /**
     * Returns the current type of the gate.
     *
     * @return GateType (AND, OR, NOT)
     */
    @Override
    public GateType getType() {
        return this.type;
    }

    /**
     * Sets the inputs for the gate.
     *
     * @param arr
     *            boolean array of input values
     */
    @Override
    public void setInputs(boolean[] arr) {
        this.inputs = arr.clone();
    }

    /**
     * Returns the current input values.
     */
    @Override
    public boolean[] getInputs() {
        if (this.inputs == null) {
            return null;
        }
        return this.inputs.clone(); // defensive copy
    }

    /**
     * Returns the output given the current inputs and type.
     *
     * @return boolean output
     */
    @Override
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
        LogicGate1 gate1 = new LogicGate1();
        gate1.setType(GateType.OR);
        gate1.setInputs(new boolean[] { true, false, false });
        System.out.println("OR output: " + gate1.getOutput());

        // Type-only constructor
        LogicGate1 gate2 = new LogicGate1(GateType.NOT);
        gate2.setInputs(new boolean[] { true });
        System.out.println("NOT output: " + gate2.getOutput());

        // Full constructor
        LogicGate1 gate3 = new LogicGate1(GateType.AND,
                new boolean[] { true, true, true });
        System.out.println("AND output: " + gate3.getOutput());
    }

}
