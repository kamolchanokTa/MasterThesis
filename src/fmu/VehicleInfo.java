package fmu;

public class VehicleInfo {
	
	private String Id;
	private double speed;
	private double acceleration;
	private double positionY;
	private double interDistance;
	
	public double getInterDistance() {
		return interDistance;
	}
	public void setInterDistance(double interDistance) {
		this.interDistance = interDistance;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
}
