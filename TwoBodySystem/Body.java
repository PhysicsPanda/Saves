import java.util.LinkedList;

public class Body {
	
	private double[] position;
	private double mass;
	private double g_mass;
	private double[] velocity;
	private double[] acceleration;
	
	private LinkedList<double[]> positionHistory;
	private LinkedList<double[]> velocityHistory;
	private LinkedList<double[]> accelerationHistory;

	/**
	 * @param xy       {@code double[]}coordinate of body
	 * @param mass     {@code double}
	 * @param velocity {@code double[]}
	 */
	public Body(double[] position, double mass, double[] velocity, double[] acceleration) {
		this.position = position;
		this.mass = mass;
		this.g_mass = mass * 6.67384 * Math.pow(10, -11);
		this.velocity = velocity;
		this.acceleration = acceleration;
		
		this.positionHistory = new LinkedList<double[]>();
		this.velocityHistory = new LinkedList<double[]>();
		this.accelerationHistory = new LinkedList<double[]>();
		
		return;
	}

	// setter
	public void putHistory() {
		this.positionHistory.add(new double[] {this.position[0], this.position[1]});
		this.velocityHistory.add(new double[] {this.velocity[0], this.velocity[1]});
		this.accelerationHistory.add(new double[] {this.acceleration[0], this.acceleration[1]});
		return;
	}
	
	public void setPosition(double[] position) {
		this.position = position;
		return;
	}
	
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
		return;
	}
	
	public void setAcceleration(double[] acceleration) {
		this.acceleration = acceleration;
		return;
	}
	
	// getter
	public double mass() {
		return this.mass;
	}
	
	public double g_mass() {
		return this.g_mass;
	}

	public double[] position(int tick) {
		return this.positionHistory.get(tick);
	}
	
	public double[] velocity(int tick) {
		return this.velocityHistory.get(tick);
	}
	
	public double[] acceleration(int tick) {
		return this.accelerationHistory.get(tick);
	}
	
	public LinkedList<double[]> positionData(){
		return this.positionHistory;
	}
}
