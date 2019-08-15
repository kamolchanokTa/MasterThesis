package reputation;


public class ReputationComputation extends Observer{
	private static final double satisFactionThreshold = 0.009;
	ReputationScore reputationScore;
	
	public ReputationComputation(ReputationScore reputationScore){
	      this.reputationScore = reputationScore;
	      this.reputationScore.attach(this);
	   }
	
//	public boolean validateTrustScore() {
//        int trustScore = 0;
//        if(trustScore/numberOfInteractions >= satisFactionThreshold){
//            return true;
//        }
//        return false;
//    }
	
	public double calculateReputationScore() {

        
        int overAllInteractionScore = this.reputationScore.getNegativeInteractionScore() + this.reputationScore.getPositiveInteractionScore();

        if( overAllInteractionScore ==0){
            overAllInteractionScore = 1;
        }
       
        double interactionSatisfactionScore = this.reputationScore.getPositiveInteractionScore() / overAllInteractionScore;
        

        if (interactionSatisfactionScore >= satisFactionThreshold) {

        	this.reputationScore.increaseTrustScore();
        }
        return interactionSatisfactionScore;

    }

}
