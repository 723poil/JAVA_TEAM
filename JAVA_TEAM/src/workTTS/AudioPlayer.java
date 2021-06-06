package workTTS;

import java.io.File;
import java.nio.file.Path;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {

	public static long totalTime;
	public static long currentTime;
	private static boolean check;
	
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
            	if(currentTime > 100 && !check) {
            		totalTime = (long)pa.getTotalDuration().toMillis();
            		Total_Frame.panel3.voicePlayBar.setMaximum((int) totalTime);
            		Total_Frame.panel3.voicePlayBar.setMinimum(0);
            		Total_Frame.panel3.voicePlayBar.setValue(0);
            		check = true;
            	}
                currentTime = (long)pa.getCurrentTime().toMillis();
                Total_Frame.panel3.voicePlayBar.setValue((int)currentTime);
            }
            Panel_1.valueCount = false;
            pa.stop();
            Total_Frame.panel3.voicePlayBar.setValue(0);
            check = false;
        });
        th.start();

	}
}