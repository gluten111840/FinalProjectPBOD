package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Controller {
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	EntityA enta;
	EntityB entb;
	private Textures tex;
	Random r = new Random();
	private Game game;
	
	public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
	}
	
	public void createEnemy(int enemy_count) {
		for(int i=0;i<enemy_count;i++) {
<<<<<<< HEAD
			addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
=======
			addEntity(new Enemy(r.nextInt(Game.WIDTH*2-64), -10, tex, this, game)); //-64 adjust for texture 
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
		}
	}
	
	public void tick() {
		// A Class
		for(int i=0;i<ea.size();i++) {
			enta = ea.get(i);
			
			enta.tick();
		}
		// B Class
		for(int i=0;i<eb.size();i++) {
			entb = eb.get(i);
	
			entb.tick();
		}
	}
	
	public void render(Graphics g) {
		// A Class
		for(int i=0;i<ea.size();i++) {
			enta = ea.get(i);
			
			enta.render(g);
		}
		// A Class
		for(int i=0;i<eb.size();i++) {
			entb = eb.get(i);
					
			entb.render(g);
		}
	}
	
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	public void addEntity(EntityB block) {
		eb.add(block);
	}
	
	public void removeEntity(EntityB block) {
		eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB() {
		return eb;
	}
}
