package com.example.a14049472.oxforddictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2/4/2018.
 */

public class MyRequestAsync extends AsyncTask<String,Integer,String> {



    Context context;
    TextView t1;

//constructor
    MyRequestAsync(Context context,TextView t1){
        this.context=context;
        this.t1=t1;



    }

    @Override
    protected String doInBackground(String... params) {
        String word =params[0];
        String url="https://od-api.oxforddictionaries.com:443/api/v1/entries/"+"en"+"/"+word;
        final String app_id = "d1b1954b";
        final String app_key = "3c36ae03f2ae045c1f7f6ff13659a9b6";
        String result="";



        try {
            URL myurl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) myurl.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);


            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // string append and insert string to string builder
            StringBuilder stringBuilder = new StringBuilder();

            String line ;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

           result=stringBuilder.toString();

        }
        catch (IOException e) {
            e.printStackTrace();

        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String def;

        try{
            JSONObject js=new JSONObject(s);
            JSONArray results=js.getJSONArray("results");

            JSONObject lEntries=results.getJSONObject(0);
            JSONArray laArray=lEntries.getJSONArray("lexicalEntries");

            JSONObject entries=laArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject=e.getJSONObject(0);
            JSONArray sensesArray=jsonObject.getJSONArray("senses");

            JSONObject d =sensesArray.getJSONObject(0);
            JSONArray de= d.getJSONArray("definitions");

            def=de.getString(0);

          t1.setText(def);
            Toast.makeText(context,def,Toast.LENGTH_LONG).show();



        }catch(JSONException e){
            e.printStackTrace();
        }





    }
}
