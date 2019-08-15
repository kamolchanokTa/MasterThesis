package verificationScenarios;

import contracts.ControlContract;

public class StringStabilityVerificationScenario implements IStringStabilityVerificationScenario{
	private static volatile StringStabilityVerificationScenario instance;
	
	private ControlContract controlContract;
	
	private double hvSpeed;
	private double lvSpeed;
	private double hvAcceleration;
	private double lvAcceleration;
	private StringStabilityVerificationScenario() {
		this.controlContract = new ControlContract();
	}
	
	private double timeGap;

    public static StringStabilityVerificationScenario getInstance() {
        if (instance == null) {
            synchronized (StringStabilityVerificationScenario.class) {
                if (instance == null) {
                    instance = new StringStabilityVerificationScenario();
                }
            }
        }
        return instance;
    }
    
    public void calculateTimeGap(double reportRadarRange, double hvSpeed) {
    	this.timeGap = reportRadarRange/hvSpeed ;
    }
    
    public double getTimeGap() {
    	return timeGap;
    }
    public void receiveControlInformation(double hvSpeed,double lvSpeed,double hvAcceleration,double lvAcceleration) {
    	this.hvSpeed = hvSpeed;
    	this.lvSpeed = lvSpeed;
    	this.hvAcceleration = hvAcceleration;
    	this.lvAcceleration = lvAcceleration;
    }
    
    public boolean verifyStringStability() {
    	return ((this.controlContract.isInAccelerationLimit(hvAcceleration)
    			&& this.controlContract.isInAccelerationLimit(lvAcceleration)
    			&& verifyTimeGap()
    			&& verifySpeedAlignment())? true: false);
    }
    
    public boolean verifyTimeGap() {
    	return this.controlContract.isInTimeGapLimit(timeGap)? true: false;
    }
    
    public boolean verifySpeedAlignment() {
    	return this.controlContract.isAlignWithLV(hvSpeed, lvSpeed)? true: false;
    }
    
    public boolean verifyAccelerationLimit() {
    	return ((this.controlContract.isInAccelerationLimit(hvAcceleration)
    			&& this.controlContract.isInAccelerationLimit(lvAcceleration))? true: false);
    }
    
}
