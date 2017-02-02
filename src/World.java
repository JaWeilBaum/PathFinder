import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class World extends JFrame {

	private Tile world[][];
	private final int tileSize;
	private JFrame frame;
	private JLabel view;
	private boolean flag = true;

	public World(int height, int width, int tileSize) {
		this.world = new Tile[height + 2][width + 2];
		this.tileSize = tileSize;
		createWall();
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setTitle(world.hashCode() + "");
		frame.setSize(world[0].length * tileSize, 20 + world.length * tileSize);
		frame.setVisible(true);
	}

	public Tile getTile(int y, int x) {
		return world[y][x];

	}

	public void createDistanceTilesFrom(Point startPoint) {
		if (startPoint == null) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Von wo sollen alle distanzen berechnet werden x: ");
			int _x = sc.nextInt();
			System.out.print(" y: ");
			int _y = sc.nextInt();
			distance(0, _x, _y);
		} else {
			distance(0, (int) startPoint.getX(), (int) startPoint.getY());
		}
	}

	private int findLargest() {
		int largest = Integer.MIN_VALUE;
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (world[i][j].getValue() > largest)
					largest = world[i][j].getValue();
			}
		}
		return largest;
	}

	private void distance(int ownValue, int x, int y) {

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!flag) {
			frame.remove(view);

		} else {
			flag = false;
		}
		view = new JLabel(new ImageIcon(tick(x, y)));
		frame.getContentPane().add(view);
		frame.pack();

		// System.out.println(world[y + 1][x].canMoveHereFrom(this.getTile(y,
		// x)) && world[y + 1][x].getValue() < ownValue);
		// System.out.println(ownValue);
		world[y][x] = new Counter(new Point(x, y), ownValue);
		if (world[y + 1][x + 1].getValue() > ownValue || world[y + 1][x + 1].getValue() == -1)
			distance(ownValue + 1, x + 1, y + 1);
		if (world[y - 1][x - 1].getValue() > ownValue || world[y - 1][x - 1].getValue() == -1)
			distance(ownValue + 1, x - 1, y - 1);
		if (world[y + 1][x - 1].getValue() > ownValue || world[y + 1][x - 1].getValue() == -1)
			distance(ownValue + 1, x - 1, y + 1);
		if (world[y - 1][x + 1].getValue() > ownValue || world[y - 1][x + 1].getValue() == -1)
			distance(ownValue + 1, x + 1, y - 1);
		if (world[y + 1][x].getValue() > ownValue || world[y + 1][x].getValue() == -1)
			distance(ownValue + 1, x, y + 1);
		if (world[y - 1][x].getValue() > ownValue || world[y - 1][x].getValue() == -1)
			distance(ownValue + 1, x, y - 1);
		if (world[y][x + 1].getValue() > ownValue || world[y][x + 1].getValue() == -1)
			distance(ownValue + 1, x + 1, y);
		if (world[y][x - 1].getValue() > ownValue || world[y][x - 1].getValue() == -1)
			distance(ownValue + 1, x - 1, y);
		

	}

	private BufferedImage tick(int x, int y) {

		BufferedImage img = new BufferedImage(world[0].length * tileSize, world.length * tileSize,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		Tile t;
		int max = findLargest();
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (y != i || x != j) {
					t = world[i][j];
					if (t instanceof Wall) {
						g.setColor(t.getTileColor());
						g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
					}
					if (t instanceof Counter) {
						if (t.getValue() != -1) {
							g.setColor(Tile.getColorWithFloat((float) t.getValue() / (float) max));
							g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
						}
					}
				}
				g.setColor(Color.BLACK);
				g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
			}
		}
		return img;
	}

	private void createWall() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (i == 0 || j == 0 || i == world.length - 1 || j == world[i].length - 1)
					world[i][j] = new Wall(new Point(i, j));
				else
					world[i][j] = new Counter(new Point(i, j), -1);
			}
		}
	}

	public void paint(Graphics g) {

		super.paint(g);

		// g.drawImage(tick(), 0,20,null);
	}

}
