//Counts down the times for aspects of the game
//(specific to each game)
public class Counter 
{
	//These represent the number of timer passes required
	//for pregame segment of the game (milliseconds to wait/10)
	private int pregame;
	//D
	private int tickShip;
	
	public Counter()
	{
		pregame = 1000;
		tickShip = 25;
	}
	
	//Let's the Game know when to start
	public boolean update()
	{
		if(pregame > 0)
		{
			pregame--;
		}
		return !(pregame > 0);
	}
	
	//tells the ship to tick down temperature
	public boolean tick()
	{
		tickShip--;
		if(tickShip <= 0)
		{
			tickShip = 25;
			return true;
		}
		return false;
	}
}
