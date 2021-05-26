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
		
		JFrame totalFrame = new JFrame("Working TTS"); // Frame ����
		
		Panel_1 panel1 = new Panel_1(); // ����Ʈ �г� ����
		Panel_2 panel2 = new Panel_2(); // ��ư �г� ����
		Panel_3 panel3 = new Panel_3(); // �ؽ�Ʈ�ʵ� �г� ����
		
		totalFrame.setBounds(100, 100, 500, 650);
		
		panel1.setBounds(5, 0, 300, 400);		
		panel2.setBounds(305, 0, 195, 400);			
		panel3.setBounds(5, 405, 490, 250);
		
		totalFrame.add(panel1);
		totalFrame.add(panel2);
		totalFrame.add(panel3);
		
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // frame â ����
		totalFrame.setVisible(true);
		totalFrame.setLocationRelativeTo(null);
		totalFrame.setResizable(false);		
		
		totalFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) { // â�� ���� �� ���
				directoryPath = getDirectoryPath();   // ���丮 ��θ� ���ϴ� �Լ�
				try {
					if(!directoryPath.toString().equals("")) {
						Panel_1.UpdateTextList(panel1);   // ����Ʈ ������Ʈ �Լ�
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static Path getDirectoryPath() {                               // ���丮 ��� ���ϴ� �Լ�
		JFileChooser fileChooser = new JFileChooser();                    // FileChooser ����
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // ���丮�� ������ �� �ֵ��� ����
		int result = fileChooser.showOpenDialog(fileChooser);             // ��ȭâ ����
		if(result == JFileChooser.CANCEL_OPTION) return Paths.get("");    // ��� Ŭ�� �� �� ��� ��ȯ
		
		return fileChooser.getSelectedFile().toPath();                    // ��ΰ� ����
	}

}
