package com.techelevator.wallet;

import java.util.LinkedHashMap;
import java.util.Map;

public class Wallet {
	
	private double balance = 0;
	
	private double change;

	public double getBalance() {
		return balance;
	}

	public double getChange() {
		return change;
	}

	public double makeChange(double balance) {
		change = balance;
		return change;
	}
	
	public double resetBalance() {
		balance = 0.0;
		return balance;
	}

	public double addMoney(int depositAmount) {
		if (depositAmount > 0 && (balance + depositAmount) <= 5000) {
		balance = balance + (double) depositAmount;
		}
		return balance;
	}
	
	public double removeMoney(double totalPriceForItem) {
		if(balance >= totalPriceForItem && totalPriceForItem > 0) {
			balance = balance - (double) totalPriceForItem;
		}
		return balance;
	}
	
	public Map<String, Integer> makeChangeMap(double change) {
		Map<String, Integer> changeMap = new LinkedHashMap<String, Integer>();
		
		int twenties = (int) (change / 20);
		change -= 20d * (double) twenties;
		changeMap.put("Twenties", twenties);
		
		int tens = (int) (change / 10);
		change -= 10d * (double) tens;
		changeMap.put("Tens", tens);
		
		int fives = (int) (change / 5);
		change -= 5d * (double) fives;
		changeMap.put("Fives", fives);
		
		int ones = (int) (change / 1);
		change -= (double) ones;
		changeMap.put("Ones", ones);
		
		int quarters = (int) (change / 0.25);
		change -= quarters * 0.25;
		changeMap.put("Quarters", quarters);
		
		int dimes = (int) (change / 0.10);
		change -= dimes * 0.10;
		changeMap.put("Dimes", dimes);
		
		int nickels = (int) (change / 0.05);
		change -= nickels * 0.05;
		changeMap.put("Nickels", nickels);
		
		int pennies = (int) (change / 0.01);
		change -= pennies * 0.01;
		changeMap.put("Pennies", pennies);
		
		return changeMap;
	}
}
