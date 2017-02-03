import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	private ArrayList<Counter> workList = new ArrayList<Counter>();
	private int fX = 3;
	private int fY = 10;
	private int counter = 0;

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
			calculate(startPoint);
		}
	}

	private boolean listContainsPoint(Point p) {
		Counter current;
		for (int i = 0; i < workList.size(); i++) {
			current = workList.get(i);
			if (current.getX() == (int) p.getX() && current.getY() == (int) p.getY()) {
				return true;
			}
		}
		return false;
	}

	private void calculate(Point startPoint) {
		world[(int) startPoint.getY()][(int) startPoint.getX()] = new Counter(startPoint, 0);
		workList.add((Counter) world[(int) startPoint.getY()][(int) startPoint.getX()]);
		Counter current;
		while (workList.size() > 0) {

			// System.out.println(workList.size());
			current = workList.get(0);
			workList.remove(0);
			// if (current.getY() == 10 && current.getX() == 10) {
			// System.out.println("Target Forund");
			// return;
			// }
			distance(current.getValue(), current.getX(), current.getY());
			Collections.sort(workList);
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
		if (world[y][x] instanceof Wall)
			return;
//		if (counter % 10000 == 0) {
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
//		}
		counter++;
		world[y][x] = new Counter(new Point(x, y), ownValue);

		// ArrayList<Tile> sourounding = new ArrayList<Tile>();
		//
		// sourounding.add(world[y + 1][x + 1]);
		//
		// sourounding.add(world[y - 1][x + 1]);
		//
		// sourounding.add(world[y + 1][x - 1]);
		//
		// sourounding.add(world[y - 1][x - 1]);
		//
		// sourounding.add(world[y + 1][x]);
		//
		// sourounding.add(world[y - 1][x]);
		//
		// sourounding.add(world[y][x + 1]);
		//
		// sourounding.add(world[y][x - 1]);
		//
		// Collections
		// for (Tile tile : sourounding) {
		// if (tile.getValue() != -2) {
		// distance(ownValue + 1, tile.getX(), tile.getY());
		// }
		// }

		// System.out
		// .println(world[y + 1][x].canMoveHereFrom(this.getTile(y, x)) &&
		// world[y + 1][x].getValue() < ownValue);
		// System.out.println(ownValue);
		boolean flag = false;
		if (x == 2) {
			System.out.println("x: " + x + " y: " + y + " value: " + world[y][x].getValue() + "\nx + 1: " + (x + 1)
					+ " y:" + y + " value: " + world[y][x].getValue() + "\n"
					+ (world[y][x + 1].getValue() > ownValue || world[y][x + 1].getValue() == -1));
		}
		world[y][x] = new Counter(new Point(x, y), ownValue);
		if (world[y + 1][x + 1].getValue() > 1 + ownValue || world[y + 1][x + 1].getValue() == -1) {
			// distance(ownValue + 1, x + 1, y + 1);

			if (!listContainsPoint(new Point(x + 1, y + 1))) {
				world[y + 1][x + 1].setPrio(-1);
				world[y + 1][x + 1].setValue(ownValue + 1);
				workList.add((Counter) world[y + 1][x + 1]);

			}
		}
		if (world[y][x + 1].getValue() > 1 + ownValue || world[y][x + 1].getValue() == -1) {
			// distance(ownValue + 1, x + 1, y);

			if (!listContainsPoint(new Point(x + 1, y))) {
				world[y][x + 1].setPrio(0);
				world[y][x + 1].setValue(ownValue + 1);
				workList.add((Counter) world[y][x + 1]);
			}
		}
		if (world[y + 1][x].getValue() > 1 + ownValue || world[y + 1][x].getValue() == -1) {
			// distance(ownValue + 1, x, y + 1);

			if (!listContainsPoint(new Point(x, y + 1))) {
				world[y + 1][x].setPrio(0);
				world[y + 1][x].setValue(ownValue + 1);
				workList.add((Counter) world[y + 1][x]);
			}
		}

		if (world[y - 1][x - 1].getValue() > 1 + ownValue || world[y - 1][x - 1].getValue() == -1) {
			// distance(ownValue + 1, x - 1, y - 1);

			if (!listContainsPoint(new Point(x - 1, y - 1))) {
				world[y - 1][x - 1].setValue(ownValue + 1);
				workList.add((Counter) world[y - 1][x - 1]);
			}
		}
		if (world[y - 1][x].getValue() > 1 + ownValue || world[y - 1][x].getValue() == -1) {
			// distance(ownValue + 1, x, y - 1);

			if (!listContainsPoint(new Point(x, y - 1))) {
				world[y - 1][x].setPrio(Integer.MAX_VALUE);
				world[y - 1][x].setValue(ownValue + 1);
				workList.add((Counter) world[y - 1][x]);
			}
		}

		if (world[y][x - 1].getValue() > 1 + ownValue || world[y][x - 1].getValue() == -1) {
			// distance(ownValue + 1, x - 1, y);

			if (!listContainsPoint(new Point(x - 1, y))) {
				world[y][x - 1].setPrio(Integer.MAX_VALUE);
				world[y][x - 1].setValue(ownValue + 1);

				workList.add((Counter) world[y][x - 1]);
			}
		}

		if (world[y + 1][x - 1].getValue() > 1 + ownValue || world[y + 1][x - 1].getValue() == -1) {
			// distance(ownValue + 1, x - 1, y + 1);

			if (!listContainsPoint(new Point(x - 1, y + 1))) {
				world[y + 1][x - 1].setValue(ownValue + 1);
				workList.add((Counter) world[y + 1][x - 1]);
			}
		}
		if (world[y - 1][x + 1].getValue() > 1 + ownValue || world[y - 1][x + 1].getValue() == -1) {
			// distance(ownValue + 1, x + 1, y - 1);

			if (!listContainsPoint(new Point(x + 1, y - 1))) {
				world[y - 1][x + 1].setValue(ownValue + 1);
				workList.add((Counter) world[y - 1][x + 1]);
			}
		}

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
							g.setColor(Color.black);
							;
							g.drawString(t.getValue() + "", j * tileSize, i * tileSize + 10);
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
				else if ((i == 3 || i == 7) && j >= 3 && j <= 7)
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
