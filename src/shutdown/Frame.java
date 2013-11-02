package shutdown;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel leftTime;
	private Timer timer;
	private int seconds = 60;
	private int minutes = 1;
	private JButton btnNewButton_1;
	boolean timerIsStarted = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Shutdown Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 79);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Zeit in Minuten");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startCountdown();
			}
		});
		
		panel.add(btnNewButton);
		
		leftTime = new JLabel("0");
		panel.add(leftTime);
		
		btnNewButton_1 = new JButton("Abbrechen");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelCountdown();
			}
		});
		
		panel.add(btnNewButton_1);
	}

	private void startCountdown()
	{
		if (this.timerIsStarted == false) {
			String time = this.getTextField().getText();
			time = time.replaceAll(",", ".");
			this.getLeftTime().setText(time);
			countdown(Math.round(Float.parseFloat(time)));
		}
	}
	
	private void countdown(int startTime) {
		final Frame frame = this;
		int delay = 1000;
		frame.seconds = 60;
		if (startTime != 0) {
			frame.minutes = startTime -1;
			timer = new Timer();
	        
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	            	frame.timerIsStarted = true;
	            	frame.seconds -= 1;
	    			getLeftTime().setText(frame.minutes + ":" + frame.seconds);
	    			if (frame.seconds == 0 && frame.minutes == 0) {
	    				timer.cancel();
	    				frame.timerIsStarted = false;
	    				frame.initalizeShutdown();
	    			}
	    			if (frame.seconds == 0) {
	    				frame.minutes -= 1; 
	    				frame.seconds = 60;
	    			}
	    			
	            }
	        }, 0, delay);
		} else {
			initalizeShutdown();
		}
        
	}
	
	private void cancelCountdown() 
	{
		if (this.timerIsStarted == true) {
			timer.cancel();
		}
		
	}
	
	private void initalizeShutdown() {
		try {
			Start.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JTextField getTextField() {
		return textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JLabel getLeftTime() {
		return leftTime;
	}

}
