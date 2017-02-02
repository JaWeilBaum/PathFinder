import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		World w = new World(5, 10, 30);
		w.createDistanceTilesFrom(new Point(1, 1));
	}

}
