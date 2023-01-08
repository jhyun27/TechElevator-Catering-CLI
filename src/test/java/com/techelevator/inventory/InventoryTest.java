package com.techelevator.inventory;

import java.util.Map;

import org.junit.*;

import com.techelevator.items.Option;

public class InventoryTest {
	
	private Inventory inventory;
	
	@Before
	public void setup() {
		inventory = new Inventory();
	}
	
	
	@Test
	public void remove_item_from_inventory_removes_quantity_selected() {
		
		Map<String, Option> resultMap = inventory.removeFromInventory("B1", 5);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(18, resultMap.size());
		Assert.assertEquals(45, resultMap.get("B1").getStock());
	}
	
	@Test
	public void remove_negative_items_does_not_change_inventory() {
		
		Map<String, Option> resultMap = inventory.removeFromInventory("A4", -5);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(18, resultMap.size());
		Assert.assertEquals(50, resultMap.get("A4").getStock());
	}
	
	@Test
	public void remove_0_items_does_not_change_inventory() {
		
		Map<String, Option> resultMap = inventory.removeFromInventory("A4", 0);
		
		//Assert
		Assert.assertNotNull(resultMap);
		Assert.assertEquals(18, resultMap.size());
		Assert.assertEquals(50, resultMap.get("A4").getStock());
	}
	
}
