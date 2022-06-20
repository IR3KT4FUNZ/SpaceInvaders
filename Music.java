import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//Class to play sound effects in space invaders
//Eric Wang, Elliot Ngo, 6/14/2022

class Music {
	
	public Clip clip;
	public void musicCreate(String musicLocation) {
		try {	
			File musicPath = new File(musicLocation); // access music file based on the music location
			
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
			}
			else {
				System.out.println("Music not available");
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	public void play(){
        clip.setFramePosition(0);  //sets music to start of file
        clip.start(); // plays music
    }
	
	public void loop() {
		clip.setFramePosition(0);  //sets music to start of file
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop(){
         clip.stop(); // stops music
    }

}
