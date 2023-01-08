package com.techelevator.cart;

import java.util.LinkedHashMap;
import java.util.Map;


import org.junit.*;

public class CartTest {
	
	private Cart cart;
	
	@Before
	public void setup() {
		cart = new Cart();
	}
	
//	@Test
//	public void user_order_stores_quantity_ordered() {
//		
//		// Act
//		Map<String, Integer> resultMap = cart.userOrder("B1", 20);
//		
//		//Assert
//		Assert.assertNotNull(resultMap);
//		Assert.assertEquals(1, resultMap.size());
//		Assert.assertEquals(20, resultMap.get("B1"), 2);
//	}
//	
//	@Test
//	public void negative_user_order_does_not_stores_quantity_ordered() {
//		Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
//		// Act
//		Map<String, Integer> resultMap = cart.userOrder("B1", -20);
//		
//		//Assert
//		Assert.assertEquals(expected, resultMap);
//
//	}
//	
//	@Test
//	public void user_cannot_order_more_than_inventory_quantity() {
//		Map<String, Integer> expected = new LinkedHashMap<String, Integer>();
//		// Act
//		Map<String, Integer> resultMap = cart.userOrder("B1", 51);
//		
//		//Assert
//		Assert.assertEquals(expected, resultMap);
//
//	}
	
	@Test
	public void adding_to_cart_adds_a_type_name_price_quantity_totalPrice() {
		
		// Act
		Map<String, CartReceipt> resultMap = cart.addToCart("A4", 20);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(1, resultMap.size());
		Assert.assertEquals(20, resultMap.get("A4").getQuantity(), 2);
		Assert.assertEquals("Bruschetta", resultMap.get("A4").getName());
		Assert.assertEquals("Appetizer", resultMap.get("A4").getType());
		Assert.assertEquals(2.65, resultMap.get("A4").getPrice(), 2);
		Assert.assertEquals(53, resultMap.get("A4").getTotalPriceForItem(), 2);
	}
	
	@Test
	public void calculate_total_returns_total_of_all_items_in_cart_receipt_map() {
		cart.addToCart("A4", 2);
		cart.addToCart("D4", 2);
		cart.addToCart("E3", 3);
		// Act
		double result = cart.calculateTotal();
		
		//Assert
		Assert.assertEquals(41.95, result, 2);
		
	}
	
	
}
