
public class Location {
	
	private int coordX;
	
	private int coordY;
	
	public Location(int x, int y) {
		this.coordX = x;
		this.coordY = y;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Location))
			return false;
		Location loc = (Location) obj;
		return loc.coordX == coordX && loc.coordY == coordY;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(coordX) << 16 + Integer.hashCode(coordY);
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

}
