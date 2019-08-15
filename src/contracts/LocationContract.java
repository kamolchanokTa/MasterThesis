package contracts;

public class LocationContract {
	
	private static final double demandFrequency = 1;
	
	//demand
	public boolean isMeetfrequencyUpdate(double frequency) {
		return ((frequency == demandFrequency)? true: false);
	}

}
