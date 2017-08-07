import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import java.awt.Rectangle;

public class Game extends Canvas implements Runnable {
	public static final int WIDTH = 240;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String gameTitle = "Arkanoid";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	//init classes 
	private Player player;
	private Ball ball;
	private Brick brick;

	private boolean initHit; // checks if ball is still moveable with player
	private boolean ballHit;

	private Rectangle p; // rectangle shape for player paddle
	private Rectangle b; // rectangle shape for ball
	
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
		double delta = 0; // time passed
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
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
			}
		}
		stop();

	}

	private void tick() {
		player.tick();
		ball.tick();
		wallCollision();
		if (!initHit) {
			ballWallCollision();
			playerCollision();
		}

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
		brick.render(g);

		g.dispose();
		bs.show();
	}

	public void initial() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/spritesheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new UserInput(this));
		player = new Player(200, 300, this, 100, 20);
		ball = new Ball(this, player);
		brick = new Brick(this, 1, 1);
		initHit = true;
		ballHit = true;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		// if right key is hit change speed of paddle
		if (key == KeyEvent.VK_RIGHT) {
			player.setXSpeed(3);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
		// if left key is hit change speed of paddle
		else if (key == KeyEvent.VK_LEFT) {
			player.setXSpeed(-3);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
		// if space bar is hit, release ball from paddle
		else if (key == KeyEvent.VK_SPACE) {
			if (initHit) {
				ball.setXSpeed(2);
				ball.setYSpeed(-2);
			}
			initHit = false;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			player.setXSpeed(0);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
		// if left key is hit change speed of paddle
		else if (key == KeyEvent.VK_LEFT) {
			player.setXSpeed(0);
			if (initHit == true)
				ball.setXSpeed(player.getXSpeed());
		}
	}

	// method to check collisions between of player paddle and wall
	public void wallCollision() {
		if (player.getXPos() < 0) {
			player.setXSpeed(2);
			if (initHit)
				ball.setXSpeed(player.getXSpeed());
		} else if (player.getxPos() > 455) {
			player.setXSpeed(-2);
			if (initHit)
				ball.setXSpeed(player.getXSpeed());
		}
	}

	// method to check collisions between wall and ball
	public void ballWallCollision() {
		// checks if ball hits left side of frame
		if (ball.getxPos() < 0) {
			ball.setXSpeed(-ball.getxSpeed());
			ballHit = false;
		}
		// checks if ball hits right side of frame
		else if (ball.getxPos() > 475) {
			ball.setxSpeed(-ball.getxSpeed());
			ballHit = false;
		}
		// checks if ball hits top side of frame
		else if (ball.getyPos() < 15) {
			ball.setySpeed(-ball.getySpeed());
			ballHit = false;
		}
	}

	// method to check collision between player paddle and wall
	public void playerCollision() {
		p = new Rectangle((int) player.getxPos(), (int) player.getyPos(), player.getWidth(), player.getHeight());
		b = new Rectangle((int) ball.getxPos(), (int) ball.getyPos(), ball
				.getBall().getWidth(), ball.getBall().getHeight() - 20);
		if (p.intersects(b)) {
			if (!ballHit) {
				if (ball.getxSpeed() > 0 && ball.getxPos() < player.getxPos()) {
					ball.setySpeed(-ball.getySpeed());
					ball.setxSpeed(-ball.getxSpeed());
					System.out.println("1");
				}
				else if (ball.getxSpeed() < 0 && ball.getxPos() < player.getxPos()) {
					//ball.setySpeed(-ball.getySpeed());
					if (player.getXSpeed() < 0) {
						ball.setySpeed(ball.getySpeed());
					}
					else
						ball.setySpeed(-ball.getySpeed());
					System.out.println("2");
				}
				else if (ball.getxSpeed() > 0 && ball.getxPos() > player.getxPos()) {
					//ball.setySpeed(-ball.getySpeed());
					if (player.getXSpeed() > 0) {
						ball.setySpeed(ball.getySpeed());
					}
					else
						ball.setySpeed(-ball.getySpeed());
					System.out.println("3");
				}
				else if (ball.getxSpeed() < 0 && ball.getxPos() > player.getxPos() + 75) {
					ball.setySpeed(-ball.getySpeed());
					ball.setxSpeed(-ball.getxSpeed());
					System.out.println("4");
				}
				else
					ball.setySpeed(-ball.getySpeed());
			}
		}

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
