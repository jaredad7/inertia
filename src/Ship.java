import java.awt.Image;

import javax.swing.ImageIcon;


public class Ship 
{
	//The x position and the x velocity
	private int x;
	private double dx;
	
	//The y position and the y velocity
	private int y;
	private double dy;
	
	//MAXIMUM X&Y
	private final int MAXDIR = 4;
	
	//rate of change
	private final double CH = 1;
	
	//The ship's Minimum temperature and the ship's temperature
	private final int MINTEMP = -20;
	private int temp;
	
	//The images for the ship
	Image hot;
	Image warm;
	Image cool;
	Image cold;
	
	//boolean for end of game
	
	public Ship(int i, int j)
	{
		x = i;
		y = j;
		temp = MINTEMP*-1;
		dx = 0;
		dy = 0;
		
		//Images
		hot = new ImageIcon(this.getClass().getResource("hotship.png")).getImage();
		warm = new ImageIcon(this.getClass().getResource("warmship.png")).getImage();
		cool = new ImageIcon(this.getClass().getResource("coolship.png")).getImage();
		cold = new ImageIcon(this.getClass().getResource("coldship.png")).getImage();
	}
	
	//Update the ship
	public boolean update(Counter c)
	{
		x += dx;
		y += dy;
		if(c.tick())
		{
			temp--;
		}
		
		return (temp < MINTEMP);
	}
	
	
	//Get the ship's current image
	public Image getImage()
	{
		if(temp >= MINTEMP/-2)
		{
			return hot;
		}
		else if(temp >= 0)
		{
			return warm;
		}
		else if(temp >= MINTEMP/2)
		{
			return cool;
		}
		else
		{
			return cold;
		}		
	}
	
	//Add a value to a ship's temp (add negatives for bad effects)
	public void tempCH(int c)
	{
		temp += c;
	}
	
	//While a player holds the button to move
	//The ship will accelerate
	public void upx()
	{
		if(dx <= MAXDIR)
			dx += CH;
	}
	public void downx()
	{
		if(dx >= MAXDIR*-1)
			dx -= CH;
	}
	public void upy()
	{
		if(dy >= MAXDIR*-1)
			dy -= CH;
	}
	public void downy()
	{
		if(dy <= MAXDIR)
			dy += CH;
	}
	
	//Access ship positions
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
