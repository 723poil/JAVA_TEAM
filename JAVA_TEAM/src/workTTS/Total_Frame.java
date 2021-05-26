package workTTS;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Total_Frame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Path directoryPath = Paths.get("");

	public static void main(String[] args) {
		
		JFrame totalFrame = new JFrame("Working TTS"); // Frame 생성
		
		Panel_1 panel1 = new Panel_1(); // 리스트 패널 생성
		Panel_2 panel2 = new Panel_2(); // 버튼 패널 생성
		Panel_3 panel3 = new Panel_3(); // 텍스트필드 패널 생성
		
		totalFrame.setBounds(100, 100, 500, 650);
		
		panel1.setBounds(5, 0, 300, 400);		
		panel2.setBounds(305, 0, 195, 400);			
		panel3.setBounds(5, 405, 490, 250);
		
		totalFrame.add(panel1);
		totalFrame.add(panel2);
		totalFrame.add(panel3);
		
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // frame 창 실행
		totalFrame.setVisible(true);
		totalFrame.setLocationRelativeTo(null);
		totalFrame.setResizable(false);		
		
		totalFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) { // 창이 실행 될 경우
				directoryPath = getDirectoryPath();   // 디렉토리 경로를 구하는 함수
				try {
					if(!directoryPath.toString().equals("")) {
						Panel_1.UpdateTextList(panel1);   // 리스트 업데이트 함수
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static Path getDirectoryPath() {                               // 디렉토리 경로 구하는 함수
		JFileChooser fileChooser = new JFileChooser();                    // FileChooser 선언
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // 디렉토리만 선택할 수 있도록 설정
		int result = fileChooser.showOpenDialog(fileChooser);             // 대화창 열기
		if(result == JFileChooser.CANCEL_OPTION) return Paths.get("");    // 취소 클릭 시 빈 경로 반환
		
		return fileChooser.getSelectedFile().toPath();                    // 경로값 리턴
	}

}
