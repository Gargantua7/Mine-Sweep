package pers.gargantua.mine_sweep;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public final class Gaming extends JPanel implements MouseListener {
    
    public static Gaming gaming = null;
    
    static GridLayout gridLayout = new GridLayout();
    
    Maps maps = Maps.getMaps();
    
    private Gaming() {
        setLayout(gridLayout);
    }
    
    public static Gaming getGaming() {
        if (gaming == null)
            gaming = new Gaming();
        return gaming;
    }
    
    public void start() {
        removeAll();
        maps.init();
        gridLayout.setRows(maps.config.r);
        gridLayout.setColumns(maps.config.c);
        MainFrame.getMainFrame().setSize(maps.config.c * 50, maps.config.r * 50 + 70);
        for (int i = 0; i < maps.config.r; i++) {
            for (int j = 0; j < maps.config.c; j++) {
                Ground button = maps.get(i, j);
                button.addMouseListener(this);
                add(button);
            }
        }
    }
    
    public void superKnock(Ground button) {
        if (!button.getText().equals(""))
            return;
        button.setNor();
        if (button.around == 0) {
            int r = button.r;
            int c = button.c;
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if ((i != r || j != c) && i >= 0 && i < maps.config.r && j >= 0 && j < maps.config.c) {
                        superKnock(maps.get(i, j));
                    }
                }
            }
        }
    }
    
    public boolean judge() {
        if (StaBar.getStaBar().mines != maps.config.m) return false;
        for (int i = 0; i < maps.config.r; i++) {
            for (int j = 0; j < maps.config.c; j++) {
                if (maps.get(i, j).getText().equals("P") && !maps.get(i, j).isMine) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void buttonTurn(Ground button) {
        
        if (button.getText().equals("P") || button.r >= maps.config.r || button.c >= maps.config.c ||
                button.r < 0 || button.c < 0)
            return;
        if (button.isMine) {
            button.setMine();
            gameOver();
        } else
            superKnock(button);
    }
    
    public void midJudge(Ground button) {
        if (button.getText().equals("") || button.getText().equals("P") || button.getText().equals("M") ||
                button.getText().equals("?") || button.getText().equals("0"))
            return;
        int quantityOfMarked = 0;
        int r = button.r;
        int c = button.c;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if ((i != r || j != c) && i >= 0 && i < maps.config.r && j >= 0 && j < maps.config.c &&
                        maps.get(i, j).getText().equals("P")) {
                    quantityOfMarked++;
                }
            }
        }
        if (quantityOfMarked == button.around) {
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if ((i != r || j != c) && i >= 0 && i < maps.config.r && j >= 0 && j < maps.config.c) {
                        buttonTurn(maps.get(i, j));
                    }
                }
            }
        }
    }
    
    public void gameOver() {
        for (int i = 0; i < maps.config.r; i++) {
            for (int j = 0; j < maps.config.c; j++) {
                Ground ground = maps.get(i, j);
                if (ground.getText().equals("P") && !ground.isMine) {
                    ground.setForeground(MyStyle.M);
                    ground.setText("X");
                } else if (!ground.getText().equals("P") && ground.isMine) {
                    ground.setMine();
                }
            }
        }
        new MyDialog("Game Over");
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Ground ground = (Ground) e.getSource();
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                buttonTurn(ground);
                break;
            case MouseEvent.BUTTON2:
                midJudge(ground);
                break;
            case MouseEvent.BUTTON3:
                String text = ground.getText();
                switch (text) {
                    case "":
                        ground.setFlag();
                        StaBar.getStaBar().mines++;
                        if (judge())
                            new MyDialog("<html><body><p align=\"center\">Congratulation !<br/>Times: "
                                    + StaBar.getStaBar().times + " s</p></body></html>"
                            );
                        break;
                    case "P":
                        ground.setQue();
                        StaBar.getStaBar().mines--;
                        break;
                    case "?":
                        ground.setNew();
                        break;
                }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton b = (JButton) e.getSource();
        if (b.getText().equals(""))
            b.setBackground(MyStyle.CHOOSE);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        JButton b = (JButton) e.getSource();
        if (!(b.getText().equals("M") || b.getText().equals("P") || b.getText().equals("?") || b.getText().equals("0")))
            b.setBackground(MyStyle.B);
    }
}