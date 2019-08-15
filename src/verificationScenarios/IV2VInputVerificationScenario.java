package verificationScenarios;

public interface IV2VInputVerificationScenario {
	
	public void setLVInformation(double LVSpeed,  double LVAcceleration, double frequency);
	public void setTimeOfPacketLoss(double timeOfPacketLoss);
	public boolean verifyScenarioV2VInput();

}
