package prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import fmu.CSVFileReader;
import fmu.VehicleInfo;
import services.CACCService;
import services.CACCServiceType;

public class Prototype {
	public DrivingInfo leaderDrivingInfo;
	public DrivingInfo followerDrivingInfo;
	public CACCServiceType leaderCACC;
	public CACCServiceType followerCACC;

	public List<VehicleInfo> leaderVehicle;
	public List<VehicleInfo> follower1;
	public List<VehicleInfo> follower2;

	public Prototype() {
		leaderCACC = new CACCService();
		followerCACC = new CACCService();
	}

	public void generateFormationForCatchUp() {
		readAllCSV();
		int platoonplanId = generateIdentity();
		String LVID = leaderVehicle.get(0).getId();
		String HVID = follower1.get(0).getId();
		List<String> vehicleList = generateVehicleList(LVID, HVID);
		int x = generateLocationX();
		double catchupspeed = Math.round(generateCatchUpSpeed());
		double lvSpeed = Math.round(generateSpeed());
		double meetingLocation = Math.round(generateMeethingLocationY(catchupspeed));
		double startLocationLeader = Math.round(generateStartLocationY(meetingLocation, lvSpeed));
		double leadermeetingLocationY = Math.round(generateMeethingLocationYForLeader(lvSpeed));

		leaderDrivingInfo = new DrivingInfo(x, (startLocationLeader + lvSpeed), lvSpeed, leadermeetingLocationY);
		followerDrivingInfo = new DrivingInfo(x, catchupspeed, catchupspeed, meetingLocation);

		leaderCACC.receivePlatoonplan(platoonplanId + "", catchupspeed, lvSpeed, vehicleList,
				x + "," + leadermeetingLocationY, generateMeetingTime(), x + "," + startLocationLeader,
				generateStartTime(), generateLocationX() + "," + generateLocationY());

		followerCACC.receivePlatoonplan(platoonplanId + "", catchupspeed, lvSpeed, vehicleList,
				x + "," + meetingLocation, generateMeetingTime(), generateLocationX() + "," + generateLocationY(),
				generateStartTime(), generateLocationX() + "," + 0);
	}

	public void readAllCSV() {
		String fileLV = "//Users//kamolchanoktangsri//eclipse-workspace//CACC//csvfiles//CACC_LV.csv";
		String fileFV1 = "//Users//kamolchanoktangsri//eclipse-workspace//CACC//csvfiles//CACC_F1.csv";
		// String fileFV2 = "//Users//kamolchanoktangsri//eclipse-workspace//CACC//csvfiles//CACC_F2.csv";
		CSVFileReader readCSVFile = new CSVFileReader();
		leaderVehicle = readCSVFile.readCSVFile(fileLV, true);
		follower1 = readCSVFile.readCSVFile(fileFV1, false);
		// follower2 = readCSVFile.readCSVFile(fileFV2, false);
	}

	public void generateStringStability() {
		readAllCSV();
		generateFormationForCatchUp();
		int iteration = 0;
		for(int i=0;i < leaderVehicle.size(); i++ ) {
			iteration++;
			
			leaderCACC.receiveAllInformation(leaderVehicle.get(i).getId(),generateIDFrequency(true),
					leaderVehicle.get(i).getSpeed(), leaderVehicle.get(i).getAcceleration(), generateDSRCFrequency(true), generateDSRCTimeOut(),
					leaderVehicle.get(i).getId(), leaderVehicle.get(i).getSpeed(),leaderVehicle.get(i).getAcceleration(), generateCANMSGFrequency(true), generateErrorCounter(), generateErrorCounter(),
					generateLocationX(),leaderVehicle.get(i).getPositionY(), generateIDFrequency(true),
					leaderVehicle.get(i).getInterDistance(),generateRadarFrequency(true),generateVehicleLength());
			leaderCACC.combineReputationScores();
			followerCACC.receiveAllInformation(leaderVehicle.get(i).getId(),generateIDFrequency(true),
					leaderVehicle.get(i).getSpeed(), leaderVehicle.get(i).getAcceleration(), generateDSRCFrequency(true), generateDSRCTimeOut(),
					follower1.get(i).getId(), follower1.get(i).getSpeed(),follower1.get(i).getAcceleration(), generateCANMSGFrequency(true), generateErrorCounter(), generateErrorCounter(),
					generateLocationX(),follower1.get(i).getPositionY(), generateIDFrequency(true),
					follower1.get(i).getInterDistance(),generateRadarFrequency(true),generateVehicleLength());
			followerCACC.combineReputationScores();
			
			boolean isLeaderSafe = leaderCACC.getReputationScore().validateTrustScore(leaderCACC.getReputationScore().getTrustScore(),iteration);
			System.out.println("Iteration "+ iteration );
			if(isLeaderSafe){
	            System.out.println("CACC service of leader vehicle meets the safety criteria");
	        }
	        
	        System.out.println("The trust score of leading vehicel: " + leaderCACC.getReputationScore().getTrustScore() );
	        boolean isFollowerSafe = followerCACC.getReputationScore().validateTrustScore(followerCACC.getReputationScore().getTrustScore(),iteration);
	        if(isFollowerSafe){
	            System.out.println("CACC service of follower vehicle meets the safety criteria");
	        }
			System.out.println("The trust score of following vehicel: " + followerCACC.getReputationScore().getTrustScore()) ;
		}
		
	}

