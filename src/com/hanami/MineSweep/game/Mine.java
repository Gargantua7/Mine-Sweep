package com.hanami.MineSweep.game;

public class Mine {

	private boolean mine;
	private int around;
	
	public Mine() {
		mine = false;
	}
	
	public boolean getMine() {
		return mine;
	}
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public int getAround() {
		return around;
	}
	
	public void setAround(int around) {
		this.around = around;
	}
}
