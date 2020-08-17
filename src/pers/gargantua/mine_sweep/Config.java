package pers.gargantua.mine_sweep;

/**
 * @author Gargantua丶
 **/
public final class Config {
    public int r;
    public int c;
    public int m;
    
    public Config(int r, int c, int m) {
        this.r = r;
        this.c = c;
        this.m = m;
    }
    
    public static final Config EASY = new Config(8, 8, 10);
    public static final Config ORD = new Config(16, 16, 40);
    public static final Config HEAD = new Config(16, 32, 99);
}
