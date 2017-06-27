package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Hard implements Strategy, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, Integer> level() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("speed", 3);
		map.put("frequency", 3);
		map.put("bars", 6);
		map.put("mans", 3);
		map.put("plates", 60);
		return map;
	}

}
