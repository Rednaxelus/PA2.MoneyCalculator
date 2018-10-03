package moneycalculator.model;

public enum Currency {
    
    EUR("EUR","euros","€"),
    GBP("GBP","libras","£"),
    USD("USD","dolares","$");

    private String isoCode; 
    private String name; 
    private String symbol; 

    private Currency(String isoCode, String name, String symbol) {
        this.isoCode = isoCode;
        this.name = name;
        this.symbol = symbol;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

}