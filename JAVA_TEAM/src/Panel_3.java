

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel_3 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	// panel3 컴포넌트
	private final JTextField textField;       // 텍스트를 입력할 공간
	private final JButton buttonText;         // 텍스트를 입력하고 텍스트 파일에 저장할 때 사용
	
	public Panel_3() {
		
		setLayout(null);
		
		textField = new JTextField(70);
		textField.setBounds(0, 0, 410, 40);
		add(textField);
		
		buttonText = new JButton("저장");
		buttonText.setBounds(415, 0, 60, 40);
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
