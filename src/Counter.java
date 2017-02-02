import java.awt.Color;
import java.awt.Point;

public class Counter extends Tile{

	private final int distanceToOrigin;
	private final Robot origin;
	private Counter previous; 
	
	public Counter(Point location, int distanceToOrigin, Robot origin, Counter previous) {
		super(location, new Color(0, 0, 0, 0));
		this.distanceToOrigin = distanceToOrigin;
		this.origin = origin;
		this.previous = previous;
	}
	
	

}
