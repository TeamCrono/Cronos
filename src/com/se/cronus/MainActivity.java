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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast; 

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.se.cronus.utils.*;

/************************************************************************
Copyright 2012 Jeremy Feinstein

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **************************************************************************/



/***
 * 
 * @author dj
 *
 */
public class MainActivity extends FragmentActivity {
	/*for fragments*/
	private int cur;
	private final int MAIN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private int curLeftType;
	
	FragmentTransaction ft;
	SlidingMenu profile;
	SlidingMenu[] feedFragment;
	
	/*general stuff*/
	LinearLayout parent;
	boolean ifTrue;
	ListView FeedList;
	FeedAdapter feedAdapt;
	Feed[] FeedArray;
	//HListView newFeedList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		/*THIS SECTION DEALS WITH FRAGMENT HANDLING*////however it doesnt work right now.
		cur = 0;
		curLeftType = CUtils.TEST_FEED;
		// configure the SlidingMenu
        profile = new SlidingMenu(this);
        profile.setMode(SlidingMenu.LEFT);
        profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        profile.setFadeDegree(0.35f);
        profile.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        profile.setMenu(R.layout.fragment_left);
        profile.setBackgroundColor(CUtils.CRONUS_BLUE_WHITE);
        profile.setBehindOffset(60);

        
		// configure the SlidingMenu
        feedFragment = new SlidingMenu[5];
        
        
        feedFragment[CUtils.TEST_FEED] = new SlidingMenu(this);
        feedFragment[CUtils.TEST_FEED].setMode(SlidingMenu.RIGHT);
        feedFragment[CUtils.TEST_FEED].setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.shadow);
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        feedFragment[CUtils.TEST_FEED].setFadeDegree(0.35f);
        feedFragment[CUtils.TEST_FEED].attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        feedFragment[CUtils.TEST_FEED].setMenu(R.layout.fragment_right);
        feedFragment[CUtils.TEST_FEED].setBackgroundColor(CUtils.CRONUS_BLUE_WHITE);
        feedFragment[CUtils.TEST_FEED].setBehindOffset(40);
        
//        feedFragment[CUtils.FACEBOOK_FEED] = new SlidingMenu(this);
//        feedFragment[CUtils.FACEBOOK_FEED].setMode(SlidingMenu.RIGHT);
//        feedFragment[CUtils.FACEBOOK_FEED].setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
////        menu.setShadowWidthRes(R.dimen.shadow_width);
////        menu.setShadowDrawable(R.drawable.shadow);
////        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        feedFragment[CUtils.FACEBOOK_FEED].setFadeDegree(0.35f);
//        feedFragment[CUtils.FACEBOOK_FEED].attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//        feedFragment[CUtils.FACEBOOK_FEED].setMenu(R.layout.fragment_left);
//        feedFragment[CUtils.FACEBOOK_FEED].setBackgroundColor(CUtils.CRONUS_BLUE_WHITE);
//        feedFragment[CUtils.FACEBOOK_FEED].setBehindOffset(40);
       
        
        
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
		this.getActionBar().setIcon(R.drawable.temp_cronos_logo);
		//this.getActionBar().
	
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch(item.getItemId()){
	   case R.id.action_logo:
		   if(cur == MAIN){
				//go left
			   profile.animate().start();
			   profile.bringToFront();
			   profile.isMenuShowing();// use this over cur == MAIN
			   //profile.removeAllViews();// uset this one for the items
			   
			   cur = LEFT;
			   
			}else{
				//go right
				cur = MAIN;
				profile.animate().start();
			}
		   break;
	   case R.id.action_item:
		   
		if(cur == MAIN){
			//go Right
			cur = RIGHT;
			item.setIcon(R.drawable.feed_item_icon_back);
		}else{
			//goleft
			cur = MAIN;
			item.setIcon(R.drawable.feed_item_icon);
		}
		   break;
	   }

	    return super.onOptionsItemSelected(item);
	}
	
	public void addFeed(Feed f){
		 feedAdapt.add(f);;
		 
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.layout.action_bar, menu);
		this.getActionBar().setBackgroundDrawable(new ColorDrawable(CUtils.CRONUS_GREEN_DARK));
		int h =this.getActionBar().getHeight();
		int w =this.getActionBar().getHeight();//make it a squar
		
		
		return true;
	}

}
