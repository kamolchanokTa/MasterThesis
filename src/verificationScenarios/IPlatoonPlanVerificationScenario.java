package verificationScenarios;

import java.util.Date;

public interface IPlatoonPlanVerificationScenario {
	
	public boolean verifyReceivingTime(Date receivingTime, Date meetingTime, Date hvStartTime);
	public boolean verifyPosition(double lvSpeed, String meetingLocation, String hvStartLocation, Date meetingTimeDate ,Date hvStartTime, double rendezvousSpeed);

}
