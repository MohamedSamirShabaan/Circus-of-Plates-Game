package eg.edu.alexu.csd.oop.game.DesignPattern;

public abstract class Observer {
	
	protected Observable subject;
	public abstract void update();
	public abstract int getScore();
}
