package com.game.src.main;

<<<<<<< HEAD
import java.util.LinkedList;
=======
//import java.util.LinkedList;
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics {

<<<<<<< HEAD
	public static boolean Collision(EntityA enta, LinkedList<EntityB> entb) {
		for(int i=0;i<entb.size();i++) {
			if(enta.getBounds().intersects(entb.get(i).getBounds())) {
				return true;
			}
=======
	public static boolean Collision(EntityA enta, EntityB entb) {
//		for(int i=0;i<entb.size();i++) {
			if(enta.getBounds().intersects(entb.getBounds())) {
				return true;
//			}
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
		}
		return false;
	}
	
<<<<<<< HEAD
	public static boolean Collision(EntityB entb, LinkedList<EntityA> enta) {
		for(int i=0;i<enta.size();i++) {
			if(entb.getBounds().intersects(enta.get(i).getBounds())) {
				return true;
			}
=======
	public static boolean Collision(EntityB entb, EntityA enta) {
//		for(int i=0;i<enta.size();i++) {
			if(entb.getBounds().intersects(enta.getBounds())) {
				return true;
//			}
>>>>>>> 6558e54eb5a835bc0a78ff4dd8419699a65570d3
		}
		return false;
	}

}
