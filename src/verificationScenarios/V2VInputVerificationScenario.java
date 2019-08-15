package verificationScenarios;

import contracts.V2VInputContract;

public class V2VInputVerificationScenario implements IV2VInputVerificationScenario {
	
	private static volatile V2VInputVerificationScenario instance;
	
	private double LVSpeed;
	private double LVAcceleration;
	private double frequency;
	private double timeOfPacketLoss;
	
	private V2VInputContract V2VInput;

	private V2VInputVerificationScenario() {
		V2VInput = new V2VInputContract();
	}
	
	public static V2VInputVerificationScenario getInstance() {
	    if (instance == null) {
	        synchronized (V2VInputVerificationScenario.class) {
	            if (instance == null) {
	                instance = new V2VInputVerificationScenario();
	            }
	        }
	    }
	    return instance;
	 }
	
	public void setLVInformation(double LVSpeed,  double LVAcceleration, double frequency) {
		this.LVSpeed = LVSpeed;
		this.LVAcceleration = LVAcceleration;
		this.frequency = frequency;
	}
	
	public void setTimeOfPacketLoss(double timeOfPacketLoss) {
		this.timeOfPacketLoss = timeOfPacketLoss;
	}
	
	public boolean verifyScenarioV2VInput() {
		return ((this.V2VInput.isMeetfrequencyUpdate(frequency) &&
				!this.V2VInput.isCommunicationFail(timeOfPacketLoss))? true: false);
	}
	

}
