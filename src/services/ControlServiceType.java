package services;

import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;

public abstract class ControlServiceType {
	
	ReputationScore reputationScore;
	
	public abstract void contolTimeGap(double reportRadarRange, double hvSpeed, VehicleRole hvRole);
	public abstract void controlSpeedAndAcceleration(double hvSpeed,double lvSpeed,double hvAcceleration,double lvAcceleration);
	public abstract double getComputedTimeGap();

	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}
	
}
