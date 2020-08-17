package pers.gargantua.mine_sweep;

import javax.swing.*;

public final class Ground extends JButton {
    
    public int r;
    public int c;
    public boolean isMine = false;
    public int around = 0;
    
    public Ground(int r, int c) {
        this.r = r;
        this.c = c;
        
        setFont(MyStyle.FONTB);
        setBackground(MyStyle.B);
    }
    
    public void setMine() {
        setText("M");
        setBackground(MyStyle.M);
    }
    
    public void setFlag() {
        setText("P");
        setBackground(MyStyle.P);
    }
    
    public void setNor() {
        if (around == 0) {
            setBackground(MyStyle.ZERO);
        }
        setText(String.valueOf(around));
        setForeground(MyStyle.NUMBER.get(around));
    }
    
    public void setQue() {
        setText("?");
        setBackground(MyStyle.Q);
    }
    
    public void setNew() {
        setText("");
        setBackground(MyStyle.B);
    }
}
