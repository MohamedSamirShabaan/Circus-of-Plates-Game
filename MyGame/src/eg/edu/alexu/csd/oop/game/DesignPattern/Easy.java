package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Easy implements Strategy, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, Integer> level() {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("speed", 1);
		map.put("frequency", 1);
		map.put("bars", 2);
		map.put("mans", 1);
		map.put("plates", 40);
		return map;
	}

}
