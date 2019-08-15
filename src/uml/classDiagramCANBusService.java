package uml;

public class classDiagramCANBusService {

}

/**
*
*@startuml

	scale 1100 width
	scale 1100 height


package contract {

	class CANMessageContract [[java:contracts.CANMessageContract]] {
		-{static}int demandTEC
		-{static}int demandREC
		-{static}double demandFrequency
		+boolean isMeetfrequencyUpdate(double frequency)
		+boolean isErrorActive(int runtimeTEC, int runtimeREC)
	}
}

package service{
	
	
	class CANBusCommunicationService [[java:services.CANBusCommunicationService]] {
		-CANMessageVerificationScenario canBusCommunication
		+CANBusCommunicationService()
		+void receiveInVehicleInfo(double speed, ...)
		+void receiveErrorCouter(int TEC, int REC)
	}


	abstract class InVehicleServiceType [[java:services.InVehicleServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void receiveInVehicleInfo(double speed, double acceleration, double frequency)
		~{abstract}void receiveErrorCouter(int TEC, int REC)
	}
}

package verificationScenario {
	class CANMessageVerificationScenario [[java:verificationScenarios.CANMessageVerificationScenario]] {
		-{static}CANMessageVerificationScenario instance
		-CANMessageContract canMessageContract
		-int runtimeTEC
		-int runtimeREC
		-double speed
		-double acceleration
		-double frequency
		-CANMessageVerificationScenario()
		+{static}CANMessageVerificationScenario getInstance()
		+void setCouters(int TEC, int REC)
		+void setInVehicleInfo(double speed,)
		+boolean verifyCANMessage()
	}
	
	
	
	interface ICANMessageVerificationScenario <<Singleton>> [[java:verificationScenarios.ICANMessageVerificationScenario]] {
		void setCouters(int TEC, int REC)
		void setInVehicleInfo(double speed,...)
		boolean verifyCANMessage()
	}
	
}




InVehicleServiceType <|-- CANBusCommunicationService : extends



InVehicleServiceType<..CANMessageVerificationScenario : verifies

CANMessageVerificationScenario -- CANMessageContract




ICANMessageVerificationScenario <|.. CANMessageVerificationScenario: implements






@enduml
*
*/