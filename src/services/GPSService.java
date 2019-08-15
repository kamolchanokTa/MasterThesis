package services;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import verificationScenarios.PositionVerificationScenario;
import verificationScenarios.IPositionVerificationScenario;

public class GPSService extends ExternalPerceptionServiceType {
	
	IPositionVerificationScenario positionVerification;
	
	public GPSService() {
		this.positionVerification = PositionVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		new ReputationComputation(reputationScore);
	}

	@Override
	public void receiveVehicleLocation(double latitudeHV, double longtitudeHV, double frequency ) {
		this.positionVerification.setLocation( latitudeHV,  longtitudeHV,  frequency);
		if(this.positionVerification.verifyPositionFequencyUpdate())
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
	}

	@Override
	public void receiveReportedRadarRange(double reportedRadarRange, double frequency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVehicleLength(double vehicleLength) {
		// TODO Auto-generated method stub
	}
	
	
}
