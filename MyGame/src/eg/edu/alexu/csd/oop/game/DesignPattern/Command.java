package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.circutOfPlates.Frame;

public class Command implements ActionListener{

	private GameState gs = GameState.getInstance();
	private Strategy strategy;
	private GameController gameController;

	public Command(GameController gameController){
		gs.setState(new PlayingState(gameController));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if( ((JMenuItem)e.getSource()).getText() .equals("New Game")){
			gs.setState(new NewGameState(gameController));
			new Frame(new Easy(), gs.getShapePath());
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Save Game")){
			PauseState ps = new PauseState(gameController);
			gs.setState(ps);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.dir")));
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName = selectedFile.getAbsolutePath();
				gs.setFileName(fileName);
			}
			ps.doAction("Save Game");
		}
		else if( ((JMenuItem)e.getSource()).getText().equals("Load Game")){
			PauseState ps = new PauseState(gameController);
			gs.setState(ps);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.dir")));
			if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName = selectedFile.getAbsolutePath();
				gs.setFileName(fileName);
			}
			ps.doAction("Load Game");
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Save as Picture")){
			PauseState ps = new PauseState(gameController);
			gs.setState(ps);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.dir")));
			if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String fileName = selectedFile.getAbsolutePath();
				gs.setFileName(fileName);
			}
			ps.doAction("Save as Picture");
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Low")){
			strategy = new Easy();
			new Frame(strategy, gs.getShapePath());
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Medium")){
			strategy = new Normal();
			new Frame(strategy, gs.getShapePath());
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("High")){
			strategy = new Hard();
			new Frame(strategy, gs.getShapePath());
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Exit")){
			System.exit(0);
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Pause")){
			gs.setState(new PauseState(gameController));
		}
		else if( ((JMenuItem)e.getSource()).getText() .equals("Resume")){
			gs.setState(new PlayingState(gameController));
		}
	}


}
