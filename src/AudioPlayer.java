import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer
{
	private static boolean     play;//continues to play music when true, alter via setPlay(boolean p)
	private static boolean     debug;
	private static Thread      musicPlayer;
	private static AudioPlayer audioPlayer;
	
	//Audio Player default values
	public AudioPlayer()
	{
		debug = true;
		play = false;
	}
	//AudioPlayer OnCreate options
	public AudioPlayer(boolean d, boolean p)
	{
		debug = d;
		play = p;
	}

	/*Good for creating a short sound effect.
	 *Contains a debug method
	 *Debug can be set to false via the setDebug(boolean d) method
	 */
	public void miniClip(final String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(this.getClass()
					.getResource(filename)));
			clip.start();
		} catch (Exception e)
		{
			if(debug)
				System.out.println(e);
		}
	}

	/*Plays a looped audio file
	 *Easily stopped by calling AudioPlayer.setContinue(false); in the main thread
	 *Debug can be set false via the setDebug(boolean d) method
	 */
	public void handleAudio(String filename) 
	{
		final String FILENAME = filename; // For some stupid java reason, this needs to be final in order to be used later
		musicPlayer = new Thread()
		{
			SourceDataLine soundLine;
			public void run()
			{
				  soundLine = null;
			      int BUFFER_SIZE = 64*1024;  // 64 KB
			do
			{
			      // Set up an audio input stream piped from the sound file.
			      try {
			         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(FILENAME));
			         AudioFormat audioFormat = audioInputStream.getFormat();
			         DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			         soundLine = (SourceDataLine) AudioSystem.getLine(info);
			         soundLine.open(audioFormat);
			         soundLine.start();
			         int nBytesRead = 0;
			         byte[] sampledData = new byte[BUFFER_SIZE];
			         while (nBytesRead != -1) 
			         {
			            nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
			            if (nBytesRead >= 0) 
			            {
			               // Writes audio data to the mixer via this source data line.
			               soundLine.write(sampledData, 0, nBytesRead);
			            }
			            if(!play)
			            	break;
			         }
			      } catch (Exception e) 
			      {
				if(debug)
			           e.printStackTrace();
			      }finally 
			      {
			         soundLine.drain();
			         soundLine.close();
			      }   
			}while(play);//close do
			}//close run
		};
		musicPlayer.start();
	}

	public void setDebug(boolean d)
	{
		debug = d;
	}

	public void setContinue(boolean p)
	{
		play = p;
	}
}