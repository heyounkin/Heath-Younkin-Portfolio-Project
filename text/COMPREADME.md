# LogicGate Component Family

This project implements a reusable component family for boolean logic gates,
following the Ohio State University CSE component design style. The component
supports building, evaluating, and manipulating logic gates such as AND, OR,
and NOT, and provides standard methods such as `equals`, `hashCode`, and
`clone` through a layered interface and class hierarchy.

### Component Layering

- **LogicGate (interface)**
  The highest-level type. Exposes public operations for working with logic gates.

- **LogicGateKernel (interface)**
  Defines the minimal set of kernel methods, including:

  - `setType`
  - `getType`
  - `setInputs`
  - `getInputs`
  - `getOutput`

- **LogicGateSecondary (abstract class)**
  Implements all standard methods:

  - `equals`
  - `hashCode`
  - `toString`
  - `clone`
  - `toggleInput`

- **LogicGate1 (final class)**
  A concrete representation of a logic gate using:

  ```java
  private GateType type;
  private boolean[] inputs;
  ```

- **GateType enum**
  - Enumerates supported gate types: AND, OR, NOT.
