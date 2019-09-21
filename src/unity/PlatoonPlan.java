package unity;

import java.util.ArrayList;
import java.util.List;

import director.timedirector.TimeTriggeredWorker;
import prototype.Prototype;
import simulator.core.framework.component.SemanticDomainMember;


public class PlatoonPlan extends TimeTriggeredWorker {

    ActiveMQMessageProducer amqp;
    Prototype prototype;

    public PlatoonPlan(SemanticDomainMember parentComponent){
        
        // Call base constructor
		super(parentComponent);

        // set simcomp name
        this.getSimulationComponent().setName("HalloIchBin1");

        // (activeMQ_url, queueName);
        // activeMQ_url:
        String 	ipAddress = "localhost";
	    String urlBroker = "tcp://" + ipAddress + ":61616";
        //  queueName: TBD
        amqp = new ActiveMQMessageProducer(urlBroker,"feralunity");
        amqp.connectProducer();
        prototype = new Prototype();
        prototype.generateFormationForCatchUp();
        offsetleader += prototype.leaderDrivingInfo.y;
        offsetfollower += prototype.followerDrivingInfo.y;
        System.out.println((prototype.followerDrivingInfo.meetingLocation -300));
    }

    @Override
    public void timeStep(){
        sendDataToAMQ();
    }
    public float offsetleader;
    public float offsetfollower;
    public float offset;
	public void sendDataToAMQ(){
    	
//    	float speed = 3f;
//    	offset += speed;
		
		List<String> msg = new ArrayList<>();
		if(offsetfollower < (prototype.followerDrivingInfo.meetingLocation - 500)) {
			offsetleader += prototype.leaderDrivingInfo.speed;
			offsetfollower += prototype.followerDrivingInfo.speed;
			msg.add("<carName TestCar carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (+offsetleader) +" segmentPosY> <carSpeed " + (prototype.leaderDrivingInfo.speed)+" carSpeed>");
			msg.add("<carName Camcar carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (offsetfollower) +" segmentPosY> <carSpeed " +  (prototype.followerDrivingInfo.speed)+" carSpeed>");	

		}
		else {
			offsetleader += prototype.leaderDrivingInfo.speed;
			offsetfollower += prototype.leaderDrivingInfo.speed ;
			msg.add("<carName TestCar carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (+offsetleader) +" segmentPosY> <carSpeed " + (prototype.leaderDrivingInfo.speed)+" carSpeed>");	
			msg.add("<carName Camcar carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (offsetfollower) +" segmentPosY> <carSpeed " +  (prototype.leaderDrivingInfo.speed)+" carSpeed>");

		}
        for(int i = 0; i < msg.size();i++)
        {
        	amqp.sendMessage(msg.get(i));
        }
//    	}
    }

   
}