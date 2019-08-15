package services;

import reputation.ReputationScore;

public abstract class ExternalPerceptionServiceType {
	
	ReputationScore reputationScore;
	
	public abstract void receiveReportedRadarRange(double reportedRadarRange, double frequency);
	public abstract void setVehicleLength(double vehicleLength);
	
	public abstract void receiveVehicleLocation(double latitudeHV, double longtitudeHV, double frequency );
	
	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}

}
