package components.logicgate;
/**
 * Enhanced client interface for LogicGate.
 *
 * <p>
 * Extends the kernel with convenience operations that can be implemented using
 * only the kernel and {@code Standard} methods.
 */
public interface LogicGate extends LogicGateKernel {

    /**
     * Toggles (inverts) a single input at the given index.
     *
     * @param inputIndex
     *            index of input to toggle
     * @updates this
     * @requires 0 ≤ inputIndex < |this.inputs|
     * @ensures this.inputs[inputIndex] = #!this.inputs[inputIndex]
     */
    void toggleInput(int inputIndex);

    /**
     * Returns a string representation of this gate and its current output.
     *
     * @return a readable string e.g. "AND(1,1) = 1"
     * @ensures toString = (this.type) + "(" + entries(this.inputs) + ") = " +
     *          this.getOutput()
     */
    @Override
    String toString();

    /**
     * Creates a duplicate LogicGate with the same type and inputs.
     *
     * @return new LogicGate identical to this one
     * @ensures clone != this AND clone.getType() = this.type AND
     *          entries(clone.getInputs()) = this.inputs
     */
    LogicGate clone();

    /**
     * Reports whether this {@code LogicGate} is equal to the given object.
     *
     * @param obj
     *            the object to compare to this
     * @return {@code true} if this and {@code obj} represent the same logic
     *         gate; {@code false} otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a hash code for this {@code LogicGate}. The hash code is
     * computed from the gate's {@code GateType} and the values of its
     * input array, and is consistent with {@link #equals(Object)}.
     *
     * @return a hash code value for this {@code LogicGate}
     */
    @Override
    int hashCode();

}
