# MasterThesis
Model and implementation of reputation element for Cooperative Adaptive Cruise Control (CACC)by Kamolchanok Tangsri
# Organization
## Code
Code sits in the src Folder. There are 7 modules of interest: 
1. Prototye : is a main function to run the project
2. Tests : contains all unit test; apply JUnit 5
3. Contract: Contains classes for aggragation of function and non-fucntional properties
4. Verification Scenarios:  represents implementation of various services that are able to determine their reputation scores based on observed properties. It defined scenario and measurement methods for properties which studied 
from HAZOP and defined demand and guarantee.
5. Services: defined methods of entire services using in CACC
6. Reputation: contains reputation calculation package
7. UML: using PlantUML to generate class diagrams

The code is implemented by using Java 11 based on Builder Pattern, Singleton Pattern, and Observer pattern.

## todo
- [x] implement reputation package 
- [ ] integrate with Unity for simulation
- [ ] integrate with CACC_fmus (MathLab path)for simulation

