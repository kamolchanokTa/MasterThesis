package prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import services.CACCService;
import services.CACCServiceType;

public class Prototype {

	public static int generateIdentity(){
        Random rand = new Random();

        int identity = rand.nextInt(100);

        identity += 1;

        return identity;

    }
	
	public static double generateSpeed(){
		double max = 30d;
		double min = 20d;

        //double acceleration = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }

	public static double generateCatchUpSpeed(){
        double max = 30f;
        double min = 20f;

        //double acceleration = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }
	
	public static Date generateMeetingTime(){
        
		Date meetingtime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*1000));
		System.out.println("Meeting: "+ meetingtime);
        return meetingtime;
    }
	
	public static Date generateStartTime(){
        Date startTime = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.toMillis(3600*500));
		System.out.println("strating: "+ startTime);
        return startTime;
    }

    public static double generateReportedRange(){
        double max = 66.0d;
        double min = 16.0d;
        //double distance = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }

    public static double generateAcceleration(){
        double max = 2.75f;
        double min = -5f;

        //double acceleration = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }
    
    public static int generateLocationX(){
        int max = 10;
        int min = 1;

        Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
    
    public static int generateLocationY(){
        int max = 10;
        int min = 1;

        Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
    
    public static double generateIDFrequency(boolean isExactFrequency ){
        if(isExactFrequency) {
        	return 1d;
        }
        else {
        	return 0.8d;
        }
    }
    
    public static double generateDSRCFrequency(boolean isExactFrequency ){
        if(isExactFrequency) {
        	return 0.1d;
        }
        else {
        	return 0.2d;
        }
    }
    
    public static double generateRadarFrequency(boolean isExactFrequency ){
        if(isExactFrequency) {
        	return 0.01d;
        }
        else {
        	return 0.02d;
        }
    }
    public static int generateErrorCounter(){
    	int max = 127;
        int min = 0;

        //double acceleration = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
    }
    
    public static double generateDSRCTimeOut(){
    	double max = 2.0d;
    	double min = 0.0d;

        //double acceleration = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }
    
    public static double generateCANMSGFrequency(boolean isExactFrequency){
    	if(isExactFrequency) {
        	return  0.005d;
        }
        else {
        	return 0.002d;
        }
    }
    
    public static List<String> generateVehicleList(boolean isCorrect, int LVId, int HVId){
    	if(isCorrect) {
        	return new ArrayList<String>(Arrays.asList(LVId+"", HVId+"")); 
        }
        else {
        	return new ArrayList<String>(Arrays.asList(LVId+"", HVId+"")); 
        }
    }
    
    public static double generateVehicleLength() {
    	double max = 8.0d;
        double min = 5.0d;
        //double distance = (double) ((Math.random()*((max-min)+1))+min);
        Random r = new Random();
        return r.nextDouble()*(max-min)+ min;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CACCServiceType leaderCACC = new CACCService();
		CACCServiceType followerCACC = new CACCService();
		int LVID = generateIdentity();
		int HVID = generateIdentity();
		int platoonplanId = generateIdentity();
		leaderCACC.receivePlatoonplan(platoonplanId+"", generateCatchUpSpeed(), generateSpeed(),
				generateVehicleList(true, LVID, HVID), generateLocationX()+","+generateLocationY() 
				, generateMeetingTime(), generateLocationX()+","+generateLocationY(),
				generateStartTime(), generateLocationX()+","+generateLocationY());
		
		followerCACC.receivePlatoonplan(platoonplanId+"", generateCatchUpSpeed(), generateSpeed(),
				generateVehicleList(true, LVID, HVID), generateLocationX()+","+generateLocationY() 
				, generateMeetingTime(), generateLocationX()+","+generateLocationY(),
				generateStartTime(), generateLocationX()+","+generateLocationY());
		double LVSpeed= generateSpeed();
		
		leaderCACC.receiveAllInformation(LVID+"", generateIDFrequency(true), 
				LVSpeed, generateAcceleration(), generateDSRCFrequency(true), generateDSRCTimeOut()
				, LVID+"", LVSpeed, generateAcceleration(), generateCANMSGFrequency(true), generateErrorCounter(), generateErrorCounter(),
				generateLocationX() , generateLocationY(), generateIDFrequency(true),
				generateReportedRange(), generateRadarFrequency(true), generateVehicleLength());
		
		leaderCACC.combineReputationScores();
		followerCACC.receiveAllInformation(LVID+"", generateIDFrequency(true), 
				LVSpeed, generateAcceleration(), generateDSRCFrequency(true), generateDSRCTimeOut()
				, HVID+"", LVSpeed, generateAcceleration(), generateCANMSGFrequency(true), generateErrorCounter(), generateErrorCounter(),
				generateLocationX() , generateLocationY(), generateIDFrequency(true),
				generateReportedRange(), generateRadarFrequency(true), generateVehicleLength());
	   
		followerCACC.combineReputationScores();
		
		System.out.println("The trust score of leading vehicel: " + leaderCACC.getReputationScore().getTrustScore());
		
		System.out.println("The trust score of following vehicel: " + followerCACC.getReputationScore().getTrustScore());
	}

}
