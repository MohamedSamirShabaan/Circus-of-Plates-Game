package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;

public class Score extends Observer implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Observable ob;
	 private int score;
	public Score(Observable ob){
	      this.ob = ob;
	      this.ob.attach(this);
	   }
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		score++;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

}
