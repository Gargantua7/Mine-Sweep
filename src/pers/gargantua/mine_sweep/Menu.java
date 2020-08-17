package pers.gargantua.mine_sweep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Menu extends JMenuBar {
    
    public Menu() {
        
        JMenu file = FileMenu.getFileMenu();
        JMenu about = AboutFile.getAboutFile();
        
        add(file);
        add(about);
    }
    
}

class FileMenu extends JMenu {
    
    private static FileMenu fileMenu;
    
    private FileMenu() {
        
        super("File");
        setFont(MyStyle.FONTS);
        
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        
        newGame.setFont(MyStyle.FONTS);
        exit.setFont(MyStyle.FONTS);
        
        exit.setForeground(Color.RED);
        
        newGame.addActionListener((ActionEvent e) -> {
            StaBar.getStaBar().stop();
            MainFrame.getMainFrame().cardLayout.show(MainFrame.getMainFrame().mainPanel, "start");
            MainFrame.getMainFrame().setSize(300, 230);
        });
        exit.addActionListener((ActionEvent e) -> System.exit(0));
        
        add(newGame);
        addSeparator();
        add(exit);
    }
    
    public static FileMenu getFileMenu() {
        if (fileMenu == null)
            fileMenu = new FileMenu();
        return fileMenu;
    }
}

class AboutFile extends JMenu {
    
    String information = "<html><body>"
            + "<h1>Mine Sweep<br>Java Edition<br>Alpha-2.0.0 Rebuild<h1>"
            + "<p align=\"right\">Power by Gargantua<br>"
            + "From DGUT.CST"
            + "</p></body></html>";
    
    private static AboutFile aboutFile = null;
    
    private AboutFile() {
        
        super("About");
        setFont(MyStyle.FONTS);
        
        JMenuItem inf = new JMenuItem("Information");
        JMenuItem up = new JMenuItem("Update Note");
        
        inf.setFont(MyStyle.FONTS);
        up.setFont(MyStyle.FONTS);
        
        inf.addActionListener((ActionEvent e) ->
                new MyDialog("Information", information, 400, 300, false));
        up.addActionListener((ActionEvent e) -> {
            File file = new File("src/pers/gargantua/mine_sweep/Release Note.html");
            long len = file.length();
            byte[] filecount = new byte[(int) len];
            try {
                FileInputStream in = new FileInputStream(file);
                try {
                    in.read(filecount);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            new MyDialog("Release Note", new String(filecount, StandardCharsets.UTF_8), 400, 300, false);
        });
        
        add(inf);
        addSeparator();
        add(up);
        
    }
    
    public static AboutFile getAboutFile() {
        if (aboutFile == null)
            aboutFile = new AboutFile();
        return aboutFile;
    }
}

