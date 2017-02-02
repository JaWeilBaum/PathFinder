import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class World extends JFrame {

	private Tile world[][];
	private final int tileSize;

	public World(int height, int width, int tileSize) {
		this.world = new Tile[height + 2][width + 2];
		this.tileSize = tileSize;
		createWall();
		setTitle(world.hashCode() + "");
		setSize(world[0].length * tileSize, 20 + world.length * tileSize);
		setVisible(true);
	}

	public Tile getTile(int y, int x) {
		return world[y][x];

	}

	public void createDistanceTilesFrom(Point startPoint) {
		
	}
	
	private void distance(int ownValue, int x, int y) {
		if(world[y + 1][x].canMoveHereFrom(this) && (int)world[y + 1][x])
	}
	
	private void createWall() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (i == 0 || j == 0 || i == world.length - 1 || j == world[i].length - 1)
					world[i][j] = new Wall(new Point(i, j));
				else
					world[i][j] = new Ground(new Point(i, j));
			}
		}
	}

	public void paint(Graphics g) {

		super.paint(g);
		Tile t;
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				t = world[i][j];
				if (t instanceof Wall) {
					g.setColor(t.getTileColor());
					g.fillRect(j * tileSize, 20 + i * tileSize, tileSize, tileSize);
				}
				g.setColor(Color.BLACK);
				g.drawRect(j * tileSize, 20 + i * tileSize, tileSize, tileSize);
			}
		}

	}

}
