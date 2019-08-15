package uml;

public class classDiagramExternalPerception {

}
/**
*
*@startuml

	scale 1100 width
	scale 1100 height


package contract {

	
	
	class InterVehicleDistanceContract [[java:contracts.InterVehicleDistanceContract]] {
		-{static}double vehicleLength
		-{static}double demandFrequency
		-double maxReportedRange
		-double minReportedRange
		+void calculateMinReportedRange(double reportedRadarRange)
		+void calculateMaxReportedRange(double reportedRadarRange,...)
		+boolean verifyReportedRange(double reportedRadarRange)
		+boolean isMeetfrequencyUpdate(double frequency)
		+boolean isCloseFollowMode(double reportedRadarRange)
	}
	
	
	class LocationContract [[java:contracts.LocationContract]] {
		-{static}double demandFrequency
		+boolean isMeetfrequencyUpdate(double frequency)
	}
}

package service{
	
	class GPSService [[java:services.GPSService]] {
		~PositionVerificationScenario positionVerification
		+GPSService()
		+void receiveVehicleLocation(double latitudeHV,..., double frequency)
		~void receiveReportedRadarRange(double reportedRadarRange, ...)
		~void setVehicleLength(double vehicleLength)
	}
	
	class RadarSensingService [[java:services.RadarSensingService]] {
		~InterVehicleDistanceVerificationScenario interVehicleDistance
		+RadarSensingService()
		+void receiveReportedRadarRange(double reportedRadarRange,...)
		+void setVehicleLength(double vehicleLength)
		~void receiveVehicleLocation(double latitudeHV,...)
	}


	
	
	
	abstract class ExternalPerceptionServiceType [[java:services.ExternalPerceptionServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void receiveReportedRadarRange(double reportedRadarRange, ...)
		~{abstract}void setVehicleLength(double vehicleLength)
		~{abstract}void receiveVehicleLocation(double latitudeHV,...y)
	}
}

package verificationScenario {
	
	
	class InterVehicleDistanceVerificationScenario [[java:verificationScenarios.InterVehicleDistanceVerificationScenario]] {
		-{static}InterVehicleDistanceVerificationScenario instance
		-InterVehicleDistanceContract interVehicleDistance
		-double TVLength
		-double frequency
		-InterVehicleDistanceVerificationScenario()
		+{static}InterVehicleDistanceVerificationScenario getInstance()
		+void setTargetVehicleLength(double vehicleLength)
		+void setFrequency(double frequency)
		+void calulateLimitDistance(double reportedRadarRange, ...)
		+boolean verifyCloseFollowMode(double reportedRadarRange,...)
	}
	
	
	
	class PositionVerificationScenario [[java:verificationScenarios.PositionVerificationScenario]] {
		-{static}PositionVerificationScenario instance
		-double latitudeHV
		-double longtitudeHV
		-double frequency
		-LocationContract locationContract
		-PositionVerificationScenario()
		+{static}PositionVerificationScenario getInstance()
		+void setLocation(double latitudeHV, ...)
		+void setFrequency(double frequency)
		+boolean verifyPositionFequencyUpdate()
	}
	
	
	
	interface IInterVehicleDistanceVerificationScenario <<Singleton>>[[java:verificationScenarios.IInterVehicleDistanceVerificationScenario]] {
		void setTargetVehicleLength(double vehicleLength)
		void setFrequency(double frequency)
		void calulateLimitDistance(double reportedRadarRange,...)
		boolean verifyCloseFollowMode(double reportedRadarRange,...)
	}
	interface IPositionVerificationScenario <<Singleton>> [[java:verificationScenarios.IPositionVerificationScenario]] {
		void setLocation(double latitudeHV,...)
		void setFrequency(double frequency)
		boolean verifyPositionFequencyUpdate()
	}
}



ExternalPerceptionServiceType <|-- GPSService : extends
ExternalPerceptionServiceType <|-- RadarSensingService : extends


 

ExternalPerceptionServiceType<..PositionVerificationScenario : verifies
ExternalPerceptionServiceType<..InterVehicleDistanceVerificationScenario : verifies



PositionVerificationScenario -- LocationContract
InterVehicleDistanceVerificationScenario -- InterVehicleDistanceContract






IInterVehicleDistanceVerificationScenario <|.. InterVehicleDistanceVerificationScenario : implements
IPositionVerificationScenario <|.. PositionVerificationScenario : implements


@enduml
*
*/