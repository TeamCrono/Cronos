package com.se.cronus;

import java.util.ArrayList;

import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/***
 * 
 * @author dj
 * 
 */
public class FeedAdapter extends ArrayAdapter<Feed> {

	ImageView headerLogo[];
	LinearLayout feeditemlist[];
	Feed[] values;
	Context mContext;

	public FeedAdapter(Context context, Feed[] feeds, int r) {
		super(context, r, feeds);
		this.values = feeds;
		headerLogo = new ImageView[feeds.length];
		feeditemlist = new LinearLayout[feeds.length];
		mContext = context;
	}

	public Feed getFeed(int index) {
		if (index < values.length)
			return values[index];
		return null;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			// Inflate new View
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(R.layout.feed, null);
		}
		headerLogo[position] = (ImageView) convertView.findViewById(R.id.header);
		feeditemlist[position] = (LinearLayout) convertView
				.findViewById(R.id.feeditemlist);

		Feed f = values[position];
		switch (f.type) {
		case CUtils.FACEBOOK_FEED:
			// Set the image view to Facebook
			headerLogo[position].setImageResource(R.drawable.facebook_logo_crop);
			feeditemlist[position].setBackgroundColor(CUtils.FACEBOOK_BLUE);
			break;
		case CUtils.TWITTER_FEED:
			headerLogo[position].setImageResource(R.drawable.twitter_logo);
			feeditemlist[position].setBackgroundColor(CUtils.TWITTER_BLUE);
			break;
		case CUtils.INSTA_FEED:
			headerLogo[position].setImageResource(R.drawable.instagram_logo);
			feeditemlist[position].setBackgroundColor(CUtils.INSTA_BROWN);
			break;
		case CUtils.PINTREST_FEED:
			headerLogo[position].setImageResource(R.drawable.pinterest_logo);
			feeditemlist[position].setBackgroundColor(CUtils.PINTREST_RED);
			break;
		}

		// checks on checks
		if (f.items.size() > 0 && !f.isInit && f.type == f.items.get(0).type) {
			updateItems(f.items, f.type,position);
			f.isInit = true;
		}
		return convertView;
	}
	//This will eventually be fixed to be more object oriented stile and no so C code looking
	private void updateItems(ArrayList<FeedItem> items, int type, int position) {
		// TODO Auto-generated method stub
		// lots of checks to avoid random acurences trust me all needed.
		for (int i = 0; i < items.size(); i++) {
			if (feeditemlist[position].findViewById(items.get(i).getId()) == null) {
				feeditemlist[position].removeView(items.get(i));
				if (((ViewGroup) items.get(i).getParent()) != null
						&& ((ViewGroup) items.get(i).getParent())
								.findViewById(items.get(i).getId()) != null) {
					((ViewGroup) items.get(i).getParent()).removeView(items
							.get(i));
				}
				if (feeditemlist[position].getChildCount() < items.size()
						&& type == items.get(i).type)
					feeditemlist[position].addView(items.get(i));
			}
		}
	}

	public void updateItems(int feedOrPosition, boolean isfeed) {
		if(isfeed)
		for (int i = 0; i < values.length; i++) {
			if (values[i].type == feedOrPosition) {
				updateItems(values[i].items, feedOrPosition, i);
			}
		}
		else
			updateItems(values[feedOrPosition].items, values[feedOrPosition].type, feedOrPosition);
	}

	public void resetItems() {
		
		if (values[0].isSearchMode())
			for (int i = 0; i < values.length; i++) {
				feeditemlist[i].removeAllViews();
				updateItems(values[i].getSearch(), values[i].type, i);
			}
		else
			for (int i = 0; i < values.length; i++) {
				feeditemlist[i].removeAllViews();
				updateItems(values[i].items, values[i].type, i);
			}
	}
}
