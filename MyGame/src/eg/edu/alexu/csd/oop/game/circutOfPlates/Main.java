package eg.edu.alexu.csd.oop.game.circutOfPlates;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.info("Enter Main");		 

		JFrame window = new JFrame("Circus Of Plates ");
		View theApplet = new View(window);
		theApplet.init();
		theApplet.start();
		window.setSize(960, 580);
		Toolkit toolKit = window.getToolkit();
		Dimension size = toolKit.getScreenSize();
		window.setLocation((size.width - window.getWidth()) / 2, (size.height - window.getHeight()) / 2);
		window.setContentPane(theApplet);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
