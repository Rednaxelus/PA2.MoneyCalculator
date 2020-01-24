/*
 * For questions about licensing ask.
 */
package view;

import model.Currency;
import model.ExchangeRate;

/**
 *
 * @author Alex
 */
public interface ExchangeRateLoader {

    int ECHANGE_LOADING_FAILED = -1;

    ExchangeRate getExchangeRate(Currency from, Currency to);
}
