package com.hanami.MineSweep;

import com.hanami.MineSweep.gui.MainFrame;

public class Main {

	static MainFrame mainFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame();
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
}