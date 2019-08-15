package verificationScenarios;

public interface ICANMessageVerificationScenario {
	
	public void setCouters(int TEC, int REC);
	public void setInVehicleInfo(double speed, double acceleration, double frequency);
	public boolean verifyFrequencyUpdate();
	public boolean verifyErrorActive();

}
