package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.util.HashMap;
import java.util.Map;

public interface Observable {

	Map<String, Observer> observers = new HashMap<String, Observer>();
	
	public void attach(Observer observer);

	public void notifyAllObservers();
	
	public int getScore(String name);
}
