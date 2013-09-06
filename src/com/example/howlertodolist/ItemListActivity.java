package com.example.howlertodolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ItemListActivity extends FragmentActivity {
	
	private static final String LIST_FRAGMENT_TAG = "listFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);
		
		showFragment();
	}


	
	private void showFragment(){
		
		Utils.log("showing item list fragment");
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm.findFragmentById(R.id.ItemListActivity);
		
		if(frag == null){
			
			frag = new ItemListFragment();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.ItemListActivity, frag, LIST_FRAGMENT_TAG);
			ft.commit();
			
		}
	}

}
