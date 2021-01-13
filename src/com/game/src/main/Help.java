package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Help {

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font title = new Font("SanSerif", Font.BOLD,66);
		g.setFont(title);
		g.setColor(Color.WHITE);
		g.drawString("Help", Game.WIDTH/2-150, 100);
		Font word = new Font("SanSerif", Font.BOLD,46);
		g.setFont(word);
		g.drawString("Press arrow keys to move the spaceship",Game.WIDTH/2-100, 255);
		g.drawString("Press space to shoot",Game.WIDTH/2-100, 355);
		g.drawString("Press esc to pause the game",Game.WIDTH/2-100, 455);
		g.drawString("Press Esc to return to main menu", Game.WIDTH/2-90, 710);
		
		
	}

}
