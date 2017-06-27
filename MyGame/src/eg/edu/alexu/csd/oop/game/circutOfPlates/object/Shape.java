package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Shape implements ShapeIF, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public abstract void draw(int x, int y) ;

	@Override
	public abstract void setLife(int x);

	@Override
	public abstract int getLife();

	@Override
	public abstract void updateLife();

	@Override
	public abstract void setColor(Color color) ;

	@Override
	public abstract Color getColor();
	
}
