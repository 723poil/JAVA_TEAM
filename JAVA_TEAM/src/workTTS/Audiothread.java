package workTTS;

import java.io.File;

public class Audiothread implements Runnable {

	private final File audio;	
	
	public Audiothread (File audio) {
		this.audio = audio;
	}
	
	public void run() {
		
		AudioPlayer.playAudio(audio);
	}
}
