package verificationScenarios;

import contracts.CANMessageContract;

public class CANMessageVerificationScenario implements ICANMessageVerificationScenario{
	
	private static volatile CANMessageVerificationScenario instance;
	
	private CANMessageContract canMessageContract;
	
	private int runtimeTEC;
	private int runtimeREC;
	private double speed;
	private double acceleration;
	private double frequency;
	
	private CANMessageVerificationScenario() {
		this.canMessageContract = new CANMessageContract();
		
	}
	
	public static CANMessageVerificationScenario getInstance() {
	    if (instance == null) {
	        synchronized (CANMessageVerificationScenario.class) {
	            if (instance == null) {
	                instance = new CANMessageVerificationScenario();
	            }
	        }
	    }
	    return instance;
	 }

	public void setCouters(int TEC, int REC) {
		// TODO Auto-generated method stub
		this.runtimeTEC = TEC;
		this.runtimeREC = REC;
	}

	public void setInVehicleInfo(double speed, double acceleration, double frequency) {
		// TODO Auto-generated method stub
		this.speed = speed;
		this.acceleration = acceleration;
		this.frequency = frequency;
	}
	
	
	public boolean verifyFrequencyUpdate() {
		return this.canMessageContract.isMeetfrequencyUpdate(frequency)? true: false;
		
	}
	
	public boolean verifyErrorActive() {
		return this.canMessageContract.isErrorActive(runtimeTEC, runtimeREC)? true: false;
		
	}

}
