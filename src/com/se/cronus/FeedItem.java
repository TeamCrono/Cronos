package com.se.cronus;

import android.content.Context;
import android.widget.RelativeLayout;

public class FeedItem extends RelativeLayout {
	public int type;
	
	
	public FeedItem(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type=type;
	}

}
