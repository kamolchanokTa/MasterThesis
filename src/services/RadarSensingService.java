package services;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;
import verificationScenarios.InterVehicleDistanceVerificationScenario;

public class RadarSensingService extends ExternalPerceptionServiceType{
	
	InterVehicleDistanceVerificationScenario interVehicleDistance;
	
	public RadarSensingService() {
		this.interVehicleDistance = InterVehicleDistanceVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		new ReputationComputation(reputationScore);
	}
	@Override
	public void receiveReportedRadarRange(double reportedRadarRange, double frequency, VehicleRole role) {
		this.interVehicleDistance.setFrequency(frequency);
		this.interVehicleDistance.calulateLimitDistance(reportedRadarRange);
		
		if(this.interVehicleDistance.verifyCloseFollowMode(reportedRadarRange) && role.equals(VehicleRole.follower)) 
			this.reputationScore.increasePositiveInteractionScore();
		else if(role.equals(VehicleRole.follower))
			this.reputationScore.increaseNegativeInteractionScore();
	}
	@Override
	public void setVehicleLength(double vehicleLength) {
		this.interVehicleDistance.setTargetVehicleLength(vehicleLength);
	}
	
	@Override
	public void receiveVehicleLocation(double latitudeHV, double longtitudeHV, double frequency) {
		// TODO Auto-generated method stub
		
	}

}
