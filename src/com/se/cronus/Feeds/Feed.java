package com.se.cronus.Feeds;

import java.util.ArrayList;

import com.se.cronus.MainActivity;
import com.se.cronus.R;
import com.se.cronus.backend.ItemGenerator;
import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CUtils;
import com.se.cronus.utils.CronusApp;

import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/***
 * 
 * @author dj
 * 
 */
public class Feed extends RelativeLayout {

	public int type;
	public ArrayList<FeedItem> items;
	public boolean isInit;
	private ArrayList<FeedItem> rItems;

	// variables to handle search1
	public ArrayList<FeedItem> sItems;
	private boolean searchMode;// Activate!
	public ImageView headerLogo;
	public LinearLayout feeditemlist;
	public LinearLayout padding;// stuppid that it came to this
	public RelativeLayout overlays;
	public ImageView moveUp;
	public ImageView moveDown;
	public ImageView remove;
	public TextView subtitle;
	public ItemGenerator itemgen;
	public ArrayList<Object> network;

	public String getSubtitle() {
		return subtitle.getText().toString();
	}

	public void setSubtitle(String subtitle) {
		this.subtitle.setText(subtitle);
	}

	public Feed(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type = type;
		isInit = false;

		items = new ArrayList<FeedItem>();
		rItems = new ArrayList<FeedItem>();
		sItems = new ArrayList<FeedItem>();
		Integer feedids = ((CronusApp) ((MainActivity) this.getContext())
				.getApplication()).feedIDgen;
		if (MainActivity.TESTING) {
			// for testing loop
//			for (int i = 0; i < 4; i++) {
//				items.add(new FeedItem(this.getContext(), type, feedids++));
//				items.add(new FeedItem(this.getContext(), type, feedids++));
//				items.add(new FeedItem(this.getContext(), type, feedids++));
//				items.add(new FeedItem(this.getContext(), type, feedids++));
//			}
		}
		((CronusApp) ((MainActivity) this.getContext()).getApplication()).feedIDgen = feedids;
		searchMode = false;
		itemgen = new ItemGenerator(this);
	}

	/**
	 * 
	 **/

	@Override
	public void setClickable(boolean clickable) {
		// TODO Auto-generated method stub
		super.setClickable(clickable);
		HorizontalScrollView rent = (HorizontalScrollView)feeditemlist.getParent();
		rent.setClickable(clickable);
		feeditemlist.setClickable(clickable);
		
		for (FeedItem i : items) {
			i.setClickable(clickable);
		}
	}

	public boolean onItemRecieved(FeedItem item) {
		// TODO: still need to finish this!
		if (items.size() >= 15) {
			rItems.add(items.remove(items.size() - 1));
		}

		if (contains(item))
			return false;
		else
			items.add(0,item);
		return true;
	}

	private boolean contains(FeedItem item) {
		// TODO Done but not tested

		for (int i = 0; i < items.size(); i++) {
			if (item.equals(items.get(i))) {
				return true;
			}
		}
		for (int i = 0; i < rItems.size(); i++) {
			if (item.equals(rItems.get(i))) {
				return true;
			}
		}

		return false;
	}

	

	// this handles the view when in searchy searchy mode!
	public void offSearch() {
		searchMode = false;

	}

	public void onSearch(String toFind) {
		searchMode = true;
		// Emptey all the items in search list
		sItems.removeAll(sItems);
		
		ArrayList<FeedItem> fullList = new ArrayList<FeedItem>();
		fullList.addAll(items);
		fullList.addAll(rItems);

		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).search(toFind))
				sItems.add(fullList.get(i));
		}
		

	}

	public ArrayList<FeedItem> getSearch() {
		return rItems;
	}

	public boolean isSearchMode() {
		return searchMode;
	}

	/**
	 * 
	 */

	public void setUpView() {
		switch (type) {
		case CUtils.FACEBOOK_FEED:
			// Set the image view to Facebook
			headerLogo.setImageResource(R.drawable.facebook_logo_crop);
			feeditemlist.setBackgroundColor(CUtils.FACEBOOK_BLUE);
			padding.setBackgroundColor(CUtils.FACEBOOK_BLUE);
			overlays.setBackgroundColor(CUtils.FACEBOOK_BLUE_CLEAR);
			break;
		case CUtils.TWITTER_FEED:
			headerLogo.setImageResource(R.drawable.twitter_logo);
			feeditemlist.setBackgroundColor(CUtils.TWITTER_BLUE);
			padding.setBackgroundColor(CUtils.TWITTER_BLUE);
			overlays.setBackgroundColor(CUtils.TWITTER_BLUE_CLEAR);

			break;
		case CUtils.INSTA_FEED:
			headerLogo.setImageResource(R.drawable.instagram_logo);
			feeditemlist.setBackgroundColor(CUtils.INSTA_BROWN);
			padding.setBackgroundColor(CUtils.INSTA_BROWN);
			overlays.setBackgroundColor(CUtils.INSTA_BROWN_CLEAR);

			break;
		case CUtils.PINTREST_FEED:
			headerLogo.setImageResource(R.drawable.pinterest_logo);
			feeditemlist.setBackgroundColor(CUtils.PINTREST_RED);
			padding.setBackgroundColor(CUtils.PINTREST_RED);
			overlays.setBackgroundColor(CUtils.PINTREST_RED_CLEAR);

			break;
		}
	}

	public String getFeedTitle() {
		// TODO Auto-generated method stub
		switch (type) {
		case CUtils.FACEBOOK_FEED:
			// Set the image view to Facebook
			return "Facebook: " + subtitle.getText().toString();

		case CUtils.TWITTER_FEED:
			return "Twitter: " + subtitle.getText().toString();

		case CUtils.INSTA_FEED:
			return "Instagram: " + subtitle.getText().toString();

		case CUtils.PINTREST_FEED:
			return "Pintrest: " + subtitle.getText().toString();
		}
		return "Some thing weird happend, how did you break this app?";

	}
	

	public void update() {
		// TODO Auto-generated method stub
		Thread genthread = new Thread(itemgen);
		itemgen.numItemsRequested(5);
		genthread.run();
	}

}