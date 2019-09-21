package services;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;
import verificationScenarios.StringStabilityVerificationScenario;
import verificationScenarios.IStringStabilityVerificationScenario;

public class PLFControlService extends ControlServiceType {

	private IStringStabilityVerificationScenario controlVerification;
	
	public PLFControlService() {
		this.controlVerification = StringStabilityVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		new ReputationComputation(reputationScore);
	}
	
	public double getComputedTimeGap() {
		return this.controlVerification.getTimeGap();
	}
	public void contolTimeGap(double reportRadarRange, double hvSpeed, VehicleRole hvRole) {
		this.controlVerification.calculateTimeGap(reportRadarRange, hvSpeed);
		if(this.controlVerification.verifyTimeGap() && hvRole == VehicleRole.follower) 
			this.reputationScore.increasePositiveInteractionScore();
		else if(hvRole == VehicleRole.follower)
			this.reputationScore.increaseNegativeInteractionScore();
	}
	
	public void controlSpeedAndAcceleration(double hvSpeed,double lvSpeed,double hvAcceleration,double lvAcceleration) {
		this.controlVerification.receiveControlInformation(hvSpeed, lvSpeed, hvAcceleration, lvAcceleration);
		
		if(this.controlVerification.verifySpeedAlignment()) 
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
		
		if(this.controlVerification.verifyAccelerationLimit()) 
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
		
//		System.out.println("postive: "+ this.reputationScore.getPositiveInteractionScore() + " negative: " + reputationScore.getNegativeInteractionScore());
	}
	
}
