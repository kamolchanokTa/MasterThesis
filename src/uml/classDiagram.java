package uml;

public class classDiagram {

	
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
	
	
	class ControlContract [[java:contracts.ControlContract]] {
		-{static}double minAcceleration
		-{static}double maxAcceleration
		-{static}double minTimeGap
		-{static}double maxTimeGap
		+boolean isInAccelerationLimit(double acceleration)
		+boolean isInTimeGapLimit(double timeGap)
		+boolean isAlignWithLV(double hvSpeed, double lvSpeed)
	}
	
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

package service{
	class CACCService [[java:services.CACCService]] {
		-CACCServiceDirector caccServices
		-ExternalCommunicationServiceType dsrcCommService
		-ExternalCommunicationServiceType v2iCommService
		-ExternalPerceptionServiceType radarService
		-ExternalPerceptionServiceType gpsService
		-InVehicleServiceType invehicleService
		-ControlServiceType controlService
		-ReputationScore platoonPlanReputationScore
		-ReputationScore dsrcReputationScore
		-ReputationScore CANMsgReputationScore
		-ReputationScore gpsReputationScore
		-ReputationScore radarReputationScore
		-ReputationScore controlReputationScore
		+CACCService()
		+void receivePlatoonplan(String platoonId,...)
		+void receiveAllInformation(String LVID,...)
		+void combineReputationScores()
	}

	abstract class CACCServiceType [[java:services.CACCServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		+{abstract}void receiveAllInformation(String LVID,...)
		+{abstract}void receivePlatoonplan(String platoonId,...)
		+{abstract}void combineReputationScores()
	}
	class CACCServiceDirector [[java:services.CACCServiceDirector]] {
		-ExternalCommunicationServiceType DSRCBuilder
		-ExternalCommunicationServiceType V2IBuilder
		-ExternalPerceptionServiceType GPSBuilder
		-ExternalPerceptionServiceType RadarBuilder
		-ControlServiceType ControlBuilder
		-InVehicleServiceType InVehicleBuilder
		+void setDSRCCommunicationService(ExternalCommunicationServiceType builder)
		+void setV2ICommunicationService(ExternalCommunicationServiceType builder)
		+void setGPSService(ExternalPerceptionServiceType builder)
		+void setRadarSensingService(ExternalPerceptionServiceType builder)
		+void setControlService(ControlServiceType builder)
		+void setCANBusService(InVehicleServiceType builder)
		+ExternalCommunicationServiceType getDSRCBuilder()
		+ExternalCommunicationServiceType getV2IBuilder()
		+ExternalPerceptionServiceType getGPSBuilder()
		+ExternalPerceptionServiceType getRadarBuilder()
		+ControlServiceType getControlBuilder()
		+InVehicleServiceType getInVehicleBuilder()
	}
	
	
	class CANBusCommunicationService [[java:services.CANBusCommunicationService]] {
		-CANMessageVerificationScenario canBusCommunication
		+CANBusCommunicationService()
		+void receiveInVehicleInfo(double speed, ...)
		+void receiveErrorCouter(int TEC, int REC)
	}
	class DSRCCommunicationService [[java:services.DSRCCommunicationService]] {
		~IVehicleIdentityVerificationScenario vehicleIdentity
		~IV2VInputVerificationScenario v2vInput
		+DSRCCommunicationService()
		+void receiveLVID(String LVID, double frequency)
		+void receiveLVInformation(double LVSpeed, ...)
		+void detectPaketLoss(double time)
		+void setHVID(String HVID)
		+void setVehicleList(List<String> vehicleIdList)
		+void verifyIdentity()
		+void verifyV2VInformation()
		~void receivePlatoonPlan(String platoonId,...)
	}
	
	class GPSService [[java:services.GPSService]] {
		~PositionVerificationScenario positionVerification
		+GPSService()
		+void receiveVehicleLocation(double latitudeHV,..., double frequency)
		~void receiveReportedRadarRange(double reportedRadarRange, ...)
		~void setVehicleLength(double vehicleLength)
	}
	
	class PLFControlService [[java:services.PLFControlService]] {
		-StringStabilityVerificationScenario controlVerification
		+PLFControlService()
		+void contolTimeGap(double reportRadarRange, double hvSpeed)
		+void controlSpeedAndAcceleration(double hvSpeed,...)
	}
	
	class RadarSensingService [[java:services.RadarSensingService]] {
		~InterVehicleDistanceVerificationScenario interVehicleDistance
		+RadarSensingService()
		+void receiveReportedRadarRange(double reportedRadarRange,...)
		+void setVehicleLength(double vehicleLength)
		~void receiveVehicleLocation(double latitudeHV,...)
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


	abstract class InVehicleServiceType [[java:services.InVehicleServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void receiveInVehicleInfo(double speed, double acceleration, double frequency)
		~{abstract}void receiveErrorCouter(int TEC, int REC)
	}
	
	abstract class ControlServiceType [[java:services.ControlServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void contolTimeGap(double reportRadarRange, double hvSpeed)
		~{abstract}void controlSpeedAndAcceleration(double hvSpeed,...)
	}
	
	
	
	abstract class ExternalPerceptionServiceType [[java:services.ExternalPerceptionServiceType]] {
		~ReputationScore reputationScore
		+ReputationScore getReputationScore()
		~{abstract}void receiveReportedRadarRange(double reportedRadarRange, ...)
		~{abstract}void setVehicleLength(double vehicleLength)
		~{abstract}void receiveVehicleLocation(double latitudeHV,...y)
	}
	abstract class ExternalCommunicationServiceType [[java:services.ExternalCommunicationServiceType]] {
		~ReputationScore reputationScore
		~{abstract}void receivePlatoonPlan(String platoonId,...)
		~{abstract}void receiveLVID(String LVID, double frequency)
		~{abstract}void receiveLVInformation(double LVSpeed,...)
		~{abstract}void detectPaketLoss(double time)
		~{abstract}void setHVID(String HVID)
		~{abstract}void setVehicleList(List<String> vehicleIdList)
		~{abstract}void verifyIdentity()
		~{abstract}void verifyV2VInformation()
		+ReputationScore getReputationScore()
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
	interface IPlatoonPlanVerificationScenario [[java:verificationScenarios.IPlatoonPlanVerificationScenario]] {
		void verifyReceivingTime(Date receivingTime,...)
		void verifyPosition(double lvSpeed,...)
	}
	
	
	interface ICANMessageVerificationScenario [[java:verificationScenarios.ICANMessageVerificationScenario]] {
		void setCouters(int TEC, int REC)
		void setInVehicleInfo(double speed,...)
		boolean verifyCANMessage()
	}
	
	
	interface IInterVehicleDistanceVerificationScenario [[java:verificationScenarios.IInterVehicleDistanceVerificationScenario]] {
		void setTargetVehicleLength(double vehicleLength)
		void setFrequency(double frequency)
		void calulateLimitDistance(double reportedRadarRange,...)
		boolean verifyCloseFollowMode(double reportedRadarRange,...)
	}
	interface IPositionVerificationScenario [[java:verificationScenarios.IPositionVerificationScenario]] {
		void setLocation(double latitudeHV,...)
		void setFrequency(double frequency)
		boolean verifyPositionFequencyUpdate()
	}
	
	interface IStringStabilityVerificationScenario [[java:verificationScenarios.IStringStabilityVerificationScenario]] {
		void calculateTimeGap(double reportRadarRange,...)
		void receiveControlInformation(double hvSpeed,...)
		boolean verifyStringStability()
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
}

package reputation {

class ReputationScore [[java:reputation.ReputationScore]] {
	-{static}ReputationScore instance
	-int positiveInteractionScore
	-int negativeInteractionScore
	-int trustScore
	-List<Observer> observers
	+ReputationScore()
	+int getTrustScore()
	+void setTrustScore(int trustScore)
	+void increaseTrustScore()
	+int getPositiveInteractionScore()
	+void setPositiveInteractionScore(int positiveInteractionScore)
	+int getNegativeInteractionScore()
	+void setNegativeInteractionScore(int negativeInteractionScore)
	+void increasePositiveInteractionScore()
	+void increaseNegativeInteractionScore()
	+void attach(Observer observer)
	+void notifyAllObservers()
}

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
}
CACCServiceType <|-- CACCService
CACCServiceDirector -- CACCService
CACCServiceDirector *-- CANBusCommunicationService
CACCServiceDirector *-- DSRCCommunicationService
CACCServiceDirector *-- V2ICommunicationService
CACCServiceDirector *-- GPSService
CACCServiceDirector *-- RadarSensingService
CACCServiceDirector *-- PLFControlService

InVehicleServiceType <|-- CANBusCommunicationService : extends
ExternalCommunicationServiceType <|-- DSRCCommunicationService : extends
ExternalCommunicationServiceType <|-- V2ICommunicationService : extends
ExternalPerceptionServiceType <|-- GPSService : extends
ExternalPerceptionServiceType <|-- RadarSensingService : extends
ControlServiceType <|-- PLFControlService : extends


ExternalCommunicationServiceType<..V2VInputVerificationScenario : verifies
ExternalCommunicationServiceType<..VehicleIdentityVerificationScenario : verifies
ExternalCommunicationServiceType<..PlatoonPlanVerificationScenario : verifies
 
ControlServiceType<..StringStabilityVerificationScenario : verifies
ExternalPerceptionServiceType<..PositionVerificationScenario : verifies
ExternalPerceptionServiceType<..InterVehicleDistanceVerificationScenario : verifies
InVehicleServiceType<..CANMessageVerificationScenario : verifies

V2VInputVerificationScenario -- V2VInputContract
VehicleIdentityVerificationScenario -- IdentificationContract
PlatoonPlanVerificationScenario -- PlatoonPlanContract
StringStabilityVerificationScenario -- ControlContract
PositionVerificationScenario -- LocationContract
InterVehicleDistanceVerificationScenario -- InterVehicleDistanceContract
CANMessageVerificationScenario -- CANMessageContract


IPlatoonPlanVerificationScenario <|.. PlatoonPlanVerificationScenario : implements

ICANMessageVerificationScenario <|.. CANMessageVerificationScenario: implements
IInterVehicleDistanceVerificationScenario <|.. InterVehicleDistanceVerificationScenario : implements
IPositionVerificationScenario <|.. PositionVerificationScenario : implements
IStringStabilityVerificationScenario <|.. StringStabilityVerificationScenario : implements
IV2VInputVerificationScenario <|.. V2VInputVerificationScenario : implements
IVehicleIdentityVerificationScenario <|.. VehicleIdentityVerificationScenario : implements

Observer <|-- ReputationComputation :extends
ReputationScore *-- Observer : observers

ReputationScore --> DSRCCommunicationService: belong to
ReputationScore --> V2ICommunicationService: belong to
ReputationScore --> CANBusCommunicationService: belong to
ReputationScore --> GPSService: belong to
ReputationScore --> RadarSensingService: belong to
ReputationScore --> PLFControlService: belong to


@enduml
*
*/
