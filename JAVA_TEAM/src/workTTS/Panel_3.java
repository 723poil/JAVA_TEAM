package workTTS;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_3 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// panel3 ������Ʈ
	private final JTextField textField;       // �ؽ�Ʈ�� �Է��� ����
	private final JButton buttonText;         // �ؽ�Ʈ�� �Է��ϰ� �ؽ�Ʈ ���Ͽ� ������ �� ���
	
	public Panel_3() {
		
		setLayout(null);
		
		textField = new JTextField(70);
		textField.setBounds(8, 405, 400, 60);
		add(textField);
		
		buttonText = new JButton("����");
		buttonText.setBounds(418, 405, 60, 60);
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
			
		}
	}
}
