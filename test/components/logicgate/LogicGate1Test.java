package components.logicgate;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for the LogicGate interface behavior (using LogicGate1 as the
 * concrete implementation).
 */
public class LogicGate1Test {

    /**
     * Helper to build a LogicGate with given type and inputs.
     * @param type
     *            GateType of the LogicGate
     * @param inputs
     *            input values for the LogicGate
     * @return g
     *         constructed LogicGate
     */
    private LogicGate1 createGate(GateType type, boolean... inputs) {
        LogicGate1 g = new LogicGate1();
        g.setType(type);
        g.setInputs(inputs);
        return g;
    }

    // -- constructor tests --

    /**
     * Tests that the default constructor leaves type and inputs null.
     */
    @Test
    public void testDefaultConstructorNull() {
        LogicGate1 g = new LogicGate1();
        assertNull("Default constructor should leave type null", g.getType());
        assertNull("Default constructor should leave inputs null", g.getInputs());
    }

    /**
     * Tests that the type-only constructor sets the type and leaves inputs null.
     */
    @Test
    public void testTypeOnlyConstructorNullInputs() {
        LogicGate1 g = new LogicGate1(GateType.AND);
        assertEquals("Type-only constructor should set the type",
                GateType.AND, g.getType());
        assertNull("Type-only constructor should leave inputs null", g.getInputs());
    }

    /**
     * Tests that the full constructor sets type and clones inputs.
     */
    @Test
    public void testFullConstructorClonesInputs() {
        boolean[] arr = { true, false, true };
        LogicGate1 g = new LogicGate1(GateType.OR, arr);

        assertEquals("Full constructor should set the type",
                GateType.OR, g.getType());

        boolean[] stored = g.getInputs();
        assertArrayEquals("Full constructor should copy input values",
                arr, stored);
        assertNotSame("Inputs should be cloned, not aliased",
                arr, stored);

        // Mutate original array and confirm gate is unaffected
        arr[0] = false;
        assertArrayEquals("Mutating original array should not affect gate inputs",
                new boolean[] { true, false, true }, g.getInputs());
    }

    // -- newInstance, clear and transferFrom tests --

    /**
     * Tests that newInstance produces a fresh, empty LogicGate.
     */
    @Test
    public void testNewInstance() {
        LogicGate1 g1 = new LogicGate1(GateType.OR, new boolean[] { true, false });
        LogicGate1 g2 = g1.newInstance();

        assertNotNull("newInstance() must not return null", g2);
        assertNotSame("newInstance() must return a distinct object", g1, g2);
        assertNull("newInstance() gate should start with null type", g2.getType());
        assertNull("newInstance() gate should start with null inputs", g2.getInputs());
    }

    /**
     * Tests that clear resets the LogicGate to an empty state.
     */
    @Test
    public void testClearResetsTypeAndInputs() {
        LogicGate1 g = new LogicGate1(GateType.AND, new boolean[] { true, true });
        assertEquals(GateType.AND, g.getType());
        assertNotNull(g.getInputs());

        g.clear();

        assertNull("clear() should reset type to null", g.getType());
        assertNull("clear() should reset inputs to null", g.getInputs());
    }

    /**
     * Tests that clear resets the LogicGate to an empty state.
     */
    @Test
    public void testClearNullIsNull() {
        LogicGate1 g1 = new LogicGate1(GateType.AND, new boolean[] { true, true });
        LogicGate1 g2 = new LogicGate1(GateType.OR, new boolean[] { false, true });

        assertEquals(GateType.AND, g1.getType());
        assertEquals(GateType.OR, g2.getType());

        assertNotNull(g1.getInputs());
        assertNotNull(g2.getInputs());

        g1.clear();
        g2.clear();

        assertEquals("type is null regardless of original type",
        g1.getType(), g2.getType());
        assertEquals("inputs are null regardless of original inputs",
        g1.getInputs(), g2.getInputs());
    }

