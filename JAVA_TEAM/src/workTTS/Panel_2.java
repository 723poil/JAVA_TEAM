package workTTS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Panel_2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JButton resetPath; // ��� �缳�����ִ� ��ư
	private final JButton button2;
	private final JButton button3;
	private final JButton defaultList;
	private final JToggleButton button5;
	private final Panel_1 panel1;
	private final Panel_3 panel3;
	
	public Panel_2(Panel_1 panel1, Panel_3 panel3) { // �г�1�� �����ͼ� ����Ʈ �����Ҷ� ������ ��
		setLayout(null);
		
		resetPath = new JButton("��� �缳��");    // ��� �缳�� ��ư
		button2 = new JButton("����Ʈ ��ü ����");  // ��ư Ŭ�� �� ��Ȯ�� â�� �߸� ������ 
		button3 = new JButton("����Ʈ ����");      // 1���� ������ ���� ���� �ǵ��� (mp3���ϵ� ���� ���� �Ǿ����)
		defaultList = new JButton("����Ʈ �ʱ�ȭ");
		button5 = new JToggleButton("���� �ݺ� ���", false);
		this.panel1 = panel1;                   // �г�1 ����
		this.panel3 = panel3;
		
		resetPath.setBounds(15,25,150,50);
		button2.setBounds(15,100,150,50);
		button3.setBounds(15,175,150,50);
		defaultList.setBounds(15,250,150,50);	
		button5.setBounds(15,325,150,50);
		
		add(resetPath);
		add(button2);
		add(button3);
		add(defaultList);
		add(button5);
		
		EventHandler handler = new EventHandler();
		resetPath.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		defaultList.addActionListener(handler);
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
						Panel_1.UpdateTextList(panel1, panel3);   // ����Ʈ ������Ʈ �Լ�
					}                                     // �����ִ� panel1�� �����ڷ� ������ ����
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(event.getSource() == defaultList) { // GUI���� ���븸 ����� �� ���� �ؽ�Ʈ ���ϰ� mp3������ ���� ������� ��!
				for(int i=0; i<=Panel_1.count ; i++) {  // ����Ʈ ������Ʈ �� ���� �ʱ�ȭ
					Panel_1.storedTTS[i] = "";
				}
				Panel_1.count = -1;
				
				panel1.updateUI();
				// �ؽ�Ʈ���ϰ� mp3���� ����� �Լ� ����
			}
		}
	}
}
