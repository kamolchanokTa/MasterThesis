package services;

import java.util.Date;
import java.util.List;

import reputation.ReputationComputation;
import reputation.ReputationScore;

public class CACCService extends CACCServiceType{
	
	private CACCServiceDirector  caccServices;
	private ExternalCommunicationServiceType dsrcCommService;
	private ExternalCommunicationServiceType v2iCommService ;
	private ExternalPerceptionServiceType radarService;
	private ExternalPerceptionServiceType gpsService;
	private InVehicleServiceType invehicleService;
	private ControlServiceType controlService;
	
	private ReputationScore platoonPlanReputationScore;
	private ReputationScore dsrcReputationScore;
	private ReputationScore CANMsgReputationScore;
	private ReputationScore gpsReputationScore;
	private ReputationScore radarReputationScore;
	private ReputationScore controlReputationScore;
	
	public CACCService() {
		//this.reputationScore = ReputationScore.getInstance();
		this.reputationScore = new ReputationScore();
		this.reputationScore.setPositiveInteractionScore(0);
		this.reputationScore.setNegativeInteractionScore(0);
		
		new ReputationComputation(reputationScore);
		this.caccServices = new CACCServiceDirector();
		
		this.dsrcCommService = new DSRCCommunicationService();
		this.v2iCommService = new V2ICommunicationService();
		this.radarService = new RadarSensingService();
		this.gpsService = new GPSService();
		this.invehicleService = new CANBusCommunicationService();
		this.controlService = new PLFControlService();
		
		this.caccServices.setDSRCCommunicationService(dsrcCommService);
		this.caccServices.setV2ICommunicationService(v2iCommService);
		this.caccServices.setRadarSensingService(radarService);
		this.caccServices.setGPSService(gpsService);
		this.caccServices.setControlService(controlService);
		this.caccServices.setCANBusService(invehicleService);
		
	}
	
	public void receivePlatoonplan(String platoonId, double rendezvousSpeed, 
			double lvSpeed, List<String> vehicleIdList,
			String meetingLocation, Date meetingTime,
			String hvStartLocation, Date hvStartTime,
			String destination) {
		this.caccServices.constructV2ICommunicationService(platoonId, rendezvousSpeed, lvSpeed, vehicleIdList, meetingLocation, meetingTime, hvStartLocation, hvStartTime, destination);
		printPlatoonPlan(platoonId, rendezvousSpeed, lvSpeed, vehicleIdList, meetingLocation, meetingTime, hvStartLocation, hvStartTime, destination);

	}
	
	private void printPlatoonPlan(String platoonId, double rendezvousSpeed, double lvSpeed, List<String> vehicleIdList,
			String meetingLocation, Date meetingTime, String hvStartLocation, Date hvStartTime, String destination) {
		System.out.println("Platoon plan Id: " + platoonId);
		System.out.println("	Catch up speed: " + rendezvousSpeed);
		System.out.println("	leader speed: " + lvSpeed);
		System.out.println("	meeting Location: " + meetingLocation);
		System.out.println("	meeting Time: " + meetingTime.toString());
		System.out.println("	hvStart Time: " + hvStartTime.toString());
		System.out.println("	hvStartLocation: " + hvStartLocation);
		System.out.println("	destination: " + destination);
		System.out.println("	vehicle Id List: " + vehicleIdList.toString());
		System.out.println("positive reputation score: " + this.caccServices.getV2IBuilder().reputationScore.getPositiveInteractionScore());
		System.out.println("negative reputation score: " + this.caccServices.getV2IBuilder().reputationScore.getNegativeInteractionScore());
	}

	public void receiveAllInformation(String LVID, double IDfrequency,
			double LVSpeed, double LVAcceleration, double LVfrequency,
			double detectTime, String HVID,
			double hvSpeed, double hvAcceleration, double CANMsgFrequency,
			int TEC, int REC,
			double latitudeHV, double longtitudeHV, double gpsFrequency,
			double reportedRadarRange, double radarFrequency,double vehicleLength) {
		
		this.caccServices.constructDSRCCommunicationService(LVID,IDfrequency,LVSpeed, LVAcceleration, LVfrequency,detectTime,HVID);
		this.caccServices.getDSRCBuilder().verifyIdentity();
		this.caccServices.getDSRCBuilder().verifyV2VInformation();
		
		this.caccServices.constructCANBusService(hvSpeed,hvAcceleration,CANMsgFrequency,TEC,REC);

		this.caccServices.constructGPSService(latitudeHV,longtitudeHV, gpsFrequency);

		
		this.caccServices.constructRadarSensingService(vehicleLength, reportedRadarRange,radarFrequency);

		
		this.caccServices.constructControlService(reportedRadarRange, hvSpeed, LVSpeed,hvAcceleration, LVAcceleration,this.dsrcCommService.verifyRole());

		printCACCReceivedInformation(LVID,IDfrequency,LVSpeed, LVAcceleration, LVfrequency,detectTime,HVID,
				hvSpeed,hvAcceleration,CANMsgFrequency,TEC,REC,
				latitudeHV,longtitudeHV, gpsFrequency,
				vehicleLength,reportedRadarRange,radarFrequency);
	}
	
