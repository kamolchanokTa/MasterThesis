package contracts;

public class V2VInputContract {

	private static final double demandFrequency = 0.1;
	private static final double demandPacketLossTime = 2.0;
	
	//demand
	public boolean isCommunicationFail(double packetLossTime) {
		if (packetLossTime > demandPacketLossTime) return true;
		else return false;
	}
	
	//demand
	public boolean isMeetfrequencyUpdate(double frequency) {
		return ((frequency == demandFrequency)? true: false);
	}
}
