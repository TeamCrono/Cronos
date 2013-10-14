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

public class FeedItemAdapter extends ArrayAdapter<FeedItem>{
	
	private ImageView img;
	private FeedItem[] values;
	private int res;
	private Context mContext;
	public FeedItemAdapter(Context context, int resource, FeedItem[] items) {
		super(context, resource);
		// TODO Auto-generated constructor stub
		values = items;
		mContext = context;
		res = resource;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (convertView == null) {
			// Inflate new View
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate( res, parent, false);
		}
		img = (ImageView) view.findViewById(R.id.background);
		
		FeedItem f = values[position];
		if(f.type == CUtils.FACEBOOK_FEED) {
			//Set the image view to Facebook
			img.setImageResource(R.drawable.facebook_logo);
			
			System.out.println("facebook made");
			
		}
		return view;
	}
}
