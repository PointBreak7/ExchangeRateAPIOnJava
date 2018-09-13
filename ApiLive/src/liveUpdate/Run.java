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
    //essential URL structure is built using constants
    //*declarations
    public static final String ACCESS_KEY="e8418a5b954a01c1543f7012dd112340",
            BASE_URL = "http://apilayer.net/api/",
            ENDPOINT = "live";
    
    //this object is used for executing requests to the (REST) API
    static CloseableHttpClient httpClient = HttpClients.createDefault();
    
    /*
    Notes:
    
    A JSON response of the form {"key":"rvalue"} is considered a simple Java JSONObject.
    To get a simple value from the JSONObject, use: <JSONObject identifier>.get<type>("key");
    
    A json response of the form {"key":{"key":"value"}} is considered a complex Java JSONObject.
    To get a complex value like another JSONObject, use: <JSONObject identifier>.getJSONObject("key");
    
    Values can also be JSONArray Objects. JSONArray objects are simple, consisting of multiple JSONObject Objects
    
    
    */
    
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
            
            //parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
            
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
    
    public static void main(String[] args) throws IOException {
        sendLiveRequest();
        httpClient.close();
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
