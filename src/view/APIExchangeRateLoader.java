/*
 * For questions about licensing ask.
 */
package view;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Currency;
import model.ExchangeRate;
import org.json.JSONObject;

/**
 *
 * @author Alex
 */
public class APIExchangeRateLoader implements ExchangeRateLoader {

    final static String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private String makeRequest(String url_str) throws MalformedURLException, IOException {

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
        return str;
    }

    @Override
    public ExchangeRate getExchangeRate(Currency from, Currency to) {

        // Setting URL
        String url_str = API_URL + from;

        // build a JSON object
        try {
            JSONObject obj = new JSONObject(makeRequest(url_str));
            // get the result
            return new ExchangeRate(from, to, obj.getJSONObject("rates").getDouble(to.getIsoCode()));
        } catch (IOException ex) {
            Logger.getLogger(APIExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