    /**
     * Tests that clear resets a LogicGate to the same state as newInstance.
     */
    @Test
    public void testNewInstanceClear() {
        LogicGate1 g1 = new LogicGate1(GateType.OR, new boolean[] { true, false });
        LogicGate1 g2 = g1.newInstance();

        g1.clear();

        assertNotNull("newInstance() must not return null", g2);
        assertNotSame("newInstance() must return a distinct object", g1, g2);
        assertNull("newInstance() gate should start with null type", g2.getType());
        assertNull("newInstance() gate should start with null inputs", g2.getInputs());
        assertEquals("a newInstance should effectively be cleared",
                g1, g2);
    }

    /**
     * Tests that transferFrom copies state from source and clears source.
     */
    @Test
    public void testTransferFromCopiesStateAndClearsSource() {
        LogicGate1 source = new LogicGate1(GateType.OR,
                new boolean[] { false, true, false });
        LogicGate1 target = new LogicGate1();

        target.transferFrom(source);

        // Target should now have source's state
        assertEquals("transferFrom should copy type",
                GateType.OR, target.getType());
        assertArrayEquals("transferFrom should copy inputs",
                new boolean[] { false, true, false }, target.getInputs());

        // Source should be cleared
        assertNull("Source type should be cleared after transferFrom",
                source.getType());
        assertNull("Source inputs should be cleared after transferFrom",
                source.getInputs());
    }

    /**
     * Tests that transferFrom clears the old state and it is null.
     */
    @Test
    public void testTransferFromNullIsNull() {
        LogicGate1 g1 = new LogicGate1(GateType.AND, new boolean[] { true, true });
        LogicGate1 g2 = new LogicGate1(GateType.OR, new boolean[] { false, true });
        LogicGate1 place1 = new LogicGate1(GateType.AND, new boolean[] { true, true });

        assertEquals(GateType.AND, g1.getType());
        assertEquals(GateType.OR, g2.getType());

        assertNotNull(g1.getInputs());
        assertNotNull(g2.getInputs());

        place1.clear();
        g1.transferFrom(place1);
        g2.clear();


        assertEquals("type is null regardless of original type",
        g1.getType(), g2.getType());
        assertEquals("type is null regardless of original type",
        g1.getType(), place1.getType());
        assertEquals("inputs are null regardless of original inputs",
        g1.getInputs(), g2.getInputs());
        assertEquals("inputs are null regardless of original inputs",
        g1.getInputs(), place1.getInputs());
    }

    // -- setType and getType tests --

