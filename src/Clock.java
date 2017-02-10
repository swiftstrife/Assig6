import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * 
 */

/**
 * @author BigMac
 *
 */
public class Clock extends JFrame
{
	private int counter = 0;
	private boolean runTimer = false;
	private final int PAUSE = 100;
	private String start = "START";
	private String stop = "STOP";

	public Timer clockTimer;
	public JButton startStopButton;
	public JLabel timeText;
	public JPanel timerPanel;


	public Clock()
	{

		clockTimer = new Timer(1000, timerEvent);

		timeText = new JLabel("" + formatToTime(counter));

		startStopButton = new JButton(start);
		startStopButton.addActionListener(buttonEvent);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 200);
	}


	public String formatToTime(long seconds)
	{
		long s = seconds % 60;
		long m = (seconds / 60) % 60;
		return String.format("%01d:%02d", m, s);
	}


	private ActionListener timerEvent = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			counter++;
			timeText.setText("" + formatToTime(counter));
		}
	};


	private ActionListener buttonEvent = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			TimerClass timerThread = new TimerClass();
			timerThread.start();
		}
	};


	private class TimerClass extends Thread
	{
		public void run()
		{
			if (runTimer)
			{
				startStopButton.setText(start);
				clockTimer.stop();
				runTimer = false;
				timeText.setText("" + formatToTime(counter));
			}
			else if (!runTimer)
			{
				startStopButton.setText(stop);
				clockTimer.start();
				runTimer = true;
				counter = 0;
				timeText.setText("" + formatToTime(counter));
			}
			doNothing(PAUSE);
		}


		public void doNothing(int milliseconds)
		{
			try
			{
				Thread.sleep(milliseconds);
			} catch (InterruptedException e)
			{
				System.out.println("Unexpected interrupt");
				System.exit(0);
			}
		}
	}
}
