package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ShapePool implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ShapeFactory factory = ShapeFactory.getInstance();
	private List<GameObject> menu = new LinkedList<GameObject>();
	private int size;
	private final int FIXED_DISTANCE = 200;
	
	public ShapePool(String name ,int size , int width ,int height) {
	
		for(int i=0 ; i<size ; i++){
			try {
				int x = (int) (Math.random() * width);
				int y = -1 * (int)(Math.random() * height);
				GameObject temp = factory.createDrawShape(name, x , y);
				menu.add(temp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public ShapePool(int size, String name , int x ,int y) {
		for(int i=0 ; i<size ; i++){
			try {
				GameObject temp = factory.createDrawShape(name, x , y);
				menu.add(temp);
				x+=FIXED_DISTANCE;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public ShapePool(int size , int width ,int height, String name) {
		for(int i=0 ; i<size ; i++){
			try {
				int x = (int) (Math.random() * width);
				int y = (int)(Math.random() * height);
				if(y<0){y+=height/2;}
				if(y>height/2){y-=height/2;}
				if(x<0){x=5;}
				if(width-x<=200){x-=((200-(width-x))+5);}
				GameObject temp = factory.createDrawShape(name, x , y);
				menu.add(temp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	
	public ShapePool(String name, int width ,int height) {
		for(int i=0 ; i<size ; i++){
			try {
				int x = (int) (Math.random() * width);
				int y = -1 * (int)(Math.random() * height);
				GameObject temp = factory.createDrawShape(name, x , y);
				menu.add(temp);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public void setSize(int size) {
		this.size = size;
	}

	public LinkedList<GameObject> getShapes(){
		return (LinkedList<GameObject>) menu;
	}

	
}
