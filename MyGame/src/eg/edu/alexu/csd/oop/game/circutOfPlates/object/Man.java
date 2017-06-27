package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

import javax.imageio.ImageIO;

public class Man extends Shape implements StackCarrier, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private transient BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int y;
	private Stack<ShapeIF> rightList = new Stack<ShapeIF>();
	private Stack<ShapeIF> leftList = new Stack<ShapeIF>();
	private boolean isRight = true;

	public void draw(int posX, int posY) {
		super.setX(posX);
		super.setY(posY);
		this.y = posY;
		// create a bunch of buffered images and place into an array, to be
		// displayed sequentially
		try {
			spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(
					"/clown1.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setY(int mY) {
		if (true)
			return;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth() {
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public boolean hasNext() {
		return hasNext(getActiveStack());
	}

	@Override
	public ShapeIF next() {
		return next(getActiveStack());
	}

	private int pointer = 1;

	@Override
	public void detectStack(boolean isRight) {
		this.isRight = isRight;
	}

	private boolean hasNext(Stack<ShapeIF> s) {
		int size = s.size();
		if (pointer > size) {
			pointer = 1;
			return false;
		}
		return true;
	}

	private ShapeIF next(Stack<ShapeIF> s) {
		if (hasNext(s)) {
			int size = s.size();
			return s.get(size - pointer++);
		}
		return null;
	}

	@Override
	public void add(ShapeIF s) {
		getActiveStack().push(s);
	}

	@Override
	public int size() {
		return getActiveStack().size();
	}

	@Override
	public void resetIterator() {
		pointer = 1;

	}

	private Stack<ShapeIF> getActiveStack() {
		if (isRight)
			return rightList;
		else
			return leftList;
	}

	@Override
	public void removeBottomElement() {
		Stack<ShapeIF> s = getActiveStack();
		if (s.size() >= 16) {
			s.remove(0);
		}
	}

	@Override
	public void removeLast3Elements() {
		Stack<ShapeIF> s = getActiveStack();
		s.pop();
		s.pop();
		s.pop();
	}

	@Override
	public boolean isSizeMoreThan(int max) {
		if (getActiveStack().size() >= max)
			return true;
		return false;
	}

	@Override
	public void setLife(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateLife() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

}