import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


public class PowerUp {
	private int xPos;
	private int yPos;
	Font myFont;
	public PowerUp(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		 myFont = new Font ("Courier New", 1, 17);
	}
	
	public void removePowerUp(CopyOnWriteArrayList<PowerUp> powerupList, CopyOnWriteArrayList<PowerUp> powerupListTemp) {
		Iterator<PowerUp> pu = powerupList.iterator();
		while(pu.hasNext()) {
			PowerUp powerup = pu.next();
			if (powerup.getyPos() < 0) {
				powerupListTemp.add(powerup); //adds powerup that is out of play into temp collection
			}
		}
		powerupList.removeAll(powerupListTemp); // removes unwanted powerup after left screen
	}
	//tick method creating animation of powerup
	public void tick(CopyOnWriteArrayList<PowerUp> powerupList) {
		for (PowerUp powerUp : powerupList) {
			powerUp.yPos--;
		}
	}
	//render method that draws powerup
	public void render(Graphics g, CopyOnWriteArrayList<PowerUp> powerupList) {
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		for (PowerUp powerUp: powerupList ) {
			g.drawString("+50", powerUp.getxPos(), powerUp.getyPos());
		}
		
	}
	public int getxPos() {
		return xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
