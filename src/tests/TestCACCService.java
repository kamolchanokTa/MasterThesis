package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import services.CACCService;
import services.CACCServiceType;

public class TestCACCService {

	static CACCServiceType caccService;
	static Date meetingtime;
	static Date startTime ;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	caccService = new CACCService();
    	meetingtime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*34));
    	startTime= new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*16));
    }

    @Before
    public void resetReputationScore() throws IOException {
//    	caccService.getReputationScore().setNegativeInteractionScore(0);
//    	caccService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidInformationStableState() {
    	caccService.receivePlatoonplan("1", 25, 20, Arrays.asList(1+"", 2+""), "4,1500",meetingtime, "4,0", startTime,"4,3000" );
    	caccService.receiveAllInformation("1", 1.0d, 20.0d, 1.0d, 0.1d, 0.1d, "2", 20.0d, 1.0d,  0.005d, 0, 0, 4,40, 1, 20, 0.1,5);
    	caccService.combineReputationScores();
    	assertEquals("The vehicl of Platoon is in stable state", 12, caccService.getReputationScore().getPositiveInteractionScore());
    }
    
	@Test
    public void TestValidPlatoonPlan() {
    	assertEquals("The time gap meets the control requirement", 12, caccService.getReputationScore().getPositiveInteractionScore());
    	
    }
	
}
