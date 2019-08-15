package contracts;

public class CANMessageContract {

	private static final int demandTEC = 127;
	private static final int demandREC = 127;
	private static final double demandFrequency = 0.005; // second = 0.5 msec;
	
	//demand
	public boolean isMeetfrequencyUpdate(double frequency) {
		return ((frequency == demandFrequency)? true: false);
	}
	
	//demand
	public boolean isErrorActive(int runtimeTEC, int runtimeREC) {
		return ((runtimeTEC <= demandTEC) && (runtimeREC <= demandREC)? true: false);
	}
}
