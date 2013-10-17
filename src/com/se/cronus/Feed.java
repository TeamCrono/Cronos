package com.se.cronus;

import java.util.ArrayList;

import com.se.cronus.utils.CUtils;

import android.content.Context;
import android.widget.LinearLayout;

public class Feed extends LinearLayout {

	public int type;
	public ArrayList<FeedItem> items;
	public boolean isInit;
	
	public Feed(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type = type;
		
		isInit = false;
		
		
		items = new ArrayList<FeedItem>();
		items.add(new FeedItem(this.getContext(),
				CUtils.FACEBOOK_FEED));
		items.add(new FeedItem(this.getContext(),
				CUtils.FACEBOOK_FEED));
		items.add(new FeedItem(this.getContext(),
				CUtils.FACEBOOK_FEED));
		items.add(new FeedItem(this.getContext(),
				CUtils.FACEBOOK_FEED));
		
		
		
	}

	public void add(FeedItem item) {
		items.add(item);
	}

}