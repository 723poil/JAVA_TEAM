

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Total_Frame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Path directoryPath = Paths.get("");

	public static void main(String[] args) {
		
		JFrame totalFrame = new JFrame("Panel Test");
		Panel_1 Panel1 = new Panel_1();
		JPanel subpanel = new JPanel();
		
		Dimension panelDm = new Dimension(300, 740);
		
		Panel1.setPreferredSize(panelDm);
		Panel1.setBounds(5, 0, 320, 740);
		totalFrame.add(Panel1);
		
		subpanel.setBounds(510, 0, 150, 740);
		totalFrame.add(subpanel);
		
		
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // frame 창 실행
		totalFrame.setSize(500, 650);
		totalFrame.setVisible(true);
		totalFrame.setLocationRelativeTo(null);
		totalFrame.setResizable(false);		
		
		totalFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) { // 창이 실행 될 경우
				directoryPath = getDirectoryPath();   // 디렉토리 경로를 구하는 함수
				try {
					Panel_1.UpdateTextList(Panel1);   // 리스트 업데이트 함수
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static Path getDirectoryPath() {           // 디렉토리 경로 구하는 함수
		JFileChooser fileChooser = new JFileChooser();          // FileChooser 선언
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // 디렉토리만 선택할 수 있도록 설정
		fileChooser.showOpenDialog(fileChooser);                          // 대화창 열기
		
		return fileChooser.getSelectedFile().toPath();                    // 경로값 리턴
	}

}
