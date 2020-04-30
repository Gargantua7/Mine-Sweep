package pers.hanami.MineSweep.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pers.hanami.MineSweep.Main;
import pers.hanami.MineSweep.game.Map;


@SuppressWarnings("serial")
public class Start extends JPanel implements ActionListener{

	JLabel r, c, m;
	JTextField row, column, mines;
	JButton start,easy, mid, hard;
	
	public Start() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints first = new GridBagConstraints();
		GridBagConstraints last = new GridBagConstraints();
		
		setLayout(gridBagLayout);
		
		first.fill = GridBagConstraints.BOTH;
		last.fill = GridBagConstraints.BOTH;
		last.gridwidth = GridBagConstraints.REMAINDER;
		
		r = new MyLabel("Row :", gridBagLayout, first);
		c = new MyLabel("Column :", gridBagLayout, first);
		m = new MyLabel("Mines :", gridBagLayout, first);
		
		row = new MyText("", gridBagLayout, last);
		column = new MyText("", gridBagLayout, last);
		mines = new MyText("", gridBagLayout, last);
		
		easy = new MyButton("EASY", gridBagLayout, first);
		mid = new MyButton("MIDDLE", gridBagLayout, first);
		hard = new MyButton("HARD", gridBagLayout, last);
		start = new MyButton("Game Start!", gridBagLayout, last);
		
		easy.addActionListener(this);
		mid.addActionListener(this);
		hard.addActionListener(this);
		start.addActionListener(this);
		
		add(r);
		add(row);
		add(c);
		add(column);
		add(m);
		add(mines);
		add(easy);
		add(mid);
		add(hard);
		add(start);
		
	}

	protected void setting(int r, int c, int m) {
		row.setText(Integer.toString(r));
		column.setText(Integer.toString(c));
		mines.setText(Integer.toString(m));
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == easy)
			setting(8, 8, 10);
		if (e.getSource() == mid)
			setting(16, 16, 40);
		if (e.getSource() == hard)
			setting(16, 32, 99);
		if (e.getSource() == start) {
			boolean res = true;
			try {
				Integer r = new Integer(Integer.valueOf(row.getText()));
				Integer c = new Integer(Integer.valueOf(column.getText()));
				Integer m = new Integer(Integer.valueOf(mines.getText()));
			}catch (NumberFormatException e1) {
				res = false;
				new MyDialog("Invalid Value", "ERROR", false);
			}
			if (res && !(row.getText().equals("") || column.getText().equals("") || mines.getText().equals(""))) {
				if(Integer.valueOf(row.getText()) < 1 || Integer.valueOf(column.getText()) < 1){
					new MyDialog("Too few rows or columns", "ERROR", false);
				} else if(Integer.valueOf(mines.getText()) < 1){
					new MyDialog("Too few mines", "ERROR", false);
				} else if (Integer.valueOf(row.getText()) * Integer.valueOf(column.getText()) < Integer.valueOf(mines.getText())) {
					new MyDialog("Too many mines", "ERROR", false);
				} else {
					Main.getMainFrame().switchs("gaming");
					Map.setRow(Integer.valueOf(row.getText()));
					Map.setCol(Integer.valueOf(column.getText()));
					Map.setMines(Integer.valueOf(mines.getText()));
					Main.getMainFrame().gaming.start();
					Main.getMainFrame().staBar.reset(Integer.valueOf(mines.getText()));
					new Thread(Main.getMainFrame().staBar).start();
				}
			}
		}
	}
}

@SuppressWarnings("serial")
class MyLabel extends JLabel{
	public MyLabel(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyFont.FONT);
		g.setConstraints(this, c);
	}
}

@SuppressWarnings("serial")
class MyText extends JTextField{
	public MyText(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyFont.FONT);
		g.setConstraints(this, c);
	}
}

@SuppressWarnings("serial")
class MyButton extends JButton{
	public MyButton(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyFont.FONT);
		setBackground(MyFont.GROUND);
		g.setConstraints(this, c);
	}
}
