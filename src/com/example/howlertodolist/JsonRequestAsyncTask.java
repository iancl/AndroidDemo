package com.example.howlertodolist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;

public class JsonRequestAsyncTask extends AsyncTask<Void, Void, JSONObject> {
	
	private Activity mActivity;
	
	static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
	
	public JsonRequestAsyncTask(Activity a){
		mActivity = a;	
	}
	
	
	@Override
	protected JSONObject doInBackground(Void... arg0) {
		
		if(isCancelled()) return null;
	     // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://api.androidhive.info/contacts/");
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
            if(isCancelled()) return null;
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(isCancelled()) return null;
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Utils.log("Buffer Error" + "Error converting result " + e.toString());
        }
        
        if(isCancelled()) return null;
        
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Utils.log("JSON Parser" + "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
		
	
	@Override
	protected void onProgressUpdate(Void... values) {
		Utils.log("update");
	}
	
	@Override
	protected void onPostExecute(JSONObject result) {
		Utils.log(result.toString());
		
		((JSONActivity)mActivity).updateFragment(result);
		
		
	}
}
