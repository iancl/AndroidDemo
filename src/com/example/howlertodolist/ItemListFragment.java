package com.example.howlertodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemListFragment extends ListFragment {
	
	private static final String ID_EXTRA = "howler.extra.id";
	private static final String INDEX_EXTRA = "howler.extra.index";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		
		ItemManager im = ItemManager.getInstance();
		
		ItemListAdapter adapter = new ItemListAdapter(im.getAllItems(), getActivity());
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		ItemListAdapter adapter = (ItemListAdapter)getListAdapter();
		adapter.notifyDataSetChanged();
		
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.item_list, menu);
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent(getActivity(), ItemActivity.class);
		
		intent.putExtra(INDEX_EXTRA, position);
		
		startActivity(intent);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		boolean ret = false;
		
		switch (item.getItemId()) {
		
		case R.id.action_add_item:
			
			addAndShowItem();
			
			ret = true;
			break;
			
		default:
			//do nothing
			break;
		}
		
		return ret;
	}
	
	private void addAndShowItem(){
		Intent intent = new Intent(getActivity(), ItemActivity.class);
		Item item = new Item();
		ItemManager.getInstance().addItem(item);
		
		intent.putExtra(ID_EXTRA, item.getId());
		
		startActivity(intent);
	}
	
} // end class
