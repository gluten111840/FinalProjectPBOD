package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y,Textures tex, Game game) {
		super(x,y);
		this.tex = tex;
		this.game = game;
	}
	
	public void tick() {
		this.y-=5;
		
		if(Physics.Collision(this, game.eb)) {
			System.out.println("COLLISION DETECTED");
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.missile, (int)x,(int)y,null);
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
		this.y = y;
	}

}
