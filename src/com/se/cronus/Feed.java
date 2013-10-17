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
		// for testing loop
		for (int i = 0; i < 4; i++) {
			items.add(new FeedItem(this.getContext(), type));
			items.add(new FeedItem(this.getContext(), type));
			items.add(new FeedItem(this.getContext(), type));
			items.add(new FeedItem(this.getContext(), type));
		}

	}

	public void add(FeedItem item) {
		items.add(item);
	}

}