package contracts;

import java.util.Date;

public class PlatoonPlanContract {
	
	public boolean checkReceivingTime(Date receivingTime, Date meetingTime, Date hvStartTime) {
		return (receivingTime.compareTo(hvStartTime) < 0 && receivingTime.compareTo(meetingTime) < 0 && hvStartTime.compareTo(meetingTime) <0 )? true: false; 
	}

	public boolean verifyRendezvousSpeed(double virtualPositionDifference, double rendezvousSpeed, double lvSpeed) {
		// TODO Auto-generated method stub
		if(virtualPositionDifference >0 && rendezvousSpeed > lvSpeed)
			return true;
		else if(virtualPositionDifference >0 && rendezvousSpeed < lvSpeed)
			return false;
		else if(virtualPositionDifference <0 && rendezvousSpeed < lvSpeed)
			return true;
		else
			return false;
			
	}
	
	//public boolean verifyRendezvousSpeed()

}
