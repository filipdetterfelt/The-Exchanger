import java.time.LocalDateTime;

public class CurrencyPair {

    String NAME_OF_ORIGINAL_CURRENCY;
    String nameOfExchangedCurrency;
    double exchangeRate;
    LocalDateTime dateOfExchange;


    public double calculateExhangedValue(double belopp, double exchangedCurrencyRate) {

        return belopp * exchangedCurrencyRate;
    }















}
