package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private Game game;
	public MouseInput(Game g) {
		this.game=g;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int mx = arg0.getX();
		int my = arg0.getY();
//		private Rectangle playButton = new Rectangle(Game.WIDTH-80,300,150,60);
//		private Rectangle helpButton = new Rectangle(Game.WIDTH-80,400,150,60);
//		private Rectangle quitButton = new Rectangle(Game.WIDTH-80,500,150,60);
		if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=300)&&(my<=360)) {
			game.setState(1);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}