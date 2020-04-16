package com.hanami.MineSweep.game;


import java.util.ArrayList;
import java.util.Random;

public class Lists {

	private ArrayList<ArrayList<Mine>> mines;
	
	public Lists() {

		int r = Map.getRow();
		int c = Map.getCol();
		int m = Map.getMines();
		
		mines = new ArrayList<ArrayList<Mine>>();
		for (int i = 0; i < r; i++) {
			ArrayList<Mine> mines = new ArrayList<Mine>();
			for (int j = 0; j < c; j++) {
				mines.add(new Mine());
			}
			this.mines.add(mines);
		}

		Random random = new Random();
		for (int i = 0; i < m; i++) {
			Mine ms = mines.get(random.nextInt(r)).get(random.nextInt(c));
			if (!ms.getMine())
				ms.setMine(true);
			else
				i--;
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (mines.get(i).get(j).getMine())
					continue;
				int ms = 0;
				if (i - 1 >= 0 && j - 1 >= 0 && mines.get(i - 1).get(j - 1).getMine())
					ms++;
				if (i - 1 >= 0 && mines.get(i - 1).get(j).getMine())
					ms++;
				if (i - 1 >= 0 && j + 1 < c && mines.get(i - 1).get(j + 1).getMine())
					ms++;
				if (j - 1 >= 0 && mines.get(i).get(j - 1).getMine())
					ms++;
				if (j + 1 < c && mines.get(i).get(j + 1).getMine())
					ms++;
				if (i + 1 < r && j - 1 >= 0 && mines.get(i + 1).get(j - 1).getMine())
					ms++;
				if (i + 1 < r && mines.get(i + 1).get(j).getMine())
					ms++;
				if (i + 1 < r && j + 1 < c && mines.get(i + 1).get(j + 1).getMine())
					ms++;
				mines.get(i).get(j).setAround(ms);
			}
		}
		
		for (ArrayList<Mine> i : mines) {
			for (Mine ms : i) {
				System.out.print(ms.getAround() + " ");
			}
			System.out.println();
		}
	}
	
	public boolean getMine(int i, int j) {
		return mines.get(i).get(j).getMine();
	}
	
	public int getAround(int i, int j) {
		return mines.get(i).get(j).getAround();
	}
}
