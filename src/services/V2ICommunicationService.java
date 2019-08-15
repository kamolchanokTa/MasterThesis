package services;

import java.util.Date;
import java.util.List;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import services.DSRCCommunicationService.VehicleRole;
import verificationScenarios.PlatoonPlanVerificationScenario;
import verificationScenarios.IPlatoonPlanVerificationScenario;

public class V2ICommunicationService extends ExternalCommunicationServiceType {
	
	IPlatoonPlanVerificationScenario platoonPlan;
	
	public V2ICommunicationService() {
		this.platoonPlan = PlatoonPlanVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		new ReputationComputation(reputationScore);
	}
	public void receivePlatoonPlan(String platoonId, double rendezvousSpeed, 
				double lvSpeed, List<String> vehicleIdList,
				String meetingLocation, Date meetingTime,
				String hvStartLocation, Date hvStartTime,
				String destination){
			
		if(this.platoonPlan.verifyReceivingTime(new Date(System.currentTimeMillis()),meetingTime,hvStartTime ))
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
		
		if(this.platoonPlan.verifyPosition(lvSpeed, meetingLocation, hvStartLocation, meetingTime,hvStartTime, rendezvousSpeed))
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
	//setVehicleList(vehicleIdList);
	}
	@Override
	public void receiveLVID(String LVID, double frequency) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void receiveLVInformation(double LVSpeed, double LVAcceleration, double frequency) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void detectPacketLoss(double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHVID(String HVID) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setVehicleList(List<String> vehicleIdList) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void verifyIdentity() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void verifyV2VInformation() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public VehicleRole verifyRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
