package com.game.src.main;

//import java.awt.Canvas;
//import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.io.IOException;


public class Player {
	private double x;
	private double y;
	private double velX;
	private double velY;
	
	private BufferedImage player;
	
	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		player = ss.grabImage(1, 1, 65, 64);
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
	}
	
	public void render(Graphics g) {
		g.drawImage(player, (int)x, (int)y,null);
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
