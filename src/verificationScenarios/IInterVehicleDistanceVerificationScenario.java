package verificationScenarios;

public interface IInterVehicleDistanceVerificationScenario {
	
	public void setTargetVehicleLength(double vehicleLength);
	
	public void setFrequency(double frequency);
	public void calulateLimitDistance(double reportedRadarRange);
	
	public boolean verifyCloseFollowMode(double reportedRadarRange);

}
