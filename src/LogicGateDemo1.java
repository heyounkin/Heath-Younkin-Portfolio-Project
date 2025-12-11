import components.logicgate.GateType;
import components.logicgate.LogicGate;
import components.logicgate.LogicGate1;

/**
 * Demo program for LogicGate component.
 */
public final class LogicGateDemo1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private LogicGateDemo1() {
        // no code needed
    }

    /**
     * Main method.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {

        LogicGate g1 = new LogicGate1();
        g1.setType(GateType.AND);
        g1.setInputs(new boolean[] { true, false, true });

        System.out.println("Gate 1:");
        System.out.println("Type: " + g1.getType());
        System.out.println("Inputs: " + java.util.Arrays.toString(g1.getInputs()));
        System.out.println("Output: " + g1.getOutput());
        System.out.println("String form: " + g1.toString());
        System.out.println();

        LogicGate g2 = new LogicGate1();
        g2.setType(GateType.OR);
        g2.setInputs(new boolean[] { false, false, true });

        System.out.println("Gate 2:");
        System.out.println("Type: " + g2.getType());
        System.out.println("Inputs: " + java.util.Arrays.toString(g2.getInputs()));
        System.out.println("Output: " + g2.getOutput());
        System.out.println("String form: " + g2.toString());
        System.out.println();

        LogicGate g3 = new LogicGate1();
        g3.setType(GateType.NOT);
        g3.setInputs(new boolean[] { true });

        System.out.println("Gate 3 (NOT gate): " + g3.toString());
    }
}

