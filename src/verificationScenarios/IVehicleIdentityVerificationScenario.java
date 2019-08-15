package verificationScenarios;

import java.util.List;

public interface IVehicleIdentityVerificationScenario {
	
	public void setLVID(String LVID);
	
	public void setFrequency(double frequency);
	
	public void setHVID(String HVID);
	
	public void setVehicleList(List<String> vehicleIdList);
	
	public boolean verifyScenarioHVIsPartOfPlatoon();
	
	public boolean verifyFrequencyUpdate();
	
	public boolean IsLeader();

}
