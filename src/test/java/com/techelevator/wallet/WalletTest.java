package com.techelevator.wallet;

import java.util.Map;

import org.junit.*;

public class WalletTest {
	
	private Wallet wallet;
	
	@Before
	public void setup() {
		wallet = new Wallet();
	}

	@Test
	public void addmoney_method_updates_balance() {
		Assert.assertEquals(10d, wallet.addMoney(10), 2);
	}
	
	@Test
	public void balance_20_returns_1_twenty() {
		
		// Act
		Map<String, Integer> resultMap = wallet.makeChangeMap(20);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(8, resultMap.size());
		Assert.assertEquals(new Integer (1), resultMap.get("Twenties"));
		Assert.assertEquals(new Integer (0), resultMap.get("Tens"));
		Assert.assertEquals(new Integer (0), resultMap.get("Fives"));
		Assert.assertEquals(new Integer (0), resultMap.get("Ones"));
		Assert.assertEquals(new Integer (0), resultMap.get("Quarters"));
		Assert.assertEquals(new Integer (0), resultMap.get("Dimes"));
		Assert.assertEquals(new Integer (0), resultMap.get("Nickels"));
		Assert.assertEquals(new Integer (0), resultMap.get("Pennies"));
	}
	
	@Test
	public void balance_36_returns_1_twenty_1_ten_1_five_1_one() {
		
		// Act
		Map<String, Integer> resultMap = wallet.makeChangeMap(36);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(8, resultMap.size());
		Assert.assertEquals(new Integer (1), resultMap.get("Twenties"));
		Assert.assertEquals(new Integer (1), resultMap.get("Tens"));
		Assert.assertEquals(new Integer (1), resultMap.get("Fives"));
		Assert.assertEquals(new Integer (1), resultMap.get("Ones"));
		Assert.assertEquals(new Integer (0), resultMap.get("Quarters"));
		Assert.assertEquals(new Integer (0), resultMap.get("Dimes"));
		Assert.assertEquals(new Integer (0), resultMap.get("Nickels"));
		Assert.assertEquals(new Integer (0), resultMap.get("Pennies"));
	}
	
	@Test
	public void balance_10_dollars_25_cents_returns_1_ten_1_quarter() {
		
		// Act
		Map<String, Integer> resultMap = wallet.makeChangeMap(10.25);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(8, resultMap.size());
		Assert.assertEquals(new Integer (0), resultMap.get("Twenties"));
		Assert.assertEquals(new Integer (1), resultMap.get("Tens"));
		Assert.assertEquals(new Integer (0), resultMap.get("Fives"));
		Assert.assertEquals(new Integer (0), resultMap.get("Ones"));
		Assert.assertEquals(new Integer (1), resultMap.get("Quarters"));
		Assert.assertEquals(new Integer (0), resultMap.get("Dimes"));
		Assert.assertEquals(new Integer (0), resultMap.get("Nickels"));
		Assert.assertEquals(new Integer (0), resultMap.get("Pennies"));
	}
	
	@Test
	public void balance_40_cents_returns_1_quarter_1_dime_1_nickel() {
		
		// Act
		Map<String, Integer> resultMap = wallet.makeChangeMap(0.40);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(8, resultMap.size());
		Assert.assertEquals(new Integer (0), resultMap.get("Twenties"));
		Assert.assertEquals(new Integer (0), resultMap.get("Tens"));
		Assert.assertEquals(new Integer (0), resultMap.get("Fives"));
		Assert.assertEquals(new Integer (0), resultMap.get("Ones"));
		Assert.assertEquals(new Integer (1), resultMap.get("Quarters"));
		Assert.assertEquals(new Integer (1), resultMap.get("Dimes"));
		Assert.assertEquals(new Integer (1), resultMap.get("Nickels"));
		Assert.assertEquals(new Integer (0), resultMap.get("Pennies"));
	}
	
	@Test
	public void balance_4_cents_returns_4_pennies() {
		
		// Act
		Map<String, Integer> resultMap = wallet.makeChangeMap(0.04);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(8, resultMap.size());
		Assert.assertEquals(new Integer (0), resultMap.get("Twenties"));
		Assert.assertEquals(new Integer (0), resultMap.get("Tens"));
		Assert.assertEquals(new Integer (0), resultMap.get("Fives"));
		Assert.assertEquals(new Integer (0), resultMap.get("Ones"));
		Assert.assertEquals(new Integer (0), resultMap.get("Quarters"));
		Assert.assertEquals(new Integer (0), resultMap.get("Dimes"));
		Assert.assertEquals(new Integer (0), resultMap.get("Nickels"));
		Assert.assertEquals(new Integer (4), resultMap.get("Pennies"));
	}
	
	@Test
	public void make_change_transfers_balance_to_change_variable() {
		
		//Assert
		Assert.assertEquals(100, wallet.makeChange(100), 2);
		Assert.assertEquals(0, wallet.makeChange(0), 2);
		Assert.assertEquals(1.50, wallet.makeChange(1.50), 2);
		Assert.assertEquals(0.632486, wallet.makeChange(0.632486), 2);
	}
	
	@Test
	public void reset_balance_resets_balance_to_0() {
		
		//Assert
		Assert.assertEquals(0, wallet.resetBalance(), 2);
	}
	
	@Test
	public void add_money_adds_deposit_to_balance() {
		
		//Assert
		Assert.assertEquals(100, wallet.addMoney(100), 2);
		Assert.assertEquals(623, wallet.addMoney(523), 2);
	}
	
	@Test
	public void add_negative_money_does_not_change_balance() {
		
		//Assert
		Assert.assertEquals(0, wallet.addMoney(-100), 2);
	}
	
	@Test
	public void cannot_deposit_over_5000() {
		
		//Assert
		Assert.assertEquals(0, wallet.addMoney(5001), 2);
	}
	
	@Test
	public void add_money_cannot_make_balance_over_5000() {
		
		//Assert
		Assert.assertEquals(4999, wallet.addMoney(4999), 2);
		Assert.assertEquals(4999, wallet.addMoney(2), 2);
	}
	
	@Test
	public void remove_money_deducts_from_balance() {
		
		wallet.addMoney(500);
		//Assert
		Assert.assertEquals(400, wallet.removeMoney(100), 2);
		Assert.assertEquals(338, wallet.removeMoney(62), 2);
	}
	
	@Test
	public void remove_money_cannot_exceed_balance() {
		
		wallet.addMoney(500);
		//Assert
		Assert.assertEquals(500, wallet.removeMoney(501), 2);
	}
	
	@Test
	public void cannot_remove_negative_money() {
		
		wallet.addMoney(500);
		//Assert
		Assert.assertEquals(500, wallet.removeMoney(-200), 2);
	}
	
}
