package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.ExternalPerceptionServiceType;
import services.GPSService;

public class TestGPSService {
	static ExternalPerceptionServiceType gpsService;
	static double vehicleLength = 5;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	gpsService = new GPSService();
    }
    
    @Before
    public void resetReputationScore() throws IOException {
    	gpsService.getReputationScore().setNegativeInteractionScore(0);
    	gpsService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidGPSFrequencyUpdate() {
    	
    	gpsService.receiveVehicleLocation(4.0d,40.0d,1.0d);
    	assertEquals("the frequncy of GPS Information meets the requirement", 1, gpsService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidGPSFrequencyUpdate() {
    	gpsService.receiveVehicleLocation(4.0d,40.0d,2.0d);
    	assertEquals("the frequncy of GPS Information does not meet the requirement", 1, gpsService.getReputationScore().getNegativeInteractionScore());
    }
}
