package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {

	private Rectangle backButton = new Rectangle(Game.WIDTH-80,300,150,60);
	
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial",Font.BOLD,40);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Help", Game.WIDTH-60, 150);
		g.drawString("Back", backButton.x+25, backButton.y+45);
		
		g2d.draw(backButton);
		
		Font fnt2 = new Font("arial",Font.CENTER_BASELINE,30);
		g.setFont(fnt2);
		g.drawString("Arrow Key to move", backButton.x-50,backButton.y+200);
		g.drawString("Space bar to Shoot", backButton.x-50,backButton.y+240);
		g.drawString("Esc to pause", backButton.x-30,backButton.y+300);
		
		
	}

}
