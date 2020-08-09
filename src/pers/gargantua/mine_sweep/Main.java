package pers.gargantua.mine_sweep;

import pers.gargantua.mine_sweep.gui.MainFrame;

public class Main {

	static MainFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
}