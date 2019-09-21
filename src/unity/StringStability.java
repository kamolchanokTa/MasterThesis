package unity;

import java.util.ArrayList;
import java.util.List;

import director.timedirector.TimeTriggeredWorker;
import prototype.Prototype;
import simulator.core.framework.component.SemanticDomainMember;


public class StringStability extends TimeTriggeredWorker {

    ActiveMQMessageProducer amqp;
    Prototype prototype;

    public StringStability(SemanticDomainMember parentComponent){
        
        // Call base constructor
		super(parentComponent);

        // set simcomp name
        this.getSimulationComponent().setName("HalloIchBin1");

        String 	ipAddress = "localhost";
	    String urlBroker = "tcp://" + ipAddress + ":61616";
        //  queueName: TBD
        amqp = new ActiveMQMessageProducer(urlBroker,"feralunity");
        amqp.connectProducer();
        prototype = new Prototype();
        prototype.generateStringStability();
    }

    @Override
    public void timeStep(){
        sendDataToAMQ();
    }
    public float offsetleader;
    public float offsetfollower;
    public float offset;
    private int index = 0;
    private float xValue= 0;
	public void sendDataToAMQ() {
		for(int j = 0; j< prototype.leaderVehicle.size(); j++) {
			
			List<String> msg = new ArrayList<>();
			msg.add("<carName "+  prototype.leaderVehicle.get(j).getId() +" carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (-25+prototype.leaderVehicle.get(j).getPositionY()+ xValue) +" segmentPosY> <carSpeed " + (prototype.leaderVehicle.get(j).getSpeed())+" carSpeed>");
//			msg.add("<carName Camcar "+  prototype.follower1.get(j).getId() +" carName> <segmentPosX -4 segmentPosX> <segmentPosY "+ (prototype.follower1.get(j).getPositionY()) +" segmentPosY> <carSpeed " + (prototype.follower1.get(j).getSpeed())+" carSpeed>");
//			msg.add("<carName "+prototype.leaderVehicle.get(j).getId() +" carName> <segmentPosX -4 segmentPosX> <segmentPosY "+  (-25+ prototype.follower1.get(j).getPositionY()+ xValue) +" segmentPosY> <carSpeed " +  (prototype.follower1.get(j).getSpeed()) +" carSpeed>");
			msg.add("<carName Camcar carName> <segmentPosX -4 segmentPosX> <segmentPosY "+  (-25+ prototype.follower1.get(j).getPositionY()+ xValue) +" segmentPosY> <carSpeed " +  (prototype.follower1.get(j).getSpeed()) +" carSpeed>");

			if(index < j-1 )
		        index++;
		        else
		        	{
		        	 xValue += prototype.follower1.get(j).getPositionY();
		        	index = 0;
		        	
		        	}
	        for(int i = 0; i < msg.size();i++)
	        {
	        	amqp.sendMessage(msg.get(i));
	        }
			
		}
    }

   
}