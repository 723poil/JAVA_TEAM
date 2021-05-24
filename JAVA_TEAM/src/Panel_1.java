

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

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
		public static String[] storedTTS = new String[30];  // �ִ� 30���� ����
		public static int count = -1;                       // ����� �ؽ�Ʈ�� ��
		
		public Panel_1() {
			textToSpeechList = new JList<String>(storedTTS);  // ����� �ؽ�Ʈ�� List�� ����
			
			textToSpeechList.setVisibleRowCount(25);          // �ִ� ���� �� 25�� ����
			textToSpeechList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // ����Ʈ���� �ϳ����� ����
			textToSpeechList.setBounds(0, 0, 340, 700);
			textToSpeechList.setFixedCellHeight(20);
			textToSpeechList.setFixedCellWidth(300);
			add(new JScrollPane(textToSpeechList));           // List�� ��ũ�� ����
			
			textToSpeechList.addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							// ���� ��� method ����
							
						}});
		}
		
	public static void UpdateTextList(Panel_1 panel) throws IOException {
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Total_Frame.directoryPath);
		
		for(Path p : directoryStream) {
			if(!Files.isDirectory(p)) {
				StringBuilder builder = new StringBuilder();    // �ؽ�Ʈ ������ ������ builder
				
				FileInputStream fileStream = null;
				fileStream = new FileInputStream(p.toString()); // p����� fileStream (FileInputStream) ����
				
				byte readBuffer[] = new byte[fileStream.available()]; // ����Ʈ ������ readBuffer�� fileStream ũ�⸸ŭ ����
				
				while(fileStream.read(readBuffer) != -1);             // end of file ���� read
				builder.append(new String(readBuffer, "UTF-8"));      // builder�� �ٿ��ֱ�
				
				fileStream.close();                                   // ��ü ��� �� ��Ʈ�� �ݱ�
				
				if(builder.toString().equals("")) break;              // �ؽ�Ʈ ������ ������ ���� ��� �������� ����
				storedTTS[++count] = builder.toString();              // storedTTS ��ü�� ����
			}
		}
		panel.textToSpeechList.updateUI();                            // List ������Ʈ�ؼ� ���� ����
	}
}