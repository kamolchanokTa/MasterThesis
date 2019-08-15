package uml;

public class classDiagramControlService {

}

/**
*
*@startuml

	scale 1100 width
	scale 1100 height


package contract {
	
	class ControlContract [[java:contracts.ControlContract]] {
		-{static}double minAcceleration
		-{static}double maxAcceleration
		-{static}double minTimeGap
		-{static}double maxTimeGap
		+boolean isInAccelerationLimit(double acceleration)
		+boolean isInTimeGapLimit(double timeGap)
		+boolean isAlignWithLV(double hvSpeed, double lvSpeed)
	}
}

package service{
	
	class PLFControlService [[java:services.PLFControlService]] {
		-StringStabilityVerificationScenario controlVerification
		+PLFControlService()
		+void contolTimeGap(double reportRadarRange, double hvSpeed)
		+void controlSpeedAndAcceleration(double hvSpeed,...)
	}
	
	
	abstract class ControlServiceType [[java:services.ControlServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void contolTimeGap(double reportRadarRange, double hvSpeed)
		~{abstract}void controlSpeedAndAcceleration(double hvSpeed,...)
	}
}

package verificationScenario {
	
	class StringStabilityVerificationScenario [[java:verificationScenarios.StringStabilityVerificationScenario]] {
		-{static}StringStabilityVerificationScenario instance
		-ControlContract controlContract
		-double hvSpeed
		-double lvSpeed
		-double hvAcceleration
		-double lvAcceleration
		-double timeGap
		-StringStabilityVerificationScenario()
		+{static}StringStabilityVerificationScenario getInstance()
		+void calculateTimeGap(double reportRadarRange, double hvSpeed)
		+void receiveControlInformation(double hvSpeed, ...)
		+boolean verifyStringStability()
	}
	
	
	
	
	interface IStringStabilityVerificationScenario <<Singleton>> [[java:verificationScenarios.IStringStabilityVerificationScenario]] {
		void calculateTimeGap(double reportRadarRange,...)
		void receiveControlInformation(double hvSpeed,...)
		boolean verifyStringStability()
	}
	
}




ControlServiceType <|-- PLFControlService : extends
 
ControlServiceType<..StringStabilityVerificationScenario : verifies


StringStabilityVerificationScenario -- ControlContract



IStringStabilityVerificationScenario <|.. StringStabilityVerificationScenario : implements


@enduml
*
*/
