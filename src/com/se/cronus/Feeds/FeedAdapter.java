package com.se.cronus.Feeds;

import java.util.ArrayList;

import com.se.cronus.MainActivity;
import com.se.cronus.R;
import com.se.cronus.R.drawable;
import com.se.cronus.R.id;
import com.se.cronus.R.layout;
import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CUtils;
import com.se.cronus.utils.CronusApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;

/***
 * 
 * @author dj
 * 
 */
public class FeedAdapter extends ArrayAdapter<Feed> {

	ImageView headerLogo[];
	LinearLayout feeditemlist[];
	Feed[] values;
	LinearLayout[] padding;// stuppid that it came to this
	RelativeLayout[] overlays;
	Context mContext;
	private int numValues;
	MainActivity main;

	public FeedAdapter(Context context, Feed[] feeds, int r) {
		super(context, r, feeds);
		this.values = feeds;
		headerLogo = new ImageView[feeds.length];
		feeditemlist = new LinearLayout[feeds.length];
		overlays = new RelativeLayout[feeds.length];
		mContext = context;
		numValues = feeds.length;
		padding = new LinearLayout[numValues];
		main = (MainActivity) context;
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
			convertView.setPadding(0, 1, 0, 1);
			convertView.setId(((CronusApp) ((Activity) this.getContext())
					.getApplication()).feedIDgen++);
		}
		final View view = convertView;
		feeditemlist[position] = (LinearLayout) convertView
				.findViewById(R.id.feeditemlist);
		feeditemlist[position].setPadding(0, 0, 0, 0);
		padding[position] = (LinearLayout) convertView
				.findViewById(R.id.feedpadding);

		overlays[position] = (RelativeLayout) convertView
				.findViewById(R.id.feed_overlay);

		headerLogo[position] = (ImageView) convertView
				.findViewById(R.id.feed_header);

