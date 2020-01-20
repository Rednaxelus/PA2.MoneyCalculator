
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import moneycalculator.MoneyCalculator;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;
import org.json.JSONObject;

public class MoneyCalculatorText implements MoneyCalculator{


    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyFrom;
    private Currency currencyTo;
    
    @Override
    public void execute() throws Exception{
        input();
        process();
        output();
    }

    private void input(){
        System.out.println("Introduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduzca divisa origen");
        currencyFrom = Currency.valueOf(scanner.next().toUpperCase());//currencies.get(scanner.next().toUpperCase());
        
        money = new Money(amount, currencyFrom);

        System.out.println("Introduzca divisa destino");
        currencyTo = Currency.valueOf(scanner.next().toUpperCase());  
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(currencyFrom, 
                currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + " " + currencyFrom.getSymbol()
                + " convert to " + money.getAmount() * exchangeRate.getRate()
                + " " + currencyTo.getSymbol());
    }
    
    public static ExchangeRate getExchangeRate(Currency from, Currency to) 
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
