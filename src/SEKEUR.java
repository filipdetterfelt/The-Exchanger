import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class SEKEUR {
    private static final String API_KEY = "c419a2f16c0967ee3eaa58f1";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/SEK";

    public static void main(String[] args) {
        try {
            // Skapa URL-objekt
            URL url = new URL(API_URL);

            // Öppna anslutning
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

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

            // Hämta växelkursen
            JsonObject rates = jsonobj.getAsJsonObject("conversion_rates");
            double rate = rates.get("EUR").getAsDouble();

            double usdAmount = 100.0; // Exempelbelopp i USD
            double sekAmount = usdAmount * rate;

            System.out.println(usdAmount + " USD är " + sekAmount + " SEK.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}