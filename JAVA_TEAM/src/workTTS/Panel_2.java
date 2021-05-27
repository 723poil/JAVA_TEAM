package workTTS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JButton resetPath; // ��� �缳�����ִ� ��ư
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;
	private final JButton button5;
	private final Panel_1 panel1;
	
	public Panel_2(Panel_1 panel1) { // �г�1�� �����ͼ� ����Ʈ �����Ҷ� ������ ��
		setLayout(null);
		
		resetPath = new JButton("��� �缳��");  // ��� �缳�� ��ư
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();
		this.panel1 = panel1;        // �г�1 ����
		
		resetPath.setBounds(15,25,150,50);
		button2.setBounds(15,100,150,50);
		button3.setBounds(15,175,150,50);
		button4.setBounds(15,250,150,50);	
		button5.setBounds(15,325,150,50);
		
		add(resetPath);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		
		EventHandler handler = new EventHandler();
		resetPath.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		button4.addActionListener(handler);
		button5.addActionListener(handler);
	}
	
	private class EventHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource() == resetPath) { // ��� �缳�� ��ư�� Ŭ������ ���
				Total_Frame.directoryPath = Total_Frame.getDirectoryPath();   // Total_Frame�� ������ method�� ������
				try {
					if(!Total_Frame.directoryPath.toString().equals("")) {
						Panel_1.UpdateTextList(panel1);   // ����Ʈ ������Ʈ �Լ�
					}                                     // �����ִ� panel1�� �����ڷ� ������ ����
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
