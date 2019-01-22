package com.tw.moneyconversiontask;

import java.util.Currency;
import java.util.Locale;

public class Account {

	int accountNumber;

	private Locale locale;

	public int getAmount() {
		return amount;
	}

	private int amount;

	public Currency getCurrency() {
		return c;
	}

	private Currency c;

	public Account(Locale locale, int amount, int accountNumber) {
		this.locale = locale;
		this.amount = amount;
		this.c = Currency.getInstance(locale);
		this.accountNumber = accountNumber;
	}

	public void balance() {
		System.out.println("Account is currently holding: " + amount + " " + c.getCurrencyCode());
	}

	public void withdraw(int w) {
		if (w <= amount) {
			amount -= w;
			System.out.println("Withdrawn amount in source currency: " + w + c.getCurrencyCode());
		}
		else {
			System.out.println("Insufficient funds.");
		}
	}
}
