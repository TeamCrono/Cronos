package com.se.cronus;

import java.util.ArrayList;

import com.se.cronus.utils.CUtils;

import android.content.Context;
import android.widget.LinearLayout;

public class Feed extends LinearLayout {

	public int type;
	private ArrayList<FeedItem> feeditems;
	public FeedItemAdapter itemAdapt;
	public Feed(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type = type;
		feeditems = new ArrayList<FeedItem>();
		feeditems.add(new FeedItem(this.getContext(), CUtils.FACEBOOK_FEED));
		itemAdapt = new FeedItemAdapter(this.getContext(),R.layout.feed_item,(FeedItem[]) feeditems.toArray());
	}

	public void add(FeedItem item) {
		feeditems.add(item);
		itemAdapt.add(item);
	}

}