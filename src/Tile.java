import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import org.omg.CORBA.INTERNAL;

public class Tile implements Comparable<Tile>{

	protected final Color tileColor;
	protected Point location;
	protected boolean canMove;
	protected int value;
	protected int prio;

	public Tile(Point location, Color color) {
		this.tileColor = color;
		this.location = location;
		this.canMove = false;
		this.value = -1;
		this.prio = Integer.MAX_VALUE;
	}

	public int getValue() {
		return value;
	}
	
	public static Color getRandomColor() {
		Random r = new Random();
		return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	}
	
	public static Color getColorWithFloat(float f)  {
		int red = 216 - (int) (208 * f);
		int green = 27 + (int) (152 * f);
		int blue = 51 - (int) (25 * f);
		return new Color(red, green, blue);
	}
	
	public Color getTileColor() {
		return tileColor;
	}

	public Point getLocation() {
		return this.location;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getX() {
		return (int) this.location.getX();
	}

	public int getY() {
		return (int) this.location.getY();
	}
	
	public boolean canMoveHereFrom(Tile tile) {
		if (this instanceof Wall) {
			return false;
		}
		return true;
	}
	
	public int getPrio() {
		return prio;
	}
	
	public void setPrio(int prio) {
		this.prio = prio;
	}
	
	public int compareTo(Tile o) {
		return Integer.compare(this.prio, o.prio);
	}
}
