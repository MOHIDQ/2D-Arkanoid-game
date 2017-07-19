import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Player {
	private double xPos;
	private double yPos;
	private BufferedImage player;
	public Player(double xPos, double yPos, Game game) {
		this.xPos = xPos;
		this.yPos = yPos;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		player = ss.grabImage(2, 1, 32, 32);
		
	}
	
	public void tick() {
		xPos++;
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int)xPos, (int)yPos, null);
	}

}
