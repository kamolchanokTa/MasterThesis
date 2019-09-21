package unity
// package Definition

import director.eventdirector.simpleapi.EventTriggeredDomain
import frontend.groovy.Feral
import part.component.realtime.timedirector.PollingRealTimeTriggeredSemantics
import part.component.realtime.timedirector.RealTimeTriggeredSemantics
import scenario.consolescenario.ConsoleScenario
import simulator.core.framework.director.coredirector.Director
import simulator.core.framework.director.coredirector.DirectorComponent
import simulator.core.framework.scenario.Scenario
import simulator.core.framework.time.SimulationDuration
import simulator.core.framework.time.SimulationTime

// Setup Feral <-> Groovy interface
Feral.setup();

// Create simulation scenario and root director
// - Create scenario with defined ending time
SimulationTime simulationDuration = new SimulationTime(3600,0)
Scenario simulationScenario = new ConsoleScenario(simulationDuration);

// Create director
Director director = new DirectorComponent(null);

// - Create and attach real time triggered director semantics, speed factor is 1.0
RealTimeTriggeredSemantics semantics = new PollingRealTimeTriggeredSemantics(director, 0.5f);

// Set duration of time step
SimulationDuration simulationStepDuration = new SimulationDuration(SimulationDuration._1MS.mul(200f));
semantics.setStepDuration(simulationStepDuration);

// - Add director as root component to director
simulationScenario.setRootComponent(director);

StringStability platoonPlan = new StringStability(director)


long current_time = director.getSimulationTime().getTimeAsLong();
System.out.println(current_time);

simulationScenario.run();

current_time = director.getSimulationTime().getTimeAsLong();
System.out.println(current_time);







