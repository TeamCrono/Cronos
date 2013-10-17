package com.se.cronus;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.se.cronus.utils.*;
public class MainActivity extends FragmentActivity {

	LinearLayout parent;
	boolean ifTrue;
	ListView FeedList;
	FeedAdapter feedAdapt;
	Feed[] FeedArray;
	//HListView newFeedList;
	FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*THIS SECTION DEALS WITH FRAGMENT HANDLING*/
		
		
//		// Check that the activity is using the layout version with
//        // the fragment_container FrameLayout
//        if (findViewById(R.id.fragment_container) != null) {
//
//            // However, if we're being restored from a previous state,
//            // then we don't need to do anything and should return or else
//            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                return;
//            }
//		
//		
//            // Create a new Fragment to be placed in the activity layout
//            SideLeftFragment firstFragment = new SideLeftFragment();
//            
//            // In case this activity was started with special instructions from an
//            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());
//            
//            // Add the fragment to the 'fragment_container' FrameLayout
//            //getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, (Fragment) firstFragment).commit();
//            
//            ft = getSupportFragmentManager().beginTransaction();
//            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//            
//            ft.add(R.id.fragment_container, firstFragment).commit();
//            
//            
//        }
		
		
		
		
		/*THIS SECTION DEALS WITH FEED ADAPTERS AND STUFFS*/
		FeedArray  = new Feed[]{new Feed(this,CUtils.FACEBOOK_FEED),
								new Feed(this,CUtils.INSTA_FEED),
								new Feed(this,CUtils.PINTREST_FEED),
								new Feed(this,CUtils.TWITTER_FEED),
								new Feed(this,CUtils.PINTREST_FEED),
								new Feed(this,CUtils.FACEBOOK_FEED)};
	
		FeedList = (ListView)findViewById(R.id.feedlist);
		//FeedList.setLayoutParams(new LayoutParams(CUtils.getScreenWidth(this), LayoutParams.MATCH_PARENT));
		
		feedAdapt= new FeedAdapter(this, FeedArray, R.id.feedlist); //newFeedList.getId());

		FeedList.setAdapter(feedAdapt);
		
		
		/*THIS SECTION IS FOR INIT AND DESIGN STUFF*/

		((View) FeedList.getParent()).setBackgroundColor(CUtils.CRONUS_BLUE_WHITE);
		
		this.getActionBar().setBackgroundDrawable(new ColorDrawable(CUtils.CRONUS_GREEN_DARK));
		//this.getActionBar().
	
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    if(item.getItemId() == android.R.id.home) {
	    	//app icon in action bar clicked; go back
	        //do something
	    	//Toast.makeText(this, "clicked" , 10000);
	    	System.out.println("pewop");
	        return true;
	    }

	    return super.onOptionsItemSelected(item);
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
