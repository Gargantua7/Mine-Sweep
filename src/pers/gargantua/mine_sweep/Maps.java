package pers.gargantua.mine_sweep;

import java.util.ArrayList;
import java.util.Random;

public final class Maps extends ArrayList<ArrayList<Ground>> {
    
    private static Maps maps = null;
    
    public Config config;
    
    private Maps() {
    }
    
    public static Maps getMaps() {
        if (maps == null)
            maps = new Maps();
        return maps;
    }
    
    public void init() {
        clear();
        for (int i = 0; i < config.r; i++) {
            ArrayList<Ground> list = new ArrayList<>();
            for (int j = 0; j < config.c; j++) {
                list.add(new Ground(i, j));
            }
            add(list);
        }
        
        Random random = new Random();
        for (int i = 0; i < config.m; i++) {
            while (true) {
                Ground ground = get(random.nextInt(config.r), random.nextInt(config.c));
                if (!ground.isMine) {
                    ground.isMine = true;
                    break;
                }
            }
        }
        
        for (int i = 0; i < config.r; i++) {
            for (int j = 0; j < config.c; j++) {
                if (this.get(i, j).isMine) continue;
                int quantityOfMine = 0;
                for (int r = i - 1; r <= i + 1; r++) {
                    for (int c = j - 1; c <= j + 1; c++) {
                        if ((i != r || j != c) && r >= 0 && r < config.r && c >= 0 && c < config.c &&
                                get(r, c).isMine){
                            quantityOfMine++;
                        }
                    }
                }
                get(i, j).around = quantityOfMine;
            }
        }
    }
    
    public Ground get(int r, int c) {
        return this.get(r).get(c);
    }
}
