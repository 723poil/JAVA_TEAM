package workTTS;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			
		}
	}
	
	public Icon makeIcon(String imageName) {  // 아이콘 생성 method
		return new ImageIcon(getClass().getResource(imageName));
	}
}
