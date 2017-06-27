package eg.edu.alexu.csd.oop.game.circutOfPlates.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

public class Plate extends Shape implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SPRITE_WIDTH = 20;
	private static final int SPRITE_HEIGHT = 5;
	private static final int MAX_MSTATE = 1;
	// an array of sprite images that are drawn sequentially
	private transient BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	private int y;
	private Color color;
	private final  Color[] availableColors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};

	
	
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
		super.setX(posX);
		this.y = posY;
		if(this.color==null)
			this.color = this.getRandColor(availableColors);
		for (int i = 0; i < spriteImages.length; i++) {
			spriteImages[i] = new BufferedImage(SPRITE_WIDTH, SPRITE_HEIGHT,	BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = spriteImages[i].createGraphics();
			g2.setColor(color);
			g2.setBackground(color);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(20));
			g2.drawLine(0, 0, getWidth(), 0);
		}
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
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
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
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
	
	private Color getRandColor(Color[] s){
		Random rand = new Random();
		int index = rand.nextInt(s.length);
		return s[index];
	}
	
}
