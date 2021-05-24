

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
		public static String[] storedTTS = new String[30];  // 최대 30개로 설정
		public static int count = -1;                       // 저장된 텍스트의 수
		
		public Panel_1() {
			textToSpeechList = new JList<String>(storedTTS);  // 저장된 텍스트를 List에 삽입
			
			textToSpeechList.setVisibleRowCount(25);          // 최대 행의 수 25로 설정
			textToSpeechList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // 리스트에서 하나씩만 선택
			textToSpeechList.setBounds(0, 0, 340, 700);
			textToSpeechList.setFixedCellHeight(20);
			textToSpeechList.setFixedCellWidth(300);
			add(new JScrollPane(textToSpeechList));           // List에 스크롤 부착
			
			textToSpeechList.addListSelectionListener(
					new ListSelectionListener() {

						@Override
						public void valueChanged(ListSelectionEvent e) {
							// 음성 재생 method 기입
							
						}});
		}
		
	public static void UpdateTextList(Panel_1 panel) throws IOException {
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Total_Frame.directoryPath);
		
		for(Path p : directoryStream) {
			if(!Files.isDirectory(p)) {
				StringBuilder builder = new StringBuilder();    // 텍스트 내용을 저장할 builder
				
				FileInputStream fileStream = null;
				fileStream = new FileInputStream(p.toString()); // p경로의 fileStream (FileInputStream) 생성
				
				byte readBuffer[] = new byte[fileStream.available()]; // 바이트 형식의 readBuffer를 fileStream 크기만큼 저장
				
				while(fileStream.read(readBuffer) != -1);             // end of file 까지 read
				builder.append(new String(readBuffer, "UTF-8"));      // builder에 붙여주기
				
				fileStream.close();                                   // 객체 사용 후 스트림 닫기
				
				if(builder.toString().equals("")) break;              // 텍스트 파일의 내용이 없을 경우 갱신하지 않음
				storedTTS[++count] = builder.toString();              // storedTTS 객체에 저장
			}
		}
		panel.textToSpeechList.updateUI();                            // List 업데이트해서 내용 갱신
	}
}