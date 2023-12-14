public class Object {
    private double exchangedAmount;
    private double rate;
    private double reverseRate;
    private String date;
    private Enum<Currencies> baseCurrency;
    private Enum<Currencies> targetCurrency;

    Object(double exchangedAmount, double rate, Enum<Currencies> baseCurrency, Enum<Currencies> targetCurrency, String date) {
        this.exchangedAmount = exchangedAmount;
        this.rate = rate;
        this.date = date;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Enum<Currencies> getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Enum<Currencies> baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Enum<Currencies> getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Enum<Currencies> targetCurrency) {
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
    public double getReverseRate() {
        reverseRate = convertRateToReverse(rate);
        return reverseRate;
    }
    public double convertRateToReverse(double rate){
        return reverseRate = 1/rate;
    }


}