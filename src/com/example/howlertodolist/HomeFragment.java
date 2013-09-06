package com.example.howlertodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
	
	
	private Button mToDoListButton;
	private Button mGetJsonButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		
		addAllReferences(view);
		setAllListeners();
		
		return view;
		
	}
	
	// add all references: buttons, views, etc
	private void addAllReferences(View view){
		
		mToDoListButton = (Button)view.findViewById(R.id.button_to_do_list);
		mGetJsonButton = (Button)view.findViewById(R.id.button_get_json);
	}
	
	// set event listeners
	private void setAllListeners(){
		
		// go to To do list
		
		mToDoListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity(), ItemListActivity.class);
				startActivity(intent);
				
			}
		});
		
		mGetJsonButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(getActivity(), JSONActivity.class);
				startActivity(intent);
			}
		});
	}
	
} // end clas
