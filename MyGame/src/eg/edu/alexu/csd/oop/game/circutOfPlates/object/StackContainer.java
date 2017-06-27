package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.DesignPattern.ClassPathEngine;
import eg.edu.alexu.csd.oop.game.DesignPattern.FlyWeight;

public class StackContainer extends JPanel implements Serializable, GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SPRITE_WIDTH = 30;
	private static final int SPRITE_HEIGHT = 15 * 6;
	private static final int MAX_MSTATE = 1;
	private static final Logger logger = LoggerFactory.getLogger(ClassPathEngine.class);
	private int y, x;
	private boolean visible;
	private transient BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private boolean isRight;
	private Man man;

	public StackContainer(StackCarrier stackCarrier, boolean isRight) {
		Man m = (Man)stackCarrier;
		draw(m.getX(), isRight);
		this.man = m;
		this.isRight = isRight;
		this.setBackground(Color.WHITE);
		this.man.detectStack(isRight);
	}

	public void draw(int x, boolean isRight) {
		int deltaX = 7;
		if (!isRight)
			deltaX += 125;
		this.x = (x + deltaX);
		y = (485 - SPRITE_HEIGHT);
		visible = true;
		spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_HEIGHT,
				BufferedImage.TYPE_INT_ARGB);

	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth() {
		return SPRITE_WIDTH;
	}

	@Override
	public int getHeight() {
		return SPRITE_WIDTH;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public void setY(int mY) {
		return;
	}

	@Override
	public int getY() {
		return y;
	}

	public void removeTop3Plates() {
		// When Collect 3 similar ... removes last 3 plates
		pointer-=3;// = stack.size();
		logger.debug("pointer : " + pointer + " (Insinde Remove Top 3 Plates)");
		Graphics2D g2 = (Graphics2D) spriteImages[0].getGraphics();
		g2.setComposite(AlphaComposite.Clear);
		g2.fillRect(0, 0, SPRITE_WIDTH, SPRITE_HEIGHT - ((pointer) * 6));
		man.detectStack(isRight);
		if (man.isSizeMoreThan(SPRITE_HEIGHT / 6)) {
			g2.fillRect(0, 0, SPRITE_WIDTH,(3 * 6));
		}
	}

	private int pointer = 0;

	public void addPlate(ShapeIF p2) {
		ShapeIF p = (ShapeIF) FlyWeight.getInstance().getPlate(p2);

		if (man.isSizeMoreThan(SPRITE_HEIGHT / 6)) {
			spriteImages[0] = swap(spriteImages[0], 0, 6);
			pointer = SPRITE_HEIGHT / 6;
			Graphics2D g2 = (Graphics2D) spriteImages[0].getGraphics();
			Random rand = new Random();
			int r = rand.nextInt(5) + 5;
			g2.drawImage(p.getSpriteImages()[0], r,
					SPRITE_HEIGHT - pointer * 6, null);
		} else {
			pointer++;
			Graphics2D g2 = (Graphics2D) spriteImages[0].getGraphics();
			Random rand = new Random();
			int r = rand.nextInt(5) + 5;
			g2.drawImage(p.getSpriteImages()[0], r,
					SPRITE_HEIGHT - pointer * 6, null);
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		int deltaX = 7;
		if (!isRight)
			deltaX += 125;
		this.x = (man.getX() + deltaX);
	}

	private BufferedImage swap(BufferedImage source, int dx, int dy) {
		BufferedImage swapped = new BufferedImage(source.getWidth(),
				source.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < (source.getWidth() - dx); i++) {
			for (int j = 0; j < (source.getHeight() - dy); j++) {
				int rgba = source.getRGB(i, j);
				// System.out.println("Pixel at (" + i +","+j+")  swapped to ("
				// + (i+dx) +","+(j+dy)+"  has value "+ rgba);
				swapped.setRGB(dx + i, dy + j, rgba);
			}
		}

		return swapped;
	}
}
