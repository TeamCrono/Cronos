package com.se.cronus;

import com.se.cronus.utils.CUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class FeedAdapter extends ArrayAdapter<Feed> {

	ImageView headerLogo;
	ListView feedlist;
	Feed[] values;
	Context mContext;
	public FeedAdapter(Context context, Feed[] feeds) {
		super(context, R.layout.feed);
		this.values = feeds;
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			// Inflate new View
			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate( R.layout.feed, parent, false);

		}
		headerLogo = (ImageView) view.findViewById(R.id.header);
		feedlist = (ListView) view.findViewById(R.id.feedlist);

		Feed f = values[position];
		if (f.type == CUtils.FACEBOOK_FEED) {
			// Set the image view to Facebook
			headerLogo.setImageResource(R.drawable.facebook_logo);
			feedlist.setBackgroundColor(Color.rgb(59, 89, 152));
			feedlist.setAdapter(f.itemAdapt);
			System.out.println("facebook made");

		}
		return view;
	}
}
