package verificationScenarios;

import java.util.Date;

import contracts.PlatoonPlanContract;

public class PlatoonPlanVerificationScenario implements IPlatoonPlanVerificationScenario {
	
	private static volatile PlatoonPlanVerificationScenario instance;
	
	private PlatoonPlanContract platoonPlanContract;
	private double hvStartToMeetingDistance;
	private double virtualPositionDifference;

	
	private PlatoonPlanVerificationScenario() {
		this.platoonPlanContract = new PlatoonPlanContract();
		
	}
	
	public static PlatoonPlanVerificationScenario getInstance() {
	    if (instance == null) {
	        synchronized (PlatoonPlanVerificationScenario.class) {
	            if (instance == null) {
	                instance = new PlatoonPlanVerificationScenario();
	            }
	        }
	    }
	    return instance;
	 }

	public boolean verifyReceivingTime(Date receivingTime, Date meetingTime, Date hvStartTime) {
		// TODO Auto-generated method stub
		return this.platoonPlanContract.checkReceivingTime(receivingTime,meetingTime,hvStartTime);
		
	}

	public boolean verifyPosition(double lvSpeed, String meetingLocation, 
			String hvStartLocation, Date meetingTime,
			Date hvStartTime, double rendezvousSpeed) {
		// TODO Auto-generated method stub
		calculateDistance( meetingLocation,  hvStartLocation);
		calculateVirtualPositionDifference(meetingTime,hvStartTime,rendezvousSpeed,lvSpeed );
		return this.platoonPlanContract.verifyRendezvousSpeed(virtualPositionDifference,rendezvousSpeed,lvSpeed);
		
	}

	private void calculateDistance(String meetingLocation, String hvStartLocation) {
		// TODO calculate distance base on location will define later
		try {
			String[] meetingLocations = meetingLocation.split(",");
			String[] hvStartLocations = hvStartLocation.split(",");
			double x1 = Integer.parseInt(hvStartLocations[0]);
			double y1 = Integer.parseInt(hvStartLocations[1]);
			double x2 = Integer.parseInt(meetingLocations[0]);
			double y2 = Integer.parseInt(meetingLocations[1]);
			this.hvStartToMeetingDistance = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		}
		catch(Exception ex) {
			
		}
	}
	
	private void calculateVirtualPositionDifference(Date meetingTime,Date hvStartTime, double rendezvousSpeed, double lvSpeed) {
		double distanceToCatchUp = (meetingTime.getTime() - hvStartTime.getTime()) * rendezvousSpeed;
		this.virtualPositionDifference = distanceToCatchUp - ((meetingTime.getTime() - hvStartTime.getTime())* lvSpeed);
	}
	
	

}
