package com.example.howlertodolist;

import java.io.File;
import java.util.UUID;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ItemFragment extends Fragment {
	
	private static final String ID_EXTRA = "howler.extra.id";
	private static final String INDEX_EXTRA = "howler.extra.index";
	private Item mItem;
	
	private EditText mTitle;
	private CheckBox mCompletedChecbox;
	private ImageView mImageView;
	
	private static final int TAKE_PICTURE = 0;
	private Uri mUri;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		Bundle extras = getActivity().getIntent().getExtras();
		
		if(extras.getSerializable(ID_EXTRA) != null){
			UUID id = (UUID)extras.getSerializable(ID_EXTRA);
			mItem = ItemManager.getInstance().getById(id);
		} else {
			int index = (int)extras.getInt(INDEX_EXTRA);
			mItem = ItemManager.getInstance().getItemAtIndex(index);
		}
		
		Utils.log("loaded item:" + mItem.toString());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_item, container, false);
		
		// setting references
		setReferences(view);
		
		// updating layout
		updateLayoutElements();
		
		//adding listners
		addAllListeners();
		
		
		return view;
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.item, menu);
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		 switch (requestCode) {
	        case TAKE_PICTURE:
	            if (resultCode == Activity.RESULT_OK) {
	                getActivity().getContentResolver().notifyChange(mUri, null);
	                ContentResolver cr = getActivity().getContentResolver();
	                try {
	                    Bitmap photo = android.provider.MediaStore.Images.Media.getBitmap(cr, mUri);
	                    mImageView.setImageBitmap(Bitmap.createScaledBitmap(photo, 320, 200, false));
	                    mItem.setImageUri(mUri);
	                } catch (Exception e) {
	                   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
	                }
	            }
	        }
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		boolean ret = false;
		
		switch (item.getItemId()) {
		
		case R.id.action_delete_item:
			
			ItemManager.getInstance().removeItem(mItem);
			mItem = null;
			getActivity().finish();
			
			ret = true;
			break;
			
		case R.id.action_take_item_photo:
			
			
			Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
	        File f = new File(Environment.getExternalStorageDirectory(),  mItem.getId().toString() +"photo.jpg");
	        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
	        mUri = Uri.fromFile(f);
	        startActivityForResult(i, TAKE_PICTURE);
			
			
			break;
		default:
			//do nothing
			break;
		}
		
		return ret;
	}
	
	private void setReferences(View view){
		
		mTitle = (EditText)view.findViewById(R.id.label_edit_title);
		mCompletedChecbox = (CheckBox)view.findViewById(R.id.checkbox_completed);
		mImageView = (ImageView)view.findViewById(R.id.imageView_item);
		
	}
	
	private void updateLayoutElements(){
		
		mCompletedChecbox.setChecked(mItem.isCompleted());
		
		String title = mItem.getTitle();
		
		if(!title.equals("please add title") && title.length() > 0){
			mTitle.setText(title);
		}
		
		if(mItem.getImageUri() != null){
			ContentResolver cr = getActivity().getContentResolver();
			try{
				Bitmap photo = android.provider.MediaStore.Images.Media.getBitmap(cr, mItem.getImageUri());
				mImageView.setImageBitmap(Bitmap.createScaledBitmap(photo, 320, 200, false));
			}catch(Exception e){
				Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
		}
		
	}
	
	
	private void addAllListeners(){
		
		// on text change
		mTitle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				mItem.setTitle(text.toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence text, int arg1, int arg2,
					int arg3) {
				
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// nothing
				
			}
		});
		
		// on checkbox value changed
		mCompletedChecbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean checked) {
				mItem.setCompleted(checked);
				Utils.log("checked: "+ checked);
				
			}
		});
		
	}

} // end class
