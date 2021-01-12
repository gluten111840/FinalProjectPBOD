package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pause {

	private Rectangle resumeButton = new Rectangle(Game.WIDTH-130,300,250,60);
	private Rectangle quitButton = new Rectangle(Game.WIDTH-80,400,150,60);

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial",Font.BOLD,50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Paused", Game.WIDTH-160, 150);
		
		g.drawString("Resume", resumeButton.x+25, resumeButton.y+45);
		g.drawString("Quit", quitButton.x+25, quitButton.y+45);
		
		g2d.draw(resumeButton);
		g2d.draw(quitButton);

	}

	
}
