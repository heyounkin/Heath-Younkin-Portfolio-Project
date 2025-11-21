/**
 * Secondary (enhanced) implementation for LogicGate.
 *
 * <p>Implements all secondary operations strictly in terms of the kernel
 * and {@code Standard} methods. Holds no representation state.
 *
 */
public abstract class LogicGateSecondary implements LogicGate {

    /**
     * Toggles (inverts) a single input at the given index.
     *
     * @param inputIndex
     *            index of input to toggle
     * @updates this
     * @requires 0 ≤ inputIndex < |this.inputs|
     * @ensures this.inputs[inputIndex] = #!this.inputs[inputIndex]
     */
    @Override
    public void toggleInput(int inputIndex) {
        boolean[] a = this.getInputs();
        if (a == null) {
            throw new IllegalStateException("inputs not set");
        }
        if (inputIndex < 0 || inputIndex >= a.length) {
            throw new IndexOutOfBoundsException(
                "inputIndex " + inputIndex + " out of range [0," + (a.length - 1) + "]");
        }
        a[inputIndex] = !a[inputIndex];
        this.setInputs(a);
    }

    /**
     * Returns a string representation of this gate and its current output.
     *
     * @return a readable string e.g. "AND(1,1) = 1"
     * @ensures toString = (this.type) + "(" + entries(this.inputs) + ") = " +
     *          this.getOutput()
     */
    @Override
    public String toString() {

    // Kernel, inputs and outputs
    GateType t = this.getType();
    boolean[] a = this.getInputs();
    boolean out = this.getOutput();

    StringBuilder sb = new StringBuilder();

    // Gate type
    if (t == null) {
        sb.append("UNSET");
    } else {
        sb.append(t.name());
    }

    sb.append("(");

    // Inputs
    if (a == null) {
        sb.append("?");
    } else {
        for (int i = 0; i < a.length; i++) {
            if (a[i]) {
                sb.append("1");
            } else {
                sb.append("0");
            }

            if (i < a.length - 1) {
                sb.append(",");
            }
        }
    }

    sb.append(") = ");

    // Output
    if (out) {
        sb.append("1");
    } else {
        sb.append("0");
    }

    return sb.toString();
    }

    /**
     * Creates a duplicate LogicGate with the same type and inputs.
     *
     * @return new LogicGate identical to this one
     * @ensures clone != this AND clone.getType() = this.type AND
     *          entries(clone.getInputs()) = this.inputs
     */
    @Override
    public LogicGate clone() {
        // Create empty instance of same concrete type
        LogicGate1 copy = this.newInstance();

        // Copy type
        copy.setType(this.getType());

        // Copy inputs
        boolean[] a = this.getInputs();
        if (a != null) {
            copy.setInputs(a);
        }
        return (LogicGate) copy;
    }
}

