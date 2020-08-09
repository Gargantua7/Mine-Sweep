package pers.gargantua.mine_sweep.gui.MenuBar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import pers.gargantua.mine_sweep.Main;
import pers.gargantua.mine_sweep.gui.MyFont;

@SuppressWarnings("serial")
public class FileMenu extends JMenu implements ActionListener{

	JMenuItem newGame, exit;
	
	public FileMenu() {
		
		super("File");
		setFont(MyFont.FONTS);
		
		newGame = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		
		newGame.setFont(MyFont.FONTS);
		exit.setFont(MyFont.FONTS);
		
		exit.setForeground(Color.RED);
		
		newGame.addActionListener(this);
		exit.addActionListener(this);
		
		add(newGame);
		addSeparator();
		add(exit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGame) {
			Main.getMainFrame().staBar.stop();
			Main.getMainFrame().newGame();
		}
		if(e.getSource() == exit)
			System.exit(0);
	}
}
