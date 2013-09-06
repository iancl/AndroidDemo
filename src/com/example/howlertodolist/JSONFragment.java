package com.example.howlertodolist;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JSONFragment extends Fragment {
	
	private Button mGetJsonButton;
	private EditText mJsonOutput;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_json, container, false);
		
		mGetJsonButton = (Button)view.findViewById(R.id.button_fetch_json);
		mJsonOutput = (EditText)view.findViewById(R.id.label_json_output);
		
		mGetJsonButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean isNetworkAvailable = Utils.isNetworkAvailable(getActivity());
				
				if(isNetworkAvailable){
					Toast.makeText(getActivity(), "internet is available", Toast.LENGTH_LONG).show();
					
					new JsonRequestAsyncTask(getActivity()).execute();
					
					//Utils.log("json" + json.toString());
				} else {
					Toast.makeText(getActivity(), "internet not available", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		
		return view;
		
	}
	
	public void JsonFetchCompleted(JSONObject json){
		mJsonOutput.setText(json.toString());
	}

}
