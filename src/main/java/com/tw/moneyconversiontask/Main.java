package com.tw.moneyconversiontask;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static int menuChoices() {
		System.out.println("What would you like to do? Please pick 1 for balance, 2 for exchange rate or 3 to withdraw from your account");

		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();

		return choice;
	}

	public static void main(String[] args) {
		Account account1 = new Account(Locale.UK, 1000, 1);
		Account account2 = new Account(Locale.US, 1000, 2);
		Account account3 = new Account(Locale.JAPAN, 10000, 3);
		List<Account> accounts = new ArrayList<>();
		accounts.add(account1);
		accounts.add(account2);
		accounts.add(account3);

		int userChoice = menuChoices();
		Scanner sc = new Scanner(System.in);

		if (userChoice == 1) {
			System.out.println("Please enter your account number: ");
			int number = sc.nextInt();
			for (int i = 0; i < accounts.size(); i++) {
				if (number == accounts.get(i).accountNumber) {
					accounts.get(i).balance();
				}
			}
		}

		else if (userChoice == 2) {
			System.out.println("Which currency would you like to exchange from? Please select USD, JPY or GBP.");
			String sourceCurrency = sc.nextLine();

			System.out.println("Which currency would you like to exchange to? Please select USD, JPY or GBP.");
			String targetCurrency = sc.nextLine();

			StringBuilder currencyDirection = new StringBuilder();
			currencyDirection.append(sourceCurrency.toUpperCase());
			currencyDirection.append("_");
			currencyDirection.append(targetCurrency.toUpperCase());

			if (!sourceCurrency.equals(targetCurrency)) {
				System.out.println("Please enter the amount: ");
				int sourceAmount = sc.nextInt();
				try {
					double rate = ExchangeRate.valueOf(currencyDirection.toString()).getRate();
					double targetAmount = sourceAmount * rate;
					System.out.println("With the rate of " + rate + ", " + sourceAmount + sourceCurrency.toUpperCase() + " is worth " + targetAmount + targetCurrency.toUpperCase());
				} catch (IllegalArgumentException e) {
					System.out.println("Something went wrong - did you enter the correct currency?");
				}
			} else {
				System.out.println("Same currency, please try again.");
			}
		}

		else if (userChoice == 3) {
			System.out.println("Please enter your account number: ");
			int number = sc.nextInt();
			System.out.println("How much would you like to withdraw?");
			int w = sc.nextInt();
			System.out.println("Which currency would you like to withdraw in? Please select USD, GBP or JPY.");
			String targetCurrency = sc.next();
			for (int i = 0; i < accounts.size(); i++) {
				if (number == accounts.get(i).accountNumber) {
					String sourceCurrency = accounts.get(i).getCurrency().toString();

					if (targetCurrency.equals(sourceCurrency)) {
						accounts.get(i).withdraw(w);
					}
					else {
						StringBuilder withdrawCurrency = new StringBuilder();
						withdrawCurrency.append(targetCurrency.toUpperCase());
						withdrawCurrency.append("_");
						withdrawCurrency.append(sourceCurrency.toUpperCase());

						try {
							double rate = ExchangeRate.valueOf(withdrawCurrency.toString()).getRate();
							double targetAmount = w * rate;
							if (targetAmount < accounts.get(i).getAmount()) {
								accounts.get(i).withdraw((int) targetAmount);
								System.out.println("In " + targetCurrency + " you have withdrawn : " + w + targetCurrency);
								accounts.get(i).balance();
							}
						} catch (IllegalArgumentException e) {
							System.out.println("Something went wrong, please try again.");
						}
					}
				}
			}
		}
	}
}
