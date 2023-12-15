import java.security.KeyStore;

public enum Currencies {
    USD ("US Dollar"),
    GBP ("British Pounds"),
    EUR ("Euro"),
    SEK ("Svensk Krona"),
    NOK ("Norsk Krona"),
    DKK ("Dansk Krona"),
    RUB ("Russian ruble"),
    INR ("Indian rupee"),
    CNY ("Chinese yuan"),
    JPY ("Japanese yen"),
    KRW ("South Korean won"),
    IDR ("Indonesian rupiah"),
    THB ("Thai baht"),
    MYR ("Malaysian ringgit"),
    VND ("Vietnamese dong"),
    BRL ("Brazilian real"),
    ZAR ("South African rand"),
    CAD ("Canadian dollar"),
    AUD ("Australian dollar"),
    NZD ("New Zealand dollar"),
    HKD ("Hong Kong dollar"),
    SGD ("Singapore dollar"),
    CHF ("Swiss franc"),
    MXN ("Mexican peso"),
    PHP ("Philippine peso"),
    PLN ("Polish zloty"),
    TRY ("Turkish lira");

    public final String fullName;

     Currencies(String s){
         fullName = s;
     }
}
