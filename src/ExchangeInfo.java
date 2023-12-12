public class ExchangeInfo {
    private double exchangedAmount;
    private double rate;
    private String date;
    private String baseCurrency;
    private String targetCurrency;

    ExchangeInfo(double exchangedAmount, double rate, String baseCurrency, String targetCurrency, String date) {
        this.exchangedAmount = exchangedAmount;
        this.rate = rate;
        this.date = date;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getExchangedAmount() {
        return exchangedAmount;
    }

    public double getRate() {
        return rate;
    }

    public String getDate() {
        return date;
    }
}