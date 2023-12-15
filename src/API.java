import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;

public class API implements Subject {
    private String apiKey = "c419a2f16c0967ee3eaa58f1";
    private final String API_URL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/";
    private final ArrayList<Observer> listOfObservers = new ArrayList<>();

    public void setApiExchangeInput(Enum<Currencies> baseCurrencyEnum, Enum<Currencies> targetCurrencyEnum, double conversionAmount) {

        try {
            String targetCurrency = targetCurrencyEnum.toString();
            URL url = new URL(API_URL + baseCurrencyEnum + "/" + targetCurrency + "/" + conversionAmount);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            JsonObject jsonObj = getJsonObject(request);
            double conversionResult = jsonObj.get("conversion_result").getAsDouble();
            double conversionRate = jsonObj.get("conversion_rate").getAsDouble();
            String timeLastUpdateUtc = jsonObj.get("time_last_update_utc").toString();
            String documentation = jsonObj.get("documentation").toString();
            String result = jsonObj.get("result").toString(); //Berättar om det gick att omvandla

            if (result.equalsIgnoreCase("\"success\"")) {
                ExchangeInfo info = new ExchangeInfo(conversionResult, conversionRate, baseCurrencyEnum, targetCurrencyEnum, timeLastUpdateUtc, documentation);
                notifyObserver(info);

            } else if (result.equalsIgnoreCase("quota-reached")) { //om API key är slut
                if (apiKey.equalsIgnoreCase("363262aeabe3f69bc894fef0")) {
                    System.out.println("account has reached the the number of requests allowed by your plan");
                    System.exit(0);
                }
                apiKey = "363262aeabe3f69bc894fef0";
                setApiExchangeInput(baseCurrencyEnum, targetCurrencyEnum, conversionAmount);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private JsonObject getJsonObject(HttpURLConnection request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        // Konvertera svaret till en sträng
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JsonElement root = JsonParser.parseString(response.toString());
        return root.getAsJsonObject();
    }

    @Override
    public void addObserver(Observer observer) {
        this.listOfObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.listOfObservers.remove(observer);
    }

    @Override
    public void notifyObserver(Object info) {
        for (Observer observer : listOfObservers) {
            observer.update(info);
        }
    }
}