package com.techelevator.inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.items.Appetizer;
import com.techelevator.items.Beverage;
import com.techelevator.items.Dessert;
import com.techelevator.items.Entree;
import com.techelevator.items.Item;
import com.techelevator.items.Option;

public class Inventory {

	private String fileName = "cateringsystem.csv";
	private Map<String, Option> cateringOptions = new LinkedHashMap<String, Option>();
	private int inventoryQuantity = 50;
	
	
	public Inventory() {
		File file = new File(fileName);
		try (Scanner fileScanner = new Scanner(file)) {
			while (fileScanner.hasNextLine()) {
				String[] itemAttributes = fileScanner.nextLine().split("\\|");
				cateringOptions.put(itemAttributes[0], createOptionFromItemAttributes(itemAttributes));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Option createOptionFromItemAttributes(String[] itemAttributes) {

		Item item = null;
		double price = Double.parseDouble(itemAttributes[2]);

		switch (itemAttributes[3]) {
		case "B":
			item = new Beverage(itemAttributes[1], price);
			break;
		case "A":
			item = new Appetizer(itemAttributes[1], price);
			break;
		case "E":
			item = new Entree(itemAttributes[1], price);
			break;
		case "D":
			item = new Dessert(itemAttributes[1], price);
			break;
		}

		return new Option(item, inventoryQuantity);

	}
	
	
	public Map<String, Option> removeFromInventory(String productChoice, int productQuantity) {
		for (String key : cateringOptions.keySet()) {
			if (key.equals(productChoice) && productQuantity > 0) {
				inventoryQuantity = cateringOptions.get(key).getStock() - productQuantity;
				Item item = cateringOptions.get(key).getItem();
				Option option = new Option(item, inventoryQuantity);
				cateringOptions.put(productChoice, option);
			}
		}
		return cateringOptions;
	}

	public Map<String, Option> getInventory() {
		
		return cateringOptions;
	}
}
