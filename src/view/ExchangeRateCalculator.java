/*
 * For questions about licensing ask.
 */
package view;

import model.ExchangeRate;

/**
 *
 * @author Alex
 */
public class ExchangeRateCalculator {

    public String giveResultAsText(ExchangeRate exchangeRate, double amount) {

        return String.format("%.2f", calculateResult(exchangeRate, amount)) + exchangeRate.getTo().getSymbol();

    }

    private double calculateResult(ExchangeRate exchangeRate, double amount) {

        return exchangeRate.getRate() * amount;
    }

}
