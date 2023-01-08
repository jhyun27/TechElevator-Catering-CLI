package com.techelevator.view;


import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.cart.CartReceipt;
import com.techelevator.items.Item;
import com.techelevator.items.Option;


public class Menu {

	private Scanner in = new Scanner(System.in);
	
//	private String productChoice;
//	
//	private int productQuantity;
//	
//	public String getProductChoice() {
//		return productChoice;
//	}
//
//	public int getProductQuantity() {
//		return productQuantity;
//	}

	public void displayUserMessage(String message) {
		System.out.println(message);
		System.out.flush();
	}
	
	public void displayAllItems(Map<String, Option> itemDisplay) {
		System.out.printf("%2s  %-20s  %-8s  %-12s%n", "Code", "Item", "Price", "Available Stock");
		
		for (String key : itemDisplay.keySet()) {
			
			Option option = itemDisplay.get(key);
			Item item = option.getItem();
			String availableStock = "";
			
			double price = item.getPrice();
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			String formattedPrice = "";
			formattedPrice = formatter.format(price);
			
			if (option.getStock() < 1) {
				availableStock = "Sold Out";
			} else {
				availableStock = Integer.toString(option.getStock());
			}
			
			System.out.printf("%2s    %-20s  %-8s  %-12s%n", key, item.getName(), formattedPrice, availableStock);
			
		}
		System.out.println();
		System.out.flush();
		
	}
	
	public void displayCart(Map<String, CartReceipt> itemDisplay, double total) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedTotal = "";
		formattedTotal = formatter.format(total);
		
		for (String key : itemDisplay.keySet()) {
			
			CartReceipt cartReceipt = itemDisplay.get(key);
			int quantity = cartReceipt.getQuantity();
			String type = cartReceipt.getType();
			String name = cartReceipt.getName();
			

			String formattedPrice = "";
			String formattedTotalPriceForItem = "";
			
			double price = cartReceipt.getPrice();
			formattedPrice = formatter.format(price);
			double totalPriceForItem = cartReceipt.getTotalPriceForItem();
			formattedTotalPriceForItem = formatter.format(totalPriceForItem);
			System.out.printf("%2s  %-10s  %-20s  %-8s %-12s%n", quantity, type, name, formattedPrice, formattedTotalPriceForItem);
			
		}
		
		System.out.println();
		System.out.print("Total: " + formattedTotal);
		System.out.println();
		System.out.flush();
		
	}
	
	public String getSelectionFromUser(String[] options) {
		String selectedOption = null;
		
		while (selectedOption == null) {
			
			displayUserOptions(options);
			int userChoice = in.nextInt();
			in.nextLine();
			
			if (userChoice - 1 >= options.length || userChoice < 1) {
				displayUserMessage("Please select a valid option!");
			} else {
				selectedOption = options[userChoice - 1];
			}
		}
		return selectedOption;
		
	}
	
	public void displayBalance(double balance) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = "";
		formattedBalance = formatter.format(balance);
		
		System.out.println();
		displayUserMessage("Your current balance is: " + formattedBalance);
		System.out.println();
	}
	
	public void displayChange(Map<String,Integer> changeMap, double change) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = "";
		formattedBalance = formatter.format(change);
		
		System.out.println();
		displayUserMessage("Your change: " + formattedBalance);
		System.out.println();
		
		for (String key : changeMap.keySet()) {
			
			String billOrCoin = key;
			int numberOfBills = changeMap.get(key);
			
			System.out.printf("%2s  %-12s%n", billOrCoin + ":", numberOfBills);
			
		}
		System.out.println();
	}
	
	private void displayUserOptions(String[] options) {
		
		for (int i = 0; i < options.length; i++) {
			System.out.println( (i + 1) + ") " + options[i] );
		}
		System.out.print("choice >>>");
		System.out.flush();
	}
	
	public int getMoneyFromUser() {
		displayUserMessage("Please deposit money: ");
		int depositAmount = in.nextInt();
		in.nextLine();
		return depositAmount;
	}
	
	
	public String getProductChoiceFromUser(double balance) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = "";
		formattedBalance = formatter.format(balance);
		
		displayUserMessage("Your current balance is: " + formattedBalance);
		displayUserMessage("Please enter the code of the item you would like to purchase >>>");
		String productChoice = in.nextLine();
		return productChoice;
		
	}
		
	public int getQuantityFromUser() {
		displayUserMessage("Please enter the number of those you would like >>>");
		int productQuantity = in.nextInt();
		in.nextLine();
		return productQuantity;
	}
	
	public boolean validateDeposit(double balance, double depositAmount) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedBalance = "";
		formattedBalance = formatter.format(balance);
		
		if (balance + depositAmount > 5000) {
			displayUserMessage("Your deposit causes balance to exceed $5000.00 limit.");
			displayUserMessage("Your current balance is: " + formattedBalance);
			return false;
		}
		return true;
	}
	
	public boolean validateBalance(double balance, double totalPriceForItem) {
		if(balance - totalPriceForItem <= 0) {
			displayUserMessage("Your purchase exceeds you wallet balance. Please select another option or deposit more money.");
			return false;
		}
		return true;
	}
	
	public boolean validateIfProductExists(String productChoice, Map<String, Option> inventory) {
		if (inventory.containsKey(productChoice)) {
			return true;
		} 
		displayUserMessage("Product does not exist, please enter a new item code. ");
		return false;
	}
	
	public boolean validateIfProductInStock(String productChoice, int productQuantity, int currentStock) {
		if (currentStock < productQuantity) {
			displayUserMessage("Not enough product in stock, Please enter lesser amount. ");
			return false;
		}
		return true;
	}
	
	public void quitProgram() {
		displayUserMessage("Thank You! Enjoy your meal!");
	}
	

	
}
	

