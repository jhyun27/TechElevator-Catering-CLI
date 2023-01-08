package com.techelevator;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.techelevator.wallet.Wallet;
import com.techelevator.cart.Cart;
import com.techelevator.cart.CartReceipt;
import com.techelevator.inventory.Inventory;
import com.techelevator.items.Option;

public class CateringSystem {
	
	private Inventory inventory = new Inventory();
	
	private Cart cart = new Cart();
	
	private Wallet wallet = new Wallet();
	
	private List<String> logList = new ArrayList<String>();
	
	public Map<String, Option> getItems() {
		return inventory.getInventory();
	}
	
	public List<String> getLogList() {
		return logList;
	}

	public Map<String, Option> getInventory() {
		return inventory.getInventory();
	}
	
//	public Map<String, Integer> getCart(String productChoice, int productQuantity) {
//		return cart.userOrder(productChoice, productQuantity);
//	}
	
	public double addMoney(int depositAmount) {
		return wallet.addMoney(depositAmount);
	}
	
	public double removeMoney(double totalPriceForItem) {
		return wallet.removeMoney(totalPriceForItem);
	}
	
	public double getBalance() {
		return wallet.getBalance();
	}
	
	public double getChange() {
		return wallet.getChange();
	}
	
	public double makeChange(double balance) {
		return wallet.makeChange(balance);
	}
	
	public double getResetBalance() {
		return wallet.resetBalance();
	}
	
	public Map<String, Integer> getChangeMap(double balance) {
		return wallet.makeChangeMap(balance);
	}
	
	public Map<String, Option> removeFromInventory(String productChoice, int productQuantity) {
		return inventory.removeFromInventory(productChoice, productQuantity);
	}
	
	
	public Map<String, CartReceipt> addToCartReceiptMap(String productChoice, int productQuantity) {
		return cart.addToCart(productChoice, productQuantity);
	}
	
	public Map<String, CartReceipt> getCartReceiptMap() {
		return cart.getCartReceiptMap();
	}
	
	public double getCartTotal() {
		return cart.calculateTotal();
	}
	
	public void logAddMoney(int depositAmount) {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedDeposit = "";
		formattedDeposit = formatter.format(depositAmount);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date date = new Date();
	    
	    String formattedDate = dateFormatter.format(date);
	    
	    String logBalance = formatter.format(wallet.getBalance());
	    
	    String addMoneyLog = formattedDate + " ADD MONEY " + formattedDeposit + " " + logBalance;
	    
	    logList.add(addMoneyLog);
		
	}
	
	public void logGiveChange(double change) {
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String formattedChange = "";
		formattedChange = formatter.format(change);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date date = new Date();
	    
	    String formattedDate = dateFormatter.format(date);
	    
	    String logBalance = formatter.format(wallet.getBalance());
	    
	    String addChangeLog = formattedDate + " GIVE CHANGE " + formattedChange + " " + logBalance;
	    
	    logList.add(addChangeLog);
		
	}
	
	public void logOrder(String productChoice, Map<String, CartReceipt> receipt) {
		String logName = receipt.get(productChoice).getName();
		String logQuantity = Integer.toString(receipt.get(productChoice).getQuantity());
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		String logTotalPrice = formatter.format(receipt.get(productChoice).getTotalPriceForItem());
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
	    Date date = new Date();
	    
	    String formattedDate = dateFormatter.format(date);
	    
	    String logBalance = formatter.format(wallet.getBalance());
	    
	    String addLogOrder = formattedDate + " " + logQuantity + " " + logName + " " + productChoice + " " + logTotalPrice + " " + logBalance;
	    
	    logList.add(addLogOrder);
		
	}
	
}
