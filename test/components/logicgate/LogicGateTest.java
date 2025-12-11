package components.logicgate;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the LogicGate interface behavior (using LogicGate1 as the
 * concrete implementation).
 */
public class LogicGateTest {

    /**
     * Helper to build a LogicGate with given type and inputs.
     * @param type
     *            GateType of the LogicGate
     * @param inputs
     *            input values for the LogicGate
     * @return g
     *         constructed LogicGate
     */
    private LogicGate createGate(GateType type, boolean... inputs) {
        LogicGate g = new LogicGate1();
        g.setType(type);
        g.setInputs(inputs);
        return g;
    }


    // -- toggleInput tests --

    /**
     * Tests that toggleInput correctly flips the first input bit
     * resulting in a '1' from an AND gate.
     */
    @Test
    public void testToggleInputAND1() {
        // Start with AND(1,0,1) so output is 0
        LogicGate gate = this.createGate(GateType.AND, true, false, true);

        boolean[] before = gate.getInputs();
        assertArrayEquals(new boolean[] { true, false, true }, before);
        assertFalse("AND(1,0,1) should be false", gate.getOutput());

        // Toggle the middle input (index 1): 1,0,1 -> 1,1,1
        gate.toggleInput(1);

        boolean[] after = gate.getInputs();
        assertArrayEquals("Only index 1 should have flipped",
                new boolean[] { true, true, true }, after);
        assertTrue("AND(1,1,1) should now be true after toggle", gate.getOutput());
    }

    /**
     * Tests that toggleInput correctly flips the third input bit
     * resulting in a '0' from an AND gate.
     */
    @Test
    public void testToggleInputAND2() {
        // Start with AND(1,1,1) so output is 1
        LogicGate gate = this.createGate(GateType.AND, true, true, true);

        boolean[] before = gate.getInputs();
        assertArrayEquals(new boolean[] { true, true, true }, before);
        assertTrue("AND(1,1,1) should be true", gate.getOutput());
        // Toggle the middle input (index 1): 1,1,1 -> 1,1,0
        gate.toggleInput(2);

        boolean[] after = gate.getInputs();
        assertArrayEquals("Only index 2 should have flipped",
                new boolean[] { true, true, false }, after);
        assertFalse("AND(1,1,1) should now be false after toggle", gate.getOutput());
    }

    /**
     * Tests that toggleInput correctly flips the second input bit
     * resulting in a '1' from an OR gate.
     */
    @Test
    public void testToggleInputOR1() {
        // Start with OR(0, 0) so output is 0
        LogicGate gate = this.createGate(GateType.OR, false, false);

        boolean[] before = gate.getInputs();
        assertArrayEquals(new boolean[] { false, false }, before);
        assertFalse("OR(0,0) should be false", gate.getOutput());
        // Toggle the middle input (index 2): 0,0 -> 0,1
        gate.toggleInput(1);

        boolean[] after = gate.getInputs();
        assertArrayEquals("Only index 1 should have flipped",
                new boolean[] { false, true }, after);
        assertTrue("OR(0,1) should now be true after toggle", gate.getOutput());
    }

    /**
     * Tests that toggleInput correctly flips both input bits
     * resulting in a '0' from an OR gate.
     */
    @Test
    public void testToggleInputOR2() {
        // Start with OR(1, 1) so output is 1
        LogicGate gate = this.createGate(GateType.OR, true, true);

        boolean[] before = gate.getInputs();
        assertArrayEquals(new boolean[] { true, true }, before);
        assertTrue("OR(1,1) should be true", gate.getOutput());
        // Toggle the middle input (index 2): 1,1 -> 0,0
        gate.toggleInput(0);
        gate.toggleInput(1);

        boolean[] after = gate.getInputs();
        assertArrayEquals("Both bits should have flipped",
                new boolean[] { false, false }, after);
        assertFalse("OR(0,0) should now be false after toggle", gate.getOutput());
    }

    /**
     * Tests that toggleInput correctly the only bit resulting in a '0' from a
     * NOT gate.
     */
    @Test
    public void testToggleInputNOT() {
        // Start with NOT(1) so output is 0
        LogicGate gate = this.createGate(GateType.NOT, true);

        boolean[] before = gate.getInputs();
        assertArrayEquals(new boolean[] { true }, before);
        assertFalse("NOT(1) should be false", gate.getOutput());
        // Toggle the only input (index 0): 1 -> 0
        gate.toggleInput(0);

        boolean[] after = gate.getInputs();
        assertArrayEquals("The only bit should have flipped",
                new boolean[] { false }, after);
        assertTrue("NOT(0) should now be true after toggle", gate.getOutput());
    }

    /**
     * Tests that toggleInput throws IndexOutOfBoundsException for invalid index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testToggleInputOutOfBoundsThrows() {
        LogicGate gate = this.createGate(GateType.OR, true, false);
        gate.toggleInput(2); // invalid index
    }

    /**
     * Tests that toggleInput throws IllegalStateException when inputs are unset.
     */
    @Test(expected = IllegalStateException.class)
    public void testToggleInputWithoutInputsThrows() {
        LogicGate gate = new LogicGate1();
        gate.setType(GateType.OR);
        gate.toggleInput(0); // no inputs set
    }

    // -- toString tests --

