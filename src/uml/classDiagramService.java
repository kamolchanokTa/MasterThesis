package uml;

public class classDiagramService {

}

/**
*
*@startuml






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
CACCServiceType <|-- CACCService
CACCServiceDirector -- CACCService: use

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




@enduml
*
*/
