package pers.hanami.MineSweep.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import pers.hanami.MineSweep.Main;
import pers.hanami.MineSweep.game.Lists;
import pers.hanami.MineSweep.game.Map;

@SuppressWarnings("serial")
public class Gaming extends JPanel implements MouseListener {

	private int r, c, m;
	private ArrayList<ArrayList<MyButtonS>> buttons;
	protected Lists lists;
	static GridLayout gridLayout = new GridLayout();

	public Gaming() {

		setLayout(gridLayout);
	}

	public void start() {
		removeAll();
		r = Map.getRow();
		c = Map.getCol();
		m = Map.getMines();
		gridLayout.setRows(r);
		gridLayout.setColumns(c);
		buttons = new ArrayList<ArrayList<MyButtonS>>();
		Main.getMainFrame().setSize(c * 50, r * 50 + 70);
		for (int i = 0; i < r; i++) {
			ArrayList<MyButtonS> buttons = new ArrayList<MyButtonS>();
			for (int j = 0; j < c; j++) {
				MyButtonS b = new MyButtonS();
				buttons.add(b);
				b.addMouseListener(this);
				add(b);
			}
			this.buttons.add(buttons);
		}
		lists = new Lists();
	}

	public MyButtonS getButton(int i, int j) {
		return buttons.get(i).get(j);
	}

	public void superK(MyButtonS b, int i, int j) {
		if (!b.getText().equals(""))
			return;
		int n = lists.getAround(i, j);
		b.setNor(n);
		if (n == 0) {
			if (i - 1 >= 0 && j - 1 >= 0)
				superK(buttons.get(i - 1).get(j - 1), i - 1, j - 1);
			if (i - 1 >= 0)
				superK(buttons.get(i - 1).get(j), i - 1, j);
			if (i - 1 >= 0 && j + 1 < c)
				superK(buttons.get(i - 1).get(j + 1), i - 1, j + 1);
			if (j - 1 >= 0)
				superK(buttons.get(i).get(j - 1), i, j - 1);
			if (j + 1 < c)
				superK(buttons.get(i).get(j + 1), i, j + 1);
			if (i + 1 < r && j - 1 >= 0)
				superK(buttons.get(i + 1).get(j - 1), i + 1, j - 1);
			if (i + 1 < r)
				superK(buttons.get(i + 1).get(j), i + 1, j);
			if (i + 1 < r && j + 1 < c)
				superK(buttons.get(i + 1).get(j + 1), i + 1, j + 1);
		}
	}

	public boolean judge() {
		if (Main.getMainFrame().staBar.getMines() == m) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (buttons.get(i).get(j).getText().equals("P") && !lists.getMine(i, j)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public void buttonTurn(MyButtonS b, int r, int c) {

		if (b.getText().equals("P") || r >= this.r || c >= this.c || r < 0 || c < 0)
			return;
		if (lists.getMine(r, c)) {
			b.setMine();
			gameOver();
		} else
			superK(b, r, c);
	}

	public void midJudge(MyButtonS b, int i, int j) {
		if(b.getText().equals("") || b.getText().equals("P") || b.getText().equals("M") || b.getText().equals("?") || b.getText().equals("0"))
			return;
		int n = 0;
		if (i - 1 >= 0 && j - 1 >= 0 && buttons.get(i - 1).get(j - 1).getText().equals("P"))
			n++;
		if (i - 1 >= 0 && buttons.get(i - 1).get(j).getText().equals("P"))
			n++;
		if (i - 1 >= 0 && j + 1 < c && buttons.get(i - 1).get(j + 1).getText().equals("P"))
			n++;
		if (j - 1 >= 0 && buttons.get(i).get(j - 1).getText().equals("P"))
			n++;
		if (j + 1 < c && buttons.get(i).get(j + 1).getText().equals("P"))
			n++;
		if (i + 1 < r && j - 1 >= 0 && buttons.get(i + 1).get(j - 1).getText().equals("P"))
			n++;
		if (i + 1 < r && buttons.get(i + 1).get(j).getText().equals("P"))
			n++;
		if (i + 1 < r && j + 1 < c && buttons.get(i + 1).get(j + 1).getText().equals("P"))
			n++;
		if (n == lists.getAround(i, j)) {
			if (i - 1 >= 0 && j - 1 >= 0)
				buttonTurn(buttons.get(i - 1).get(j - 1), i - 1, j - 1);
			if (i - 1 >= 0)
				buttonTurn(buttons.get(i - 1).get(j), i - 1, j);
			if (i - 1 >= 0 && j + 1 < c)
				buttonTurn(buttons.get(i - 1).get(j + 1), i - 1, j + 1);
			if (j - 1 >= 0)
				buttonTurn(buttons.get(i).get(j - 1), i, j - 1);
			if (j + 1 < c)
				buttonTurn(buttons.get(i).get(j + 1), i, j + 1);
			if (i + 1 < r && j - 1 >= 0)
				buttonTurn(buttons.get(i + 1).get(j - 1), i + 1, j - 1);
			if (i + 1 < r)
				buttonTurn(buttons.get(i + 1).get(j), i + 1, j);
			if (i + 1 < r && j - 1 < c)
				buttonTurn(buttons.get(i + 1).get(j + 1), i + 1, j + 1);
		}
	}
	
	public void gameOver() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (buttons.get(i).get(j).getText().equals("P")) {
					if (!lists.getMine(i, j)) {
						buttons.get(i).get(j).setForeground(MyFont.M);
						buttons.get(i).get(j).setText("X");
					}
				} else if (lists.getMine(i, j)) {
					buttons.get(i).get(j).setMine();
				}
			}
		}
		new MyDialog("Game Over");

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (e.getSource() == buttons.get(i).get(j)) {
					MyButtonS b = buttons.get(i).get(j);
					String s = b.getText();
					int c = e.getButton();
					if (c == MouseEvent.BUTTON1) {
						buttonTurn(b, i, j);
					}
					if (c == MouseEvent.BUTTON2) {
						midJudge(b, i, j);
					}
					if (c == MouseEvent.BUTTON3) {
						if (s.equals("")) {
							b.setFlag();
							Main.getMainFrame().staBar.mineAdd();
							if (judge()) {
								new MyDialog("<html><body><p align=\"center\">Congratulation !<br/>Times: "
										+ Main.getMainFrame().staBar.getTimes() + " s</p></body></html>");
							}
						}
						if (s.equals("P")) {
							b.setQue();
							Main.getMainFrame().staBar.mineCut();
						}
						if (s.equals("?"))
							b.setNew();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals(""))
			b.setBackground(MyFont.CHOOSE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		if (!(b.getText().equals("M") || b.getText().equals("P") || b.getText().equals("?") || b.getText().equals("0")))
			b.setBackground(MyFont.B);
	}
}

@SuppressWarnings("serial")
class MyButtonS extends JButton {
	public MyButtonS() {
		setFont(MyFont.FONTB);
		setBackground(MyFont.B);
	}

	public void setMine() {
		setText("M");
		setBackground(MyFont.M);
	}

	public void setFlag() {
		setText("P");
		setBackground(MyFont.P);
	}

	public void setNor(int n) {
		ArrayList<Color> colors = new NumColor();
		if (n == 0) {
			setBackground(MyFont.ZERO);
		}
		setText(String.valueOf(n));
		setForeground(colors.get(n));
	}

	public void setQue() {
		setText("?");
		setBackground(MyFont.Q);
	}

	public void setNew() {
		setText("");
		setBackground(MyFont.B);
	}
}
