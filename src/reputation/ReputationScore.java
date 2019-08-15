package reputation;

import java.util.ArrayList;
import java.util.List;

import contracts.IdentificationContract;
import verificationScenarios.VehicleIdentityVerificationScenario;

public class ReputationScore {
	
	private static volatile ReputationScore instance;
	
	private int positiveInteractionScore; 
	private int negativeInteractionScore; 
	private int trustScore;
	
	public ReputationScore(){
		
	}
	
//	public static ReputationScore getInstance() {
//	    if (instance == null) {
//	        synchronized (ReputationScore.class) {
//	            if (instance == null) {
//	                instance = new ReputationScore();
//	            }
//	        }
//	    }
//	    return instance;
//	 }

	private List<Observer> observers = new ArrayList<Observer>();


    public int getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(int trustScore) {
        this.trustScore = trustScore;
    }

 
    public void increaseTrustScore() {
        trustScore++;
    }

    public int getPositiveInteractionScore() {
        return positiveInteractionScore;
    }

    public void setPositiveInteractionScore(int positiveInteractionScore) {
        this.positiveInteractionScore = positiveInteractionScore;
        notifyAllObservers();
    }

    public int getNegativeInteractionScore() {
        return negativeInteractionScore;
    }

    public void setNegativeInteractionScore(int negativeInteractionScore) {
        this.negativeInteractionScore = negativeInteractionScore;
        notifyAllObservers();
    }

    

    public void increasePositiveInteractionScore() {
        positiveInteractionScore++;
        notifyAllObservers();
    }

    public void increaseNegativeInteractionScore() {
        negativeInteractionScore++;
        notifyAllObservers();
    }

    
    public void attach(Observer observer){
        observers.add(observer);		
     }

     public void notifyAllObservers(){
        for (Observer observer : observers) {
           observer.calculateReputationScore();
        }
     }

}
