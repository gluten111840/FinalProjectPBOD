package com.game.src.main;

//import java.awt.Canvas;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
//import java.io.IOException;


public class Player extends GameObject implements EntityA{
	private double velX;
	private double velY;

	private Textures tex;
	private Game game;
	private Controller c;
	
	public Player(double x, double y, Textures tex,Game g,Controller c) {
		super(x,y);
		this.tex = tex;
		this.game = g;
		this.c=c;
//		anim = new Animation(tex.player, 3, 3, 1, 1);
	}
	
	public void tick() {
//		this.x++;
		this.x= x+velX;
		this.y= y+velY;
		if(x<=0){
			this.x=0;
		}
		if(x>=(Game.WIDTH*Game.SCALE)-65) {
			this.x=(Game.WIDTH*Game.SCALE)-65;
		}
		if(y<=0) {
			this.y=0;
		}
		if(y>=(Game.HEIGHT*Game.SCALE)-64) {
			this.y=(Game.HEIGHT*Game.SCALE)-64;
		}
		
		for (int i=0;i< game.eb.size();i++) {
			EntityB tempEnt = game.eb.get(i);
			if (Physics.Collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				game.setEnemy_killed(game.getEnemy_killed()+1);
				Game.Health-=20;
				if(Game.Health <=0) {	
					//restart after dying
					game.setState(0);
//					for (i=game.eb.size()-1;i>=0;i--) {
//						c.removeEntity(game.eb.get(i));
//						
//					}
//					for (i=game.ea.size()-1;i>=0;i--) {
//						c.removeEntity(game.ea.get(i));			
//					}
//					game.setEnemy_count(3);		//to trigger spawn enemy
//					game.setEnemy_killed(3);	//
//					Game.Health=100;		
					game.respawn();
				}
			}
				
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y,null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 64, 64);
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
	
	public double getVelX() {
		return this.velX;
	}
	
	public double getVelY() {
		return this.velY;
	}
	
	public void setVelX(double x) {
		this.velX = x;
	}
	
	public void setVelY(double y) {
		this.velY=y;
	}

	
}
