package com.example.howlertodolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ItemActivity extends FragmentActivity {
	
	private static final String ITEM_FRAGMENT_TAG = "ItemFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		showFragment();
	}
	
	
private void showFragment(){
		
		Utils.log("loading Item Fragment");
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm.findFragmentById(R.id.ItemActivity);
		
		if(frag == null){
			
			frag = new ItemFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.ItemActivity, frag, ITEM_FRAGMENT_TAG);
			ft.commit();
			
		}
	}


}
