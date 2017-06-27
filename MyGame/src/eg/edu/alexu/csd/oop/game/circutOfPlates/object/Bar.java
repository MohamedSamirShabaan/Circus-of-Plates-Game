package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;


public class Bar extends eg.edu.alexu.csd.oop.game.circutOfPlates.object.Shape implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SPRITE_WIDTH = 200;
	public static final int SPRITE_HEIGHT = 10;
	private int life = 5;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private transient BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int x;
	private int y;
	private Color color;
	private final Color[] availableColors = {Color.BLACK,Color.GRAY,Color.ORANGE,Color.PINK};
	

	@Override
	public Color getColor(){
		return color;
	}
	@Override
	public void setColor(Color color){
		this.color = color;;
	}
	
	@Override
	public void draw(int posX, int posY){
		this.x = posX;
		this.y = posY;
		if(this.color==null)
			this.color = this.getRandColor(availableColors);
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		spriteImages[0] = new BufferedImage(SPRITE_WIDTH, SPRITE_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();
		g2.setColor(color);
		g2.setBackground(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(20));
		g2.drawLine(0, 0, getWidth(), 0);
		g2.dispose();
	}
	
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
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return SPRITE_WIDTH;
	}

	@Override
	public int getHeight() {
		return SPRITE_HEIGHT;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
	
	@Override
	public int getLife() {
		return life;
	}
	
	@Override
	public void updateLife() {
		life--;
	}
	
	@Override
	public void setLife(int life){
		this.life=life;
	}
	
	private Color getRandColor(Color[] s){
		Random rand = new Random();
		int index = rand.nextInt(s.length);
		return s[index];
	}
}
