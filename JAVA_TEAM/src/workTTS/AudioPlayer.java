package workTTS;

import java.io.File;
import java.nio.file.Path;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

	public static long totalTime;
	private static long currentTime;
	
	public static void playAudio(File audio) {
		@SuppressWarnings("unused")
		JFXPanel panel = new JFXPanel();
		Path p = audio.toPath();
		Media m = new Media(p.toUri().toString());
		MediaPlayer pa = new MediaPlayer(m);
		currentTime = 0;
		totalTime = 500000;
		pa.play();
		pa.setRate(1);
		

		Thread th = new Thread(()-> {
			
            while(currentTime < totalTime) { // 재생시간에 따라 스레드 종료
            	if(currentTime > 100) totalTime = (long)pa.getTotalDuration().toMillis();
                currentTime = (long)pa.getCurrentTime().toMillis();
                System.out.println((long)pa.getTotalDuration().toMillis());
                System.out.println(currentTime);
            }
            pa.stop();
        });
        th.start();

	}
}