package services;

import java.util.Date;
import java.util.List;

import services.DSRCCommunicationService.VehicleRole;

public class CACCServiceDirector {
	
	private ExternalCommunicationServiceType DSRCBuilder;
	private ExternalCommunicationServiceType V2IBuilder;
	private ExternalPerceptionServiceType GPSBuilder;
	private ExternalPerceptionServiceType RadarBuilder;
	private ControlServiceType ControlBuilder;
	private InVehicleServiceType InVehicleBuilder;
	
	
	public void setDSRCCommunicationService(ExternalCommunicationServiceType builder) {
        //builder.
		this.DSRCBuilder= builder;
    }
	
	public void setV2ICommunicationService(ExternalCommunicationServiceType builder) {
        //builder.
		this.V2IBuilder = builder;
    }
	
	public void setGPSService(ExternalPerceptionServiceType builder) {
        //builder.
		this.GPSBuilder = builder;
    }
	
	public void setRadarSensingService(ExternalPerceptionServiceType builder) {
        //builder.
		this.RadarBuilder = builder;
    }
	
	public void setControlService(ControlServiceType builder) {
        //builder.
		this.ControlBuilder = builder;
    }
	
	public void setCANBusService(InVehicleServiceType builder) {
        //builder.
		this.InVehicleBuilder = builder;
    }

	
	public ExternalCommunicationServiceType getDSRCBuilder() {
		return DSRCBuilder;
	}

	public ExternalCommunicationServiceType getV2IBuilder() {
		return V2IBuilder;
	}

	public ExternalPerceptionServiceType getGPSBuilder() {
		return GPSBuilder;
	}

	public ExternalPerceptionServiceType getRadarBuilder() {
		return RadarBuilder;
	}

	public ControlServiceType getControlBuilder() {
		return ControlBuilder;
	}

	public InVehicleServiceType getInVehicleBuilder() {
		return InVehicleBuilder;
	}

	public void constructDSRCCommunicationService(String lVID, double iDfrequency, double lVSpeed, double lVAcceleration, double lVfrequency, double detectTime, String hVID) {
		this.DSRCBuilder.receiveLVID(lVID,iDfrequency);
		this.DSRCBuilder.receiveLVInformation(lVSpeed, lVAcceleration, lVfrequency);
		this.DSRCBuilder.detectPacketLoss(detectTime);
		this.DSRCBuilder.setHVID(hVID);
	}

	public void constructV2ICommunicationService(String platoonId, double rendezvousSpeed, double lvSpeed, List<String> vehicleIdList, String meetingLocation, Date meetingTime, String hvStartLocation, Date hvStartTime, String destination) {
		this.V2IBuilder.receivePlatoonPlan(platoonId, rendezvousSpeed, lvSpeed, vehicleIdList, meetingLocation, meetingTime, hvStartLocation, hvStartTime, destination);
		this.DSRCBuilder.setVehicleList(vehicleIdList);
    }
	
	public void constructGPSService(double latitudeHV, double longtitudeHV, double gpsFrequency) {
		this.GPSBuilder.receiveVehicleLocation(latitudeHV,longtitudeHV, gpsFrequency);
    }
	
	public void constructRadarSensingService(double vehicleLength, double reportedRadarRange, double radarFrequency) {
		this.RadarBuilder.setVehicleLength(vehicleLength);
		this.RadarBuilder.receiveReportedRadarRange(reportedRadarRange,radarFrequency);
    }
	
	public void constructControlService(double reportedRadarRange, double hvSpeed, double lVSpeed, double hvAcceleration, double lVAcceleration, VehicleRole role) {
		this.ControlBuilder.contolTimeGap(reportedRadarRange, hvSpeed, role);
		this.ControlBuilder.controlSpeedAndAcceleration(hvSpeed, lVSpeed,hvAcceleration, lVAcceleration);
    }
	
	public void constructCANBusService(double hvSpeed, double hvAcceleration, double CANMsgFrequency, int TEC, int REC) {
		this.InVehicleBuilder.receiveInVehicleInfo(hvSpeed,hvAcceleration,CANMsgFrequency);
		this.InVehicleBuilder.receiveErrorCouter(TEC,REC);
    }
}
