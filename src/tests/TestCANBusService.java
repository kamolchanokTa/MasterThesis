package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.CANBusCommunicationService;
import services.InVehicleServiceType;

public class TestCANBusService {

	static InVehicleServiceType canBusService;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	canBusService = new CANBusCommunicationService();
    }

    @Before
    public void resetReputationScore() throws IOException {
    	canBusService.getReputationScore().setNegativeInteractionScore(0);
    	canBusService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidInvehicleInfo() {
    	canBusService.receiveInVehicleInfo(20.0d, 1.0d, 0.005d);
    	assertEquals("the receiving vehicle information meets frequency", 1, canBusService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidCANBusErrorCouters() {
    	canBusService.receiveErrorCouter(128,127);
    	assertEquals("the vehicle is not able to form platoon", 1, canBusService.getReputationScore().getNegativeInteractionScore());
    }
    
    @Test
    public void TestValidCANBusErrorCouters() {
    	canBusService.receiveErrorCouter(127,127);
    	assertEquals("the vehicle is not able to form platoon", 1, canBusService.getReputationScore().getPositiveInteractionScore());
    }
}
