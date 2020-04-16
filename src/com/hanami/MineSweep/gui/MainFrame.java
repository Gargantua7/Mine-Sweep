package com.hanami.MineSweep.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hanami.MineSweep.gui.MenuBar.Menu;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	JPanel mainPanel, gamePanel;
	CardLayout cardLayout = new CardLayout();
	Start start= new Start();
	Gaming gaming= new Gaming();
	public StaBar staBar = new StaBar();
	
	public MainFrame() {
		
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(gaming, BorderLayout.CENTER);
		gamePanel.add(staBar, BorderLayout.SOUTH);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(cardLayout);
		mainPanel.add("start", start);
		mainPanel.add("gaming", gamePanel);
		
		setLayout(new BorderLayout());
		
		add(new Menu(), BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		setTitle("Mine Sweep");
		setSize(300, 230);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void switchs(String s) {
		cardLayout.show(mainPanel, s);
	}
	
	public void newGame() {

		switchs("start");
		setSize(300, 230);
	}
}
