package com.se.cronus;

import android.app.ActionBar;
import android.app.Service;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.se.cronus.items.ItemFragmentView;
import com.se.cronus.items.TestFragView;
import com.se.cronus.utils.CUtils;

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
public class MainActivity extends /* Sliding */FragmentActivity implements
		OnClickListener {
	public static final boolean TESTING = true;
	/* for fragments */
	private final int MAIN = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private int CUR;
	private SlidingMenu curAttatched;

	// action bar stuff
	private ActionBar act;
	private ImageView item;
	private ImageView search;
	private EditText searchText;
	private TextView searchTextV;

	FragmentTransaction ft;
	SlidingMenu profile;

	/* general stuff */
	LinearLayout parent;
	boolean ifTrue;
	ListView FeedList;
	FeedAdapter feedAdapt;
	Feed[] FeedArray;

	// Keyboard stuff
	InputMethodManager imm;

	// HListView newFeedList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.se.cronus.R.layout.activity_main);
		// general utilities for app
		imm = (InputMethodManager) this
				.getSystemService(Service.INPUT_METHOD_SERVICE);

		/* THIS SECTION DEALS WITH FRAGMENT HANDLING */// /however it doesnt
														// work right now.
		CUR = MAIN;

		// configure the SlidingMenu
		setUpProfile();

		// configure the SlidingMenu
		setUpItemFragment(new TestFragView(CUtils.TEST_FEED, this));

		/* THIS SECTION DEALS WITH FEED ADAPTERS AND STUFFS */

		if (TESTING) {
			setTestingFeedArray();
		} else {

			setFeedArray();
		}

		// set up list Adapter
		setUpListAdapter();

		/* THIS SECTION IS FOR INIT AND DESIGN STUFF */
		// setBehindContentView(new SlidingMenu(this));
		// this.getSlidingMenu().setLayoutParams(new LayoutParams(0, 0));
		setUpActionBar();

		// set onclicks

		item.setOnClickListener(this);
		search.setOnClickListener(this);
		searchTextV.setClickable(true);
		searchTextV.setOnClickListener(this);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
		if (menu.equals(profile)
				|| ((SlidingMenu) menu).getId() == profile.getId()) {
			System.out.print("PROFILE OPENED");

		}

		return super.onMenuOpened(featureId, menu);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * 
	 */
	private void setUpListAdapter() {
		FeedList = (ListView) findViewById(com.se.cronus.R.id.feedlist);
		feedAdapt = new FeedAdapter(this, FeedArray,
				com.se.cronus.R.id.feedlist); // newFeedList.getId());

		FeedList.setAdapter(feedAdapt);
	}

	private void setFeedArray() {
		// TODO create A feed list consisting of all of users current feeds;

		FeedArray = null;
	}

	/**
	 * 
	 */
	private void setTestingFeedArray() {
		FeedArray = new Feed[] { new Feed(this, CUtils.FACEBOOK_FEED),
				new Feed(this, CUtils.INSTA_FEED),
				new Feed(this, CUtils.PINTREST_FEED),
				new Feed(this, CUtils.TWITTER_FEED) };
	}

	/**
	 * set up array that saves past right fragments
	 */
	private void setUpItemFragment(ItemFragmentView v) {

		curAttatched = new SlidingMenu(this);
		curAttatched.setMode(SlidingMenu.RIGHT);
		curAttatched.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		curAttatched.setShadowWidthRes(com.se.cronus.R.dimen.shadow_width);
		// menu.setShadowDrawable(R.drawable.shadow);
		curAttatched.setFadeDegree(0.35f);
		// curAttatched.attachToActivity(this, //attatched with onclick
		// SlidingMenu.SLIDING_CONTENT);
		curAttatched.setMenu(v);
		curAttatched
				.setBehindOffsetRes(com.se.cronus.R.dimen.slidingmenu_offset);

		curAttatched.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		curAttatched.setOnOpenedListener(new OnOpenedListener() {

			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				onOpenItem();
			}

		});
		curAttatched.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				onOpenMain();
			}

		});
	}

	public boolean changeItemFragment(ItemFragmentView newf) {
		// TODO: check for valid View first
		((ItemFragmentView) curAttatched.getMenu()).destroy();
		setUpItemFragment(newf);
//		curAttatched.setMenu(newf);
		onClick(item);
		item.performClick();
		curAttatched.showContent();
		curAttatched.showSecondaryMenu();
		return true;
	}

	/**
	 * sets up left hand side
	 */
	private void setUpProfile() {
		profile = new SlidingMenu(this);
		profile.setSlidingEnabled(true);
		profile.setMode(SlidingMenu.LEFT);
		profile.setMenu(com.se.cronus.R.layout.fragment_left);

		profile.setShadowWidthRes(com.se.cronus.R.dimen.shadow_width);
		// profile.setShadowDrawable(R.drawable.shadow);
		profile.setBehindOffsetRes(com.se.cronus.R.dimen.slidingmenu_offset);
		profile.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// profile.setBehindWidth(30);
		profile.setBehindScrollScale(0.25f);
		profile.setFadeDegree(0.35f);
		profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		profile.setBackgroundColor(Color.RED);// CUtils.CRONUS_BLUE_WHITE);
		profile.setOnOpenedListener(new OnOpenedListener() {

			@Override
			public void onOpened() {
				onOpenProfile();
			}

		});
		profile.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {
				onOpenMain();
			}

		});
	}

	private void setUpActionBar() {
		((View) FeedList.getParent())
				.setBackgroundColor(CUtils.CRONUS_BLUE_WHITE);
		act = this.getActionBar();
		act.setBackgroundDrawable(new ColorDrawable(CUtils.CRONUS_GREEN_DARK));
		act.setIcon(com.se.cronus.R.drawable.temp_cronos_logo);
		act.setCustomView(com.se.cronus.R.layout.action_bar);
		act.setDisplayHomeAsUpEnabled(true);
		act.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
				| ActionBar.DISPLAY_SHOW_HOME);

		// extra icons
		search = (ImageView) findViewById(com.se.cronus.R.id.action_search_b);
		searchText = (EditText) findViewById(com.se.cronus.R.id.action_search_et);
		searchTextV = (TextView) findViewById(com.se.cronus.R.id.action_search_tv);
		item = (ImageView) findViewById(com.se.cronus.R.id.action_item);

		searchText.setTextColor(CUtils.CRONUS_GREEN_LIGHT);
		searchText.setTextSize(15);

		searchTextV.setTextColor(CUtils.CRONUS_GREEN_LIGHT);
		searchTextV.setTextSize(15);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			switch (CUR) {
			case RIGHT:
				viewMain();
				return true;
			case MAIN:
				viewProfile();
				return true;
			case LEFT:
				viewMain();
				return true;
			}

		}

		return super.onOptionsItemSelected(item);
	}

	public void addFeed(Feed f) {
		feedAdapt.add(f);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.layout.action_bar, menu);
		act.setBackgroundDrawable(new ColorDrawable(CUtils.CRONUS_GREEN_DARK));
		int h = act.getHeight();
		int w = act.getHeight();// make it a squar

		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case com.se.cronus.R.id.action_item:
			switch (CUR) {
			case MAIN:
				viewCurItem();
				return;
			case LEFT:
				viewMain();
				return;
			case RIGHT:
				viewMain();
				return;
			}
			return;
		case R.id.action_search_b:
			onSearchClick();
			return;
		case R.id.action_search_tv:
			onSearchClick();
			return;
		}

	}

	// this is the back end methods for search bar that is yet to be
	// implemented!
	private void onSearch(String toFind) {
		for (int i = 0; i < feedAdapt.getCount(); i++) {
			feedAdapt.getFeed(i).onSearch(toFind);
		}
		feedAdapt.resetItems();
	}

	private void offSearch() {
		for (int i = 0; i < feedAdapt.getCount(); i++) {
			feedAdapt.getFeed(i).offSearch();
		}
		feedAdapt.resetItems();
	}

	private void onSearchClick() {
		// enable search
		if (searchText.getVisibility() == View.GONE
				&& searchTextV.getVisibility() == View.GONE) {
			searchTextV.setText("");
			searchText.setVisibility(View.VISIBLE);
			searchText.setFocusableInTouchMode(true);
			searchText.setFocusable(true);
			searchText.requestFocus();
			searchText.setSelected(true);
			imm.showSoftInput(searchText, 0);
			return;
		}
		// clear search
		if (searchText.getVisibility() == View.GONE
				&& searchTextV.getVisibility() == View.VISIBLE) {
			searchTextV.setText("");
			searchTextV.setVisibility(View.GONE);
			searchText.setText("");
			searchText.setVisibility(View.GONE);
			offSearch();
		}
		// search
		if (searchText.getVisibility() == View.VISIBLE
				&& searchTextV.getVisibility() == View.GONE) {
			imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
			String toSearch = searchText.getText().toString().trim();
			String title;
			if (toSearch.length() > 1) {
				int max = 32;
				int min = 16;
				if (toSearch.length() > max)
					title = toSearch.substring(0, max);
				else if (toSearch.length() < min) {
					title = toSearch;
					for (int i = toSearch.length(); i < min; i++)
						title += " ";
				} else {
					title = toSearch;
				}
				searchTextV.setText(title);
				searchTextV.setVisibility(View.VISIBLE);
				searchText.setText("");
				searchText.setVisibility(View.GONE);
				// TODO: do search
				onSearch(toSearch);
				viewMain();
				return;
			}

		}
	}

	// this section is to help with save state
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// This is going to handle activating buttons and all that
	// disable profile
	private void onOpenItem() {
		System.out.println("onOpenItem");
		item.setBackgroundResource(com.se.cronus.R.drawable.feed_item_icon_back);
		profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

		// ((ViewGroup)profile.getParent()).removeView(profile);
		// profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		// profile.setSlidingEnabled(true);
		// // this.setBehindContentView(profile);
		// profile.setVisibility(View.VISIBLE);
		// System.out.println("Profile enabled");
		CUR = RIGHT;
	}

	// disable curitem
	private void onOpenProfile() {
		System.out.println("onOpenProfile");
		curAttatched.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

		CUR = LEFT;
	}

	// enable both drares
	private void onOpenMain() {
		System.out.println("onOpenMain");
		item.setBackgroundResource(com.se.cronus.R.drawable.feed_item_icon);
		profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		curAttatched.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

		// profile.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		// profile.setSlidingEnabled(false);
		// // this.setBehindContentView(null);
		// profile.setVisibility(View.GONE);
		// System.out.println("Profile disabled");
		CUR = MAIN;
	}

	public void viewProfile() {
		profile.showMenu(true);
		// ((ViewGroup)profile.getParent()).removeView(profile);

	}

	public void viewCurItem() {

		// go Right
		for (int i = 0; i < 3; i++)
			curAttatched.showMenu(true);
		if (!curAttatched.isMenuShowing()) {
			System.out.println("Item didn't show, trying again");
		}

	}

	private void viewMain() {
		// goleft
		if (curAttatched.isMenuShowing()) {
			curAttatched.showContent(true);
		}
		if (profile.isMenuShowing()) {
			profile.showContent(true);
		}

	}
}
