package com.se.cronus;

import java.util.ArrayList;

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

public class FeedAdapter extends ArrayAdapter<Feed> {

	ImageView headerLogo;
	LinearLayout feeditemlist;
	Feed[] values;
	Context mContext;

	public FeedAdapter(Context context, Feed[] feeds, int r) {
		super(context, r, feeds);
		this.values = feeds;
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			// Inflate new View
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(R.layout.feed, null);
		}
		headerLogo = (ImageView) convertView.findViewById(R.id.header);
		feeditemlist = (LinearLayout) convertView
				.findViewById(R.id.feeditemlist);

		Feed f = values[position];
		if (f.type == CUtils.FACEBOOK_FEED) {
			// Set the image view to Facebook
			headerLogo.setImageResource(R.drawable.facebook_logo_crop);
			feeditemlist.setBackgroundColor(CUtils.FACEBOOK_BLUE);

		}
		if (!f.isInit) {
			updateItems(f.items);
			f.isInit = true;
		}
		return convertView;
	}

	private void updateItems(ArrayList<FeedItem> items) {
		// TODO Auto-generated method stub
		// lots of checks to avoid random acurences trust me all needed.
		for (int i = 0; i < items.size(); i++) {
			if (feeditemlist.findViewById(items.get(i).getId()) == null) {
				feeditemlist.removeView(items.get(i));
				if (((ViewGroup) items.get(i).getParent()) != null
						&& ((ViewGroup) items.get(i).getParent())
								.findViewById(items.get(i).getId()) != null) {
					((ViewGroup) items.get(i).getParent()).removeView(items
							.get(i));
				}
				if (feeditemlist.getChildCount() < items.size())
					feeditemlist.addView(items.get(i));
			}
		}
	}

	public void updateItems(int feed) {
		for (int i = 0; i < values.length; i++) {
			if (values[i].type == feed) {
				updateItems(values[i].items);
				return;
			}
		}
	}
}
