package presenter;

import model.Currency;
import model.ExchangeRate;
import view.ExchangeRateCalculator;
import view.View;
import view.ExchangeRateLoader;

public class CalculationDelegation implements CustomObserver {

    private final View view;
    private final ExchangeRateLoader exchangeLoader;
    private final ExchangeRateCalculator exchangeRateCalculator;

    public CalculationDelegation(View view, ExchangeRateLoader exchangeLoader, ExchangeRateCalculator exchangeRateCalculator) {
        this.view = view;
        this.exchangeLoader = exchangeLoader;
        this.exchangeRateCalculator = exchangeRateCalculator;
    }

    @Override
    public void buttonPressed() {

        Currency from = Currency.valueOf(view.obtainCurrencyFrom());
        Currency to = Currency.valueOf(view.obtainCurrencyTo());

        ExchangeRate exchangeRate = exchangeLoader.getExchangeRate(from, to);

        String resultString;
        if (exchangeRate == null) {
            resultString = "failure to obtain exchange-rate";
        } else {
            resultString = exchangeRateCalculator.giveResultAsText(exchangeRate, view.getAmount());
        }

        view.showResultOfCalculation(resultString);
    }

}
