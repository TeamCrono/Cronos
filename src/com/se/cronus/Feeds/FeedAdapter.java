package com.se.cronus.Feeds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.se.cronus.MainActivity;
import com.se.cronus.R;
import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CronusApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/***
 * 
 * @author dj
 * 
 */
public class FeedAdapter extends ArrayAdapter<Feed> {

	List<Feed> values;
	List<View> converted;
	Context mContext;
	private boolean overlay;
	private int h = 200;
	MainActivity main;
	private int hight, width;

	public FeedAdapter(Context context, List<Feed> feeds, int r) {
		super(context, r, feeds);
		this.values = feeds;
		mContext = context;
		main = (MainActivity) context;
		converted = new ArrayList<View>();
		overlay = false;
		hight = 264;
		width = -500;
	}

	public Feed getFeed(int index) {
		if (index < values.size())
			return values.get(index);
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
//			((LinearLayout)convertView.findViewById(R.id.feed_body)).setLayoutParams(new RelativeLayout.LayoutParams(-1, h));
		}
		Feed f = values.get(position);
		this.fixNulls(position);
		f.feeditemlist = (LinearLayout) convertView
				.findViewById(R.id.feeditemlist);
		f.headerLogo = (ImageView) convertView.findViewById(R.id.feed_header);
		f.padding = (LinearLayout) convertView.findViewById(R.id.feedpadding);
		f.overlays = (RelativeLayout) convertView
				.findViewById(R.id.feed_overlay);
		if (!overlay)
			f.overlays.setVisibility(View.INVISIBLE);
		else
			f.overlays.setVisibility(View.VISIBLE);

		System.out.println("overlay hight" + f.overlays.getHeight());
		f.remove = (ImageView) convertView.findViewById(R.id.feed_nav_delete);
		f.moveDown = (ImageView) convertView.findViewById(R.id.feed_nav_down);
		f.moveUp = (ImageView) convertView.findViewById(R.id.feed_nav_up);
		f.subtitle = (TextView) convertView.findViewById(R.id.feed_subtitle);
		
		f.feeditemlist.setPadding(0, 0, 0, 0);

		setOnClicks(position);
		f.setUpView();
		// checks on checks
		if (f.items.size() > 0 && !f.isInit && f.type == f.items.get(0).type)
			updateItems(position);
		converted.add(convertView);

