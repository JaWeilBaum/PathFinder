import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		World w = new World(15, 15, 30);
		w.createDistanceTilesFrom(new Point(5, 3));
	}

}
