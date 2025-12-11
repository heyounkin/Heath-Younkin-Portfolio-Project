package components.logicgate;

/**
 * Secondary (enhanced) implementation for LogicGate.
 *
 * <p>Implements all secondary operations strictly in terms of the kernel
 * and {@code Standard} methods. Holds no representation state.
 *
 */
public abstract class LogicGateSecondary implements LogicGate {

    /**
     * No additional representation needed.
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
     * No additional representation needed.
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
     * No additional representation needed.
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

    /**
     * No additional representation needed.
     */
    @Override
    public boolean equals(Object obj) {
    if (obj == this) {
        return true;
    }
    if (!(obj instanceof LogicGate1)) {
        return false;
    }
    LogicGate1 other = (LogicGate1) obj;
    return this.getType() == other.getType()
            && java.util.Arrays.equals(this.getInputs(), other.getInputs());
    }

    /**
     * No additional representation needed.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;

        // Type part (null-safe)
        GateType t = this.getType();
        result = prime * result + (t == null ? 0 : t.hashCode());

        // Inputs part (Arrays.hashCode handles null arrays as 0)
        boolean[] a = this.getInputs();
        result = prime * result + java.util.Arrays.hashCode(a);

        return result;
    }
}

