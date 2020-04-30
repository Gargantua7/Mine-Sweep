package pers.hanami.MineSweep.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StaBar extends JPanel implements Runnable{

	JLabel time, mine;
	private int times = 0, mines = 0, max;
	private boolean sta;
	
	public StaBar() {
		
		setLayout(new GridLayout(1, 3));
		time = new JLabel("Time: " + times);
		mine = new JLabel(mines + "/" + max, JLabel.RIGHT);
		
		time.setFont(MyFont.FONTB);
		mine.setFont(MyFont.FONTB);
		add(time);
		add(mine);
	}
	
	@Override
	public void run() {
		sta = false;
		time.setText("Times: 0");
		while(true) {
			int con = 0;
			while(con++ < 10) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mine.setText(mines + "/" + max);
				if(sta)
					break;
			}
			if(sta)
				break;
			times++;
			time.setText("Times: " + times);
		}
	}

	public void reset(int mines) {
		this.mines = 0;
		times = 0;
		this.max = mines;
	}
	
	public void mineAdd() {
		mines++;
	}
	
	public void mineCut() {
		mines--;
	}
	
	public int getMines() {
		return mines;
	}
	
	public int getTimes() {
		return times;
	}
	
	public void stop() {
		sta = true;
	}
}
