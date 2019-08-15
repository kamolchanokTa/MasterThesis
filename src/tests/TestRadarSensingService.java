package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;


import services.ExternalPerceptionServiceType;
import services.RadarSensingService;


public class TestRadarSensingService {

	static ExternalPerceptionServiceType radarSensingService;
	static double vehicleLength = 5;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	radarSensingService = new RadarSensingService();
    }
    
    @Before
    public void resetReputationScore() throws IOException {
    	radarSensingService.getReputationScore().setNegativeInteractionScore(0);
    	radarSensingService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidCloseFollowMode() {
    	
    	radarSensingService.setVehicleLength(vehicleLength);
    	radarSensingService.receiveReportedRadarRange(4.0d, 0.01d);
    	assertEquals("the vehicle is in close follow mode", 1, radarSensingService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidCloseFollowMode() {
    	radarSensingService.setVehicleLength(vehicleLength);
    	radarSensingService.receiveReportedRadarRange(10.0d, 0.01d);
    	assertEquals("the vehicle is not in close follow mode", 1, radarSensingService.getReputationScore().getNegativeInteractionScore());
    }
    
    @Test
    public void TestInValidFrequency() {
    	radarSensingService.setVehicleLength(vehicleLength);
    	radarSensingService.receiveReportedRadarRange(4.0d, 0.02d);
    	assertEquals("the frequncy of update radar does not meet requirement", 1, radarSensingService.getReputationScore().getNegativeInteractionScore());
    }
}