	public static int generateIdentity() {
		Random rand = new Random();

		int identity = rand.nextInt(100);

		identity += 1;

		return identity;

	}

	public static double generateSpeed() {
		double max = 21d;
		double min = 20d;

		// double acceleration = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return Math.round(r.nextDouble() * (max - min) + min);
	}

	public static double generateCatchUpSpeed() {
		double max = 30f;
		double min = 21f;

		// double acceleration = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return r.nextDouble() * (max - min) + min;
	}

	public static Date generateMeetingTime() {

		Date meetingtime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600 * 100));
		System.out.println("Meeting: " + meetingtime);
		return meetingtime;
	}

	public static Date generateStartTime() {
		Date startTime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600 * 50));
		System.out.println("strating: " + startTime);
		return startTime;
	}

	public static double generateReportedRange() {
		double max = 66.0d;
		double min = 16.0d;
		// double distance = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return r.nextDouble() * (max - min) + min;
	}

	public static double generateAcceleration() {
		double max = 2.75f;
		double min = -5f;

		// double acceleration = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return r.nextDouble() * (max - min) + min;
	}

	public static int generateLocationX() {
		int max = 10;
		int min = 1;

		Random r = new Random();
		return -4;
	}

	public static int generateLocationY() {
		int max = 10;
		int min = 1;

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static double generateMeethingLocationY(double catchupSpeed) {
		double y = catchupSpeed * (60 * 3);
		return y;
	}

	public static double generateMeethingLocationYForLeader(double lvSpeed) {
		double y = lvSpeed * (60 * 3.8);
		return y;
	}

	public static double generateStartLocationY(double meethinglocationY, double lvSpeed) {
		double distance = lvSpeed * (60 * 3);
		double startLocationLeader = meethinglocationY - distance;
		return startLocationLeader;

	}

	public static double generateIDFrequency(boolean isExactFrequency) {
		if (isExactFrequency) {
			return 1d;
		} else {
			return 0.8d;
		}
	}

	public static double generateDSRCFrequency(boolean isExactFrequency) {
		if (isExactFrequency) {
			return 0.1d;
		} else {
			return 0.2d;
		}
	}

	public static double generateRadarFrequency(boolean isExactFrequency) {
		if (isExactFrequency) {
			return 0.01d;
		} else {
			return 0.02d;
		}
	}

	public static int generateErrorCounter() {
		int max = 127;
		int min = 0;

		// double acceleration = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static double generateDSRCTimeOut() {
		double max = 2.0d;
		double min = 0.0d;

		// double acceleration = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return r.nextDouble() * (max - min) + min;
	}

	public static double generateCANMSGFrequency(boolean isExactFrequency) {
		if (isExactFrequency) {
			return 0.005d;
		} else {
			return 0.002d;
		}
	}

	public static List<String> generateVehicleList(String LVId, String HVId) {
		return new ArrayList<String>(Arrays.asList(LVId, HVId));
	}

	public static double generateVehicleLength() {
		double max = 8.0d;
		double min = 5.0d;
		// double distance = (double) ((Math.random()*((max-min)+1))+min);
		Random r = new Random();
		return 5;// r.nextDouble()*(max-min)+ min;
	}

}
