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

    public void setApiExchangeInput(Enum<Currencies> baseCurrency, Enum<Currencies> targetCurrencyEnum, double amount) {
        double exhangedAmount = 0;
        try {
            String targetCurrency = targetCurrencyEnum.toString();
            URL url = new URL(API_URL + baseCurrency);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            JsonObject jsonobj = getJsonObject(request);

            JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");
            double rate = rates.get(targetCurrency).getAsDouble();

            double exchangedAmount = rate * amount;

            ExchangeInfo info = new ExchangeInfo(exchangedAmount, rate,baseCurrency.toString(), targetCurrency, jsonobj.get("time_last_update_unix").getAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Map<String, ExchangeInfo> currencyExtractedInfoMap(ExchangeInfo info) {

        Map<String, ExchangeInfo> exchangeInfoMap = new HashMap<>();

        exchangeInfoMap.put(info.getTargetCurrency(), info);

        return exchangeInfoMap;
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

}
