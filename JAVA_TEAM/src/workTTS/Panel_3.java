package workTTS;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
	public final JProgressBar voicePlayBar;
	private final JLabel mainImage;
	
	public Panel_3() {
		setLayout(null); //
		
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
		
		listCount.setBounds(0, 0, 480, 30);
		voicePlayBar.setBounds(0, 30, 300, 30);
		stopButton.setBounds(40, 75, 50, 50);
		playButton.setBounds(120, 75, 50, 50);
		pauseButton.setBounds(200, 75, 50, 50);
		mainImage.setBounds(320, 0, 140, 140);
		
		add(listCount);
		add(voicePlayBar);
		add(stopButton);
		add(playButton);
		add(pauseButton);
		add(mainImage);
		
		textField = new JTextField(70);
		textField.setBounds(3, 145, 400, 50);
		add(textField);
		
		buttonText = new JButton("����");
		buttonText.setBounds(413, 145, 60, 50);
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
				StringBuilder builder = new StringBuilder();
				
				try {
					builder.append(new String(string.getBytes(StandardCharsets.UTF_8), "UTF-8"));
					Panel_1.storedTTS[++Panel_1.count] = builder.toString();	// storedTTS ��ü�� ����
					if(Panel_1.count < 10) {
						File file = new File(Total_Frame.directoryPath.toString()+
								"\\"+"0_"+Panel_1.count+".txt");
						setTextFile(file, builder);	// �ؽ�Ʈ ���� ���� �� ���Ͽ� �ؽ�Ʈ �߰�
					}
					else if(Panel_1.count < 20) {
						File file = new File(Total_Frame.directoryPath.toString()+
								"\\"+"1_"+(Panel_1.count-10)+".txt");
						setTextFile(file, builder);	// �ؽ�Ʈ ���� ���� �� ���Ͽ� �ؽ�Ʈ �߰�
					}
					else {
						File file = new File(Total_Frame.directoryPath.toString()+
								"\\"+"2_"+(Panel_1.count-20)+".txt");
						setTextFile(file, builder);	// �ؽ�Ʈ ���� ���� �� ���Ͽ� �ؽ�Ʈ �߰�
					}
					
					SetFileTTS.create(string, Panel_1.count);	// TTS ���� ����
					AudioPlayer.playAudio(new File(Total_Frame.directoryPath.toString()
							+"\\TTS\\"+Panel_1.count+".mp3"));
					
					Total_Frame.panel1.updateUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				textField.setText("");		// textField �ʱ�ȭ
			}
		}
	}
	
	public Icon makeIcon(String imageName) {  // ������ ���� method
		return new ImageIcon(getClass().getResource(imageName));
	}
	
	public void setTextFile(File file, StringBuilder string) {              	// �ؽ�Ʈ���Ͽ� �Է��� �ؽ�Ʈ �߰�
			try {
				file.createNewFile();
				
				BufferedWriter output = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF-8"));
				
				output.write(string.toString());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
