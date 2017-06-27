package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eg.edu.alexu.csd.oop.game.circutOfPlates.object.ShapeIF;

public class ShapeFactory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Singleton design pattern.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ShapeFactory.class);
	private static ShapeFactory instance;

	public static ShapeFactory getInstance() {
		try {
			if (instance == null) {
				instance = new ShapeFactory();
			}
			return instance;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error in singltone!!!");
		}
	}

	public static void destoryInstance() {
		instance = null;
	}

	private ShapeFactory() {
		
	}
	public  List<Class<? extends ShapeIF>> loadPlugn(String path){
		ClassPathEngine cpe = new ClassPathEngine();
		classes = cpe.loadPlugins(path);
		return classes;
	}
	private List<Class<? extends ShapeIF>> classes = new LinkedList<Class<? extends ShapeIF>>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ShapeIF createDrawShape(String className , int x, int y) throws ClassNotFoundException {
		try {			
			for(Class c : classes){
				if(c.getSimpleName().equals(className)){
					logger.debug(c.getName());
					ShapeIF newShape = (ShapeIF) c.newInstance();
					if(newShape instanceof ShapeIF && ShapeIF.class.isAssignableFrom(newShape.getClass())){
						newShape.draw(x,y);
						return newShape;
					}
				}
			}
			Class<ShapeIF> newclass = (Class<ShapeIF>) Class.forName("eg.edu.alexu.csd.oop.game.circutOfPlates.object."+className);
			logger.debug(newclass.getName());
			ShapeIF newShape = newclass.newInstance();
			if(newShape instanceof ShapeIF && ShapeIF.class.isAssignableFrom(newShape.getClass())){
				newShape.draw(x,y);
				return newShape;
			}
			return newShape;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("ClassNotFoundException");
			throw new ClassNotFoundException();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ShapeIF createShape(String className) throws ClassNotFoundException {
		try {			
			for(Class c : classes){
				if(c.getSimpleName().equals(className)){
					logger.debug(c.getName());
					ShapeIF newShape = (ShapeIF) c.newInstance();
					if(newShape instanceof ShapeIF && ShapeIF.class.isAssignableFrom(newShape.getClass())){
						return newShape;
					}
				}
			}
			Class<ShapeIF> newclass = (Class<ShapeIF>) Class.forName("eg.edu.alexu.csd.oop.game.circutOfPlates.object."+className);
			logger.debug(newclass.getName());
			ShapeIF newShape = newclass.newInstance();
			if(newShape instanceof ShapeIF && ShapeIF.class.isAssignableFrom(newShape.getClass())){
				return newShape;
			}
			return newShape;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("ClassNotFoundException");
			throw new ClassNotFoundException();
		}
	}
	
	
}