package services;

import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;

public abstract class ExternalPerceptionServiceType {
	
	ReputationScore reputationScore;
	
	public abstract void receiveReportedRadarRange(double reportedRadarRange, double frequency,VehicleRole role);
	public abstract void setVehicleLength(double vehicleLength);
	
	public abstract void receiveVehicleLocation(double latitudeHV, double longtitudeHV, double frequency );
	
	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}

}
