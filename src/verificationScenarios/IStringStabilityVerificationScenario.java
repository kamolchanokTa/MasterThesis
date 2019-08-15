package verificationScenarios;

public interface IStringStabilityVerificationScenario {
	
	public void calculateTimeGap(double reportRadarRange, double hvSpeed);
	public void receiveControlInformation(double hvSpeed,double lvSpeed,double hvAcceleration,double lvAcceleration);
	public double getTimeGap();
	public boolean verifyStringStability();
	public boolean verifyTimeGap();
	public boolean verifySpeedAlignment();
	public boolean verifyAccelerationLimit();

}