		return convertView;
	}

	// This will eventually be fixed to be more object oriented stile and no so
	// C code looking

	public void updateItems(int feedOrPosition, boolean isfeed) {
		if (isfeed)
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i).type == feedOrPosition) {
					updateItems(i);
				}
			}
		else
			updateItems(feedOrPosition);
	}

	public void resetItems() {

		if (values.get(0).isSearchMode())
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i).feeditemlist != null)
					values.get(i).feeditemlist.removeAllViews();// this is null,
				// lets find
				// out why!
				updateItems(i);
			}
		else
			for (int i = 0; i < values.size(); i++) {
				values.get(i).feeditemlist.removeAllViews();
				updateItems(i);
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

	public void setOnClicks(final int position) {
		values.get(position).moveDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				swapViews(position, -1);
			}

		});
		values.get(position).moveUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				swapViews(position, 1);
			}

		});
		values.get(position).remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeFeed(position);
			}

		});

		// this is one of those things that works and I cannot explain why

		values.get(position).headerLogo
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						headerOnClick();
					}

				});

	}

	protected void headerOnClick() {
		if (!((MainActivity) mContext).searchB) {
			((MainActivity) mContext).reorderB = true;
			overlay = true;
			Vibrator vib = (Vibrator) mContext
					.getSystemService(Context.VIBRATOR_SERVICE);
			vib.vibrate(300);

			((MainActivity) mContext).getActionBar().getCustomView()
					.findViewById(R.id.action_search_b)
					.setVisibility(View.GONE);
			((MainActivity) mContext).getActionBar().getCustomView()
					.findViewById(R.id.action_done).setVisibility(View.VISIBLE);
			((MainActivity) mContext).getActionBar().getCustomView()
					.findViewById(R.id.action_done)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							doneOnClick();
							overlay = false;
						}

					});
			for (int i = 0; i < values.size(); i++) {

				if (values.get(i) != null && converted.get(i) != null) {

					System.out.println("-------" + i + "-------");
					if (fixNulls(i)) {
						doneOnClick();
						headerOnClick();
						return;
					}
					values.get(i).overlays.setVisibility(View.VISIBLE);// HERE
					values.get(i).overlays
							.setLayoutParams(new RelativeLayout.LayoutParams(
									-1, hight));
					// -1, values.get(i).padding.getHeight()
					// + values.get(i).headerLogo
					// .getHeight()));

					values.get(i).setClickable(false);
					if (i == 3
							&& values.get(3).overlays.getVisibility() != View.VISIBLE) {
						doneOnClick();
						values.get(3).headerLogo.performClick();
						return;
					}

					System.out.println("-------" + i + "-------");

				}
			}
		}

	}

	private boolean fixNulls(int i) {
		if (i < converted.size() && i < values.size()) {
			// System.out.println("before check");
			// System.out.println("feeditemlist its null:"
			// + Boolean.toString(values.get(i).feeditemlist == null));
			// System.out.println("headerLogo its null:"
			// + Boolean.toString(values.get(i).headerLogo == null));
			// System.out.println("padding its null:"
			// + Boolean.toString(values.get(i).padding == null));
			// System.out.println("overlays its null:"
			// + Boolean.toString(values.get(i).overlays == null));
			// System.out.println("remove its null:"
			// + Boolean.toString(values.get(i).remove == null));
			// System.out.println("moveDown its null:"
			// + Boolean.toString(values.get(i).moveDown == null));
			// System.out.println("moveUp its null:"
			// + Boolean.toString(values.get(i).moveUp == null));
			// System.out.println("converted[" + i + "] its null:"
			// + Boolean.toString(converted.get(i) == null));
			boolean before = false;
			boolean after = false;
			if (converted.get(i) == null) {
				// Inflate new View
				// inflate the layout
				before = true;
				LayoutInflater inflater = ((Activity) mContext)
						.getLayoutInflater();
				converted.add(i, inflater.inflate(R.layout.feed, null));
				converted.get(i).setPadding(0, 1, 0, 1);
				converted.get(i).setId(
						((CronusApp) ((Activity) this.getContext())
								.getApplication()).feedIDgen++);
			}

			if (values.get(i).feeditemlist == null) {
				values.get(i).feeditemlist = (LinearLayout) converted.get(i)
						.findViewById(R.id.feeditemlist);

			}
			if (values.get(i).headerLogo == null) {
				values.get(i).headerLogo = (ImageView) converted.get(i)
						.findViewById(R.id.feed_header);

			}
			if (values.get(i).padding == null) {
				values.get(i).padding = (LinearLayout) converted.get(i)
						.findViewById(R.id.feedpadding);

			}
			if (values.get(i).overlays == null) {
				values.get(i).overlays = (RelativeLayout) converted.get(i)
						.findViewById(R.id.feed_overlay);
			}
			if (values.get(i).remove == null) {
				values.get(i).remove = (ImageView) converted.get(i)
						.findViewById(R.id.feed_nav_delete);
			}
			if (values.get(i).moveDown == null) {
				values.get(i).moveDown = (ImageView) converted.get(i)
						.findViewById(R.id.feed_nav_down);
			}
			if (values.get(i).moveUp == null) {
				values.get(i).moveUp = (ImageView) converted.get(i)
						.findViewById(R.id.feed_nav_up);
			}
			if (values.get(i).subtitle == null) {
				values.get(i).subtitle = (TextView) converted.get(i)
						.findViewById(R.id.feed_subtitle);
			}
			if (converted.get(i) != null) {
				after = true;
			}
			// System.out.println("after check");
			// System.out.println("feeditemlist its null:"
			// + Boolean.toString(values.get(i).feeditemlist == null));
			// System.out.println("headerLogo its null:"
			// + Boolean.toString(values.get(i).headerLogo == null));
			// System.out.println("padding its null:"
			// + Boolean.toString(values.get(i).padding == null));
			// System.out.println("overlays its null:"
			// + Boolean.toString(values.get(i).overlays == null));
			// System.out.println("remove its null:"
			// + Boolean.toString(values.get(i).remove == null));
			// System.out.println("moveDown its null:"
			// + Boolean.toString(values.get(i).moveDown == null));
			// System.out.println("moveUp its null:"
			// + Boolean.toString(values.get(i).moveUp == null));
			return (after && before);
		}
		return false;
	}

	protected void doneOnClick() {
		((MainActivity) mContext).reorderB = false;
		((MainActivity) mContext).getActionBar().getCustomView()
				.findViewById(R.id.action_search_b).setVisibility(View.VISIBLE);
		((MainActivity) mContext).getActionBar().getCustomView()
				.findViewById(R.id.action_done).setVisibility(View.GONE);
		for (int i = 0; i < values.size(); i++) {
			values.get(i).overlays.setVisibility(View.INVISIBLE);
			values.get(i).setClickable(true);
		}
	}

	public void updateItems(final int p) {
		// lots of checks to avoid random acurences trust me all needed.
		int i = 0;
		// first remove everything
		fixNulls(p);
		values.get(p).feeditemlist.removeAllViews();

		ArrayList<FeedItem> toadd = null;
		if (values.get(p).isSearchMode())
			toadd = values.get(p).sItems;
		else
			toadd = values.get(p).items;

		// then re-add them
		for (i = 0; i < toadd.size(); i++) {
			ViewGroup parent = (ViewGroup) toadd.get(i).getParent();
			if (parent != null)
				parent.removeView(toadd.get(i));
			values.get(p).feeditemlist.addView(toadd.get(i));
		}

	}

	public void swapViews(int position, int move) {
		if (position == 0 && move == 1)
			return;
		if (position == values.size() && move == -1)
			return;
		Feed temp = this.getItem(position);
		this.remove(temp);
		this.insert(temp, position - move);
		for (int i = 0; i < values.size(); i++) {
			this.updateItems(i);
		}
	}

	public void removeFeed(final int position) {
		final AlertDialog alert = new AlertDialog.Builder(this.getContext())
				.create();
		alert.setTitle("Remove Feed");
		alert.setMessage("Remove the Feed \""
				+ this.getItem(position).getFeedTitle() + "\" forever?");

		alert.setButton(AlertDialog.BUTTON_POSITIVE, "YES Remove It!",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						removeFeed(getItem(position));

					}
				});
		alert.setButton(AlertDialog.BUTTON_NEGATIVE, "NOWAY I'll Keep It!",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						alert.hide();
					}
				});
		alert.show();

	}

	private void removeFeed(Feed toRemove) {
		this.remove(toRemove);
		if (this.getCount() == 0) {
			((MainActivity) this.getContext()).getActionBar().getCustomView()
					.findViewById(R.id.action_done).performClick();
		}
	}
}
