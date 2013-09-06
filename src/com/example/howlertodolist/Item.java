package com.example.howlertodolist;

import java.util.Date;
import java.util.UUID;

import android.net.Uri;

public class Item {
	
	private String mTitle;
	private Date mDateCreated;
	private Uri mImageUri;
	private boolean mCompleted;
	private UUID mId;
	
	public Item(){
		mTitle = "please add title";
		mDateCreated = new Date();
		mCompleted = false;
		mId = UUID.randomUUID();
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public Uri getImageUri() {
		return mImageUri;
	}

	public void setImageUri(Uri imageUri) {
		this.mImageUri = imageUri;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean completed) {
		this.mCompleted = completed;
	}

	public Date getDateCreated() {
		return mDateCreated;
	}
	
	public UUID getId(){
		return mId;
	}
	
	
	public String toString(){
		return this.getTitle();
	}
	
	
}
