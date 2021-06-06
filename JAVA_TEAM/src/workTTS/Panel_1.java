package workTTS;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Panel_1 extends JPanel {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private final JList<String> textToSpeechList;
		public static String[] storedTTS = new String[30];    // �ִ� 30���� ����
		public static int count = -1;                         // ����� �ؽ�Ʈ�� ��
		public static boolean valueCount = false;
		
		public Panel_1() {
			setLayout(null);
			
			textToSpeechList = new JList<String>(storedTTS);  // ����� �ؽ�Ʈ�� List�� ����
			
			textToSpeechList.setVisibleRowCount(20);          // �ִ� ���� �� 20�� ����
			textToSpeechList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // ����Ʈ���� �ϳ����� ����
			textToSpeechList.setFixedCellHeight(19);
			textToSpeechList.setFixedCellWidth(1000);
			
			JScrollPane scrollPane = new JScrollPane(textToSpeechList);
			scrollPane.setBounds(0,0,300,400);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scrollPane);           // List�� ��ũ�� ����
			
			textToSpeechList.addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							if(e.getSource() == textToSpeechList && !valueCount) {
								if(textToSpeechList.getSelectedIndex() <= count) {
									valueCount = true;
									AudioPlayer.playAudio(new File(Total_Frame.directoryPath.toString()+
											"\\TTS\\"+textToSpeechList.getSelectedIndex()+".mp3"));
									textToSpeechList.updateUI();
								}
							}
							
						}});
		}
		
	public static void UpdateTextList(Panel_1 panel, Panel_3 panel3) 
			throws IOException, UnsupportedAudioFileException {
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Total_Frame.directoryPath);
		
		for(int i=0; i<=count ; i++) {  // ����Ʈ ������Ʈ �� ���� �ʱ�ȭ
			storedTTS[i] = "";
		}
		count = -1;  // count ���� �ʱ�ȭ ������ ������ ���� �ִ� ����Ʈ���� ������ ����!
		
		File folder = new File(Total_Frame.directoryPath+"\\TTS");
		if (!folder.exists()) {
			FileManager.createFolder(folder);
		}
		
		for(Path p : directoryStream) {
			
			if(!Files.isDirectory(p)) {
				String fileExtension = FileManager.getFileExtension(p);// ���� Ȯ���� �������� �Լ�
				StringBuilder builder = null;
				if(fileExtension.equals("txt")) {          // ���� Ȯ���ڰ� txt�� ��� �ؽ�Ʈ������ ������
					builder = SetBuilder(p);               // SetBuilder method�� builder ����
					
					if(builder.toString().equals("")) break;  // �ؽ�Ʈ ������ ������ ���� ��� �������� ����
					storedTTS[++count] = builder.toString();  // storedTTS ��ü�� ����
					
					FileManager.checkFile(count);
				}				
			}
		}
		
		panel.textToSpeechList.updateUI(); // List ������Ʈ�ؼ� ���� ����
		int listcount = count+1;
		panel3.listCount.setText("����Ʈ �� "+listcount+" / 30"); // ����Ʈ �� ��Ÿ���� �� ������Ʈ
		panel3.listCount.updateUI();	
		
	}
	
	public static StringBuilder SetBuilder(Path path) throws IOException {  // �ؽ�Ʈ ������ �ִ� builder ����
		StringBuilder builder = new StringBuilder();          // �ؽ�Ʈ ������ ������ builder

		FileInputStream fileStream = null;
		fileStream = new FileInputStream(path.toString());    // p����� fileStream (FileInputStream) ����
		
		byte readBuffer[] = new byte[fileStream.available()]; // ����Ʈ ������ readBuffer�� fileStream ũ�⸸ŭ ����
		
		while(fileStream.read(readBuffer) != -1);             // end of file ���� read
		builder.append(new String(readBuffer, "UTF-8"));      // builder�� �ٿ��ֱ�
		
		fileStream.close();                                   // ��ü ��� �� ��Ʈ�� �ݱ�
		
		return builder;
	}

}