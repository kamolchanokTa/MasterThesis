package verificationScenarios;

public interface IPositionVerificationScenario {
	
	public void setLocation(double latitudeHV, double longtitudeHV, double frequency);
	
	public void setFrequency(double frequency);
	
	public boolean verifyPositionFequencyUpdate();

}
