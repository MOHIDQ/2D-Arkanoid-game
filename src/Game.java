import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class Game extends Canvas implements Runnable {
	public static final int WIDTH = 240;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String gameTitle = "Arkanoid";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player player;
	private Ball ball;
	
	private boolean initHit; //checks if ball is still moveable with player
		
	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void run() {
		initial();
		long lastTime = System.nanoTime();
		final double FPS = 60.0;
		double ns = 1000000000 / FPS;
		double delta = 0; //time passed
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long timeNow = System.nanoTime();
			delta += (timeNow - lastTime) / ns;
			lastTime = timeNow;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
				System.out.println(initHit);
			}
		}
		stop();
		
	}
	
	private void tick() {
		player.tick();
		ball.tick();
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
		ball.render(g);
		player.render(g);
		
		
		
		g.dispose();
		bs.show();
	}
	
	public void initial() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/spritesheet.png");
		}catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new UserInput(this));
		player = new Player(200, 300, this);
		ball = new Ball(this, player);
		initHit = true;
	}
	
	public void keyPressed (KeyEvent e) { 
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			player.setXSpeed(1);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
		else if (key == KeyEvent.VK_LEFT) {
			player.setXSpeed(-1);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
		else if (key == KeyEvent.VK_SPACE) {
			initHit = false;
			ball.setXSpeed(1);
			ball.setYSpeed(-1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.gameTitle);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	
	
	

}
