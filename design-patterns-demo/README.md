# Design Patterns Demo (Java)

This project demonstrates six classic **software design patterns** implemented in **Java**.  
It is a console-based project focused on **logic, code quality, OOP principles, SOLID design, logging, exception handling, and defensive programming**.

---

## Abstract
Design patterns provide reusable solutions to common software design problems.  
This project implements **behavioral, creational, and structural patterns** in simple, real-world inspired use cases.  
It highlights how patterns can improve **reusability, maintainability, and scalability** in software systems.

---

## Objective
- To showcase the practical application of **six design patterns**:
  - Observer, Strategy, Singleton, Factory, Adapter, Proxy.
- To demonstrate **OOP concepts and SOLID principles**.
- To ensure **defensive coding, validations, exception handling, and logging**.
- To deliver a **clean, package-structured, console-based Java project** suitable for long-term maintainability.

---

## Outcome
- A **ready-to-run Java project** that illustrates how design patterns solve real-world programming challenges.
- Demonstrates **how to organize classes and packages effectively**.
- Provides a **reference repository** that can be explained during code walkthroughs and interviews.
- Ensures the candidate can **showcase creativity, problem-solving, and code quality**.

---

## Patterns Implemented

### Behavioral
1. **Observer Pattern**  
   Use Case: A `WeatherStation` notifies multiple `DisplayDevice` observers whenever the weather changes.

2. **Strategy Pattern**  
   Use Case: A `ShoppingCart` can pay using different strategies (`CreditCardPayment`, `PayPalPayment`).

### Creational
3. **Singleton Pattern**  
   Use Case: A global `AppLogger` ensures only one instance of the logger exists across the application.

4. **Factory Pattern**  
   Use Case: A `ShapeFactory` creates different `Shape` objects (`Circle`, `Rectangle`) based on input.

### Structural
5. **Adapter Pattern**  
   Use Case: A `PrinterAdapter` allows a `NewPrinter` interface to work with an `OldPrinter` class.

6. **Proxy Pattern**  
   Use Case: A `ProxyImage` delays loading of a `RealImage` from disk until it is first needed.

