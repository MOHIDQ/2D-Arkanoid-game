import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player {
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private int width;
	private int height;
	//private BufferedImage player;
	private Rectangle player;
	public Player(int xPos, int yPos, Game game, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		//player = ss.grabImage(2, 1, 32, 32);
		player = new Rectangle((int)xPos, (int)yPos, width, height);
		
	}
	
	public void tick() {
		xPos += xSpeed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)xPos - 10, (int)yPos, width, height);
		
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	public int getXSpeed() {
		return this.xSpeed;
	}
	
	public int getYSpeed() {
		return this.ySpeed;
	}
	
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getxPos() {
		return xPos;
	}

	//public BufferedImage getPlayer() {
		//return player;
	//}

	public void setPlayer(BufferedImage player) {
		//this.player = player;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	
	

}
