package view;

/*
 * For questions about licensing ask.
 */

/**
 *
 * @author Alex
 */
public interface View extends CustomObservable{

    public String obtainCurrencyFrom();

    public String obtainCurrencyTo();

    public void showResultOfCalculation(String result);

    public double getAmount();

}
