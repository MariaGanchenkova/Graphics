import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Field {
	public static final double firstImpact = 1.0;
	public static final double secondImpact = 0.3;
	
	private Map<Location, Square> centers = new HashMap<Location, Square>();
	private Map<Location, Square> prevCenters = new HashMap<Location, Square>();
	private int n;               //высота
	private int m;               //ширина
	
	public int differense1(Square squareStart, Square squareNeighbor) {
		if ( (squareStart.getMyselfLocation().getCoordY() % 2 != 0)) {
			if (((squareStart.getMyselfLocation().getCoordX() + 1 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() == squareNeighbor.getMyselfLocation().getCoordY()))) { 
				return 1;
			}
			if ((squareStart.getMyselfLocation().getCoordX() - 1 == squareNeighbor.getMyselfLocation().getCoordX())
					&& ((squareStart.getMyselfLocation().getCoordY() == squareNeighbor.getMyselfLocation().getCoordY())
							|| ((squareStart.getMyselfLocation().getCoordY()
									+ (1 - 2 * (squareStart.getMyselfLocation().getCoordX() % 2))) == squareNeighbor
											.getMyselfLocation().getCoordY())
							|| ((squareStart.getMyselfLocation().getCoordY() 
									- (1 - 2 * (squareStart.getMyselfLocation().getCoordX() % 2))) == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}

			if ((squareStart.getMyselfLocation().getCoordX() == squareNeighbor.getMyselfLocation().getCoordX())
					&& ((squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			return 0;
		} else {
			if (((squareStart.getMyselfLocation().getCoordX() + 1 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() == squareNeighbor.getMyselfLocation().getCoordY())
							|| ((squareStart.getMyselfLocation().getCoordY()
									+ (1 - 2 * (squareStart.getMyselfLocation().getCoordX() % 2))) == squareNeighbor
											.getMyselfLocation().getCoordY())
							|| ((squareStart.getMyselfLocation().getCoordY()
									- (1 - 2 * (squareStart.getMyselfLocation().getCoordX() % 2))) == squareNeighbor
											.getMyselfLocation().getCoordY()))) { 
				return 1;
			}
			if ((squareStart.getMyselfLocation().getCoordX() - 1 == squareNeighbor.getMyselfLocation().getCoordX())
					&& ((squareStart.getMyselfLocation().getCoordY() == squareNeighbor.getMyselfLocation()
							.getCoordY()))) {
				return 1;
			}

			if ((squareStart.getMyselfLocation().getCoordX() == squareNeighbor.getMyselfLocation().getCoordX())
					&& ((squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation()
							.getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation()
									.getCoordY()))) {
				return 1;
			}
			return 0;
		}
	}
	
	public int differense2(Square squareStart, Square squareNeighbor) {
		if ((squareStart.getMyselfLocation().getCoordY() % 2 == 0)) {
			if (((squareStart.getMyselfLocation().getCoordX() - 1 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			if (((squareStart.getMyselfLocation().getCoordX() == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 2 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 2 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			if (((squareStart.getMyselfLocation().getCoordX() + 2 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			return 0;
		} else {
			if (((squareStart.getMyselfLocation().getCoordX() - 2 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			if (((squareStart.getMyselfLocation().getCoordX() == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 2 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 2 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			if (((squareStart.getMyselfLocation().getCoordX() + 1 == squareNeighbor.getMyselfLocation().getCoordX()))
					&& ((squareStart.getMyselfLocation().getCoordY() - 1 == squareNeighbor.getMyselfLocation().getCoordY())
							|| (squareStart.getMyselfLocation().getCoordY() + 1 == squareNeighbor.getMyselfLocation().getCoordY()))) {
				return 1;
			}
			return 0;
		}
	}
	
	private int countFirstNeighbors(Square square) {
		int count = 0;
		for (Entry<Location, Square> entry : getCenters().entrySet()) {
			count += differense1(square, entry.getValue());
		}
		//System.out.println(square.getMyselfLocation().getCoordX() + ":" + square.getMyselfLocation().getCoordY() + " " + count);
		return count;
	}
	
    private int countSecondNeighbors(Square square) {
    	int count =0;
    	for (Entry<Location, Square> entry : getCenters().entrySet()) {
			count += differense2(square, entry.getValue());
		}
		//System.out.println(square.getMyselfLocation().getCoordX() + ":" + square.getMyselfLocation().getCoordY() + " " + count);
		return count;
	}
	
	public Field(int n, int m, Map<Location, Pixel> centers) {
		this.n = n;
		this.m = m;
		for (Map.Entry<Location, Pixel> entry : centers.entrySet()) {
			this.getCenters().put(entry.getKey(), new Square(entry.getKey().getCoordX(), entry.getKey().getCoordY()));
		}
	}

	public Map<Location, Square> nextGeneration() {
		System.out.println(this);
		for (Entry<Location, Square> entry : centers.entrySet()) {
			prevCenters.put(entry.getKey(), entry.getValue());
		}
		Iterator<Entry<Location, Square>> iterator1 = prevCenters.entrySet().iterator();
		Entry<Location, Square> current1 = iterator1.next();
		Iterator<Entry<Location, Square>> iterator2 = centers.entrySet().iterator();
		Entry<Location, Square> current2 = iterator2.next();
		while (iterator1.hasNext()) {
			while (current2.getKey().equals(current1.getKey()) && iterator2.hasNext()) {
					int firstNeighbors = countFirstNeighbors(current1.getValue());
					int secondNeighbors = countSecondNeighbors(current1.getValue());
					current2.getValue().setImpact(firstImpact * firstNeighbors + secondImpact * secondNeighbors);
					current2.getValue().changeState();
					centers.put(current2.getKey(), current2.getValue());
					//System.out.println(current2.getKey().getCoordX() + " " + current2.getKey().getCoordY() + " " + centers.get(current2.getKey()).getValue());
					current2 = iterator2.next();
			}
			current1 = iterator1.next();
		}
		return centers;
	}

	public Map<Location, Square> getCenters() {
		return centers;
	}

	public void setCenters(Map<Location, Square> centers) {
		this.centers = centers;
	}
}
