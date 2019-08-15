package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)


@Suite.SuiteClasses({
	TestV2IService.class,
	TestCACCService.class,
	TestDSRCService.class,
	TestRadarSensingService.class,
	TestGPSService.class,
	TestControlService.class,
	TestCANBusService.class
})

public class AllTests{
    public class test{
        public void main(String[] args){
            Result result = JUnitCore.runClasses(AllTests.class);
            for(Failure failure : result.getFailures()){
                System.out.println(failure.toString());
            }
            System.out.println(result.wasSuccessful());
        }
    }
}

