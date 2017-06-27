package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.io.Serializable;
import java.util.Iterator;

public interface StackCarrier extends Iterator<ShapeIF>, Serializable{

	public void detectStack(boolean isRight);
	public void add(ShapeIF s);
	public int size();
	public void resetIterator();
	public void removeLast3Elements();
	public void removeBottomElement();
	public boolean isSizeMoreThan(int max);
	
}
