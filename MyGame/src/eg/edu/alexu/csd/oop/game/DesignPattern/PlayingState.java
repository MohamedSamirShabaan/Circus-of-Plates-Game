package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

@SuppressWarnings("serial")
public class PlayingState implements State, Serializable{

	private static final Logger logger = LoggerFactory.getLogger(PlayingState.class);
	private GameController gameController;
	private String action;
	
	public PlayingState(GameController gameController){
		this.gameController = gameController;
	}
	@Override
	public void doAction(String action) {
		// TODO Auto-generated method stub
		this.action = action;
		logger.info("Resume");
		this.gameController.resume();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Playing State";
	}
	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return action;
	}

}
