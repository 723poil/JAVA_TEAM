

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
		
		
		totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // frame â ����
		totalFrame.setSize(500, 650);
		totalFrame.setVisible(true);
		totalFrame.setLocationRelativeTo(null);
		totalFrame.setResizable(false);		
		
		totalFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) { // â�� ���� �� ���
				directoryPath = getDirectoryPath();   // ���丮 ��θ� ���ϴ� �Լ�
				try {
					Panel_1.UpdateTextList(Panel1);   // ����Ʈ ������Ʈ �Լ�
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public static Path getDirectoryPath() {           // ���丮 ��� ���ϴ� �Լ�
		JFileChooser fileChooser = new JFileChooser();          // FileChooser ����
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  // ���丮�� ������ �� �ֵ��� ����
		fileChooser.showOpenDialog(fileChooser);                          // ��ȭâ ����
		
		return fileChooser.getSelectedFile().toPath();                    // ��ΰ� ����
	}

}
