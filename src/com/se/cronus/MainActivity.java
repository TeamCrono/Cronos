package com.se.cronus;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.se.cronus.utils.*;
public class MainActivity extends Activity {

	LinearLayout parent;
	boolean ifTrue;
	ListView FeedList;
	FeedAdapter feedAdapt;
	Feed[] FeedArray;
	//HListView newFeedList;
	private final int nfid = 13;//my lucky number
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FeedArray  = new Feed[]{new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED)};
	
		FeedList = (ListView)findViewById(R.id.feedlist);

		feedAdapt= new FeedAdapter(this, FeedArray, R.id.feedlist); //newFeedList.getId());

		FeedList.setAdapter(feedAdapt);
		
		((View) FeedList.getParent()).setBackgroundColor(CUtils.CRONUS_GREEN_WHITE);
		
		this.getActionBar().setBackgroundDrawable(new ColorDrawable(CUtils.CRONUS_GREEN_DARK));
		
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
