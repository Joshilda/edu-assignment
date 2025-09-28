# Smart Home System – Java Console Application

## Abstract
This project is a **console-based Smart Home System** built in Java.  
It allows users to manage smart devices such as **lights, thermostats, and door locks** through a central hub.  
The system demonstrates **object-oriented programming (OOP)**, **design patterns**, and **software best practices**.  
It is designed as part of a coding exercise to evaluate coding quality, design decisions, and adherence to global standards.

---

## Objectives
- Build a modular and extensible **Smart Home simulation** in pure Java.  
- Showcase **design pattern implementation** (Observer, Factory Method, Proxy, Singleton).  
- Provide features for **device management, scheduling, and triggers**.  
- Ensure **logging, exception handling, and validation** at all levels.  
- Follow **SOLID principles** and avoid hard-coded infinite loops.  

---

## Features
- **Device Control**  
  - Lights: turn on/off  
  - Thermostat: set temperature  
  - Door Lock: lock/unlock  

- **Hub (Central Controller)**  
  - Singleton hub that registers devices and executes commands  

- **Scheduling**  
  - Schedule device commands by time (HH:MM format)  
  - Tasks automatically reschedule daily  

- **Automation with Triggers**  
  - Example: If thermostat temperature > 75 → turn off light  

- **Proxy Access Control**  
  - Permissions enforced for devices  

- **Observer Pattern**  
  - `Hub` notifies `TriggerManager` on device state changes  

- **Logging & Error Handling**  
  - Uses `java.util.logging` for status, warnings, and errors  

---

## Design Patterns Applied
1. **Singleton Pattern** → `Hub` ensures only one system controller  
2. **Factory Method Pattern** → `DeviceFactory` creates different device objects  
3. **Proxy Pattern** → `DeviceProxy` manages access control for devices  
4. **Observer Pattern** → `Hub` notifies observers (e.g., `TriggerManager`) when device states change  

---
