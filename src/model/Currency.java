package model;

public enum Currency {

    EUR("EUR", "€"),
    USD("USD", "$"),
    CAD("CAD", "$"),
    AUD("AUD", "$"),
    JPY("JPY", "¥"),
    CNY("CNY", "¥"),
    GBP("GBP", "£");

    private final String isoCode;
    private final String symbol;

    private Currency(String isoCode, String symbol) {
        this.isoCode = isoCode;
        this.symbol = symbol;

    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public static String[] obtainCurrencyISOCodes() {
        Currency[] currencies = Currency.values();
        String[] str = new String[currencies.length];

        for (int i = 0; i < currencies.length; i++) {
            str[i] = currencies[i].getIsoCode();
        }

        return str;
    }

}
