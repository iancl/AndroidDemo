package com.example.howlertodolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
	
	private static final String HOME_FRAGMENT_TAG = "homeFragment";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		showFragment();
	}


	
	private void showFragment(){
		
		Utils.log("loading main Fragment");
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm.findFragmentById(R.id.MainActivity);
		
		if(frag == null){
			
			frag = new HomeFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.MainActivity, frag, HOME_FRAGMENT_TAG);
			ft.commit();
			
		}
	}
	
}
