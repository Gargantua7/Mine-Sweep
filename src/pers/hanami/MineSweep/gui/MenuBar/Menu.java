package pers.hanami.MineSweep.gui.MenuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class Menu extends JMenuBar{

	JMenu file, about;
	
	public Menu() {
		
		file = new FileMenu();
		about = new AboutFile();
		
		add(file);
		add(about);
		
	}

}
