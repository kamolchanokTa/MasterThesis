package verificationScenarios;

import java.util.List;

import contracts.IdentificationContract;


public class VehicleIdentityVerificationScenario implements IVehicleIdentityVerificationScenario{
	
	private static volatile VehicleIdentityVerificationScenario instance;
	
	IdentificationContract identification;
	//private List<Property> properties = new ArrayList<Property>();
	private String HVID;
	private String LVID;
	private double frequency;
	private List<String> vehicleIdList;
	
	private VehicleIdentityVerificationScenario(){
		
		identification = new IdentificationContract();
		
	}
	
	public static VehicleIdentityVerificationScenario getInstance() {
	    if (instance == null) {
	        synchronized (VehicleIdentityVerificationScenario.class) {
	            if (instance == null) {
	                instance = new VehicleIdentityVerificationScenario();
	            }
	        }
	    }
	    return instance;
	 }
	
	
	public void setLVID(String LVID) {
		this.LVID = LVID;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public void setHVID(String HVID) {
		this.HVID = HVID;
	}
	
	public void setVehicleList(List<String> vehicleIdList) {
		this.vehicleIdList = vehicleIdList;
	}

	
	//guarantee
	public boolean verifyScenarioHVIsPartOfPlatoon() {
		if(this.identification.isHVIntheVehiclePlatoonList(HVID, vehicleIdList) &&
				this.identification.isLVIntheVehiclePlatoonList(LVID, vehicleIdList) &&
				this.identification.isPartOfPlatoon()) 
			return true;
		else return false;
	}
	//demand
	public boolean verifyFrequencyUpdate() {
		return this.identification.isMeetfrequencyUpdate(frequency)? true: false;
	}

	//guarantee
	public boolean IsLeader() {
		if(this.identification.isLeadingVehicle(HVID, LVID))
			return true;
		else return false;
	}
}
