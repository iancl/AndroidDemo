package com.example.howlertodolist;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<Item> {
	
	private Activity mActivity;
	
	public ItemListAdapter(ArrayList<Item> allItems, Activity activity){
		super(activity, 0, allItems);
		mActivity = activity;
		
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = mActivity.getLayoutInflater().inflate(R.layout.custom_list_item, null);
		}
		
		// get item
		Item item = getItem(position);
		
		// set view references
		TextView title = (TextView)convertView.findViewById(R.id.custom_list_item_title);
		TextView completed = (TextView)convertView.findViewById(R.id.custon_list_item_completed);
		
		title.setText(item.getTitle());
		
		if(item.isCompleted()){
			completed.setText("completed");
			convertView.setBackgroundColor(Color.GREEN);
		} else {
			completed.setText("pending");
			convertView.setBackgroundColor(Color.RED);
		}
		
		return convertView;
		
	}

} // end class
