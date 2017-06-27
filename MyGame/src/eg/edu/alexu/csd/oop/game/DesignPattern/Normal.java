package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Normal implements Strategy, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, Integer> level() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("speed", 2);
		map.put("frequency", 2);
		map.put("bars", 4);
		map.put("mans", 2);
		map.put("plates", 50);
		return map;
	}

}
