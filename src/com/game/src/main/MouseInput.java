package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.game.src.main.Game.STATE;

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
		
		if(game.getState()== 0) {
			if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=300)&&(my<=360)) {
				game.setState(1);
			} if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=400)&&(my<=460)) {
				game.setState(2);
			} 
//			if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=500)&&(my<=560)) {
//				
//			}
			
		}
		if(game.getState()==2) {
			if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=300)&&(my<=360)) {
				game.setState(0);
			}
		}
		if(game.getState()==3) {
			if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=300)&&(my<=360)) {
				game.setState(1);
			}if((mx>= Game.WIDTH-80)&&(mx<=Game.WIDTH+70)&&(my>=400)&&(my<=460)) {
				game.setState(0);
			}
		}	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
