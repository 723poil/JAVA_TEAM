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
	
//	public static void playAudio(File audio) { //
//		@SuppressWarnings("unused")
//		JFXPanel panel = new JFXPanel();
//		Path p = audio.toPath();
//		MediaPlayer pa = new MediaPlayer(new Media(p.toUri().toString()));
//		currentTime = 0;
//		totalTime = 500000;
//		pa.play();
//		pa.setRate(1);
//		
//
//		Thread th = new Thread(()-> {
//			Total_Frame.panel3.playButton.setSelected(true);
//			Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.clickedplay);
//            while(currentTime < totalTime) { // 재생시간에 따라 스레드 종료
//            	if(currentTime > 100 && !check) {
//            		totalTime = (long)pa.getTotalDuration().toMillis();
//            		Total_Frame.panel3.voicePlayBar.setMaximum((int) totalTime);
//            		Total_Frame.panel3.voicePlayBar.setMinimum(0);
//            		Total_Frame.panel3.voicePlayBar.setValue(0);
//            		Total_Frame.panel3.totaltime.setText(totalTime());
//            		check = true;
//            	}
//            	if(Total_Frame.panel3.pauseButton.isSelected()) {
//            		pa.pause();
//            		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.clickedpause);
//            		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
//            	}
//            	if(Total_Frame.panel3.playButton.isSelected()) {
//            		pa.play();
//            		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.pauseImage);
//            		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.clickedplay);
//            	}
//            	if(Panel_3.dispose) {
//            		Total_Frame.panel2.button5.setSelected(false);
//            		Panel_3.dispose = false;
//            		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.pauseImage);
//            		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
//            		break;
//            	}
//                currentTime = (long)pa.getCurrentTime().toMillis();
//                Total_Frame.panel3.curtime.setText(curTime());
//                
//                Total_Frame.panel3.voicePlayBar.setValue((int)currentTime);
//            }
//            
//            pa.stop();
//            Total_Frame.panel3.voicePlayBar.setValue(0);
//            Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
//            
//            Total_Frame.panel3.curtime.setText("00:00");
//            if(Total_Frame.panel2.button5.isSelected()) {
//            	check = false;
//            	pa.dispose();
//            	playAudio(audio);
//            	
//            }
//            else {
//            	check = false;
//            	Panel_1.valueCount = false;
//            	Total_Frame.panel3.totaltime.setText("00:00");
//            	pa.dispose();
//            	Thread.currentThread().interrupt();
//            }
//        });
//        th.start();
//
//	}
	public static synchronized void playAudio(File audio) { //
		@SuppressWarnings("unused")
		JFXPanel panel = new JFXPanel();
		Path p = audio.toPath();
		MediaPlayer pa = new MediaPlayer(new Media(p.toUri().toString()));
		currentTime = 0;
		totalTime = 500000;
		pa.play();
		pa.setRate(1);
		
		Total_Frame.panel3.playButton.setSelected(true);
		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.clickedplay);
        while(currentTime < totalTime) { // 재생시간에 따라 스레드 종료
           	if(currentTime > 100 && !check) {
           		totalTime = (long)pa.getTotalDuration().toMillis();
           		Total_Frame.panel3.voicePlayBar.setMaximum((int) totalTime);
           		Total_Frame.panel3.voicePlayBar.setMinimum(0);
           		Total_Frame.panel3.voicePlayBar.setValue(0);
           		Total_Frame.panel3.totaltime.setText(totalTime());
           		check = true;
           	}
           	if(Total_Frame.panel3.pauseButton.isSelected()) {
           		pa.pause();
           		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.clickedpause);
           		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
           	}
           	if(Total_Frame.panel3.playButton.isSelected()) {
           		pa.play();
           		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.pauseImage);
           		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.clickedplay);
           	}
           	if(Panel_3.dispose) {
           		Total_Frame.panel2.button5.setSelected(false);
           		Panel_3.dispose = false;
           		Total_Frame.panel3.pauseButton.setIcon(Total_Frame.panel3.pauseImage);
           		Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
           		break;
           	}
            currentTime = (long)pa.getCurrentTime().toMillis();
            Total_Frame.panel3.curtime.setText(curTime());
                
            Total_Frame.panel3.voicePlayBar.setValue((int)currentTime);
       }
            
        pa.stop();
        Total_Frame.panel3.voicePlayBar.setValue(0);
        Total_Frame.panel3.playButton.setIcon(Total_Frame.panel3.playImage);
            
        Total_Frame.panel3.curtime.setText("00:00");
        if(Total_Frame.panel2.button5.isSelected()) {
       	   check = false;
       	   pa.dispose();
       	   playAudio(audio);
            	
        }
        else {
           	check = false;
           	Panel_1.valueCount = false;
           	Total_Frame.panel3.totaltime.setText("00:00");
           	pa.dispose();
           	Thread.currentThread().interrupt();
        }
	}
	
	public static String totalTime() {
		int seconds = (int)totalTime / 1000;
		int minutes = 0;
		
		minutes = seconds / 60;
		if(minutes > 0) seconds = seconds % 60;
			
		return String.format("%02d:%02d", minutes, seconds);
	}

	public static String curTime() {
		int seconds = (int)currentTime / 1000;
		int minutes = 0;
		
		minutes = seconds / 60;
		if(minutes > 0) seconds = seconds % 60;
			
		return String.format("%02d:%02d", minutes, seconds);
	}
}