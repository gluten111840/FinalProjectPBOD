package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	Bullet tempBullet;
	Game game;
	
	public Controller(Game game) {
		this.game = game;
//		addBullet(new Bullet(100,300,game));
	}
	
	public void tick() {
		for(int i=0;i<b.size();i++) {
			tempBullet = b.get(i);
			tempBullet.tick();
			
			if(tempBullet.getY()<=0) {
				removeBullet(tempBullet);
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<b.size();i++) {
			tempBullet = b.get(i);
			tempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet bullet) {
		b.add(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		b.remove(bullet);
	}
}
