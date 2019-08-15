package services;

import java.util.Date;
import java.util.List;

import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;

public abstract class ExternalCommunicationServiceType {
	
	ReputationScore reputationScore;
	
	public abstract void receivePlatoonPlan(String platoonId, double rendezvousSpeed, 
			double lvSpeed, List<String> vehicleIdList,
			String meetingLocation, Date meetingTime,
			String hvStartLocation, Date hvStartTime,
			String destination);
	public abstract void receiveLVID(String LVID, double frequency);
	
	public abstract void receiveLVInformation(double LVSpeed, double LVAcceleration, double frequency );
	
	public abstract void detectPacketLoss(double time);
	
	public abstract void setHVID(String HVID);
	
	public abstract void setVehicleList(List<String> vehicleIdList);
	public abstract void verifyIdentity();
	public abstract void verifyV2VInformation();
	public abstract VehicleRole verifyRole();
	
	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}
	

}
