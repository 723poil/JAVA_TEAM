package workTTS;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Panel_3 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// panel3 컴포넌트
	private final JTextField textField;       // 텍스트를 입력할 공간
	private final JButton buttonText;         // 텍스트를 입력하고 텍스트 파일에 저장할 때 사용
	public JLabel listCount; 
	private final JButton stopButton;
	private final JButton pauseButton;
	private final JButton playButton;
	private final JProgressBar voicePlayBar;
	private final JLabel mainImage;
	
	public Panel_3() {
		setLayout(null);
		
		listCount = new JLabel("리스트 수");          // 리스트에 있는 텍스트의 수 표시
		stopButton = new JButton();        // 음성 정지 버튼
		pauseButton = new JButton();       // 음성 일시정지 버튼
		playButton = new JButton();        // 음성 재생 버튼
		voicePlayBar = new JProgressBar(); // 음성 진행바
		mainImage = new JLabel();
		
		Icon playImage = makeIcon("playImage.png");    // 재생 아이콘 생성
		Icon pauseImage = makeIcon("pauseImage.png");  // 일시정지 아이콘 생성
		Icon stopImage = makeIcon("stopImage.png");    // 정지 아이콘 생성
		Icon main = makeIcon("mainImage.png");
		
		playButton.setIcon(playImage);             // 버튼에 set
		pauseButton.setIcon(pauseImage);
		stopButton.setIcon(stopImage);
		mainImage.setIcon(main);
		
		voicePlayBar.setValue(0);
		
		listCount.setBounds(5, 390, 480, 30);
		voicePlayBar.setBounds(5, 430, 300, 30);
		stopButton.setBounds(45, 475, 50, 50);
		playButton.setBounds(125, 475, 50, 50);
		pauseButton.setBounds(205, 475, 50, 50);
		mainImage.setBounds(325, 390, 140, 140);
		
		add(listCount);
		add(voicePlayBar);
		add(stopButton);
		add(playButton);
		add(pauseButton);
		add(mainImage);
		
		textField = new JTextField(70);
		textField.setBounds(8, 545, 400, 60);
		add(textField);
		
		buttonText = new JButton("저장");
		buttonText.setBounds(418, 545, 60, 60);
		add(buttonText);
		
		Handler handler = new Handler();
		textField.addActionListener(handler);
		buttonText.addActionListener(handler);
	}
	
	private class Handler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String string = "";		// text 저장 공간
			
			if(event.getSource() == textField || event.getSource() == buttonText) {
				string = textField.getText();
				Panel_1.storedTTS[++Panel_1.count] = string;	// storedTTS 객체에 저장
				
				try {
					File file = new File(Total_Frame.directoryPath.toString()
							+"\\"+Panel_1.count+".txt");
					setTextFile(file, string);	// 텍스트 파일 생성 및 파일에 텍스트 추가
					
					SetFileTTS.create(string, Panel_1.count);	// TTS 파일 생성
					AudioPlayer.playAudio(new File(Total_Frame.directoryPath.toString()
							+"\\TTS\\"+Panel_1.count+".mp3"));
					
					Panel_1.UpdateTextList(Total_Frame.panel1, Total_Frame.panel3);	// list 업데이트
				} catch (IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				
				
				textField.setText("");		// textField 초기화
			}
		}
	}
	
	public Icon makeIcon(String imageName) {  // 아이콘 생성 method
		return new ImageIcon(getClass().getResource(imageName));
	}
	
	public void setTextFile(File file, String string) {              	// 텍스트파일에 입력한 텍스트 추가
			try {
				FileWriter fileWriter = new FileWriter(file, false); // 텍스트 파일에 다시 저장하기 위해 객체 생성
				fileWriter.write(string);                    // 생성한 객체를 통해 텍스트 파일에 내용 갱신
				fileWriter.close();                                      // 생성된 객체 종료
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
