package com.se.cronus;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.se.cronus.utils.*;
public class MainActivity extends Activity {

	LinearLayout parent;
	boolean ifTrue;
	ListView FeedList;
	FeedAdapter feedAdapt;
	Feed[] FeedArray;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FeedArray = new Feed[]{new Feed(this,CUtils.FACEBOOK_FEED),new Feed(this,CUtils.FACEBOOK_FEED),new Feed(this,CUtils.FACEBOOK_FEED)};
		//parent = (LinearLayout) findViewById(android.R.id.content);
		FeedList = (ListView) findViewById(R.id.feedlist);
		feedAdapt= new FeedAdapter(this, FeedArray);
		FeedList.setAdapter(feedAdapt);
		
		
		
		//parent.addView(child);
	}
	
	
	
	public void addFeed(Feed f){
		 feedAdapt.add(f);;
		 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
