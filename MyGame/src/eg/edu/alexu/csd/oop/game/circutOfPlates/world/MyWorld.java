package eg.edu.alexu.csd.oop.game.circutOfPlates.world;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.DesignPattern.GameState;
import eg.edu.alexu.csd.oop.game.DesignPattern.Observable;
import eg.edu.alexu.csd.oop.game.DesignPattern.Observer;
import eg.edu.alexu.csd.oop.game.DesignPattern.Score;
import eg.edu.alexu.csd.oop.game.DesignPattern.ShapeFactory;
import eg.edu.alexu.csd.oop.game.DesignPattern.ShapePool;
import eg.edu.alexu.csd.oop.game.DesignPattern.SnapShot;
import eg.edu.alexu.csd.oop.game.DesignPattern.State;
import eg.edu.alexu.csd.oop.game.DesignPattern.Strategy;
import eg.edu.alexu.csd.oop.game.circutOfPlates.object.ShapeIF;
import eg.edu.alexu.csd.oop.game.circutOfPlates.object.StackCarrier;
import eg.edu.alexu.csd.oop.game.circutOfPlates.object.StackContainer;

@SuppressWarnings("serial")
public class MyWorld implements World, Observable, Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MyWorld.class);
	private Map<String, Observer> observers = new HashMap<String, Observer>();
	private static final int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private long startTime = System.currentTimeMillis();
	private int width;
	private int height;
	private int speed;
	private int frequency;
	private int mans;
	private int bars;
	private int plates;
	private String plugnPath;
	private List<GameObject> constant = new LinkedList<GameObject>();
	private List<GameObject> moving = new LinkedList<GameObject>();
	private List<GameObject> control = new LinkedList<GameObject>();
	private ShapePool pool = null;
	private GameState gs;
	private Strategy strategy;
	
	public MyWorld(int screenWidth, int screenHeight, Strategy strategy, String plugnPath) {
		this.strategy = strategy;
		this.width = screenWidth;
		this.height = screenHeight;
		this.plugnPath = plugnPath;
		initialize();
	}

	public void initialize() {
		// control objects (hero)
		new Score(this);
		Map<String, Integer> map = strategy.level();
		speed = map.get("speed");
		frequency = map.get("frequency");
		mans = map.get("mans");
		bars = map.get("bars");
		plates = map.get("plates");
		startTime = System.currentTimeMillis();
		gs = GameState.getInstance();
		
		// control objects (men)
		pool = new ShapePool(mans, "Man", width / 3, (int) (height * 0.8) - 60);
		control = pool.getShapes();
		for (int i = 0; i < mans; i++) {
			control.add(new StackContainer((StackCarrier) control.get(i), true)); // right
			control.add(new StackContainer((StackCarrier) control.get(i), false)); // left
		}

		// moving objects (plates)
		if(plugnPath != null){
			logger.info("Load new Shape in the path :" + plugnPath);
			List<Class<? extends ShapeIF>> plugns = ShapeFactory.getInstance().loadPlugn(plugnPath);
			String []arr = plugns.get(0).getName().split(Pattern.quote("."));
			String shapeName = arr[arr.length-1];
			pool = new ShapePool(shapeName, plates/2, width, height);
			moving = pool.getShapes();
			pool = new ShapePool("Plate", plates/2, width, height);
			moving.addAll(pool.getShapes());
		}else {
			pool = new ShapePool("Plate", plates, width, height);
			moving = pool.getShapes();
		}
		
		// constants objects (bars)
		pool = new ShapePool(bars, width, height, "Bar");
		constant = pool.getShapes();
	}

	private boolean intersect(GameObject o1, GameObject o2) {
		StackCarrier man = (StackCarrier) o2;
		ShapeIF p = (ShapeIF) o1;
		man.detectStack(true);
		boolean right = (Math.abs((o1.getX()+o1.getWidth()/2)-(o2.getX()+70+o2.getWidth()/2))<=o1.getWidth())
				&& (Math.abs((o1.getY()+o1.getHeight()/2)-((o2.getY()-(man.size()+1)*6)-95+o2.getHeight()/2))<=o1.getHeight());
		man.detectStack(false);
		boolean left = (Math.abs((o1.getX() + o1.getWidth() / 2)- (o2.getX() - 70 + o2.getWidth() / 2)) <= o1.getWidth())
				&& (Math.abs((o1.getY()+o1.getHeight()/2)-((o2.getY()-(man.size()+1)*6)-95+o2.getHeight()/2))<=o1.getHeight());
		if (right) {
			logger.info("intersection right");
			man.detectStack(true);
			man.add(p);
			man.removeBottomElement();
			
			p.setY(-1 * (int) (Math.random() * getHeight()));
			p.setX((int) (Math.random() * getWidth()));
			// Draw Plates
			StackContainer stack = getStackOfMan(man, true); 
			stack.addPlate(p);
		}
		if (left) {
			logger.info("intersection left");
			man.detectStack(false);
			man.add(p);
			man.removeBottomElement();
			
			p.setY(-1 * (int) (Math.random() * getHeight()));
			p.setX((int) (Math.random() * getWidth()));
			// Draw Plates
			StackContainer stack = getStackOfMan(man, false);
			stack.addPlate(p);
		}
		return right || left;
	}
	

	private boolean intersect2(GameObject o1, GameObject o2) {
		boolean interX = (Math.abs((o1.getX() + o1.getWidth() / 2)- (o2.getX() + o2.getWidth() / 2)) <= o2.getWidth() / 2);
		boolean interY = (Math.abs((o1.getY() + o1.getHeight() / 2)- (o2.getY() + o2.getHeight() / 2)) <= o2.getHeight() / 2);
		if (interX && interY) {
			logger.info("intersect bar & plate");
			ShapeIF p = (ShapeIF) o1;
			p.setY(-1 * (int) (Math.random() * getHeight()));
			p.setX((int) (Math.random() * getWidth()));
		}
		return interX && interY;
	}

	private boolean updateScore(GameObject o2, boolean right) {
		StackCarrier man = (StackCarrier) o2;
		man.detectStack(right);
		if (man.size() >= 3) {
			Color color1, color2, color3;
			ShapeIF p1 = (ShapeIF) man.next();
			ShapeIF p2 = (ShapeIF) man.next();
			ShapeIF p3 = (ShapeIF) man.next();
			color1 = p1.getColor();
			color2 = p2.getColor();
			color3 = p3.getColor();
			man.resetIterator();
			if (color1 == color2 && color2 == color3) {
				man.removeLast3Elements();
				(getStackOfMan(man, right)).removeTop3Plates();
				notifyAllObservers();
				logger.info("collect 3 increase score :"+right+"true=rigth flase=left");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
		// moving starts
		if (checkState()) {
			for (GameObject m : moving) {
				if (m.isVisible()) {
					for (GameObject c : control) {
						if (c instanceof StackCarrier) {
							if (!timeout & intersect(m, c)) {
								this.updateScore(c, true);
								this.updateScore(c, false);
							}
						}
					}
					for (GameObject s : constant) {
						if (!timeout & intersect2(m, s)) {
							ShapeIF b = (ShapeIF) s;
							if (b.getLife() <= 0) {
								logger.info("bar dead, reuse bar in another position");
								b.setLife(5);
								int x = (int) (Math.random() * width);
								int y = (int) (Math.random() * height);
								if (y < 0) {y += height / 2;}
								if (y > height / 2) {y -= height / 2;}
								if (x < 0) {x = 5;}
								if (width - x <= 200) {x -= ((200 - (width - x)) + 5);}
								b.setX(x);
								b.setY(y);
							} else {
								logger.info("bar intersect, reduce bar life");
								b.updateLife();
							}
						}
					}
				}

				m.setY((m.getY() + speed));
				if (m.getY() >= getHeight()) {
					// reuse the star in another position
					logger.info("reuse the plate in another position");
					m.setY(-1 * (int) (Math.random() * getHeight()));
					m.setX((int) (Math.random() * getWidth()));
				}
				m.setX(m.getX() + (Math.random() > 0.5 ? frequency : -frequency));
			}
		} else {
			doStateAction();
		} 
		return !timeout;
	}

	
	public boolean checkState(){
		if(gs.getState().toString().equals("Playing State")){
			return true;
		}
		return false;
	}
	public void doStateAction(){
		 if(gs.getState().toString().equals("Pause State")){
			 String action = gs.getState().getAction();
			if(action != null && action.equals("Save Game")){
				if (!gs.getFileName().equals(""))
					saveGame(this, gs.getFileName());
			}else if(action != null && action.equals("Load Game")){
				if (!gs.getFileName().equals(""))
					loadGame(gs.getFileName());
			}else if(action != null && action.equals("Save as Picture")){
				if (!gs.getFileName().equals(""))
					saveScreenShot(gs.getFileName(), 170, 0, width+20, height+60);
			}
		}
	}
	public State getState(){
		return gs.getState() ;
	}
	public void saveGame(World w, String fileName) {
		SnapShot ss = new SnapShot();
		ss.save(w, fileName);
	}
	public void saveScreenShot(String fileName, int startX, int startY, int width, int height){
		SnapShot ss = new SnapShot();
		BufferedImage img = (BufferedImage) ss.screenShot(startX, startY, width, height);
		File outputfile = new File(fileName+".png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			logger.error("Error while saving Screen Shot !!");
		}
    }
	public void loadGame(String fileName) {
		SnapShot ss = new SnapShot();
		World worl = ss.load(fileName);
		constant = worl.getConstantObjects();
		control = worl.getControlableObjects();
		height = worl.getHeight();
		moving = worl.getMovableObjects();
		width = worl.getWidth();
	}

	@Override
	public int getSpeed() {
		return 10;
	}

	@Override
	public int getControlSpeed() {
		return 30;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {
		return "Score=" + getScore("Score")+"   |   Time="+ Math.max(0,(MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000); // update
	}

	@Override
	public void attach(Observer observer) {
		// TODO Auto-generated method stub
		observers.put(observer.getClass().getSimpleName(), observer);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		Iterator<Entry<String, Observer>> itr = observers.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, Observer> pair = itr.next();
			Observer ob = pair.getValue();
			ob.update();
		}
	}

	@Override
	public int getScore(String name) {
		return observers.get(name).getScore();
	}
	
	private StackContainer getStackOfMan(StackCarrier man, boolean isRight){
		int i = control.indexOf(man);
		if(isRight) return (StackContainer) control.get(mans+ ((i * 2) + 1));
		return (StackContainer) control.get(mans+ ((i * 2)));
	}

}