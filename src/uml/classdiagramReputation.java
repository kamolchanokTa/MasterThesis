package uml;

public class classdiagramReputation {

}
/**
*
*@startuml


package reputation {

class ReputationScore [[java:reputation.ReputationScore]] {
	-{static}ReputationScore instance
	-int positiveInteractionScore
	-int negativeInteractionScore
	-int trustScore
	-List<Observer> observers
	+ReputationScore()
	+int getTrustScore()
	+void setTrustScore(int trustScore)
	+void increaseTrustScore()
	+int getPositiveInteractionScore()
	+void setPositiveInteractionScore(int positiveInteractionScore)
	+int getNegativeInteractionScore()
	+void setNegativeInteractionScore(int negativeInteractionScore)
	+void increasePositiveInteractionScore()
	+void increaseNegativeInteractionScore()
	+void attach(Observer observer)
	+void notifyAllObservers()
}

	abstract class Observer [[java:reputation.Observer]] {
		#ReputationScore subject
		+{abstract}void calculateReputationScore()
	}


	class ReputationComputation [[java:reputation.ReputationComputation]] {
		-{static}double satisFactionThreshold
		~ReputationScore reputationScore
		+ReputationComputation(ReputationScore reputationScore)
		+void calculateReputationScore()
	}
}
Observer <|-- ReputationComputation :extends
ReputationScore *-- Observer : observers
@enduml
*
*/