package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

<<<<<<< HEAD
=======
import com.game.src.main.classes.EntityA;
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {

	Random r = new Random();
	private Textures tex;
	private Game game;
	private Controller c;
	
	private int speed = (r.nextInt(3)+1);
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
		super(x,y);
		this.tex = tex;
		this.c = c;
		this.game = game;
<<<<<<< HEAD
=======
		
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
	}

	public void tick() {
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = 0;
<<<<<<< HEAD
			x = r.nextInt(Game.WIDTH * Game.SCALE);
		}
		
		if(Physics.Collision(this, game.ea)) {
			c.removeEntity(this);
			game.setEnemy_killed(game.getEnemy_killed()+1);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
=======
			x = r.nextInt(Game.WIDTH * Game.SCALE)-64;   //-64 adjust for texture 
		}
		for(int i=0;i< game.ea.size();i++) {
			EntityA tempent = game.ea.get(i);
			if(Physics.Collision(this, tempent)) {
				c.removeEntity(tempent);			//remove bullet on collision
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed()+1);
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(tex.enemy, (int)x, (int)y, null);
		
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	
}
