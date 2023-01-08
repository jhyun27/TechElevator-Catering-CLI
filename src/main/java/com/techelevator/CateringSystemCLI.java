package com.techelevator;

import java.util.Map;
import com.techelevator.items.Option;
import com.techelevator.view.Menu;
import com.techelevator.writer.CateringLogWriter;

public class CateringSystemCLI {

	private Menu menu;
	private CateringSystem cateringSystem;
	private CateringLogWriter writer = new CateringLogWriter();
	private final static String DISPLAY_ITEMS = "Display Catering Items";
	private final static String ORDER = "Order";
	private final static String QUIT = "Quit";
	private final static String[] MAIN_MENU_OPTIONS = { DISPLAY_ITEMS, ORDER, QUIT };
	private final static String ADD_MONEY = "Add Money";
	private final static String SELECT_PRODUCTS = "Select Products";
	private final static String COMPLETE_TRANSACTION = "Complete Transaction";
	private final static String[] ORDER_MENU_OPTIONS = { ADD_MONEY, SELECT_PRODUCTS, COMPLETE_TRANSACTION };
	
	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		cateringSystem = new CateringSystem();
		String choice = "";
		String orderMenuChoice = "";
		
		while (true) {
			choice = (String) menu.getSelectionFromUser(MAIN_MENU_OPTIONS);
			
			if (choice.equals(DISPLAY_ITEMS)) {
				displayCateringItems();
				continue;
			}

			while (true) {
				if (choice.equals(ORDER)) {
					menu.displayBalance(cateringSystem.getBalance());
					orderMenuChoice = (String) menu.getSelectionFromUser(ORDER_MENU_OPTIONS);
					
					
					if (orderMenuChoice.equals(ADD_MONEY)) {
						int depositAmount = menu.getMoneyFromUser();
						boolean balanceUnderMax = menu.validateDeposit(cateringSystem.getBalance(), depositAmount);
						while (!balanceUnderMax) {
							depositAmount = menu.getMoneyFromUser();
							balanceUnderMax = menu.validateDeposit(cateringSystem.getBalance(), depositAmount);
						}
						if (balanceUnderMax) {
							cateringSystem.addMoney(depositAmount);
							cateringSystem.logAddMoney(depositAmount);
						}
						continue;
					}
					
					if (orderMenuChoice.equals(SELECT_PRODUCTS)) {

						displayCateringItems();
						String productChoice = menu.getProductChoiceFromUser(cateringSystem.getBalance());
						boolean productExists = menu.validateIfProductExists(productChoice, cateringSystem.getInventory());

						while (!productExists) {
							productChoice = menu.getProductChoiceFromUser(cateringSystem.getBalance());
							productExists = menu.validateIfProductExists(productChoice, cateringSystem.getInventory());
						}

						int productQuantity = menu.getQuantityFromUser();
						boolean productInStock = menu.validateIfProductInStock(productChoice, productQuantity, cateringSystem.getInventory().get(productChoice).getStock());

						while (!productInStock) {
							productQuantity = menu.getQuantityFromUser();
							productInStock = menu.validateIfProductInStock(productChoice, productQuantity, cateringSystem.getInventory().get(productChoice).getStock());
						}
						
						double totalPriceForItem = cateringSystem.getInventory().get(productChoice).getItem().getPrice() * productQuantity;
						boolean sufficientBalance = menu.validateBalance(cateringSystem.getBalance(), totalPriceForItem);

						if (productExists && productInStock && sufficientBalance) {
//							cateringSystem.getCart(productChoice, productQuantity);
							cateringSystem.addToCartReceiptMap(productChoice, productQuantity);
							cateringSystem.removeFromInventory(productChoice, productQuantity);
							cateringSystem.removeMoney(cateringSystem.getCartReceiptMap().get(productChoice).getPrice() * productQuantity);
							cateringSystem.logOrder(productChoice, cateringSystem.getCartReceiptMap());
							continue;
						}
						
						if (!sufficientBalance) {
							continue;
						}
						break;
					}

					if (orderMenuChoice.equals(COMPLETE_TRANSACTION)) {
						displayCartItems();
						cateringSystem.makeChange(cateringSystem.getBalance());
						cateringSystem.getResetBalance();
						cateringSystem.logGiveChange(cateringSystem.getChange());
						menu.displayChange(cateringSystem.getChangeMap(cateringSystem.getChange()), cateringSystem.getChange());
						break;
					}
				}
				
				if (choice.equals(QUIT)) {
					writer.write(cateringSystem.getLogList());
					menu.quitProgram();
					System.exit(0);
				}  
			}
			
		}

	}
	

	private void displayCateringItems() {
		Map<String, Option> cateringOptions = cateringSystem.getItems();
		menu.displayAllItems(cateringOptions);
	}
	
	private void displayCartItems() {
		menu.displayCart(cateringSystem.getCartReceiptMap(), cateringSystem.getCartTotal());
	}

	public static void main(String[] args) {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}
}
