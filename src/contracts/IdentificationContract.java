package contracts;

import java.util.List;

public class IdentificationContract {
	
	private static final double demandFrequency = 1.0;
	// can check both HV and LV
	private boolean isHVInPlatoonList;
	private boolean isLVInPlatoonList;
	private boolean isMeetFequency;
	
	//demand
	
	
	public boolean isHVIntheVehiclePlatoonList(String VID, List<String> vehicleList) {
		isHVInPlatoonList = isIntheVehiclePlatoonList(VID, vehicleList);
		return isHVInPlatoonList;
	}
	//demand
	public boolean isLVIntheVehiclePlatoonList(String VID, List<String> vehicleList) {
		isLVInPlatoonList = isIntheVehiclePlatoonList(VID, vehicleList);
		return isLVInPlatoonList;
	}
	boolean isIntheVehiclePlatoonList(String VID, List<String> vehicleList) {
		boolean isContained = false;
		for(String vehicleId: vehicleList) {
		    if(vehicleId.trim().toLowerCase().contains(VID))
		    	isContained = true;
		}
		return isContained;
		
	}
	
	//demand
	public boolean isLeadingVehicle(String HVID, String LVID) {
		return (HVID.equalsIgnoreCase(LVID))? true: false;
	}

	//demand
	public boolean isMeetfrequencyUpdate(double frequency) {
		if(frequency == demandFrequency)
			return true;
		else 
			return false;
	}
	//guarantee
	public boolean isPartOfPlatoon() {
		if(isHVInPlatoonList && isLVInPlatoonList)
			return true;
		else 
			return false;
	}
}
