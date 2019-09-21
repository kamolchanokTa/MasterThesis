package reputation;

public abstract class Observer {
	protected ReputationScore subject;
	public abstract double calculateReputationScore();
	abstract boolean validateTrustScore(int trustScore, int i);
}
