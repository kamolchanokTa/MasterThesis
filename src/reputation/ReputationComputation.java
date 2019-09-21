package reputation;


public class ReputationComputation extends Observer{
	private static final double satisFactionThreshold = 0.8;
	ReputationScore reputationScore;
	
	public ReputationComputation(ReputationScore reputationScore){
	      this.reputationScore = reputationScore;
	      this.reputationScore.attach(this);
	   }
	
	public boolean validateTrustScore(int trustScore , int numberOfInteractions) {
        if(trustScore/numberOfInteractions >= satisFactionThreshold){
            return true;
        }
        return false;
    } 
	
	public double calculateReputationScore() {

        
        int overAllInteractionScore = this.reputationScore.getNegativeInteractionScore() + this.reputationScore.getPositiveInteractionScore();

        if( overAllInteractionScore ==0){
            overAllInteractionScore = 1;
        }
        
       // System.out.println(this.reputationScore.getPositiveInteractionScore() +" neg "+ this.reputationScore.getNegativeInteractionScore()+ " overall "+overAllInteractionScore);
       
        double interactionSatisfactionScore = ((double) this.reputationScore.getPositiveInteractionScore()/(double) overAllInteractionScore);
        //System.out.println("interaction: "+ interactionSatisfactionScore);

        if (interactionSatisfactionScore >= satisFactionThreshold) {

        	this.reputationScore.increaseTrustScore();
        }
        return interactionSatisfactionScore;

    }

}
