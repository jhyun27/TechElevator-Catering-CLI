package com.techelevator.cart;

public class CartReceipt extends Cart{
	private int quantity;
	private String type;
	private String name;
	private double price;
	private double totalPriceForItem;
	
	public CartReceipt(int quantity, String type, String name, double price, double totalPriceForItem) {
		this.quantity = quantity;
		this.type = type;
		this.name = name;
		this.price = price;
		this.totalPriceForItem = totalPriceForItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getTotalPriceForItem() {
		return totalPriceForItem;
	}
	
}
