package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GameState implements Serializable {

	//Singleton
	private static GameState instance = null;
	private State state ;
	private String fileName = "";
	private String shapePath;
	
	private GameState() {}
	public static GameState getInstance(){
		if(instance == null){
			instance = new GameState();
		}
		return instance;
	}
	
	public void setState(State stat){
		this.state = stat;
	}
	
	public State getState(){
		return state;
	}
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	public String getFileName(){
		return this.fileName ;
	}
	public void setShapePath(String path){
		this.shapePath = path;
	}
	public String getShapePath(){
		return shapePath;
	}
}
