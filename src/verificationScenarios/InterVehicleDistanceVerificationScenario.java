package verificationScenarios;

import contracts.InterVehicleDistanceContract;

public class InterVehicleDistanceVerificationScenario implements IInterVehicleDistanceVerificationScenario{
	
	private static volatile InterVehicleDistanceVerificationScenario instance;
	
	private InterVehicleDistanceContract interVehicleDistance;
	
	private double TVLength;
	private double frequency;
	
	
	private InterVehicleDistanceVerificationScenario() {
		this.interVehicleDistance = new InterVehicleDistanceContract();
	}
	
	 public static InterVehicleDistanceVerificationScenario getInstance() {
        if (instance == null) {
            synchronized (InterVehicleDistanceVerificationScenario.class) {
                if (instance == null) {
                    instance = new InterVehicleDistanceVerificationScenario();
                }
            }
        }
        return instance;
     }
	
	public void setTargetVehicleLength(double vehicleLength) {
		this.TVLength = vehicleLength;
	}
	
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public void calulateLimitDistance(double reportedRadarRange) {
		this.interVehicleDistance.calculateMaxReportedRange( reportedRadarRange, TVLength);
		this.interVehicleDistance.calculateMinReportedRange(reportedRadarRange);
	}
	
	public boolean verifyCloseFollowMode(double reportedRadarRange) {
		return (this.interVehicleDistance.isMeetfrequencyUpdate(frequency) && 
				this.interVehicleDistance.isCloseFollowMode(reportedRadarRange)? true: false);
	}
	
	

}
