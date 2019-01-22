package com.tw.moneyconversiontask;

public enum ExchangeRate {

	USD_GBP(0.775), GBP_USD(1.289), JPY_USD(0.009), USD_JPY(109.63), GBP_JPY(141.33), JPY_GBP(0.007);

	private double rate;

	ExchangeRate(double rate) {
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}
}
