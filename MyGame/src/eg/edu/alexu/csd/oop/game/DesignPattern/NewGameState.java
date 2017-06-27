package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

@SuppressWarnings("serial")
public class NewGameState implements State, Serializable{

	private static final Logger logger = LoggerFactory.getLogger(NewGameState.class);
	private GameController gameController;
	private String action;
	
	public NewGameState(GameController gameController){
		logger.info("New Game State");
		this.gameController = gameController;
	}
	@Override
	public void doAction(String action) {
		// TODO Auto-generated method stub
		this.action = action;
		
		this.gameController.pause();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "New Game State";
	}
	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return action;
	}
}
