public class PresentationView {

    private double amountTo;
    private double amountFrom;
    private double rate;
    private Enum<Currencies> currencyTo;
    private Enum<Currencies> currencyFrom;
    private String date;

    public double getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(double amountTo) {
        this.amountTo = amountTo;
    }

    public double getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(double amountFrom) {
        this.amountFrom = amountFrom;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Enum<Currencies> getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Enum<Currencies> currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Enum<Currencies> getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Enum<Currencies> currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getApiSource() {
        return apiSource;
    }

    public void setApiSource(String apiSource) {
        this.apiSource = apiSource;
    }

    String apiSource;
}


