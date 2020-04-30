package pers.hanami.MineSweep.gui.MenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import pers.hanami.MineSweep.gui.MyDialog;
import pers.hanami.MineSweep.gui.MyFont;

@SuppressWarnings("serial")
public class AboutFile extends JMenu implements ActionListener{

	JMenuItem inf, up;
	String releaseNote = "<html><body>"
			+ "<h1>Update Note</h1>"
			+ "<h2>V1.0 Alpha 200211.01</h2>"
			+ "<p align=\"left\">"
			+ "- Enter an invalid value (Non-Integer) will<br>"
			+ "&nbsp;&nbsp;promat and prevent the game starting.<br>"
			+ "- Fixed a bug where too many mines or too few<br>"
			+ "&nbsp;&nbsp;row\\column\\mine could start the game.<br>"
			+ "<p align=\"right\">11/2/2020<br>&nbsp;</p>"
			+ "<h2>V1.0 Alpha 200210.01</h2>"
			+ "<p align=\"left\">"
			+ "- Fixed a bug where Quickly Filp could be used<br>"
			+ "&nbsp;&nbsp;on non-numeric bolcks.<br>"
			+ "<p align=\"right\">10/2/2020<br>&nbsp;</p>"
			+ "<h2>V1.0 Alpha</h2>"
			+ "<p align=\"left\">"
			+ "- Fixed a bug where the edge Quickly Filp out<br>"
			+ "&nbsp;&nbsp;and overflowed.<br>"
			+ "- Show all mines after the game now.<br>"
			+ "- Improved status bar refresh rate and accuracy<br>"
			+ "- Optimezing Code and Algorithms.<br>"
			+ "- Fixed several bugs.<br></p>"
			+ "<p align=\"right\">10/2/2020<br>&nbsp;</p>"
			+ "<h2>V0.2</h2>"
			+ "<p align=\"left\">"
			+ "- Added Meun.<br>"
			+ "- Added Bottom Status Bar.<br>"
			+ "- Added Quick Filp Numbers.<br>"
			+ "- Numbers got Color.<br>"
			+ "- Added and Optimized many Color.<br>"
			+ "- Optimezing Code and Algorithms.<br>"
			+ "- Fixed several bugs.<br></p>"
			+ "<p align=\"right\">9/2/2020<br>&nbsp;</p>"
			+ "</body></html>";
	String information = "<html><body>"
			+ "<h1>Mine Sweep<br> V1.0 Alpha 200211.01<h1>"
			+ "<p align=\"right\">Power by HANAMI<br>"
			+ "From DGUT.CST"
			+ "</p></body></html>";
	
	public AboutFile() {
		
		super("About");
		setFont(MyFont.FONTS);
		
		inf = new JMenuItem("Information");
		up = new JMenuItem("Update Note");
		
		inf.setFont(MyFont.FONTS);
		up.setFont(MyFont.FONTS);
		
		inf.addActionListener(this);
		up.addActionListener(this);
		
		add(inf);
		addSeparator();
		add(up);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inf)
			new MyDialog(information, 400, 300, "Information", false);
		if(e.getSource() == up)
			new MyDialog(releaseNote, 400, 300, "Release Note", false);
	}
}
