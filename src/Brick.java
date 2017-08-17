import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Brick {
	private int[][] totalBrick;
	private int[] brickChecker;
	private Game game;
	private BufferedImage brick;
	private BufferedImage brickS;
	private int initXPos;
	private int initYPos;
	private Ball ball;
	private int PXPos;
	private int PYPos = 1;
	private int counter;
	private boolean checkB;

	public Brick(Game game, int initXPos, int initYPos, Ball ball) {
		this.game = game;
		this.initXPos = initXPos;
		this.initYPos = initYPos;
		this.ball = ball;
		checkB = false;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		brick = ss.grabImage(4, 1, 32, 32);
		brickS = ss.grabImage(5, 1, 32, 32);
		counter = 1;
		brickChecker = new int[14*8]; // size of all bricks
		totalBrick = new int[][]{{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	            				 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; //14x8
	}
	
	public void render(Graphics g) {
		//goes through contents of 2d array size and draws bricks
		for (int y = 0; y < totalBrick.length; y++) {
			for (int x = 0; x < totalBrick[y].length; x++) {
				if (totalBrick[y][x] == 0) {
					g.drawImage(brick, x*35, y*20, null);
				}
				else if (totalBrick[y][x] == 5) {
					g.drawImage(brickS, x*35, y*20, null);
				}
				else if (totalBrick[y][x] == 1) {
					/*if (checkB == true) {
						PXPos = x * 35;
						PYPos = y * 20;
						for (int i = 0; i < 200; i++) {
							System.out.println("work");
							g.setColor(Color.WHITE);
							//g.drawString("+50", PXPos, PYPos - i);
						}
						if (PXPos < 0) {
							//checkB = false;
						}


					}
					counter++;
					PXPos = x * 35;
					PYPos = y * 20;
					for (int i =0; i <brickChecker.length; i++) {
						if (brickChecker[i] == 0) {
							PYPos = y * 20 - counter;
							//for (int j = 0; j < 200; j++) {
								//System.out.println("work");
								g.setColor(Color.WHITE);
								g.drawString("+50", PXPos, PYPos);
							//}
						}
						else if (PYPos < 0) {
							brickChecker[i] = 1;
							System.out.println("g");
							counter = 1;
						}
					}*/
				}
				
			}
		}
	}
	//collision detection for brick
	public void ballToBrickIntersection() {
		if (ball.getxPos() / (initXPos * 35) < 14 && ball.getyPos() / (initYPos * 20)< 8) {
			if (totalBrick[ball.getyPos() / 20][ball.getxPos() / 35] == 0) { //0 indicates a brick exists 
				ball.setySpeed(-ball.getySpeed());
				totalBrick[ball.getyPos() / 20][ball.getxPos() / 35] = 1; // 1 indicates brick does not exist
				checkB = true;
				game.addPowerUp(ball.getxPos(), ball.getyPos());
			}
			else if (totalBrick[ball.getyPos() /20][ball.getxPos() / 35] == 5) { //5 indicates unbreakable bricks
				ball.setySpeed(-ball.getySpeed());
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
