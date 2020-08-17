package pers.gargantua.mine_sweep;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import static java.lang.Thread.sleep;

public final class StaBar extends JPanel{

	private static StaBar staBar = null;
	
	JLabel time, mine;
	public int times = 0, mines = 0;
	private boolean sta;
	
	public static StaBar getStaBar() {
		if(staBar == null)
			staBar = new StaBar();
		return staBar;
	}
	
	private StaBar() {
		
		setLayout(new GridLayout(1, 3));
		time = new JLabel("" + times);
		mine = new JLabel("", JLabel.RIGHT);
		
		time.setFont(MyStyle.FONTB);
		mine.setFont(MyStyle.FONTB);
		add(time);
		add(mine);
	}
	
	public void run() {
		new Thread(() -> {
			sta = false;
			time.setText("Times: 0");
			while (true) {
				int con = 0;
				while (con++ < 10) {
					try {
						//noinspection BusyWait
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mine.setText(mines + "/" + Maps.getMaps().config.m);
					if (sta)
						break;
				}
				if (sta)
					break;
				times++;
				time.setText("Times: " + times);
			}
		}).start();
	}

	public void stop() {
		sta = false;
	}
}
