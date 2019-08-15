package uml;

public class classDiagramExternalCommunication {

}
/**
*
*@startuml



package reputation {
	abstract class Observer [[java:reputation.Observer]] {
		#ReputationScore subject
		+{abstract}void calculateReputationScore()
	}


	class ReputationComputation [[java:reputation.ReputationComputation]] {
		-{static}double satisFactionThreshold
		~ReputationScore reputationScore
		+ReputationComputation(ReputationScore reputationScore)
		+void calculateReputationScore()
	}
	
	
	class ReputationScore [[java:reputation.ReputationScore]] {
		-{static}ReputationScore instance
		-int positiveInteractionScore
		-int negativeInteractionScore
		-int positiveIdentityScore
		-int negativeIdentityScore
		-List<Observer> observers
		-ReputationScore()
		+{static}ReputationScore getInstance()
		+int getPositiveInteractionScore()
		+void setPositiveInteractionScore(int positiveInteractionScore)
		+int getNegativeInteractionScore()
		+void setNegativeInteractionScore(int negativeInteractionScore)
		+int getPositiveIdentityScore()
		+void setPositiveIdentityScore(int positiveIdentityScore)
		+int getNegativeIdentityScore()
		+void setNegativeIdentityScore(int negativeIdentityScore)
		+void increasePositiveInteractionScore()
		+void increaseNegativeInteractionScore()
		+void increasePositiveIdentityScore()
		+void increaseNegativeIdentityScore()
		+void attach(Observer observer)
		+void notifyAllObservers()
	}
}


package contract {
	class IdentificationContract [[java:contracts.IdentificationContract]] {
		-{static}double demandFrequency
		-boolean isHVInPlatoonList
		-boolean isLVInPlatoonList
		-boolean isMeetFequency
		+boolean isHVIntheVehiclePlatoonList(String VID, List<String> vehicleList)
		+boolean isLVIntheVehiclePlatoonList(String VID, List<String> vehicleList)
		~boolean isIntheVehiclePlatoonList(String VID, List<String> vehicleList)
		+boolean isLeadingVehicle(String HVID, String LVID)
		+boolean isMeetfrequencyUpdate(double frequency)
		+boolean isPartOfPlatoon()
	}
	class PlatoonPlanContract [[java:contracts.PlatoonPlanContract]] {
		+boolean checkReceivingTime(Date receivingTime, ...)
		+void verifyRendezvousSpeed(double virtualPositionDifference,...)
	}



	class V2VInputContract [[java:contracts.V2VInputContract]] {
		-{static}double demandFrequency
		-{static}double demandPacketLossTime
		+boolean isCommunicationFail(double packetLossTime)
		+boolean isMeetfrequencyUpdate(double frequency)
	}
}

package service {
	
	class DSRCCommunicationService [[java:services.DSRCCommunicationService]] {
		~VehicleIdentityVerificationScenario vehicleIdentity
		~V2VInputVerificationScenario v2vInput
		+DSRCCommunicationService()
		+void receiveLVID(String LVID, double frequency)
		+void receiveLVInformation(double LVSpeed,...)
		+void detectPaketLoss(double time)
		+void setHVID(String HVID)
		+void setVehicleList(List<String> vehicleIdList)
		~void receivePlatoonPlan(String platoonId, ...)
	}
	
	class V2ICommunicationService [[java:services.V2ICommunicationService]] {
		~IPlatoonPlanVerificationScenario platoonPlan
		+V2ICommunicationService()
		+void receivePlatoonPlan(String platoonId, ...)
		~void receiveLVID(String LVID, double frequency)
		~void receiveLVInformation(double LVSpeed, ...)
		~void detectPaketLoss(double time)
		~void setHVID(String HVID)
		~void setVehicleList(List<String> vehicleIdList)
	}
	
	abstract class ExternalCommunicationServiceType [[java:services.ExternalCommunicationServiceType]] {
	~{abstract}void receivePlatoonPlan(String platoonId, ..., String destination)
	~{abstract}void receiveLVID(String LVID, double frequency)
	~{abstract}void receiveLVInformation(double LVSpeed, double LVAcceleration, double frequency)
	~{abstract}void detectPaketLoss(double time)
	~{abstract}void setHVID(String HVID)
	~{abstract}void setVehicleList(List<String> vehicleIdList)
	}
}

package verificationScenario {

	interface IPlatoonPlanVerificationScenario [[java:verificationScenarios.IPlatoonPlanVerificationScenario]] {
		void verifyReceivingTime(Date receivingTime,...)
		void verifyPosition(double lvSpeed,...)
	}
	
	interface IV2VInputVerificationScenario [[java:verificationScenarios.IV2VInputVerificationScenario]] {
		void setLVInformation(double LVSpeed, double LVAcceleration, double frequency)
		void setTimeOfPacketLoss(double timeOfPacketLoss)
		boolean verifyScenarioV2VInput()
	}
	
	
	interface IVehicleIdentityVerificationScenario [[java:verificationScenarios.IVehicleIdentityVerificationScenario]] {
		void setLVID(String LVID)
		void setFrequency(double frequency)
		void setHVID(String HVID)
		void setVehicleList(List<String> vehicleIdList)
		boolean verifyScenarioHVIsPartOfPlatoon()
	}
	
	
	
	class PlatoonPlanVerificationScenario [[java:verificationScenarios.PlatoonPlanVerificationScenario]] {
		-{static}PlatoonPlanVerificationScenario instance
		-PlatoonPlanContract platoonPlanContract
		-double hvStartToMeetingDistance
		-double virtualPositionDifference
		-PlatoonPlanVerificationScenario()
		+{static}PlatoonPlanVerificationScenario getInstance()
		+void verifyReceivingTime(Date receivingTime,...)
		+void verifyPosition(double lvSpeed, ..., double rendezvousSpeed)
		-void calculateDistance(String meetingLocation, ...)
		-void calculateVirtualPositionDifference(Date meetingTime, ...)
	}
	
	class V2VInputVerificationScenario [[java:verificationScenarios.V2VInputVerificationScenario]] {
		-{static}V2VInputVerificationScenario instance
		-double LVSpeed
		-double LVAcceleration
		-double frequency
		-double timeOfPacketLoss
		-V2VInputContract V2VInput
		-V2VInputVerificationScenario()
		+{static}V2VInputVerificationScenario getInstance()
		+void setLVInformation(double LVSpeed, ...)
		+void setTimeOfPacketLoss(double timeOfPacketLoss)
		+boolean verifyScenarioV2VInput()
	}
	
	
	class VehicleIdentityVerificationScenario [[java:verificationScenarios.VehicleIdentityVerificationScenario]] {
		-{static}VehicleIdentityVerificationScenario instance
		~IdentificationContract identification
		-String HVID
		-String LVID
		-double frequency
		-List<String> vehicleIdList
		-VehicleIdentityVerificationScenario()
		+{static}VehicleIdentityVerificationScenario getInstance()
		+void setLVID(String LVID)
		+void setFrequency(double frequency)
		+void setHVID(String HVID)
		+void setVehicleList(List<String> vehicleIdList)
		+boolean verifyScenarioHVIsPartOfPlatoon()
	}
}



ExternalCommunicationServiceType <|-- DSRCCommunicationService : extends
ExternalCommunicationServiceType <|-- V2ICommunicationService : extends

ExternalCommunicationServiceType<..V2VInputVerificationScenario : verifies
ExternalCommunicationServiceType<..VehicleIdentityVerificationScenario : verifies
ExternalCommunicationServiceType<..PlatoonPlanVerificationScenario : verifies

V2VInputVerificationScenario -- V2VInputContract
VehicleIdentityVerificationScenario -- IdentificationContract
PlatoonPlanVerificationScenario -- PlatoonPlanContract

IV2VInputVerificationScenario <|.. V2VInputVerificationScenario : implements
IVehicleIdentityVerificationScenario <|.. VehicleIdentityVerificationScenario : implements
IPlatoonPlanVerificationScenario <|.. PlatoonPlanVerificationScenario : implements

Observer <|-- ReputationComputation :extends
ReputationScore *-- Observer : observers
ReputationScore --> DSRCCommunicationService: belong to
ReputationScore --> V2ICommunicationService: belong to
@enduml
*
*/
