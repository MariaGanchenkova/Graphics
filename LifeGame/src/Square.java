
public class Square {
	
	private boolean value;
	private double impact;
	private Location myselfLocation;
	private double LIVE_BEGIN = 2.0; 
	private double LIVE_END = 3.3;
	private double BIRTH_BEGIN = 2.3; 
	private double BIRTH_END = 2.9;
	
	public Square(int i, int j) {
		this.myselfLocation = new Location(i, j);
		value = true;
	}
	
	public boolean getValue() {
		return value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}

	public double getImpact() {
		return impact;
	}

	public void setImpact(double impact) {
		this.impact = impact;
	}

	public Location getMyselfLocation() {
		return myselfLocation;
	}

	public void setMyselfLocation(Location myselfLocation) {
		this.myselfLocation = myselfLocation;
	}

	public void changeState() {
		if (BIRTH_BEGIN <= impact && impact <= BIRTH_END) {
			value = true;
		}
		if (impact < LIVE_BEGIN || impact > LIVE_END) {
			value = false;
		}
	}
}
