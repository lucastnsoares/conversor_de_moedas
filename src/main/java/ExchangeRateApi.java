import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ExchangeRateApi {


    public double taxaDeCambio (String moedaBase, String moedaDestino) throws IOException  {
        String apiKey = System.getenv("API_KEY");
        String url_str = "https://v6.exchangerate-api.com/v6/"+ apiKey + "/pair/" + moedaBase + "/" + moedaDestino;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        int status = request.getResponseCode();
        InputStream stream;

        if (status >= 200 && status < 300) {
            stream = request.getInputStream();
        } else {
            stream = request.getErrorStream();
        }

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader(stream));
        JsonObject jsonobj = root.getAsJsonObject();
        String reqResult = jsonobj.get("result").getAsString();
        if (reqResult.equals("error")){
            String reqErrorType = jsonobj.get("error-type").getAsString();
            switch (reqErrorType) {
                case "invalid-key":
                    throw new ApiException("""
                            
                            >>>>>> ERRO <<<<<<
                            
                            API Key inválida.
                            
                            """);
                case "inactive-account":
                    throw new ApiException("""
                            
                            >>>>>> ERRO <<<<<<
                            
                            ERRO: E-mail não validado junto ao provedor da API.
                            
                            """);
                case "unsupported-code":
                    throw new ApiException("""
                            
                            >>>>>> ERRO <<<<<<
                            
                            ERRO: Alguma das moedas selecionadas não possui suporte pela API.
                            
                            """);
                case "quota-reached":
                    throw new ApiException("""
                            
                            >>>>>> ERRO <<<<<<
                            
                            ERRO: A cota de consulta do plano escolhido foi atingida.
                            
                            """);
                case "malformed-request":
                    throw new ApiException("""
                            
                            >>>>>> ERRO <<<<<<
                            
                            ERRO: Parâmetros inválidos enviados na URL.
                            
                            """);
            }
        }
        return jsonobj.get("conversion_rate").getAsDouble();
    }
}
