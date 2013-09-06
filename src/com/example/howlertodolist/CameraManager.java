package com.example.howlertodolist;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

public class CameraManager {
	
	private Context mAppContext;
	private static final int TAKE_PICTURE = 0;
	private Uri mUri;
	private Bitmap mPhoto;
	
	public CameraManager(Context c){
		mAppContext = c;
	}
	
}
