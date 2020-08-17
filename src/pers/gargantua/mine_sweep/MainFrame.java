package pers.gargantua.mine_sweep;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MainFrame extends JFrame{
	
	public static void main(String[] args) {
		MainFrame.getMainFrame();
	}
	
	private static MainFrame mainFrame = null;
	
	public final JPanel mainPanel;
	public final CardLayout cardLayout = new CardLayout();
	
	private MainFrame() {
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(Gaming.getGaming(), BorderLayout.CENTER);
		gamePanel.add(StaBar.getStaBar(), BorderLayout.SOUTH);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(cardLayout);
		mainPanel.add("start", Start.getStart());
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
	
	public static MainFrame getMainFrame() {
		if(mainFrame == null)
			mainFrame = new MainFrame();
		return mainFrame;
	}
	
}
