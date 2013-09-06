package com.example.howlertodolist;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class JSONActivity extends FragmentActivity {

	private static final String JSON_FRAGMENT_TAG = "jsonFragment";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);
		
		showFragment();
	}

	
	public void updateFragment(JSONObject json){
		FragmentManager fm = getSupportFragmentManager();
		JSONFragment frag = (JSONFragment)fm.findFragmentByTag(JSON_FRAGMENT_TAG);
		frag.JsonFetchCompleted(json);
	}
	
	private void showFragment(){
		
		Utils.log("loading main Fragment");
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm.findFragmentById(R.id.jsonActivity);
		
		if(frag == null){
			
			frag = new JSONFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.jsonActivity, frag, JSON_FRAGMENT_TAG);
			ft.commit();
			
		}
	}

}
