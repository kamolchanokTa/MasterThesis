package services;

import reputation.ReputationScore;

public abstract class InVehicleServiceType {
	
	ReputationScore reputationScore;
	
	public abstract void receiveInVehicleInfo(double speed, double acceleration, double frequency);
	
	public abstract void receiveErrorCouter(int TEC, int REC);
	
	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}

}
