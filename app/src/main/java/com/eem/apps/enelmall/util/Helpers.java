package com.eem.apps.enelmall.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Helpers {
    protected static final String TAG = "[Helpers]";

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        Log.d(TAG, "convertInputStreamToString()");
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
    }
}