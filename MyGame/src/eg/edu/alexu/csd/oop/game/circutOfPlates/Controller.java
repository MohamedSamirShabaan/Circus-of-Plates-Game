package eg.edu.alexu.csd.oop.game.circutOfPlates;

import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;

import eg.edu.alexu.csd.oop.game.DesignPattern.Easy;
import eg.edu.alexu.csd.oop.game.DesignPattern.GameState;
import eg.edu.alexu.csd.oop.game.DesignPattern.Hard;
import eg.edu.alexu.csd.oop.game.DesignPattern.Normal;

public class Controller extends Observable {
	
	private static Controller instance;
	private View view;
	private GameState gs = GameState.getInstance();
	
	private Controller(View atr) {
		view = atr;
	}

	public static Controller getInstance(View toSend) {
		if (instance == null) {
			instance = new Controller(toSend);
		}
		return instance;
	}

	public void newGame() {
		new Frame(view.getStrategy(), gs.getShapePath());
	}

	public void mousePressedMainMenu(int x, int y) {
		if (view.isNewGameMainMenu(x, y)) {
			view.setState(view.GAME);
			newGame();
			view.frame.dispose();
		} 
		else if (view.isOptionsMainMenu(x, y)) {
			view.setState(view.OPTIONS);
		}
		else if (view.isHelpMainMenu(x, y)) {
			view.setState(view.HELP);
		} 
		else if (view.isAddShapeMainMenu(x, y)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.dir")));
			if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath();
				gs.setShapePath(path);
			}
		}
		else if (view.isExitMainMenu(x, y)) {
			System.exit(0);
		}
	}

	public void mousePressedHelp(int x, int y) {
		if (x > 500 && x < 900 && y > 480 && y < 525) {
			view.setState(view.MAINMENU);
		}
	}

	public void mousePressedOptions(int x, int y) {
		if (view.isMainMenuOptions(x, y)) {
			view.setState(view.MAINMENU);
		} else if (view.isSlowOptions(x, y)) {
			view.setStrategy(new Easy());
			view.setState(view.MAINMENU);
		} else if (view.isMediumOptions(x, y)) {
			view.setStrategy(new Normal());
			view.setState(view.MAINMENU);
		} else if (view.isHighOptions(x, y)) {
			view.setStrategy(new Hard());
			view.setState(view.MAINMENU);
		}
	}
}
