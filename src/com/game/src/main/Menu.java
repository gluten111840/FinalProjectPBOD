package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	private Rectangle playButton = new Rectangle(Game.WIDTH-80,300,150,60);
	private Rectangle helpButton = new Rectangle(Game.WIDTH-80,400,150,60);
	private Rectangle quitButton = new Rectangle(Game.WIDTH-80,500,150,60);
	
//	public Menu() {
//
//	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial",Font.BOLD,50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Space Gaem", Game.WIDTH-160, 150);
		g.drawString("Play", playButton.x+25, playButton.y+45);
		g.drawString("Help", playButton.x+25, helpButton.y+45);
		g.drawString("Quit", playButton.x+25, quitButton.y+45);
		
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	}

}
