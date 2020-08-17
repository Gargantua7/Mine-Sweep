package pers.gargantua.mine_sweep;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class Start extends JPanel{

	private static Start start = null;
	
	private final JTextField row;
	private final JTextField column;
	private final JTextField mines;
	
	
	public static Start getStart() {
		if(start == null)
			start = new Start();
		return start;
	}
	
	private Start() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints first = new GridBagConstraints();
		GridBagConstraints last = new GridBagConstraints();
		
		setLayout(gridBagLayout);
		
		first.fill = GridBagConstraints.BOTH;
		last.fill = GridBagConstraints.BOTH;
		last.gridwidth = GridBagConstraints.REMAINDER;
		
		JLabel r = new MyLabel("Row :", gridBagLayout, first);
		JLabel c = new MyLabel("Column :", gridBagLayout, first);
		JLabel m = new MyLabel("Mines :", gridBagLayout, first);
		
		row = new MyText("", gridBagLayout, last);
		column = new MyText("", gridBagLayout, last);
		mines = new MyText("", gridBagLayout, last);
		
		JButton easy = new MyButton("EASY", gridBagLayout, first);
		JButton ord = new MyButton("MIDDLE", gridBagLayout, first);
		JButton hard = new MyButton("HARD", gridBagLayout, last);
		JButton startButton = new MyButton("Game Start!", gridBagLayout, last);
		
		easy.addActionListener((ActionEvent e) -> setting(Config.EASY));
		ord.addActionListener((ActionEvent e) -> setting(Config.ORD));
		hard.addActionListener((ActionEvent e) -> setting(Config.HEAD));
		startButton.addActionListener((ActionEvent e) -> {
			int row;
			int column;
			int mine;
			try {
				row = Integer.parseInt(this.row.getText());
				column = Integer.parseInt(this.column.getText());
				mine = Integer.parseInt(mines.getText());
			}catch (NumberFormatException e1) {
				new MyDialog("Invalid Value", "ERROR", false);
				return;
			}
			if (!(this.row.getText().equals("") || this.column.getText().equals("") || mines.getText().equals(""))) {
				if(row < 1 || column < 1){
					new MyDialog("Too few rows or columns", "ERROR", false);
				} else if(mine < 1){
					new MyDialog("Too few mines", "ERROR", false);
				} else if (row * column < mine) {
					new MyDialog("Too many mines", "ERROR", false);
				} else {
					MainFrame.getMainFrame().cardLayout.show(MainFrame.getMainFrame().mainPanel, "gaming");
					Maps.getMaps().config = new Config(row, column, mine);
					Gaming.getGaming().start();
					StaBar.getStaBar().mines = 0;
					StaBar.getStaBar().times = 0;
					StaBar.getStaBar().run();
				}
			}
		});
		
		add(r);
		add(row);
		add(c);
		add(column);
		add(m);
		add(mines);
		add(easy);
		add(ord);
		add(hard);
		add(startButton);
		
	}

	public void setting(Config config) {
		row.setText(Integer.toString(config.r));
		column.setText(Integer.toString(config.c));
		mines.setText(Integer.toString(config.m));
	}
}

