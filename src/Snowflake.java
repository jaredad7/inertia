import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Snowflake 
{
	//Is it red or blue
	private boolean benefit;
	
	//pixel buffer size*2
	private final int BUF = 100;
	
	//X and Y coordinates
	private int X;
	private int Y;
	
	//remaining time in centi-seconds
	private int there;
	
	public Snowflake(Frame f)
	{
		//determine location on frame with Buf pixel buffer
		Random r = new Random();
		X = r.nextInt(f.getX()-BUF) + BUF/2;
		Y = r.nextInt(f.getY()-BUF) + BUF/2;
		//50% chance of each snowflake
		benefit = (r.nextInt(3) == 0);
		there = r.nextInt(600)+200;
	}
	
	public boolean update()
	{
		there--;
		return (there > 0);
	}
	//get the image
	public Image getImg()
	{
		if(benefit)
		{
			return new ImageIcon(this.getClass().getResource("redsnowflake.png")).getImage();
		}
		
		return new ImageIcon(this.getClass().getResource("bluesnowflake.png")).getImage();
	}
	
	//get benefit
	public boolean getB()
	{
		return benefit;
	}
	public void taken()
	{
		there = 0;
	}
	
	//get the coordinates
	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	}
}
