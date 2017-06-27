package eg.edu.alexu.csd.oop.game.circutOfPlates;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import eg.edu.alexu.csd.oop.game.DesignPattern.Easy;
import eg.edu.alexu.csd.oop.game.DesignPattern.Strategy;

public class View extends Applet implements Runnable,
		MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1290158768996527785L;
	private Image  image, back1, back2;
	private Controller controller;
	private int state;
	protected JFrame frame;
	private Graphics gg;
	private Strategy strategy = new Easy();
	protected final int MAINMENU = 0, GAME = 1, HELP = 3, OPTIONS = 4;
	private final int HEIGHT=540, WIDTH=960;
	
	public View(JFrame window) {
		// TODO Auto-generated constructor stub
		this.frame = window;
	}

	@Override
	public void init()
	{
		super.init();
		setSize(WIDTH, HEIGHT);
		state = MAINMENU;
		InputStream input;
		try
		{
			input = getClass().getClassLoader().getResourceAsStream("mmmm.jpg");
			back2 = ImageIO.read(input);
			input = getClass().getClassLoader().getResourceAsStream("ooo.jpg");
			back1 = ImageIO.read(input);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		controller = Controller.getInstance(this);
		addMouseListener(this);
	}

	@Override
	public void start()
	{
		super.start();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run()
	{
		while(true){
			repaint();
		}
		
	}

	@Override
	public void stop()
	{
		super.stop();
	}
	
	@Override
	public void update(Graphics g)
	{
		if (image == null)
		{
			image = createImage( this.getSize().width,  this
					.getSize().height);
			gg = image.getGraphics();
		}

		gg.setColor(getBackground());
		gg.fillRect(0, 0, this.getSize().width, this.getSize()
				.height);

		gg.setColor(getForeground());
		paint(gg);

		g.drawImage(image, 0, 0, this);
	}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(state == MAINMENU)
		{
			paintMainMenu(g);
		}
		else if(state == HELP)
		{
			paintHelp(g);
		}
		else if(state == OPTIONS)
		{
			paintOptions(g);
		}
	}
	
	public void paintMainMenu(Graphics g)
	{
		Font font =new Font ("Calibri (Body)",Font.ITALIC, 32);
		Font font2 = new Font("Lucida Calligraphy", Font.PLAIN, 50);
		Color m = new Color(65,105,225	);
		
		g.drawImage(back1, 0, 0, 960, 540, null);
		g.drawImage(back2, 450, 300, 400, 200, null);

		g.setColor(m);
		g.setFont(font2);
		g.drawString("Menu", 550, 40);
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.fill3DRect(500, 50, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("New Game", 560, 80);
		
		g.setColor(Color.YELLOW);
		g.fill3DRect(500, 105, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("Options", 580,135);
		
		g.setColor(Color.YELLOW);
		g.fill3DRect(500, 160, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("How to Play", 560, 190);
		
		g.setColor(Color.yellow);
		g.fill3DRect(500, 215, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("Add new Shape", 535, 245);
		
		g.setColor(Color.yellow);
		g.fill3DRect(500, 270, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("Exit", 620, 300);
		
	}
	
	public void paintHelp(Graphics g)
	{
		Font font2 = new Font("Lucida Calligraphy", Font.PLAIN, 70);
		Font font3 = new Font("Lucida Calligraphy", Font.PLAIN, 30);

		g.drawImage(back1, 0, 0, 960, 540, null);
		g.drawImage(back2, 450, 280, 500, 200, null);
		Color m = new Color(65,105,225	);
		Color n = new Color(193,205,205,160);
		Color c = new Color(93, 71, 139, 100);
		g.setColor(c);
		g.fillRect(0, 0, 960, 540);

		
		g.setColor(n);
		g.fill3DRect(500, 480, 400, 45, true);
		
		
		g.setFont(font3);
		g.setColor(Color.blue);
		g.drawString("Back To Main Menu", 530, 510);
		
		g.setColor(m);
		g.setFont(font2);
		g.drawString("Circus of Plates", 200, 70);
		
		
		Font font = new Font("Calibri (Body)", Font.ITALIC, 35);
		g.setFont(font);
		
		g.setColor(Color.white);

		g.drawString("   Circus of Plates is a single player-game in which each  ", 0, 130);

		g.drawString(" clown carry two stacks of plates, and there are a set of ", 0, 170);

		g.drawString(" colored plates that fall down and he tries to catch them, ", 0, 210);

		g.drawString(" if he manages to collect three consecutive plates of the", 0, 250);

		g.drawString(" same color, then they are vanished and his score increases ",0, 290);

		g.drawString(" and so on till he wins.", 0, 330);
		g.drawString("   The player moves with the right and left direction on the",0,370);

		g.drawString(" keyBoard .", 0, 410 	);
	}
	
	public void paintOptions(Graphics g)
	{
		Font font2 = new Font("Lucida Calligraphy", Font.PLAIN, 70);
		Font font3 = new Font("Lucida Calligraphy", Font.PLAIN, 30);

		g.drawImage(back1, 0, 0, 960, 540, null);
		g.drawImage(back2, 450, 280, 500, 200, null);
		Color m = new Color(65,105,225	);
		Color n = new Color(193,205,205,160);
		g.setColor(n);
		g.fill3DRect(500, 480, 400, 45, true);
		
		g.setFont(font3);
		g.setColor(Color.blue);
		g.drawString("Back To Main Menu", 530, 510);
		
		g.setColor(m);
		g.setFont(font2);
		g.drawString("Speed Level", 430, 70);
		
		Font font = new Font("Calibri (Body)", Font.ITALIC, 35);
		g.setFont(font);
		g.setColor(Color.white);
		
		g.setColor(Color.YELLOW);
		g.fill3DRect(500, 110, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("High", 600, 140);
		
		g.setColor(Color.yellow);
		g.fill3DRect(500, 170, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("Medium", 580, 200);
		
		g.setColor(Color.YELLOW);
		g.fill3DRect(500, 230, 300, 45,true);
		g.setColor(Color.red);
		g.drawString("Slow", 600, 265);
	}

	public void setState(int s)
	{
		this.state = s;
	}

	public Strategy getStrategy(){
		return strategy;
	}
	public void setStrategy(Strategy s){
		strategy = s;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(state == MAINMENU)
		{
			controller.mousePressedMainMenu(e.getX(), e.getY());
		}
		else if(state == HELP)
		{
			controller.mousePressedHelp(e.getX(), e.getY());
		}
		else if(state == OPTIONS)
		{
			controller.mousePressedOptions(e.getX(), e.getY());
		}
		
	}
	
	public boolean isNewGameMainMenu(int x, int y)
	{
		return (x > 500 && x < 800 && y > 50 && y < 100);
	}
	public boolean isOptionsMainMenu(int x, int y)
	{
		return (x > 500 && x < 800 && y > 105 && y < 150);
	}
	public boolean isHelpMainMenu(int x, int y)
	{
		return (x > 500 && x < 800 && y > 160 && y < 205);
	}
	public boolean isExitMainMenu(int x, int y)
	{
		return (x > 500 && x < 800 && y > 270 && y < 315);
	}
	public boolean isAddShapeMainMenu(int x, int y)
	{
		return (x > 500 && x < 800 && y > 215 && y < 260);
	}
	public boolean isHighOptions(int x, int y)
	{
		return (x > 500 && x < 800 && y > 110 && y < 155);
	}
	public boolean isMediumOptions(int x, int y)
	{
		return (x > 500 && x < 800 && y > 170 && y < 315);
	}
	public boolean isSlowOptions(int x, int y)
	{
		return (x > 500 && x < 800 && y > 230 && y < 275);
	}
	public boolean isMainMenuOptions(int x, int y)
	{
		return (x > 500 && x < 900 && y > 480 && y < 525);
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

}