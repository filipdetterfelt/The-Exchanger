import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class API /*implements APIConnection*/{
    private static final String API_KEY = "c419a2f16c0967ee3eaa58f1";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    //inner class
    public class ExchangeInfo {
        private double exchangedAmount;
        private double rate;
        private String date;

        ExchangeInfo(double exchangedAmount, double rate, String date) {                       //omvandlad valuta mängd, kurs och datum det hände
            this.exchangedAmount = exchangedAmount;
            this.rate = rate;
            this.date = date;
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

    public Map<String, ExchangeInfo> setApiExchangeInput(Enum<Currencies> baseCurrency, Enum<Currencies> targetCurrencyEnum, double amount) {
        double exhangedAmount = 0;
        Map<String, ExchangeInfo> exchangeInfoMap = new HashMap<>();
        try {
            String targetCurrency = targetCurrencyEnum.toString();
            URL url = new URL(API_URL + baseCurrency);                                   //gör URL beroende på basecurrency, om det är URL till USD eller SEK t.ex.

            HttpURLConnection request = (HttpURLConnection) url.openConnection();                 //öppnar connection, castar till httpURLconnection
            request.setRequestMethod("GET");                                                            //hämtar exchange data
            request.connect();                                                                          //till specific URL

            JsonObject jsonobj = getJsonObject(request);                                             //sparar som JsonObject, kallar på metoden getJsonObject med request som parameter

            JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");          //parsar rates till ett Jsonobjekt?
            double rate = rates.get(targetCurrency).getAsDouble();                              //rate är här "exchangerate", får targetcurrency som en double

            double exchangedAmount = rate * amount;                                                //mängden vi får efter exchangen har hänt, är exchangedamount

            ExchangeInfo info = new ExchangeInfo(exchangedAmount, rate, jsonobj.get("time_last_update_unix").getAsString());   //skickar in mängd, kurs och datum det hände
            exchangeInfoMap.put(targetCurrency, info);                                                        //här läggs currency vi konvererar till, och ovanstående info in i hasmappen

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exchangeInfoMap;        //metoden returnar mappen
    }

    private static JsonObject getJsonObject(HttpURLConnection request) throws IOException {
        InputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        // Konvertera svaret till en sträng
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Använd GSON-biblioteket för att konvertera strängen till ett JSON-objekt
        JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(response.toString());
        JsonObject jsonobj = root.getAsJsonObject();
        return jsonobj;
    }

    public void printExchangeInfo(Enum<Currencies> baseCurrency, Enum<Currencies> targetCurrencyEnum, double amount) {
        Map<String, API.ExchangeInfo> exchangeInfoMap = setApiExchangeInput(baseCurrency, targetCurrencyEnum, amount);   //skapar hashmap, skickar in input vi vill ändra. Får ut valuta vi vill konvertera till, mängden vi konverterar till, kursen och datum.
        String targetCurrency = targetCurrencyEnum.toString();

        API.ExchangeInfo info = exchangeInfoMap.get(targetCurrency);          //vår valuta vi vill konvertera till får vi ut, är info

        if (info != null) {
            double exchangedAmount = info.getExchangedAmount();                 //får mängd
            double rate = info.getRate();                                       //får kurs
            String dateOfExchange = info.getDate();                             //datum det hände

            System.out.println("Exchanged Amount: " + exchangedAmount);
            System.out.println("Rate: " + rate);
            System.out.println("Date of Exchange: " + dateOfExchange);
        } else {
            System.out.println("No exchange information available for the specified currency.");
        }
    }

}