	private void printCACCReceivedInformation(String lVID, double iDfrequency, double lVSpeed, double lVAcceleration,
			double lVfrequency, double detectTime, String hVID, 
			double hvSpeed, double hvAcceleration,double cANMsgFrequency, int TEC, int REC,
			double latitudeHV, double longtitudeHV, double gpsFrequency,
			double vehicleLength, double reportedRadarRange, double radarFrequency) {
		// TODO Auto-generated method stub
		System.out.println("CACC Received Information: ");
		System.out.println("	DSRC host Id: " + hVID);
		System.out.println("		host role: " + this.dsrcCommService.verifyRole());
		System.out.println("		leader id: " + lVID);
		System.out.println("		leader speed: " + lVSpeed);
		System.out.println("		leader accleration: " + lVAcceleration);
		System.out.println("		id frequency: " + iDfrequency);
		System.out.println("		lv frequency: " + lVfrequency);
		System.out.println("		detect Time: " + detectTime);
		System.out.println("	CAN Msg hv speed: " + hvSpeed);
		System.out.println("		hv accleration: " + hvAcceleration);
		System.out.println("		cANMsgFrequency: " + cANMsgFrequency);
		System.out.println("		TEC: " + TEC);
		System.out.println("		REC: " + REC);
		System.out.println("	GPS : hv position x: " + latitudeHV);
		System.out.println("		hv position y: " + longtitudeHV);
		System.out.println("		gps Frequency: " + gpsFrequency);
		System.out.println("	Control: vehicle Length:  " + vehicleLength);
		System.out.println("		hv accleration: " + hvAcceleration);
		System.out.println("		time gap: " + this.caccServices.getControlBuilder().getComputedTimeGap());
		System.out.println("	Radar reported Radar Range: " + reportedRadarRange);
		System.out.println("		radarFrequency: " + radarFrequency);

		
	}

	public void combineReputationScores() {
		this.platoonPlanReputationScore = this.caccServices.getV2IBuilder().getReputationScore();
		this.dsrcReputationScore = this.caccServices.getDSRCBuilder().getReputationScore();
		this.CANMsgReputationScore = this.caccServices.getInVehicleBuilder().getReputationScore();
		this.gpsReputationScore = this.caccServices.getGPSBuilder().getReputationScore();
		this.radarReputationScore = this.caccServices.getRadarBuilder().getReputationScore();
		this.controlReputationScore = this.caccServices.getControlBuilder().getReputationScore();
		
		int totalPositiveScore = (platoonPlanReputationScore.getPositiveInteractionScore() 
								+ dsrcReputationScore.getPositiveInteractionScore() + CANMsgReputationScore.getPositiveInteractionScore() 
								+ gpsReputationScore.getPositiveInteractionScore() + radarReputationScore.getPositiveInteractionScore() 
								+ controlReputationScore.getPositiveInteractionScore());
		this.reputationScore.setPositiveInteractionScore(totalPositiveScore);
		
		System.out.println("Postitive Interaction score of " +this.dsrcCommService.verifyRole()  +" V2I: "+ platoonPlanReputationScore.getPositiveInteractionScore() 
		+"  DSRC: "+  dsrcReputationScore.getPositiveInteractionScore() +" CAN: "+ CANMsgReputationScore.getPositiveInteractionScore() 
		+" GPS: "+  gpsReputationScore.getPositiveInteractionScore() +" Radar: "+ radarReputationScore.getPositiveInteractionScore() 
		+" Control: "+  controlReputationScore.getPositiveInteractionScore());
		
		int totalNegativeeScore = (platoonPlanReputationScore.getNegativeInteractionScore() 
				+ dsrcReputationScore.getNegativeInteractionScore() + CANMsgReputationScore.getNegativeInteractionScore() 
				+ gpsReputationScore.getNegativeInteractionScore() + radarReputationScore.getNegativeInteractionScore() 
				+ controlReputationScore.getNegativeInteractionScore());
		this.reputationScore.setNegativeInteractionScore(totalNegativeeScore);
		
		System.out.println("Negative Interaction score of " +this.dsrcCommService.verifyRole()  +" V2I: "+ platoonPlanReputationScore.getNegativeInteractionScore() 
		+"  DSRC: "+  dsrcReputationScore.getNegativeInteractionScore() +" CAN: "+ CANMsgReputationScore.getNegativeInteractionScore() 
		+" GPS: "+  gpsReputationScore.getNegativeInteractionScore() +" Radar: "+ radarReputationScore.getNegativeInteractionScore() 
		+" Control: "+  controlReputationScore.getNegativeInteractionScore());
	}

}
