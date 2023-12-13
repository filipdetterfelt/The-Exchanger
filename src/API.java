import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;

public class API implements Observed {
    private static final String API_KEY = "c419a2f16c0967ee3eaa58f1";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private final ArrayList<Subscriber> listOfObservers = new ArrayList<>();

    public void setApiExchangeInput(Enum<Currencies> baseCurrency, Enum<Currencies> targetCurrencyEnum, double amount) {

        try {
            String targetCurrency = targetCurrencyEnum.toString();
            URL url = new URL(API_URL + baseCurrency);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            JsonObject jsonObj = getJsonObject(request);
            JsonObject rates = jsonObj.getAsJsonObject("conversion_rates");
            double rate = rates.get(targetCurrency).getAsDouble();
            double exchangedAmount = rate * amount;

            ExchangeInfo info = new ExchangeInfo(exchangedAmount, rate, baseCurrency.toString(), targetCurrency, jsonObj.get("time_last_update_unix").getAsString());
            notifySubscriber(info);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    /*public Map<String, ExchangeInfo> currencyExtractedInfoMap(ExchangeInfo info) {

        Map<String, ExchangeInfo> exchangeInfoMap = new HashMap<>();

        exchangeInfoMap.put(info.getTargetCurrency(), info);


        return exchangeInfoMap;
    }*/

    private static JsonObject getJsonObject(HttpURLConnection request) throws IOException {
        /*InputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        */
        ////Skrev om bort kommenterade rader över till undre rad
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Konvertera svaret till en sträng
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Använd GSON-biblioteket för att konvertera strängen till ett JSON-objekt
        /*JsonParser parser = new JsonParser();
        JsonElement root = parser.parse(response.toString());
        JsonObject jsonobj = root.getAsJsonObject();
        return jsonobj;*/
        //Skrev om bort kommenterade rader över till undre rader
        JsonElement root = JsonParser.parseString(response.toString());
        return root.getAsJsonObject();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.listOfObservers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        this.listOfObservers.remove( subscriber);
    }

    @Override
    public void notifySubscriber(ExchangeInfo info) {
        for (Subscriber subscriber : listOfObservers) {
            subscriber.update(info);
        }
    }
}
