import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Tile {

	protected final Color tileColor;
	protected Point location;
	protected boolean canMove;

	public Tile(Point location, Color color) {
		this.tileColor = color;
		this.location = location;
		this.canMove = false;
	}

	public static Color getRandomColor() {
		Random r = new Random();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
	
	public Color getTileColor() {
		return tileColor;
	}

	public Point getLocation() {
		return this.location;
	}

	public int getX() {
		return (int) this.location.getX();
	}

	public int getY() {
		return (int) this.location.getY();
	}

	public boolean canMoveHereFrom(Tile tile) {
		if (tile.canMove) {
			if (this.getX() >= tile.getX() + 1 || this.getX() <= tile.getX() - 1) {
				if (this.getY() >= tile.getY() + 1 || this.getY() <= tile.getY() - 1) {
					return true;
				}
			}
		}
		return false;
	}
}
