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
		
		Font fnt = new Font("arial",Font.BOLD,50);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Help", Game.WIDTH-160, 150);
		g.drawString("huhuu", backButton.x+25, backButton.y+45);
		
		g2d.draw(backButton);
	}

}
