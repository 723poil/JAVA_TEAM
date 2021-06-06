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
	
	// panel3 ������Ʈ
	private final JTextField textField;       // �ؽ�Ʈ�� �Է��� ����
	private final JButton buttonText;         // �ؽ�Ʈ�� �Է��ϰ� �ؽ�Ʈ ���Ͽ� ������ �� ���
	public JLabel listCount; 
	private final JButton stopButton;
	private final JButton pauseButton;
	private final JButton playButton;
	private final JProgressBar voicePlayBar;
	private final JLabel mainImage;
	
	public Panel_3() {
		setLayout(null);
		
		listCount = new JLabel("����Ʈ ��");          // ����Ʈ�� �ִ� �ؽ�Ʈ�� �� ǥ��
		stopButton = new JButton();        // ���� ���� ��ư
		pauseButton = new JButton();       // ���� �Ͻ����� ��ư
		playButton = new JButton();        // ���� ��� ��ư
		voicePlayBar = new JProgressBar(); // ���� �����
		mainImage = new JLabel();
		
		Icon playImage = makeIcon("playImage.png");    // ��� ������ ����
		Icon pauseImage = makeIcon("pauseImage.png");  // �Ͻ����� ������ ����
		Icon stopImage = makeIcon("stopImage.png");    // ���� ������ ����
		Icon main = makeIcon("mainImage.png");
		
		playButton.setIcon(playImage);             // ��ư�� set
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
		
		buttonText = new JButton("����");
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
			String string = "";		// text ���� ����
			
			if(event.getSource() == textField || event.getSource() == buttonText) {
				string = textField.getText();
				Panel_1.storedTTS[++Panel_1.count] = string;	// storedTTS ��ü�� ����
				
				try {
					File file = new File(Total_Frame.directoryPath.toString()
							+"\\"+Panel_1.count+".txt");
					setTextFile(file, string);	// �ؽ�Ʈ ���� ���� �� ���Ͽ� �ؽ�Ʈ �߰�
					
					SetFileTTS.create(string, Panel_1.count);	// TTS ���� ����
					AudioPlayer.playAudio(new File(Total_Frame.directoryPath.toString()
							+"\\TTS\\"+Panel_1.count+".mp3"));
					
					Panel_1.UpdateTextList(Total_Frame.panel1, Total_Frame.panel3);	// list ������Ʈ
				} catch (IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
				
				
				textField.setText("");		// textField �ʱ�ȭ
			}
		}
	}
	
	public Icon makeIcon(String imageName) {  // ������ ���� method
		return new ImageIcon(getClass().getResource(imageName));
	}
	
	public void setTextFile(File file, String string) {              	// �ؽ�Ʈ���Ͽ� �Է��� �ؽ�Ʈ �߰�
			try {
				FileWriter fileWriter = new FileWriter(file, false); // �ؽ�Ʈ ���Ͽ� �ٽ� �����ϱ� ���� ��ü ����
				fileWriter.write(string);                    // ������ ��ü�� ���� �ؽ�Ʈ ���Ͽ� ���� ����
				fileWriter.close();                                      // ������ ��ü ����
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
