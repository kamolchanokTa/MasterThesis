package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.ControlServiceType;
import services.PLFControlService;
import services.DSRCCommunicationService.VehicleRole;


public class TestControlService {
	static ControlServiceType controlService;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	controlService = new PLFControlService();
    }

    @Before
    public void resetReputationScore() throws IOException {
    	controlService.getReputationScore().setNegativeInteractionScore(0);
    	controlService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidTimeGap() {
    	controlService.contolTimeGap(22, 20, VehicleRole.follower);
    	assertTrue("Time gap (" + controlService.getComputedTimeGap() + ") should be greater than min time gap (0.8) ", controlService.getComputedTimeGap() > 0.8d);
    	assertTrue("Time gap (" + controlService.getComputedTimeGap() + ") should be less than max time gap (2.2) ", controlService.getComputedTimeGap() < 2.2d);
    	assertEquals("The time gap meets the control requirement", 1, controlService.getReputationScore().getPositiveInteractionScore());
    }
    
	@Test
    public void TestInValidTimeGapCalculation() {
    	controlService.contolTimeGap(5, 20, VehicleRole.follower);
    	assertEquals("The time gap meets the control requirement", 1, controlService.getReputationScore().getNegativeInteractionScore());
    	
    }
	
	@Test
    public void TestValidAccelerationAndSpeed() {
    	controlService.controlSpeedAndAcceleration(20.0d, 20.0d, 0.9d, 1.0d);
    	assertEquals("The time gap meets the control requirement", 3, controlService.getReputationScore().getPositiveInteractionScore());
    }
	
	@Test
    public void TestInValidAccelerationAndSpeed() {
    	controlService.controlSpeedAndAcceleration(20.0d, 20.0d, 0.9d, 3.0d);
    	assertEquals("The time gap meets the control requirement", 2, controlService.getReputationScore().getNegativeInteractionScore());
    }

}
