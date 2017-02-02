import java.awt.Point;

public class Robot extends Tile{

	private World world;
	
	public Robot(Point location, World w) {
		super(location, Tile.getRandomColor());
		this.canMove = true;
		this.world = w;
	}

	public void calculateWholeWorld(int value) {
		if (this.canMoveHereFrom(world.getTile(this.getY() + 1, this.getX())))
		if (field[y + 1][x] > ownValue || field[y + 1][x] == -1)
			distance(ownValue + 1, x, y + 1);

		if (field[y - 1][x] > ownValue || field[y - 1][x] == -1)
			distance(ownValue + 1, x, y - 1);

		if (field[y][x + 1] > ownValue || field[y][x + 1] == -1)
			distance(ownValue + 1, x + 1, y);

		if (field[y][x - 1] > ownValue || field[y][x - 1] == -1)
			distance(ownValue + 1, x - 1, y);

		if (field[y + 1][x + 1] > ownValue || field[y + 1][x + 1] == -1)
			distance(ownValue + 1, x + 1, y + 1);
		if (field[y + 1][x - 1] > ownValue || field[y + 1][x - 1] == -1)
			distance(ownValue + 1, x - 1, y + 1);
		if (field[y - 1][x - 1] > ownValue || field[y - 1][x - 1] == -1)
			distance(ownValue + 1, x - 1, y - 1);
		if (field[y - 1][x + 1] > ownValue || field[y - 1][x + 1] == -1)
			distance(ownValue + 1, x + 1, y - 1);
	}
	
	
}
