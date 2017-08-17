import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Ball {
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private BufferedImage ball;
	
	public Ball(Game game, Player player) {
		xPos = player.getXPos() + 35;
		yPos = player.getYPos() - 23;
		xSpeed = player.getXSpeed();
		ySpeed = player.getYSpeed();
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		ball = ss.grabImage(3, 1, 32, 32);
		
	}
	
	public void tick() {
		xPos += xSpeed;
		yPos += ySpeed;
	}
	
	public void render(Graphics g) {
		g.drawImage(ball, xPos, yPos, null); // + 2 /  -17
	}
	
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public BufferedImage getBall() {
		return ball;
	}

	public void setBall(BufferedImage ball) {
		this.ball = ball;
	}
	
	
	
	
}
