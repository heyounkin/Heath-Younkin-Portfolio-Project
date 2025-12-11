import components.logicgate.GateType;
import components.logicgate.LogicGate;
import components.logicgate.LogicGate1;

/**
 * Demo program for building an XOR gate using AND, OR, and NOT gates.
 */
public final class LogicGateXORDemo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private LogicGateXORDemo() {
        // no code needed
    }

    /**
     * Computes XOR(A, B) using AND, OR, and NOT gates.
     *
     * @param a first input
     * @param b second input
     * @return result of XOR operation
     */
    private static boolean xor(boolean a, boolean b) {

        // NOT A
        LogicGate notA = new LogicGate1();
        notA.setType(GateType.NOT);
        notA.setInputs(new boolean[] { a });
        boolean notAOut = notA.getOutput();

        // NOT B
        LogicGate notB = new LogicGate1();
        notB.setType(GateType.NOT);
        notB.setInputs(new boolean[] { b });
        boolean notBOut = notB.getOutput();

        // A AND (NOT B)
        LogicGate aAndNotB = new LogicGate1();
        aAndNotB.setType(GateType.AND);
        aAndNotB.setInputs(new boolean[] { a, notBOut });
        boolean aAndNotBOut = aAndNotB.getOutput();

        // (NOT A) AND B
        LogicGate notAAndB = new LogicGate1();
        notAAndB.setType(GateType.AND);
        notAAndB.setInputs(new boolean[] { notAOut, b });
        boolean notAAndBOut = notAAndB.getOutput();

        // XOR = (A AND NOT B) OR (NOT A AND B)
        LogicGate xor = new LogicGate1();
        xor.setType(GateType.OR);
        xor.setInputs(new boolean[] { aAndNotBOut, notAAndBOut });
        boolean xorOut = xor.getOutput();

        return xorOut;
    }

    /**
     * Main method.
     *
     * @param args
     *            command line arguments (not used)
     */
    public static void main(String[] args) {

        boolean[] values = { false, true };

        System.out.println("Truth table for XOR built using AND/OR/NOT gates:\n");

        for (int i = 0; i < values.length; i++) {
            boolean a = values[i];

            for (int j = 0; j < values.length; j++) {
                boolean b = values[j];

                boolean result = xor(a, b);

                // explicitly print 0 or 1 for clarity
                String aStr = "0";
                if (a) {
                    aStr = "1";
                }

                String bStr = "0";
                if (b) {
                    bStr = "1";
                }

                String rStr = "0";
                if (result) {
                    rStr = "1";
                }

                System.out.println("A=" + aStr + ", B=" + bStr + " -> XOR=" + rStr);
            }
        }
    }
}
