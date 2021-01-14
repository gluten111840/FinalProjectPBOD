package com.game.src.main;

import java.util.Comparator;

public class ScoreComparator extends Score implements Comparator<Score> {
 
	private static final long serialVersionUID = 1L;

	public ScoreComparator(int score) {
		super(score);
	}

	public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }
}