package pers.gargantua.mine_sweep.game;

public class Map {

	static private int row, col, mines;
	
	static public void setRow(int row) {
		Map.row = row;
	}
	
	static public void setCol(int col) {
		Map.col = col;
	}
	
	static public void setMines(int mines) {
		Map.mines = mines;
	}
	
	static public int getRow() {
		return row;
	}
	
	static public int getCol() {
		return col;
	}
	
	static public int getMines() {
		return mines;
	}
}
