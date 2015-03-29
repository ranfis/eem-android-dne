package com.eem.apps.enelmall.api;

import android.os.AsyncTask;
import android.util.Log;
import com.eem.apps.enelmall.api.model.MockOffers;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataApiCall extends AsyncTask<String, String, String> {

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        Log.d("DataApiCall","convertInputStreamToString()");
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("DataApiCall","doInBackground()");
        return getOffersFromMock();
    }

    private String getOffersFromMock(){
        Log.d("DataApiCall","getOffersFromMock()");
        return MockOffers.getOffers(1000);
    }

    protected String getOffersFromApi(String... params) {
        Log.d("DataApiCall","getOffersFromApi()");
        String urlString=params[0];
        InputStream inputStream = null;
        String result = "";
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(urlString));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
            }
            else {
                Log.w("httpResponse","Empty result.");
                result = "{}";
            }

        } catch (IOException e) {
            Log.e("DataApiCall:getOffersFromApi():IOException",e.getLocalizedMessage());
        }

        return result;
    }

    public static Object parseJson(String json){
        try{
            Object jsonObj = null;
            switch (json.charAt(0)){
                case '[':
                    jsonObj = new JSONArray(json);
                    Log.v("DataApiCall:parseJson","Received a JSON Array.");
                    break;
                case '{':
                    jsonObj = new JSONObject(json);
                    Log.v("DataApiCall:parseJson","Received a JSON Object.");
                    break;
            }
            return jsonObj;
        }
        catch(JSONException err){
            Log.e("DataApiCall:getOffersFromApi():JSONException","Bad JSON");
            return null;
        }
    }
}