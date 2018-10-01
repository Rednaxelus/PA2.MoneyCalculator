package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception {
      MoneyCalculator moneyCalculator = new MoneyCalculator();
      moneyCalculator.execute();       
    }

    private Map<String, Currency> currencies = new HashMap<>();
    private Money money;
    private Currency currencyTo;
    private ExchangeRate exchangeRate;

    public MoneyCalculator() {
        currencies.put("USD", new Currency("USD","Dolar americano","$"));
        currencies.put("EUR", new Currency("EUR","Euro","€"));        
        currencies.put("GBP", new Currency("GBP","Libra","£"));        
    }
    
    private void execute() throws Exception{
        input();
        process();
        output();
    }

    private void input(){
        System.out.println("Introduzca cantidad");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduzca codigo divisa origen");
        Currency currency = currencies.get(scanner.next().toUpperCase());   

        money = new Money(amount, currency);
        
        System.out.println("Introduzca codigo divisa destino");
        currencyTo = currencies.get(scanner.next().toUpperCase());
    }
    
    private void process() throws Exception{
        exchangeRate = getExchangeRate(money.getCurrency(), currencyTo);
    }
    
    private void output(){
        System.out.println(money.getAmount() + " "
                + money.getCurrency().getSymbol() + " equivalen a " 
                + money.getAmount() * exchangeRate.getRate() + " "
                + currencyTo.getSymbol());
    }
    
    private static ExchangeRate getExchangeRate(Currency from, Currency to) 
            throws IOException {
        URL url = 
            new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" +
                    from.getCode() + "_" + to.getCode() + "&compact=y");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to.getCode())+12, 
                    line.indexOf("}"));
            return new ExchangeRate(from, to, 
                    LocalDate.of(2018, Month.SEPTEMBER, 26), 
                    Double.parseDouble(line1));
        }
    }

}