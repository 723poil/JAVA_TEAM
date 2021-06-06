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
	
	private final JButton resetPath; // 경로 재설정해주는 버튼
	private final JButton button2;
	private final JButton button3;
	private final JButton defaultList;
	private final JToggleButton button5;
	private final Panel_1 panel1;
	private final Panel_3 panel3;
	
	public Panel_2(Panel_1 panel1, Panel_3 panel3) { // 패널1을 가져와서 리스트 설정할때 도움을 줌
		setLayout(null);
		
		resetPath = new JButton("경로 재설정");    // 경로 재설정 버튼
		button2 = new JButton("리스트 전체 삭제");  // 버튼 클릭 시 재확인 창이 뜨면 좋겠음 
		button3 = new JButton("리스트 삭제");      // 1개씩 위에서 부터 삭제 되도록 (mp3파일도 같이 삭제 되어야함)
		defaultList = new JButton("리스트 초기화");
		button5 = new JToggleButton("음성 반복 재생", false);
		this.panel1 = panel1;                   // 패널1 선언
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
			if(event.getSource() == resetPath) { // 경로 재설정 버튼을 클릭했을 경우
				Total_Frame.directoryPath = Total_Frame.getDirectoryPath();   // Total_Frame의 변수와 method를 가져옴
				try {
					if(!Total_Frame.directoryPath.toString().equals("")) {
						Panel_1.UpdateTextList(panel1, panel3);   // 리스트 업데이트 함수
					}                                     // 여기있는 panel1이 생성자로 가져온 변수
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(event.getSource() == defaultList) { // GUI상의 내용만 지우는 것 말고 텍스트 파일과 mp3파일을 같이 지워줘야 함!
				for(int i=0; i<=Panel_1.count ; i++) {  // 리스트 업데이트 전 내용 초기화
					Panel_1.storedTTS[i] = "";
				}
				Panel_1.count = -1;
				
				panel1.updateUI();
				// 텍스트파일과 mp3파일 지우는 함수 생성
			}
		}
	}
}