		headerLogo[position].setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Vibrator vib = (Vibrator) mContext
						.getSystemService(Context.VIBRATOR_SERVICE);
				vib.vibrate(150);
				for (int i = 0; i < numValues; i++) {
					if (overlays[i] != null) {
						((MainActivity) mContext).getActionBar()
								.getCustomView()
								.findViewById(R.id.action_search_b)
								.setVisibility(View.GONE);
						((MainActivity) mContext).getActionBar()
								.getCustomView().findViewById(R.id.action_done)
								.setVisibility(View.VISIBLE);
						((MainActivity) mContext).getActionBar()
								.getCustomView().findViewById(R.id.action_done)
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View v) {
										((MainActivity) mContext)
												.getActionBar()
												.getCustomView()
												.findViewById(
														R.id.action_search_b)
												.setVisibility(View.VISIBLE);
										((MainActivity) mContext)
												.getActionBar().getCustomView()
												.findViewById(R.id.action_done)
												.setVisibility(View.GONE);
										for (int j = 0; j < numValues; j++) {
											if (overlays[j] != null)
												overlays[j]
														.setVisibility(View.GONE);
										}
										// overlays[i].setLayoutParams(new
										// RelativeLayout.LayoutParams(-1,
										// padding[i].getHeight() +
										// headerLogo[i].getHeight()));
										// recursiveSetClickable(view.findViewById(R.id.feed_body),
										// false);
										// recursiveSetClickable(view.findViewById(R.id.feed_head),
										// false);
									}

								});

						overlays[i].setVisibility(View.VISIBLE);
						overlays[i]
								.setLayoutParams(new RelativeLayout.LayoutParams(
										-1, padding[i].getHeight()
												+ headerLogo[i].getHeight()));
						// recursiveSetClickable(view.findViewById(R.id.feed_body),
						// false);
						// recursiveSetClickable(view.findViewById(R.id.feed_head),
						// false);

					}
				}

			}

		});
		// headerLogo[position].setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		//
		// // if (Math.abs(event.getY() - v.getY()) > 10.0)
		// ((MainActivity)mContext).findViewById(R.id.feedlist);
		// ((LinearLayout)v.getParent().getParent()).bringToFront();
		//
		// ((LinearLayout) v.getParent().getParent()).setY(event.getY());
		// return true;
		// }
		//
		// });

		// one posibility
		// headerLogo[position].setOnClickListener(new OnClickListener() {
		//
		// @SuppressLint("NewApi")
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// // RelativeLayout view = new RelativeLayout(mContext);
		// LinearLayout view = new LinearLayout(mContext);
		// view.setLayoutParams(new LayoutParams(CUtils
		// .getScreenWidth(main), CUtils.getScreenHeight(main)));
		// view.setOrientation(LinearLayout.VERTICAL);
		//
		// final Dialog d = new Dialog(mContext);
		// d.setTitle("Drag to Reorder");
		// Button back = new Button(mContext);
		// back.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// d.hide();
		// }
		//
		// });
		// d.setContentView(view);
		//
		// view.addView(back);
		//
		// for (int i = 0; i < headerLogo.length; i++) {
		// if (headerLogo[i] != null) {
		// Drawable btemp = headerLogo[i].getDrawable().mutate();
		// // RelativeLayout.LayoutParams ltemp = new
		// // RelativeLayout.LayoutParams(
		// // LayoutParams.WRAP_CONTENT,
		// // LayoutParams.WRAP_CONTENT);
		// // ltemp.addRule(RelativeLayout.BELOW,
		// // view.getChildAt(i)
		// // .getId());
		// ImageView temp = new ImageView(mContext);
		// temp.setBackground(btemp);
		// view.addView(temp);
		// temp.setLayoutParams(new LinearLayout.LayoutParams(
		// LayoutParams.WRAP_CONTENT,
		// LayoutParams.WRAP_CONTENT));
		// temp.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		// // TODO Auto-generated method stub
		// if (Math.abs(event.getY() - v.getY()) > 10.0)
		// v.setY(event.getY());
		// return true;
		// }
		//
		// });
		// }
		// }
		//
		// d.show();
		// }
		//
		// });
		// headerLogo[position].setOnDragListener(new OnDragListener() {
		//
		// @Override
		// public boolean onDrag(View v, DragEvent event) {
		// // TODO Auto-generated method stub
		// int i;
		// for (i = 0; i < numValues; i++) {
		// if (v.getId() == headerLogo[i].getId()) {
		// // TODO: dragged a header logo, do stuff!!!
		// ((HorizontalScrollView) v.getParent()).setY(event
		// .getY());
		// return true;
		// }
		// }
		// return false;
		// }
		//
		// });

		((HorizontalScrollView) feeditemlist[position].getParent())
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub

						return false;
					}
				});
		((HorizontalScrollView) feeditemlist[position].getParent())
				.getScrollX();
		// ((HorizontalScrollView)feeditemlist[position].getParent()).on

		Feed f = values[position];
		switch (f.type) {
		case CUtils.FACEBOOK_FEED:
			// Set the image view to Facebook
			headerLogo[position]
					.setImageResource(R.drawable.facebook_logo_crop);
			feeditemlist[position].setBackgroundColor(CUtils.FACEBOOK_BLUE);
			padding[position].setBackgroundColor(CUtils.FACEBOOK_BLUE);
			overlays[position].setBackgroundColor(CUtils.FACEBOOK_BLUE_CLEAR);
			break;
		case CUtils.TWITTER_FEED:
			headerLogo[position].setImageResource(R.drawable.twitter_logo);
			feeditemlist[position].setBackgroundColor(CUtils.TWITTER_BLUE);
			padding[position].setBackgroundColor(CUtils.TWITTER_BLUE);
			overlays[position].setBackgroundColor(CUtils.TWITTER_BLUE_CLEAR);

			break;
		case CUtils.INSTA_FEED:
			headerLogo[position].setImageResource(R.drawable.instagram_logo);
			feeditemlist[position].setBackgroundColor(CUtils.INSTA_BROWN);
			padding[position].setBackgroundColor(CUtils.INSTA_BROWN);
			overlays[position].setBackgroundColor(CUtils.INSTA_BROWN_CLEAR);

			break;
		case CUtils.PINTREST_FEED:
			headerLogo[position].setImageResource(R.drawable.pinterest_logo);
			feeditemlist[position].setBackgroundColor(CUtils.PINTREST_RED);
			padding[position].setBackgroundColor(CUtils.PINTREST_RED);
			overlays[position].setBackgroundColor(CUtils.PINTREST_RED_CLEAR);

			break;
		}

		// checks on checks
		if (f.items.size() > 0 && !f.isInit && f.type == f.items.get(0).type) {
			updateItems(f.items, f.type, position);
			f.isInit = true;
		}
		return convertView;
	}

	// This will eventually be fixed to be more object oriented stile and no so
	// C code looking
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
		if (isfeed)
			for (int i = 0; i < values.length; i++) {
				if (values[i].type == feedOrPosition) {
					updateItems(values[i].items, feedOrPosition, i);
				}
			}
		else
			updateItems(values[feedOrPosition].items,
					values[feedOrPosition].type, feedOrPosition);
	}

	public void resetItems() {

		if (values[0].isSearchMode())
			for (int i = 0; i < values.length; i++) {
				if (feeditemlist[i] != null)
					feeditemlist[i].removeAllViews();// this is null, lets find
														// out why!
				updateItems(values[i].getSearch(), values[i].type, i);
			}
		else
			for (int i = 0; i < values.length; i++) {
				feeditemlist[i].removeAllViews();
				updateItems(values[i].items, values[i].type, i);
			}
	}

	public void recursiveSetClickable(View v, boolean clickable) {
		v.setClickable(clickable);
		ViewGroup vcast = null;
		try {
			vcast = ((ViewGroup) v);
		} catch (Error e) {
			if (vcast == null) {
				return;
			}
		}

		for (int i = 0; i < vcast.getChildCount(); i++) {
			recursiveSetClickable(v, clickable);
		}
	}
}
