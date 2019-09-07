import javax.swing.JFrame;

public class Main 
{
	private final static int X = 900;
	private final static int Y = 568;
	
	public static void main(String args[])
	{
		JFrame frame = new JFrame("Inertia [JDGames]");
		frame.setSize(X, Y);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Game(X, Y));
		frame.setVisible(true);
		
	}
	
}
