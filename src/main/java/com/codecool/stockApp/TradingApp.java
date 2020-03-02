package com.codecool.stockApp;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Provides a command line interface for stock trading.
 **/
public class TradingApp {


	public static void main(String[] args) {
		Logger logger = new Logger();
		RemoteURLReader remoteURLReader = new RemoteURLReader();
		StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
		Trader trader = new Trader(logger, stockAPIService);

	    TradingApp app = new TradingApp();
	    app.start(logger, trader);
	}

	void start(Logger logger, Trader trader) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter a stock symbol (for example aapl):");
		String symbol = keyboard.nextLine();
		System.out.println("Enter the maximum price you are willing to pay: ");
		double price;
		try {
			price = keyboard.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Enter a number");
			return;
		}

		try {
			boolean purchased = trader.buy(symbol, price);
			if (purchased) {
				logger.log("Purchased stock!");
			}
			else {
				logger.log("Couldn't buy the stock at that price.");
			}
		} catch (Exception e) {
			logger.log("There was an error while attempting to buy the stock: " + e.getMessage());
		}
	}
}