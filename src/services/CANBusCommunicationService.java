package services;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import verificationScenarios.CANMessageVerificationScenario;
import verificationScenarios.ICANMessageVerificationScenario;

public class CANBusCommunicationService extends InVehicleServiceType{

	private ICANMessageVerificationScenario canBusCommunication;
	
	
	public CANBusCommunicationService() {
		//this.canBusCommunication = CANMessageVerificationScenario.getInstance();
		this.canBusCommunication = CANMessageVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		
		new ReputationComputation(reputationScore);
	}
	
	@Override
	public void receiveInVehicleInfo(double speed, double acceleration, double frequency) {
		this.canBusCommunication.setInVehicleInfo(speed,acceleration,frequency);
		if(this.canBusCommunication.verifyFrequencyUpdate()) {
			this.reputationScore.increasePositiveInteractionScore();
		}
		else {
			this.reputationScore.increaseNegativeInteractionScore();
		}
	}
	
	@Override
	public void receiveErrorCouter(int TEC, int REC) {
		this.canBusCommunication.setCouters( TEC,  REC);
		if(this.canBusCommunication.verifyErrorActive()) {
			this.reputationScore.increasePositiveInteractionScore();
		}
		else {
			this.reputationScore.increaseNegativeInteractionScore();
		}
	}
	
	
}
