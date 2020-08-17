package pers.gargantua.mine_sweep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

class MyLabel extends JLabel {
	public MyLabel(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyStyle.FONT);
		g.setConstraints(this, c);
	}
}

class MyText extends JTextField{
	public MyText(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyStyle.FONT);
		g.setConstraints(this, c);
	}
}

class MyButton extends JButton{
	public MyButton(String s, GridBagLayout g, GridBagConstraints c) {
		setText(s);
		setFont(MyStyle.FONT);
		setBackground(MyStyle.GROUND);
		g.setConstraints(this, c);
	}
}

class MyDialog extends JDialog{
	
	public MyDialog(String context) {
		this("Topic", context, 200, 150, true);
	}
	
	public MyDialog(String context, String title, boolean sta) {
		this( title, context, 200, 150,sta);
	}
	
	public MyDialog(String title, String context, int width, int height, boolean sta) {
		
		String str = "OK";
		if(sta) {
			StaBar.getStaBar().stop();
			str = "New Game";
		}
		
		JLabel label = new JLabel(context, JLabel.CENTER);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JScrollPane scroll = new JScrollPane(label);
		scroll.getVerticalScrollBar().setUnitIncrement(20);
		
		JButton button = new JButton(str);
		button.setFont(new Font("SansSerif", Font.PLAIN, 16));
		button.setBackground(Color.WHITE);
		button.addActionListener((ActionEvent e)-> {
			if(e.getSource() == button) {
				this.dispose();
				if(sta) {
					MainFrame.getMainFrame().cardLayout.show(MainFrame.getMainFrame().mainPanel, "start");
					MainFrame.getMainFrame().setSize(300, 230);
				}
			}
		});
		
		setTitle(title);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		setModal(true);
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}

class MyStyle {

	public static Color GROUND = Color.WHITE;
	public static Color B = new Color(221, 221, 221);
	public static Color P = Color.ORANGE;
	public static Color Q = Color.YELLOW;
	public static Color M = Color.RED;
	public static Color ZERO = new Color(110, 110, 110);
	public static Color CHOOSE = new Color(194, 194, 194);
	public static Font FONT = new Font("SansSerif", Font.PLAIN, 16);
	public static Font FONTB = new Font("SansSerif", Font.BOLD, 16);
	public static Font FONTS = new Font("SansSerif", Font.PLAIN, 14);
	
	public static List<Color> NUMBER = Arrays.asList(
			new Color(110, 110, 110),
			Color.BLUE,
			new Color(0, 136, 0),
			Color.RED,
			new Color(0, 34, 102),
			new Color(102, 0, 0),
			new Color(85, 0, 0),
			Color.BLACK,
			Color.GRAY
	);
}
