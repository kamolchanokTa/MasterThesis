package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.DSRCCommunicationService;
import services.DSRCCommunicationService.VehicleRole;
import services.ExternalCommunicationServiceType;

public class TestDSRCService {
	static ExternalCommunicationServiceType dsrcCommunicationService;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	dsrcCommunicationService = new DSRCCommunicationService();
    	dsrcCommunicationService.setVehicleList(Arrays.asList(1+"", 2+""));
    	dsrcCommunicationService.receiveLVID(String.valueOf(1),1.0d);
    }

    @Before
    public void resetReputationScore() throws IOException {
    	dsrcCommunicationService.getReputationScore().setNegativeInteractionScore(0);
    	dsrcCommunicationService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidIdentity() {
    	dsrcCommunicationService.detectPacketLoss(1.0d);
    	dsrcCommunicationService.setHVID(String.valueOf(2));
    	dsrcCommunicationService.verifyIdentity();
    	assertEquals("the vehicle is able to form platoon", 2, dsrcCommunicationService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidIdentity() {
    	dsrcCommunicationService.detectPacketLoss(1.0d);
    	dsrcCommunicationService.setHVID(String.valueOf(3));
    	dsrcCommunicationService.verifyIdentity();
    	assertEquals("the vehicle is able to form platoon", 1, dsrcCommunicationService.getReputationScore().getNegativeInteractionScore());
    }
    
    @Test
    public void TestValidV2VInformation() {
    	
    	dsrcCommunicationService.receiveLVInformation(20.0d, 1.0d, 0.1d);
    	dsrcCommunicationService.verifyV2VInformation();
    	assertEquals("the vehicle is not able to form platoon", 1, dsrcCommunicationService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidV2VInformation() {
    	
    	dsrcCommunicationService.receiveLVInformation(20.0d, 1.0d, 0.2d);
    	dsrcCommunicationService.verifyV2VInformation();
    	assertEquals("the vehicle is not able to form platoon", 1, dsrcCommunicationService.getReputationScore().getNegativeInteractionScore());
    }
    
    @Test
    public void TestLeaderRole() {
    	dsrcCommunicationService.setHVID(String.valueOf(1));
    	assertEquals("the vehicle is a leader vehicle", VehicleRole.leader, dsrcCommunicationService.verifyRole());
    }
    
    @Test
    public void TestFollowerRole() {
    	dsrcCommunicationService.setHVID(String.valueOf(2));
    	assertEquals("the vehicle is a following vehicle ", VehicleRole.follower, dsrcCommunicationService.verifyRole());
    }
}
