package workTTS;


import java.io.File;
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
		public static String[] storedTTS = new String[30];    // 최대 30개로 설정
		public static int count = -1;                         // 저장된 텍스트의 수
		
		public Panel_1() {
			textToSpeechList = new JList<String>(storedTTS);  // 저장된 텍스트를 List에 삽입
			
			textToSpeechList.setVisibleRowCount(20);          // 최대 행의 수 20로 설정
			textToSpeechList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // 리스트에서 하나씩만 선택
			textToSpeechList.setBounds(0, 0, 300, 400);
			textToSpeechList.setFixedCellHeight(19);
			textToSpeechList.setFixedCellWidth(280);
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
		
		for(int i=0; i<=count ; i++) {  // 리스트 업데이트 전 내용 초기화
			storedTTS[i] = "";
		}
		count = -1;  // count 값을 초기화 해주지 않으면 원래 있던 리스트에서 덧붙혀 나옴!
		
		for(Path p : directoryStream) {
			if(!Files.isDirectory(p)) {
				String fileExtension = getFileExtension(p);// 파일 확장자 가져오는 함수
				
				StringBuilder builder = null;
				if(fileExtension.equals("txt")) {          // 파일 확장자가 txt일 경우 텍스트내용을 가져옴
					builder = SetBuilder(p);               // SetBuilder method로 builder 생성
					
					if(builder.toString().equals("")) break;  // 텍스트 파일의 내용이 없을 경우 갱신하지 않음
					storedTTS[++count] = builder.toString();  // storedTTS 객체에 저장
				}				
			}
		}
		panel.textToSpeechList.updateUI();                    // List 업데이트해서 내용 갱신
	}
	
	public static StringBuilder SetBuilder(Path path) throws IOException {  // 텍스트 내용이 있는 builder 생성
		StringBuilder builder = new StringBuilder();          // 텍스트 내용을 저장할 builder

		FileInputStream fileStream = null;
		fileStream = new FileInputStream(path.toString());    // p경로의 fileStream (FileInputStream) 생성
		
		byte readBuffer[] = new byte[fileStream.available()]; // 바이트 형식의 readBuffer를 fileStream 크기만큼 저장
		
		while(fileStream.read(readBuffer) != -1);             // end of file 까지 read
		builder.append(new String(readBuffer, "UTF-8"));      // builder에 붙여주기
		
		fileStream.close();                                   // 객체 사용 후 스트림 닫기
		
		return builder;
	}
	
	public static String getFileExtension(Path p) {           // 파일 확장자 가져오기
		File file = new File(p.toString());                   // 파일 생성
		String filename = file.getName();                     // 파일의 이름을 가져오기
		String fileExtension = "";                            // 확장자를 저장할 객체
		
		int i = filename.lastIndexOf('.');                    // . 부분의 인덱스 저장하는 변수
		if(i>0) fileExtension = filename.substring(i+1);      // . 다음부분들을 저장해줌
		
		return fileExtension;                                 // 확장자명 리턴
	}
}