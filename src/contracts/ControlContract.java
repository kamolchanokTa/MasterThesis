package contracts;

public class ControlContract {
	private static final double minAcceleration = -5d; // unit: m/s
	private static final double maxAcceleration = 2.75d; // unit: m/s
	private static final double minTimeGap = 0.8d; // unit: s
	private static final double maxTimeGap = 2.2d; // unit: s
	
	public boolean isInAccelerationLimit(double acceleration) {
		//System.out.println("minAcceleration: " +minAcceleration + " < "+ acceleration + " < maxAcceleration: " +  maxAcceleration );
		return ((acceleration >=minAcceleration) && (acceleration <= maxAcceleration)? true: false);
	}
	
	public boolean isInTimeGapLimit(double timeGap) {
		//System.out.println("minTimeGap: " +minTimeGap + " < "+ timeGap + " < maxTimeGap: " +  maxTimeGap );
		return ((timeGap >=minTimeGap) && (timeGap <= maxTimeGap)? true: false);
	}
	
	public boolean isAlignWithLV(double hvSpeed, double lvSpeed) {
		//System.out.println("hvSpeed: " +hvSpeed + " lvSpeed: " +  lvSpeed );
		return ((hvSpeed == lvSpeed) ? true: false);
	}

}
