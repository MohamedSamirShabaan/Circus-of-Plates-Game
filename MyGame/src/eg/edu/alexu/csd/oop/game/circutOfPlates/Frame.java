package eg.edu.alexu.csd.oop.game.circutOfPlates;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.DesignPattern.Command;
import eg.edu.alexu.csd.oop.game.DesignPattern.Strategy;

public class Frame {

	private JMenuItem newG, save, savePic, load, exit, low, medium, high, pause, resume;
	private static final Logger logger = LoggerFactory.getLogger(Frame.class);
	
	public Frame(Strategy strategy, String plugnPath) {
		logger.info("New Frame");
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu options = new JMenu("Options");
		
		newG = new JMenuItem("New Game");
		save = new JMenuItem("Save Game");
		savePic = new JMenuItem("Save as Picture");
		load = new JMenuItem("Load Game");
		exit = new JMenuItem("Exit");
		file.add(newG);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(load);
		file.addSeparator();
		file.add(savePic);
		file.addSeparator();
		file.add(exit);
		
		low = new JMenuItem("Low");
		medium = new JMenuItem("Medium");
		high = new JMenuItem("High");
		
		options.add(low);
		options.add(medium);
		options.add(high);
		
		JMenu stop = new JMenu("Stop");
		pause = new JMenuItem("Pause");
		resume = new JMenuItem("Resume");
		
		stop.add(pause);
		stop.addSeparator();
		stop.add(resume);
		
		menu.add(file);
		menu.add(options);
		menu.add(stop);
		
		final GameController gameController = GameEngine.start("Circus Of Plates", new eg.edu.alexu.csd.oop.game.circutOfPlates.world.MyWorld(1000, 680,strategy,plugnPath), menu,3);
	
		Command command = new Command(gameController);
		newG.addActionListener(command);
		save.addActionListener(command);
		savePic.addActionListener(command);
		load.addActionListener(command);
		exit.addActionListener(command);
		low.addActionListener(command);
		medium.addActionListener(command);
		high.addActionListener(command);
		pause.addActionListener(command);
		resume.addActionListener(command);
	}

}
