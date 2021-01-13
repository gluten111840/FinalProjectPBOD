package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;

import com.game.src.main.classes.EntityA;

import jaco.mp3.player.MP3Player;

public class Bullet extends GameObject implements EntityA {
	
	private Textures tex;
	private Game game;
	public static final String shoot = "res/shoot.mp3";
	
	MP3Player bruk = new MP3Player(new File(shoot));
	
	public Bullet(double x, double y,Textures tex, Game game) {
		super(x,y);
		this.tex = tex;
		this.game = game;
	}
	
	public void tick() {
//		bruk.play();
		this.y-=5;
		
		
//		if(Physics.Collision(this, game.eb)) {			//edit parameter
//			System.out.println("COLLISION DETECTED");
//		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.missile, (int)x,(int)y,null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+28, (int)y, 4, 32);
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