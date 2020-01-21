package controller;


import view.CustomObserver;
import view.View;
import model.Currency;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ExchangeCalculator implements CustomObserver {

    private final View view;

    public ExchangeCalculator(View view) {
        this.view = view;
    }

    private double getExchangeRateResult(String from, String to) throws MalformedURLException, IOException {
        // Setting URL
        String url_str = "https://api.exchangerate-api.com/v4/latest/" + from;

        // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        String str;
        try ( // read from the URL
                Scanner scan = new Scanner(url.openStream())) {
            str = new String();
            while (scan.hasNext()) {
                str += scan.nextLine();
            }
        }

        // build a JSON object
        JSONObject obj = new JSONObject(str);

        // get the result
        double res = obj.getJSONObject("rates").getDouble(to);
        return res;
    }

    @Override
    public void buttonPressed() {

        Currency from = Currency.valueOf(view.obtainCurrencyFrom());
        Currency to = Currency.valueOf(view.obtainCurrencyTo());

        String resultStr = "failure to obtain exchange-rate";

        try {
            double result = getExchangeRateResult(from.getIsoCode(), to.getIsoCode());
            resultStr = String.format("%.2f", result * view.getAmount()) + to.getSymbol();
        } catch (IOException ex) {
            Logger.getLogger(ExchangeCalculator.class.getName()).log(Level.WARNING, null, ex);
        }

        view.showResultOfCalculation(resultStr);
    }

}
