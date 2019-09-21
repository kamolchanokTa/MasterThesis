package fmu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
	
	public List<VehicleInfo>  readCSVFile(String csvFile, boolean isLeader) {
	    String line = "";
	    String cvsSplitBy = ",";
	
	    List<VehicleInfo> vehicleInfos = new ArrayList<VehicleInfo>();
	    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	    	br.readLine();
	        while ((line = br.readLine()) != null) {
	        	VehicleInfo vehicleinfo = new VehicleInfo();
	            // use comma as separator
	            String[] infos = line.split(cvsSplitBy);
	            vehicleinfo.setId(infos[0]);
            	vehicleinfo.setSpeed(Double.parseDouble(infos[1]));
            	vehicleinfo.setAcceleration(Double.parseDouble(infos[2]));
            	vehicleinfo.setPositionY(Double.parseDouble(infos[3]));
	            if(!isLeader) { 
	            	vehicleinfo.setInterDistance(Double.parseDouble(infos[4]));
	            }
	            vehicleInfos.add(vehicleinfo);
	
	        }
	
	    } catch (IOException e) {
	        e.printStackTrace();
	        
	    }
	    return vehicleInfos;
	}
}
