import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick {
	private int[][] totalBrick;
	private Game game;
	private BufferedImage brick;
	private BufferedImage brickS;
	private int initXPos;
	private int initYPos;

	public Brick(Game game, int initXPos, int initYPos) {
		this.game = game;
		this.initXPos = initXPos;
		this.initYPos = initYPos;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		brick = ss.grabImage(4, 1, 32, 32);
		brickS = ss.grabImage(5, 1, 32, 32);
		totalBrick = new int[][]{{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
	}
	
	public void render(Graphics g) {
		//goes through contents of 2d array size and draws bricks
		for (int y = 0; y < totalBrick.length; y++) {
			for (int x = 0; x < totalBrick[y].length; x++) {
				if (totalBrick[y][x] == 0) {
					g.drawImage(brick, initXPos*x*35, initYPos*y*20, null);
				}
				else if (totalBrick[y][x] == 5) {
					g.drawImage(brickS, initXPos*x*35, initYPos*y*20, null);
				}
				
			}
		}
	}

	public int getInitXPos() {
		return initXPos;
	}

	public void setInitXPos(int initXPos) {
		this.initXPos = initXPos;
	}

	public int getInitYPos() {
		return initYPos;
	}

	public void setInitYPos(int initYPos) {
		this.initYPos = initYPos;
	}
	
}
