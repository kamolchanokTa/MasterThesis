package services;

import java.util.Date;
import java.util.List;

import reputation.ReputationScore;

public abstract class CACCServiceType {

	ReputationScore reputationScore;
	public ReputationScore getReputationScore() {
		return this.reputationScore;
	}
	
	public abstract void receiveAllInformation(String LVID, double IDfrequency,
			double LVSpeed, double LVAcceleration, double LVfrequency,
			double detectTime, String HVID,
			double hvSpeed, double hvAcceleration, double CANMsgFrequency,
			int TEC, int REC,
			double latitudeHV, double longtitudeHV, double gpsFrequency,
			double reportedRadarRange, double radarFrequency,double vehicleLength);
	
	public abstract void receivePlatoonplan(String platoonId, double rendezvousSpeed, 
			double lvSpeed, List<String> vehicleIdList,
			String meetingLocation, Date meetingTime,
			String hvStartLocation, Date hvStartTime,
			String destination);
	
	public abstract void combineReputationScores();
}