    /**
     * Tests that setType and getType work as expected for NOT.
     */
    @Test
    public void testSetTypeAndGetType1() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.NOT);
        assertEquals("getType should return last value set with setType",
                GateType.NOT, g.getType());
    }

    /**
     * Tests that setType and getType work as expected for AND.
     */
    @Test
    public void testSetTypeAndGetType2() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.AND);
        assertEquals("getType should return last value set with setType",
                GateType.AND, g.getType());
    }

    /**
     * Tests that setType and getType work as expected for OR.
     */
    @Test
    public void testSetTypeAndGetType3() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.OR);
        assertEquals("getType should return last value set with setType",
                GateType.OR, g.getType());
    }


    // -- setInputs and getInputs tests --

    /**
     * Tests that setInputs and getInputs work as expected with default type.
     */
    @Test
    public void testSetInputsAndGetInputsDefault() {
        LogicGate1 g = new LogicGate1();
        boolean[] arr = { true, false };
        g.setInputs(arr);

        boolean[] stored = g.getInputs();
        assertArrayEquals("Input values should be preserved", arr, stored);
        assertNotSame("Inputs should be cloned in setInputs", arr, stored);

        // Modify original array and ensure gate is unchanged
        arr[0] = false;
        assertArrayEquals("Modifying original input array should not affect gate",
                new boolean[] { true, false }, g.getInputs());
    }

    /**
     * Tests that setInputs and getInputs work as expected with OR type.
     */
    @Test
    public void testSetInputsAndGetInputsOR() {
        LogicGate1 g = new LogicGate1(GateType.OR);
        boolean[] arr = { true, false };
        g.setInputs(arr);

        boolean[] stored = g.getInputs();
        assertArrayEquals("Input values should be preserved", arr, stored);
        assertNotSame("Inputs should be cloned in setInputs", arr, stored);

        // Modify original array and ensure gate is unchanged
        arr[0] = false;
        assertArrayEquals("Modifying original input array should not affect gate",
                new boolean[] { true, false }, g.getInputs());
    }

    // -- getOutput tests --

    /**
     * Tests that getOutput throws IllegalStateException when type is unset.
     */
    @Test(expected = IllegalStateException.class)
    public void testGetOutputThrowsWhenTypeUnset() {
        LogicGate1 g = new LogicGate1();
        g.setInputs(new boolean[] { true, true });
        g.getOutput(); // should throw
    }

    /**
     * Tests that getOutput throws IllegalStateException when inputs are unset.
     */
    @Test(expected = IllegalStateException.class)
    public void testGetOutputThrowsWhenInputsUnset() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.AND);
        g.getOutput(); // should throw
    }

    /**
     * Tests for correct output of various gate types and input combinations.
     */
    @Test
    public void testGetOutputAndGateTrueWhenAllTrue() {
        LogicGate1 g = this.createGate(GateType.AND, true, true, true);
        assertTrue("AND gate should be true when all inputs are true",
                g.getOutput());
    }

    /**
     * Tests for correct output of various gate types and input combinations.
     */
    @Test
    public void testGetOutputAndGateFalseWhenAnyFalse() {
        LogicGate1 g = this.createGate(GateType.AND, true, false, true);
        assertFalse("AND gate should be false when any input is false",
                g.getOutput());
    }

    /**
     * Tests for correct output of various gate types and input combinations.
     */
    @Test
    public void testGetOutputOrGateTrueWhenAnyTrue() {
        LogicGate1 g = this.createGate(GateType.OR, false, true, false);
        assertTrue("OR gate should be true when any input is true",
                g.getOutput());
    }

    /**
     * Tests for correct output of various gate types and input combinations.
     */
    @Test
    public void testGetOutputOrGateFalseWhenAllFalse() {
        LogicGate1 g = this.createGate(GateType.OR, false, false, false);
        assertFalse("OR gate should be false when all inputs are false",
                g.getOutput());
    }

    /**
     * Tests for correct output of various gate types and input combinations.
     */
    @Test
    public void testGetOutputNotGateInvertsInput() {
        LogicGate1 g1 = this.createGate(GateType.NOT, true);
        assertFalse("NOT(true) should be false", g1.getOutput());

        LogicGate1 g2 = this.createGate(GateType.NOT, false);
        assertTrue("NOT(false) should be true", g2.getOutput());
    }

    /**
     * Tests that getOutput throws IllegalArgumentException for NOT gate
     * with wrong number of inputs.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetOutputNotGateWithWrongNumberOfInputsThrows() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.NOT);
        g.setInputs(new boolean[] { true, false }); // invalid for NOT
        g.getOutput(); // should throw
    }

    // Inherited secondary methods: toggleInput, toString, clone,
    // equals, hashCode

    /**
     * Tests that toggleInput flips the specified input bit.
     */
    @Test
    public void testToggleInputFlipsSelectedBit() {
        LogicGate1 g = this.createGate(GateType.AND, true, false, true);
        assertFalse("Initial AND(1,0,1) output should be false", g.getOutput());

        g.toggleInput(1); // flip middle bit: 1,0,1 -> 1,1,1

        assertArrayEquals("toggleInput should flip only specified bit",
                new boolean[] { true, true, true }, g.getInputs());
        assertTrue("After toggle, AND(1,1,1) output should be true",
                g.getOutput());
    }

    /**
     * Tests that toggleInput throws IndexOutOfBoundsException for invalid index.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testToggleInputOutOfBoundsThrows() {
        LogicGate1 g = this.createGate(GateType.OR, true, false);
        g.toggleInput(2); // invalid index
    }

    /**
     * Tests that toggleInput throws IllegalStateException when inputs are unset.
     */
    @Test(expected = IllegalStateException.class)
    public void testToggleInputWithoutInputsThrows() {
        LogicGate1 g = new LogicGate1();
        g.setType(GateType.OR);
        g.toggleInput(0); // no inputs set
    }

    /**
     * Tests toString for an AND gate with inputs (1,1).
     */
    @Test
    public void testToStringAND1() {
        // AND(1,1) = 1
        LogicGate1 gate = this.createGate(GateType.AND, true, true);

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
        LogicGate1 gate = this.createGate(GateType.AND, true, true, false);

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
        LogicGate1 gate = this.createGate(GateType.OR, false, false);

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
        LogicGate1 gate = this.createGate(GateType.OR, false, false);

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
        LogicGate1 gate = this.createGate(GateType.NOT, false);

        String s = gate.toString();

        // Exact format based on your LogicGateSecondary implementation:
        // "AND(1,1) = 1"
        assertEquals("String representation should match spec",
                "NOT(0) = 1", s);
    }

    /**
     * Tests that equals is reflexive (equals itself).
     */
    @Test
    public void testEqualsReflexive() {
        LogicGate1 gate = this.createGate(GateType.AND, true, true, false);
        assertTrue("equals must be reflexive", gate.equals(gate));
    }

    /**
     * Tests that equals is symmetric for two equal LogicGates.
     */
    @Test
    public void testEqualsSameTypeSameInputs() {
        LogicGate1 g1 = this.createGate(GateType.OR, true, false, true);
        LogicGate1 g2 = this.createGate(GateType.OR, true, false, true);

        assertTrue("Gates with same type and same inputs should be equal",
                g1.equals(g2));
        assertTrue("Equality must be symmetric", g2.equals(g1));
    }

    /**
     * Tests that equals returns false for LogicGates with different types.
     */
    @Test
    public void testEqualsDifferentType() {
        LogicGate1 g1 = this.createGate(GateType.AND, true, false);
        LogicGate1 g2 = this.createGate(GateType.OR, true, false);

        assertFalse("Gates with different types should not be equal",
                g1.equals(g2));
    }

    /**
     * Tests that equals returns false for LogicGates with different inputs.
     */
    @Test
    public void testEqualsDifferentInputs() {
        LogicGate1 g1 = this.createGate(GateType.AND, true, true);
        LogicGate1 g2 = this.createGate(GateType.AND, true, false);

        assertFalse("Gates with same type but different inputs should not be equal",
                g1.equals(g2));
    }

    /**
     * Tests that equals returns false when compared to null.
     */
    @Test
    public void testEqualsHandlesNull() {
        LogicGate1 gate = this.createGate(GateType.OR, true, false, false);
        assertFalse("Gate should not be equal to null", gate.equals(null));
    }

    /**
     * Tests that equals returns false when compared to an object of a different class.
     */
    @Test
    public void testEqualsDifferentClass() {
        LogicGate1 gate = this.createGate(GateType.NOT, true);
        String other = "not a gate";
        assertFalse("Gate should not be equal to an object of another class",
                gate.equals(other));
    }

    /**
     * Tests that equal LogicGates have the same hashCode.
     */
    @Test
    public void testHashCodeEqual() {
        LogicGate1 g1 = this.createGate(GateType.AND, true, true, false);
        LogicGate1 g2 = this.createGate(GateType.AND, true, true, false);

        assertTrue("Gates should be equal", g1.equals(g2));
        assertEquals("Equal gates must have same hashCode",
                g1.hashCode(), g2.hashCode());
    }

    /**
     * Tests that different LogicGates have different hashCodes.
     */
    @Test
    public void testHashCodeDifferent() {
        LogicGate1 gate = this.createGate(GateType.OR, false, true, true);
        int h1 = gate.hashCode();
        int h2 = gate.hashCode();
        assertEquals("hashCode must be consistent on repeated calls", h1, h2);
    }
}
