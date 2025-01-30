# WeaponStateMachine - Code Improvement Challenge

## **Introduction**
This repository contains a simple state management system for a weapon, but the current implementation has several design flaws. The goal of this challenge is to improve the implementation by applying best practices, including **SOLID principles**, **concurrency handling**, and **modern Java constructs** like `enum` and `record`.

---

## **Current Issues**

### **1. Violations of SOLID Principles**
- **Single Responsibility Principle (SRP)**: The `Weapon` class is handling both **state transitions** and **durability changes**, violating SRP. These should be separated.
- **Open/Closed Principle (OCP)**: The current design requires modifying `handleEvent()` every time a new state is added. Instead, we should leverage **polymorphism** and **encapsulation**.
- **Liskov Substitution Principle (LSP)**: The state transitions are hardcoded, making it impossible to introduce alternative weapon behaviors without modifying the existing class.
- **Interface Segregation Principle (ISP)**: The class does not define interfaces, making it tightly coupled.
- **Dependency Inversion Principle (DIP)**: The state transitions are directly implemented within the class instead of relying on **abstractions**.

### **2. Concurrency Issues**
- The current implementation is not **thread-safe**.
- `state` and `durability` can be modified concurrently, leading to **race conditions**.
- There is no synchronization, atomic operations, or immutability safeguards.

### **3. Use of Primitive Strings for States and Events**
- Using `String` for `state` and `event` introduces **magic values** and increases the risk of **typos and errors**.
- The code lacks a **type-safe approach** to handling states and events.

### **4. No Validation on Durability**
- The durability can become **negative**, which is an **invalid state**.
- There is no **business logic constraint** ensuring that an upgrade failure results in a properly managed state.

### **5. Lack of Testability**
- The design is tightly coupled, making it **difficult to test individual states**.
- The lack of interfaces and abstractions makes **unit testing impractical**.

---

## **Expected Improvements**

### ✅ **1. Implement State Pattern**
Refactor the code using the **State Pattern** to improve extensibility and maintainability.

### ✅ **2. Use Enums for State and Events**
Replace primitive strings with `enum` to ensure type safety and prevent errors.

### ✅ **3. Ensure Thread Safety**
- Use `AtomicInteger` for durability management.
- Implement `synchronized` or `ReentrantLock` if state modifications need to be protected.

### ✅ **4. Encapsulate Business Rules**
- Ensure durability cannot become negative.
- Decouple durability logic from state transitions.

### ✅ **5. Improve Testability**
- Extract an interface for state behavior.
- Use dependency injection to provide state handlers.

### ❌ **Red Flags**
- Use of `static` or constants for state handling (bad practice in state management).
- Hardcoded `if-else` logic for state transitions instead of polymorphism.
- Direct mutation of state without validation.

---

## **Example of a Refactored Approach**

```java
public enum WeaponState {
    IDLE, UPGRADING, ENHANCED, BROKEN;
}

public enum WeaponEvent {
    UPGRADE_START, UPGRADE_SUCCESS, UPGRADE_FAIL;
}

public record WeaponStateTransition(WeaponState from, WeaponEvent event, WeaponState to) {}
```

This is just a starting point; the full solution should involve refactoring using the **State Pattern** and ensuring **thread safety**.

---

## **Evaluation Criteria**
Your solution will be assessed based on:

1. **Adherence to SOLID principles**
2. **Correct implementation of the State Pattern**
3. **Proper handling of concurrency issues**
4. **Use of modern Java features (e.g., `enum`, `record`, `sealed classes`)**
5. **Code maintainability and testability**

