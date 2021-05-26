package workTTS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel_2 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final JButton button1;
	private final JButton button2;
	private final JButton button3;
	private final JButton button4;
	private final JButton button5;
	
	public Panel_2() {
		setLayout(null);
		
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();
		
		button1.setBounds(15,25,150,50);
		button2.setBounds(15,100,150,50);
		button3.setBounds(15,175,150,50);
		button4.setBounds(15,250,150,50);	
		button5.setBounds(15,325,150,50);
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		
		EventHandler handler = new EventHandler();
		button1.addActionListener(handler);
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
			
		}
	}
}
