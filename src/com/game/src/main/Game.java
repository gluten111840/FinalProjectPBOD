package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 380;
	public static final int SCALE = 2;
	public static int Health = 100;
	public final String TITLE = "Space Warrior";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private boolean is_shooting = false;
	private boolean is_movingRight = false;
	private boolean is_movingLeft = false;
	private boolean is_movingUp = false;
	private boolean is_movingDown = false;
	
	private int enemy_count = 5;
	private int enemy_killed = 0;
	private int skor = 0;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	private Help help;
	private Pause pause;
	private ScoreManager sm = new ScoreManager(this);
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	private boolean scoreSubmitted = false;
	
	private int totalScore;
	
	public enum STATE{
		MENU,
		GAME,
		HELP,
		PAUSE,
		HIGHSCORES,
		GAMEOVER
	};
	

	private STATE State = STATE.MENU;
	
	public void setState(int x) {
		if(x==5) {
			this.State = STATE.GAMEOVER;
		}
		if(x==4) {
			this.State = STATE.HIGHSCORES;
		}
		if(x==3) {
			this.State = STATE.PAUSE;
		}
		if(x==2) {
			this.State = STATE.HELP;
		}
		if(x==1) {
			this.State= STATE.GAME;
		}
		if(x==0) {
			this.State=STATE.MENU;
		}
	}
	
	public int getState() {
		if (State == STATE.MENU) {
			return 0;
		}else if (State == STATE.GAME) {
			return 1;
		}else if (State == STATE.HELP) {
			return 2;
		}else if (State == STATE.PAUSE) {
			return 3;
		}else if (State == STATE.HIGHSCORES) {
			return 4;
		}else if (State == STATE.GAMEOVER) {
			return 5;
		}
		return 0;
	}
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.jpg");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		
//		SpriteSheet ss = new SpriteSheet(spriteSheet);
		c = new Controller(tex, this);
		p = new Player(WIDTH-36,HEIGHT+200,tex,this,c);//*SPAWNPOINT* edit accordingly
		menu = new Menu();
		help = new Help();
		pause = new Pause();
		
		scoreSubmitted = false;
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseInput(this));
		
	
		c.createEnemy(enemy_count);
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
		if(State == STATE.GAME)
		{
			p.tick();
			c.tick();
			totalScore = (int)p.getScore();
			sm.tick();
			
		}
			
		
		if(enemy_killed >= enemy_count) {
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
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
		
		g.drawImage(background, 0, 0, null);
		if(State == STATE.GAME)
		{
			p.render(g);
			c.render(g);
			sm.render(g);
			g.setColor(Color.green);
			g.fillRect(0, Game.HEIGHT*2-50, Health*3, 50);
			g.setColor(Color.gray);
			g.drawRect(0, Game.HEIGHT*2-50, 300, 50);
			Font font = new Font("arial",Font.BOLD,35);
			g.setFont(font);
			g.setColor(Color.white);
			
		} else if(State == STATE.MENU) {
			menu.render(g);
		} else if(State == STATE.HELP) {
			help.render(g);
		} else if(State == STATE.PAUSE) {
			pause.render(g);
		} else if(State == STATE.GAMEOVER) {
			drawGameOver(g);
		} else if(State == STATE.HIGHSCORES) {
			sm.renderHOF(g);
		}
		
		
		//////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public boolean isScoreSubmitted() {
		return scoreSubmitted;
	}

	public void setScoreSubmitted(boolean scoreSubmitted) {
		this.scoreSubmitted = scoreSubmitted;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public int getSkor() {
		return skor;
	}

	public void setSkor(int skor) {
		this.skor = skor;
	}
	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public void respawn() {
		p.setX(WIDTH-36);
		p.setY(HEIGHT+200);
		for (int i=eb.size()-1;i>=0;i--) {
			c.removeEntity(eb.get(i));
			
		}
		for (int i=ea.size()-1;i>=0;i--) {
			c.removeEntity(ea.get(i));			
		}
		setEnemy_count(3);		//to trigger spawn enemy
		setEnemy_killed(3);		//
		Game.Health=100;	
//		p.setScore() = 0;
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(State == STATE.GAME) {
			if (key==KeyEvent.VK_UP) {
				this.is_movingUp = true;
				p.setVelY(-5);
			}
			if (key==KeyEvent.VK_DOWN) {
				this.is_movingDown = true;
				p.setVelY(5);
			}
			if (key==KeyEvent.VK_LEFT) {
				this.is_movingLeft = true;
				p.setVelX(-5);
			}
			if (key==KeyEvent.VK_RIGHT) {
				this.is_movingRight = true;
				p.setVelX(5);
			}
			if(key==KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(),p.getY(),tex, this));
			}
			if(key==KeyEvent.VK_ESCAPE) {
				setState(3);
			}
		} else if(State == STATE.GAMEOVER) {
			if(key==KeyEvent.VK_SPACE) {
				if(!scoreSubmitted) {
					sm.addScore(JOptionPane.showInputDialog("Input Your Name"), totalScore);
					scoreSubmitted = true;
				}
			} else if(key==KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
				init();
			}
		} else if(State == STATE.HIGHSCORES) {
			if(key==KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
				init();
			}
		} else if(State == STATE.HELP) {
			if(key==KeyEvent.VK_ESCAPE) {
				State = STATE.MENU;
				init();
			}
		}
	}
	
	public void drawGameOver(Graphics g) {
		Font title = new Font("SanSerif", Font.BOLD,66);
		g.setFont(title);
		g.setColor(Color.WHITE);
		g.drawString("GameOver", Game.WIDTH/2-150, 100);
		Font word = new Font("SanSerif", Font.BOLD,46);
		g.setFont(word);
		String stringScore = " "+totalScore;
		g.drawString("Your score is"+stringScore , Game.WIDTH/2-90, 200);
		if(sm.isTopTen(totalScore)) {
			if(totalScore>=sm.getCurrentHS()) {
				g.drawString("You got a new HighScore", Game.WIDTH/2-90, 250);
				
			}
			else {
				g.drawString("You rank in top 10", Game.WIDTH/2-90, 250);
			}	
			if(!scoreSubmitted) {
				g.drawString("Please press space to input your name", Game.WIDTH/2-90, 300);
			}
		}
		g.drawString("Please Esc to return to main menu", Game.WIDTH/2-90, 400);
		
	}
	
	public void keyReleased(KeyEvent e) {		//Smoother keystroke
		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_UP) {
			this.is_movingUp = false;
			if(!is_movingDown) {
				p.setVelY(0);
			}
			else {
				p.setVelY(5);
			}
		}
		if (key==KeyEvent.VK_DOWN) {
			this.is_movingDown = false;
			if(!is_movingUp) {
				p.setVelY(0);
			}
			else {
				p.setVelY(-5);
			}
		}
		if (key==KeyEvent.VK_LEFT) {
			this.is_movingLeft = false;
			if(!is_movingRight) {
				p.setVelX(0);
			}
			else {
				p.setVelX(5);
			}
		}
		if (key==KeyEvent.VK_RIGHT) {
			this.is_movingRight = false;
			if(!is_movingLeft) {
				p.setVelX(0);
			}
			else {
				p.setVelX(-5);
			}
		}
		if(key==KeyEvent.VK_SPACE) {
			is_shooting = false;
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