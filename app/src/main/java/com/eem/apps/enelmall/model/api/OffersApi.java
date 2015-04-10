package com.eem.apps.enelmall.model.api;

import android.util.Log;
import com.eem.apps.enelmall.StartActivity;
import com.eem.apps.enelmall.model.Offer;
import com.eem.apps.enelmall.util.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class OffersApi extends DataApiCall {
    protected static final String TAG = "[OffersApi]";
    protected static final String API_URL = "http://104.236.25.160/api/offers";
    public static ArrayList<Offer> offers = new ArrayList<>();

    protected void onPostExecute(String result) {
        Log.d(TAG, "onPostExecute()");
        try {
            JSONArray jsonOffers = ((JSONArray)JsonParser.parse(result));
            for(int i=0;i<=jsonOffers.length()-1;i++){
                Offer offer = new Offer( (JSONObject)jsonOffers.get(i) );
                offers.add(offer);
            }
            StartActivity.goToOffers();
        }
        catch(JSONException err){
            Log.e(TAG, "onPostExecute()/JSONException:Bad JSON");
        }
    }

    public static ArrayList<Offer> getAll(){
        return offers;
    }
}