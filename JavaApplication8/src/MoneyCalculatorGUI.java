
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import moneycalculator.MoneyCalculator;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import org.json.JSONObject;

public class MoneyCalculatorGUI{
    
    public double getResult(String from, String to) throws MalformedURLException, IOException{
                // Setting URL
String url_str = "https://api.exchangerate-api.com/v4/latest/" + from;

// Making Request
URL url = new URL(url_str);
HttpURLConnection request = (HttpURLConnection) url.openConnection();
request.connect();

    // read from the URL
    Scanner scan = new Scanner(url.openStream());
    String str = new String();
    while (scan.hasNext())
        str += scan.nextLine();
    scan.close();

    
    // build a JSON object
    JSONObject obj = new JSONObject(str);


    // get the first result
    double res = obj.getJSONObject("rates").getDouble(to);       
        return res;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private static ExchangeRate getExchangeRate(Currency from, Currency to) 
            throws IOException {
      
        
        // Setting URL
String url_str = "https://api.exchangerate-api.com/v4/latest/" + from.getIsoCode();

// Making Request
URL url = new URL(url_str);
HttpURLConnection request = (HttpURLConnection) url.openConnection();
request.connect();

    // read from the URL
    Scanner scan = new Scanner(url.openStream());
    String str = new String();
    while (scan.hasNext())
        str += scan.nextLine();
    scan.close();
    
    
        System.out.println(str);
    
    // build a JSON object
    JSONObject obj = new JSONObject(str);


    // get the first result

    double res = obj.getJSONObject("rates").getDouble("EUR");
        System.out.println("result: " + res);
        
        return new ExchangeRate(from, to, 
                    LocalDate.now(), 
                    res);
        }
    }
