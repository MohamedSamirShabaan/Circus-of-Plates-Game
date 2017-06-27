package eg.edu.alexu.csd.oop.game.DesignPattern;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import eg.edu.alexu.csd.oop.game.World;

@SuppressWarnings("serial")
public class SnapShot implements Serializable{

	private Image screenShot;
	public Image screenShot(int startX, int startY, int endX, int endY){
		try
		{
			Rectangle rect = new Rectangle(startX, startY, endX, endY);
			screenShot = new Robot().createScreenCapture(rect);
			return screenShot;
		}
		catch (AWTException e)
		{
			e.printStackTrace();
		}
		return screenShot;
	}
	
	public void save(World space, String filename)
	{		
		boolean flag = false;
		while(flag == false)
		{
			try
			{
				// Write to disk with FileOutputStream
				FileOutputStream fOut = new FileOutputStream(filename);

				// Write object with ObjectOutputStream
				ObjectOutputStream objOut = new ObjectOutputStream (fOut);
				// Write object out to disk
				objOut.writeObject(space);
				flag = true;
				System.out.println("Saved successfully");
				objOut.flush();
				objOut.close();
			}
			catch(NotSerializableException e)
			{
				System.out.println("Fail in save !!!!!!!\n"+e);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("resource")
	public World load(String filename)
	{
		World toRet;
		try
		{
			// Read from disk using FileInputStream
			FileInputStream fIn = new FileInputStream(filename);
	
			// Read object using ObjectInputStream
			ObjectInputStream objIn = new ObjectInputStream (fIn);
	
			// Read an object
			Object obj = objIn.readObject();
	
			if (obj instanceof World)
			{
				// Cast object to a Vector
				toRet = (World) obj;
				// Do something with vector....
				return toRet;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
