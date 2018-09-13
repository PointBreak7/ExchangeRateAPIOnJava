package liveUpdate;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Run {
    //*declarations
    public static final String ACCESS_KEY="<Your_Access_Key_Here",
            BASE_URL = "http://apilayer.net/api/",
            ENDPOINT = "live";
    static CloseableHttpClient httpClient = HttpClients.createDefault();
   
    public static void main(String[] args) throws IOException {
        sendLiveRequest();
        httpClient.close();
    }
    
    //sendLiveRequest() function is created to request and retrieve the data
    public static void sendLiveRequest(){
        
        //the following line initialiizes the HttpGet Object with the URL in order to send a request
        HttpGet get = new HttpGet(BASE_URL+ENDPOINT+"?access_key="+ACCESS_KEY);
        
        try{
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            
            //the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates= new JSONObject(EntityUtils.toString(entity));
            
            System.out.println("Live Currency Exchange Rates");
            
            //the JSONObject that carries the data is turned to string and passed to a method to format the console printing
            printFormatedObect(exchangeRates.toString());
            
            System.out.println("\n");
            response.close();
            
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    
    public static void printFormatedObect(String exchangeRate){
        for (int x=0;x<exchangeRate.length();x++){
            if (exchangeRate.charAt(x) == ','){
                System.out.println("");
            } else {
                System.out.print(exchangeRate.charAt(x));
            }
        }
    }
    
}
