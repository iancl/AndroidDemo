package com.example.howlertodolist;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utils {
	
	private static Utils sInstance;
	private static final String LOG_TAG = "howler.logTag";
	
	public static Utils getInstance(){
		
		if(sInstance == null){
			sInstance = new Utils();
		}
		
		return sInstance;
	}
	
	// to log
	public static void log(String msg){
		Log.d(LOG_TAG, msg);
	}
	
	public static boolean isNetworkAvailable(Activity activity) {
		ConnectivityManager cm = (ConnectivityManager) 
			      activity.getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			    // if no network is available networkInfo will be null
			    // otherwise check if we are connected
			    if (networkInfo != null && networkInfo.isConnected()) {
			        return true;
			    }
			    return false;
	}
	
}// end class
