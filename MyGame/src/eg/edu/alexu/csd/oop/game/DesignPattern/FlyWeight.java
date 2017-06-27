package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.game.circutOfPlates.object.ShapeIF;

public class FlyWeight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Singleton
	private static FlyWeight instance = null;

	private FlyWeight() {
	}

	public static FlyWeight getInstance() {
		if (instance == null) {
			instance = new FlyWeight();
		}
		return instance;
	}

	private HashMap<Color, ShapeIF> flyweightObjects;
	
	
	public ShapeIF getPlate(ShapeIF object) {
		if (flyweightObjects == null)
			flyweightObjects = new HashMap<Color, ShapeIF>();
		Color color = object.getColor();
		ShapeIF plate = flyweightObjects.get(color);

		// Makes A Plate Only
		if (plate == null) {
//			String className = object.getClass().getName();
//			try {
//				plate = ShapeFactory.getInstance().createShape(className);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} 
			plate = object;
			plate.setColor(color);
			flyweightObjects.put(color, plate);
		}
		return plate;

	}
}
