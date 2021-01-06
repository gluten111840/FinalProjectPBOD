package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 380;
	public static final int SCALE = 2;
	public final String TITLE = "Space Warrior";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private Player p;
	private Controller c;
	// temp
//	private BufferedImage player;
	
	
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
//		SpriteSheet ss = new SpriteSheet(spriteSheet);
		p = new Player(WIDTH-36,HEIGHT-160,this);//*SPAWNPOINT* edit accordingly
		c = new Controller(this);
		
		addKeyListener(new KeyInput(this));
	}
	
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 100) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		p.tick();
		c.tick();
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		p.render(g);
		c.render(g);
		
		//////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_UP) {
			p.setVelY(-5);
		}
		if (key==KeyEvent.VK_DOWN) {
			p.setVelY(5);
		}
		if (key==KeyEvent.VK_LEFT) {
			p.setVelX(-5);
		}
		if (key==KeyEvent.VK_RIGHT) {
			p.setVelX(5);
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_UP) {
			p.setVelY(0);
		}
		if (key==KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}
		if (key==KeyEvent.VK_LEFT) {
			p.setVelX(0);
		}
		if (key==KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		}
		if(key==KeyEvent.VK_SPACE) {
			c.addBullet(new Bullet(p.getX(),p.getY(),this));
		}
		
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	
	

}
