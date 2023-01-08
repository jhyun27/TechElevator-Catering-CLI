package com.techelevator.items;

public class Option {
	
	private Item item;
	private int stock;
	
	public Option(Item item, int stock) {
		this.item = item;
		this.stock = stock;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public int getStock() {
		return this.stock;
	}

}
