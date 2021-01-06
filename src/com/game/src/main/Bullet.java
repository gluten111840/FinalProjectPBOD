package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet {
	private double x;
	private double y;
//	private double velX;
//	private double velY;
	
	private BufferedImage image;
	
	
	public Bullet(double x, double y,Game game) {
		this.x=x;
		this.y=y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		image = ss.grabImage(2, 1, 64, 64);
	}
	
	public void tick() {
		this.y-=5;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, (int)x,(int)y,null);
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
}
