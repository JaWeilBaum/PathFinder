import java.awt.Color;
import java.awt.Point;

public class Ground extends Tile{

	private Tile aboveTile;
	
	public Ground(Point location) {
		super(location, new Color(0,0,0,0));
	}
	
	public boolean hasAboveTile() {
		return (aboveTile != null) ? true : false;
	}
	
	public Tile getAboveTile(){
		return aboveTile;
	}
	
	public void setAboveTile(Tile t) {
		aboveTile = t;
	}
}
