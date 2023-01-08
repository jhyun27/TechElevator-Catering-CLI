package com.techelevator.cart;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import com.techelevator.inventory.Inventory;

public class Cart {
	
	private Inventory inventory = new Inventory();
	
//	private Map<String, Integer> quantityMap = new LinkedHashMap<String, Integer>();
	
	private Map<String, CartReceipt> cartReceiptMap = new TreeMap<String, CartReceipt>();
	
	
	
//	public Map<String, Integer> userOrder(String productChoice, Integer productQuantity) {
//			if ( inventory.getInventory().containsKey(productChoice) && productQuantity > 0 &&
//				inventory.getInventory().get(productChoice).getStock() >= productQuantity) {
//				quantityMap.put(productChoice, productQuantity);
//			} 
//
//
//		return quantityMap;
//	}

	public Map<String, CartReceipt> addToCart(String productChoice, int productQuantity) {
		int updatedQuantity = 0;
		
		if (cartReceiptMap.get(productChoice) != null) {
			updatedQuantity = productQuantity + cartReceiptMap.get(productChoice).getQuantity();
		} else {
			updatedQuantity = productQuantity;
		}
		double price = inventory.getInventory().get(productChoice).getItem().getPrice();
		String name = inventory.getInventory().get(productChoice).getItem().getName();
		double totalForItem = price * updatedQuantity;
		
		String type = "";
		if (productChoice.contains("A")) {
			type = "Appetizer";
		}
		if (productChoice.contains("B")) {
			type = "Beverage";
		}
		if (productChoice.contains("E")) {
			type = "Entree";
		}
		if (productChoice.contains("D")) {
			type = "Dessert";
		}
		
		CartReceipt cartReceiptObject = new CartReceipt(updatedQuantity, type, name, price, totalForItem);
		
		cartReceiptMap.put(productChoice, cartReceiptObject);
		return cartReceiptMap;
	}
	
	public double calculateTotal() {
		double runningTotal = 0;
		for (String key : cartReceiptMap.keySet()) {
			runningTotal += cartReceiptMap.get(key).getTotalPriceForItem();
		}
		return runningTotal;
	}
	
	public Map<String, CartReceipt> getCartReceiptMap() {
		return cartReceiptMap;
	}
	
	 

}
