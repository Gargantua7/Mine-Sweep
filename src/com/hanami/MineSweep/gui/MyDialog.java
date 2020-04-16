package com.hanami.MineSweep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.hanami.MineSweep.Main;

@SuppressWarnings("serial")
public class MyDialog extends JDialog implements ActionListener{

	JButton jButton;
	boolean sta;
	
	public MyDialog(String s) {
		this(s, 200, 150, "", true);
	}
	
	public MyDialog(String s, String title, boolean sta) {
		this(s, 200, 150, title, sta);
	}
	
	public MyDialog(String s, int width, int height, String title, boolean sta) {
		
		this.sta = sta;
		String str = "OK";
		if(sta) {
			Main.getMainFrame().staBar.stop();
			str = "New Game";
		}
		
		JLabel jLabel = new JLabel(s, JLabel.CENTER);
		jLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane(jLabel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		jButton = new JButton(str);
		jButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
		jButton.addActionListener(this);
		jButton.setBackground(Color.WHITE);
		
		setTitle(title);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(jButton, BorderLayout.SOUTH);
		setModal(true);
		setResizable(false);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jButton) {
			this.dispose();
			if(sta) {
				Main.getMainFrame().newGame();
			}
		}
	}
}
