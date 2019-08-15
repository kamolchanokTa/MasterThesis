package verificationScenarios;

import contracts.LocationContract;

public class PositionVerificationScenario implements IPositionVerificationScenario{
	
	private static volatile PositionVerificationScenario instance;
	
	private double latitudeHV;
	private double longtitudeHV;
	private double frequency;
	
	private LocationContract locationContract;
	
	private PositionVerificationScenario() {
		this.locationContract = new LocationContract();
	}
	
	 public static PositionVerificationScenario getInstance() {
	    if (instance == null) {
	        synchronized (PositionVerificationScenario.class) {
	            if (instance == null) {
	                instance = new PositionVerificationScenario();
	            }
	        }
	    }
	    return instance;
	 }

	public void setLocation(double latitudeHV, double longtitudeHV, double frequency) {
		// TODO Auto-generated method stub
		this.latitudeHV = latitudeHV;
		this.longtitudeHV = longtitudeHV;
		setFrequency(frequency);
		
	}
	
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	
	public boolean verifyPositionFequencyUpdate() {
		return ((this.locationContract.isMeetfrequencyUpdate(frequency))? true: false);
	}

}
