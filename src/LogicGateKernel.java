import components.standard.Standard;

/**
 * Kernel Interface for LogicGate.
 *
 * this.inputs: string of boolean // the current vector for inputs.
 *
 * this.type:GateType enum // kind of gate.
 */
public interface LogicGateKernel extends Standard<LogicGate1> {

    /**
     * Sets the type (AND, OR, NOT) of this gate.
     *
     * @param gate
     *            gate type
     * @updates this
     * @requires gate != null
     * @ensures this.type = gate
     */
    void setType(GateType gate);

    /**
     * Returns the current gate type.
     *
     * @return gate type
     * @ensures getType = this.type
     */
    GateType getType();

    /**
     * Sets the inputs for this gate.
     *
     * @param arr
     *            array of input values
     * @updates this
     * @requires arr != null
     * @ensures this.inputs = entries(arr) // logical copy of contents
     */
    void setInputs(boolean[] arr);

    /**
     * Returns a copy of the current inputs.
     *
     * @return array of input values
     * @ensures entries(getInputs()) = this.inputs
     */
    boolean[] getInputs();

    /**
     * Computes the gate's boolean output using the current inputs and type.
     *
     * @return output value
     * @requires this.type is defined AND this.inputs is defined AND (this.type
     *           != GateType.NOT OR |this.inputs| = 1)
     * @ensures (this.type = AND ⇒ getOutput = (∀ i • this.inputs[i] = true))
     *          AND (this.type = OR ⇒ getOutput = (∃ i • this.inputs[i] = true))
     *          AND (this.type = NOT ⇒ getOutput = ¬this.inputs[0])
     */
    boolean getOutput();

}
