package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import services.ExternalCommunicationServiceType;
import services.V2ICommunicationService;

public class TestV2IService {
	
	static ExternalCommunicationServiceType v2iService;
	static Date meetingtime;
	static Date startTime ;
	
    @BeforeClass
    public static void intialization() throws IOException {
    	v2iService = new V2ICommunicationService();
    	meetingtime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*1000));
    	startTime= new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*500));
    }

    @Before
    public void resetReputationScore() throws IOException {
    	v2iService.getReputationScore().setNegativeInteractionScore(0);
    	v2iService.getReputationScore().setPositiveInteractionScore(0);
    }
    
    @Test
    public void TestValidIdentity() {
    	v2iService.receivePlatoonPlan("1", 26.0d, 20.0d,Arrays.asList(1+"", 2+""), "4,40", meetingtime, "4,160",startTime, "4,100");
    	assertEquals("the vehicle is able to form platoon", 2, v2iService.getReputationScore().getPositiveInteractionScore());
    }
    
    @Test
    public void TestInValidReceivePlatoonFormationPlan() {
    	v2iService.receivePlatoonPlan("1", 26.0d, 20.0d,Arrays.asList(1+"", 2+""), "4,40",startTime , "4,160",meetingtime, "4,100");
    	assertEquals("the vehicle is not able to form platoon", 2, v2iService.getReputationScore().getNegativeInteractionScore());
    }

}
