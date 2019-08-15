package contracts;

public class InterVehicleDistanceContract {
	
	private static final double vehicleLength = 7.2;
	private static final double demandFrequency = 0.01;
	
	private double maxReportedRange;
	private double minReportedRange;
	
	public void calculateMinReportedRange(double reportedRadarRange) {
		this.minReportedRange =  0.1*reportedRadarRange;
	}
	
	public void calculateMaxReportedRange(double reportedRadarRange, double TVLength) {
		
		this.maxReportedRange =  ((TVLength >= vehicleLength)?  (0.7 * reportedRadarRange) :  5.0);

	}
	//demand
	public boolean verifyReportedRange(double reportedRadarRange) {
		
		return ((reportedRadarRange > minReportedRange && reportedRadarRange <= maxReportedRange) ? true: false);
	}
	
	//demand
	public boolean isMeetfrequencyUpdate(double frequency) {
		return ((frequency == demandFrequency)? true: false);
	}
	//Guarantee
	public boolean isCloseFollowMode(double reportedRadarRange) {
		return verifyReportedRange(reportedRadarRange);
	}
	
}