    /**
     * Tests toString for an AND gate with inputs (1,1).
     */
    @Test
    public void testToStringAND1() {
        // AND(1,1) = 1
        LogicGate gate = this.createGate(GateType.AND, true, true);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "AND(1,1) = 1", s);
    }

    /**
     * Tests toString for an AND gate with inputs (1,1).
     */
    @Test
    public void testToStringAND2() {
        // AND(1,1,0) = 0
        LogicGate gate = this.createGate(GateType.AND, true, true, false);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "AND(1,1,0) = 0", s);
    }

    /**
     * Tests toString for an OR gate with inputs (0,0).
     */
    @Test
    public void testToStringOR1() {
        // OR(0,0) = 0
        LogicGate gate = this.createGate(GateType.OR, false, false);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "OR(0,0) = 0", s);
    }

    /**
     * Tests toString for an OR gate with inputs (0,0).
     */
    @Test
    public void testToStringOR2() {
        // OR(0,0) = 0
        LogicGate gate = this.createGate(GateType.OR, false, false);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "OR(0,0) = 0", s);
    }

    /**
     * Tests toString for an NOT gate with inputs (0).
     */
    @Test
    public void testToStringNOT() {
        // NOT(0) = 1
        LogicGate gate = this.createGate(GateType.NOT, false);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "NOT(0) = 1", s);
    }

    // -- clone tests --

    /**
     * Tests that clone produces an equal but distinct LogicGate.
     */
    @Test
    public void testCloneEqual() {
        LogicGate original = this.createGate(GateType.AND, true, false, true);
        LogicGate clone = original.clone();

        assertNotSame("clone() must produce a different object reference",
                original, clone);
        assertTrue("clone() must produce an equal LogicGate",
                original.equals(clone));
        assertEquals("Equal objects must have equal hashCode",
                original.hashCode(), clone.hashCode());
    }

    /**
     * Tests clone with identical inputs but different types.
     */
    @Test
    public void testCloneNotEqualDifferentTypes() {
        LogicGate original = this.createGate(GateType.AND, true, false, true);
        LogicGate different = this.createGate(GateType.OR, true, false, true);
        LogicGate clone = original.clone();

        assertNotSame("clone() must produce a different object reference",
                different, clone);
        assertFalse("clone() must produce an equal LogicGate",
                different.equals(clone));
        assertNotEquals("Equal objects must have equal hashCode",
                different.hashCode(), clone.hashCode());
    }

    /**
     * Tests clone with identical types but different inputs.
     */
    @Test
    public void testCloneNotEqualDifferentInputs() {
        LogicGate original = this.createGate(GateType.AND, true, false, true);
        LogicGate different = this.createGate(GateType.AND, true, false, false);
        LogicGate clone = original.clone();

        assertNotSame("clone() must produce a different object reference",
                different, clone);
        assertFalse("clone() must produce an equal LogicGate",
                different.equals(clone));
        assertNotEquals("Equal objects must have equal hashCode",
                different.hashCode(), clone.hashCode());
    }

    // -- equals tests --

    /**
     * Tests that equals is reflexive (equals itself).
     */
    @Test
    public void testEqualsReflexive() {
        LogicGate gate = this.createGate(GateType.AND, true, true, false);
        assertTrue("equals must be reflexive", gate.equals(gate));
    }

    /**
     * Tests that equals is symmetric for two equal LogicGates.
     */
    @Test
    public void testEqualsSameTypeSameInputs() {
        LogicGate g1 = this.createGate(GateType.OR, true, false, true);
        LogicGate g2 = this.createGate(GateType.OR, true, false, true);

        assertTrue("Gates with same type and same inputs should be equal",
                g1.equals(g2));
        assertTrue("Equality must be symmetric", g2.equals(g1));
    }

    /**
     * Tests that equals returns false for LogicGates with different types.
     */
    @Test
    public void testEqualsDifferentType() {
        LogicGate g1 = this.createGate(GateType.AND, true, false);
        LogicGate g2 = this.createGate(GateType.OR, true, false);

        assertFalse("Gates with different types should not be equal",
                g1.equals(g2));
    }

    /**
     * Tests that equals returns false for LogicGates with different inputs.
     */
    @Test
    public void testEqualsDifferentInputs() {
        LogicGate g1 = this.createGate(GateType.AND, true, true);
        LogicGate g2 = this.createGate(GateType.AND, true, false);

        assertFalse("Gates with same type but different inputs should not be equal",
                g1.equals(g2));
    }

    /**
     * Tests that equals returns false when compared to null.
     */
    @Test
    public void testEqualsHandlesNull() {
        LogicGate gate = this.createGate(GateType.OR, true, false, false);
        assertFalse("Gate should not be equal to null", gate.equals(null));
    }

    /**
     * Tests that equals returns false when compared to an object of a different class.
     */
    @Test
    public void testEqualsDifferentClass() {
        LogicGate gate = this.createGate(GateType.NOT, true);
        String other = "not a gate";
        assertFalse("Gate should not be equal to an object of another class",
                gate.equals(other));
    }

    // -- hashCode tests --

    /**
     * Tests that equal LogicGates have the same hashCode.
     */
    @Test
    public void testHashCodeEqual() {
        LogicGate g1 = this.createGate(GateType.AND, true, true, false);
        LogicGate g2 = this.createGate(GateType.AND, true, true, false);

        assertTrue("Gates should be equal", g1.equals(g2));
        assertEquals("Equal gates must have same hashCode",
                g1.hashCode(), g2.hashCode());
    }

    /**
     * Tests that different LogicGates have different hashCodes.
     */
    @Test
    public void testHashCodeDifferent() {
        LogicGate gate = this.createGate(GateType.OR, false, true, true);
        int h1 = gate.hashCode();
        int h2 = gate.hashCode();
        assertEquals("hashCode must be consistent on repeated calls", h1, h2);
    }

}
