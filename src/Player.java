import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Player {
	private double xPos;
	private double yPos;
	private int xSpeed;
	private int ySpeed;
	private BufferedImage player;
	public Player(double xPos, double yPos, Game game) {
		this.xPos = xPos;
		this.yPos = yPos;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		player = ss.grabImage(2, 1, 32, 32);
		
	}
	
	public void tick() {
		xPos += xSpeed;
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int)xPos, (int)yPos, null);
	}
	
	public double getXPos() {
		return this.xPos;
	}
	
	public double getYPos() {
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
	
	

}
