package services;

import java.util.Date;
import java.util.List;

import reputation.ReputationComputation;
import reputation.ReputationScore;
import verificationScenarios.V2VInputVerificationScenario;
import verificationScenarios.IV2VInputVerificationScenario;
import verificationScenarios.VehicleIdentityVerificationScenario;
import verificationScenarios.IVehicleIdentityVerificationScenario;

public class DSRCCommunicationService  extends ExternalCommunicationServiceType  {
	
	IVehicleIdentityVerificationScenario vehicleIdentity;
	IV2VInputVerificationScenario v2vInput;
	public enum VehicleRole {
	    leader,
	    follower
	}
	
	public DSRCCommunicationService(){
		this.vehicleIdentity = VehicleIdentityVerificationScenario.getInstance();
		this.v2vInput = V2VInputVerificationScenario.getInstance();
		
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		
		new ReputationComputation(reputationScore);
	}
	
	
	public void receiveLVID(String LVID, double frequency) {
		this.vehicleIdentity.setLVID(LVID);
		this.vehicleIdentity.setFrequency(frequency);
	}
	
	public void receiveLVInformation(double LVSpeed, double LVAcceleration, double frequency ) {
		this.v2vInput.setLVInformation(LVSpeed, LVAcceleration, frequency);
	}
	
	public void detectPacketLoss(double time) {
		this.v2vInput.setTimeOfPacketLoss(time);
	}
	
	public void setHVID(String HVID) {
		this.vehicleIdentity.setHVID(HVID);
	}
	
	public void setVehicleList(List<String> vehicleIdList) {
		this.vehicleIdentity.setVehicleList(vehicleIdList);
	}
	
	public VehicleRole verifyRole() {
		VehicleRole role = this.vehicleIdentity.IsLeader()? VehicleRole.leader: VehicleRole.follower;
		return role;
	}

	public void verifyIdentity() {
		if(this.vehicleIdentity.verifyScenarioHVIsPartOfPlatoon()) 
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
		if(this.vehicleIdentity.verifyFrequencyUpdate())
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
	}
	
	public void verifyV2VInformation() {
		if(this.v2vInput.verifyScenarioV2VInput()) 
			this.reputationScore.increasePositiveInteractionScore();
		else
			this.reputationScore.increaseNegativeInteractionScore();
	}

	@Override
	public void receivePlatoonPlan(String platoonId, double rendezvousSpeed, double lvSpeed, List<String> vehicleIdList,
			String meetingLocation, Date meetingTime, String hvStartLocation, Date hvStartTime, String destination) {
		// TODO Auto-generated method stub
		
	}

}
