package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;
import moneycalculator.model.Money;

public class MoneyCalculator {

    public static void main(String[] args) throws Exception {
      MoneyCalculator moneyCalculator = new MoneyCalculator();
      moneyCalculator.execute();       
    }

    private Money money;
    private ExchangeRate exchangeRate;
    private Currency currencyFrom;
    private Currency currencyTo;
    
    private void execute() throws Exception{
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
                + " equivalen a " + money.getAmount() * exchangeRate.getRate()
                + " " + currencyTo.getSymbol());
    }
    
    private static ExchangeRate getExchangeRate(Currency from, Currency to) 
            throws IOException {
        URL url = 
            new URL("http://free.currencyconverterapi.com/api/v5/convert?q=" +
                    from.getIsoCode() + "_" + to.getIsoCode() + "&compact=y");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = 
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to.getIsoCode())+12, 
                    line.indexOf("}"));
            return new ExchangeRate(from, to, 
                    LocalDate.of(2018, Month.OCTOBER, 3), 
                    Double.parseDouble(line1));
        }
    }
}