import API.API;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        API sekEUR = new API();

        System.out.println(sekEUR.getConvertedValue(Currency.USD, Currency.SEK, 1000.0));
        }
    }
