package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface ShapeIF extends GameObject{

	public void draw(int x, int y);
	public void setLife(int x);
	public int getLife();
	public void updateLife();
	public void setColor(Color color);
	public Color getColor();
}
