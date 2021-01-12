package com.game.src.main;

//import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics {

	public static boolean Collision(EntityA enta, EntityB entb) {
//		for(int i=0;i<entb.size();i++) {
			if(enta.getBounds().intersects(entb.getBounds())) {
				return true;
//			}
		}
		return false;
	}
	
	public static boolean Collision(EntityB entb, EntityA enta) {
//		for(int i=0;i<enta.size();i++) {
			if(entb.getBounds().intersects(enta.getBounds())) {
				return true;
//			}
		}
		return false;
	}

}