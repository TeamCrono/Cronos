package com.se.cronus.items;

import com.se.cronus.MainActivity;
import com.se.cronus.R;
import com.se.cronus.R.drawable;
import com.se.cronus.utils.CUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Pair;
import android.view.View;
import android.view.View.OnClickListener;

/***
 * 
 * @author dj
 * 
 */
public class FeedItem extends RelativeLayout implements OnClickListener {
	public int type;
	private ImageView bg;
	private TextView tv;
	private int H = 150;
	private int W = 150;
	private Drawable bgpic;
	private String tvstr;
	private int itemid;
	private ItemDoc Doc;
	private ItemFragmentView view;
	

	public ItemDoc getDoc() {
		return Doc;
	}

	public ImageView getBg() {
		return bg;
	}

	public void setBg(Drawable bg) {
		this.bgpic = bg;
	}

	public TextView getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tvstr = tv;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public FeedItem(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type = type;
		bg = new ImageView(context);
		tv = new TextView(context);
		Doc = new ItemDoc();

		this.setClickable(true);

		setLayoutParams();

		

	}

	public FeedItem(Context con, int type, ItemDoc doc) {
		super(con);
		createView(type, doc);
		this.type = type;
		bg = new ImageView(con);
		tv = new TextView(con);
		Doc = new ItemDoc();
		this.setClickable(true);

		setLayoutParams();

	}

	private void createView(int type2, ItemDoc doc2) {
		// TODO: team mates make the different views
		switch (type2) {
		case CUtils.FACEBOOK_FEED:
			view = null;
			break;
		case CUtils.TWITTER_FEED:
			view = null;
			break;
		case CUtils.INSTA_FEED:
			view = null;
			break;
		case CUtils.PINTREST_FEED:
			view = null;
			break;
		}
	}

	@SuppressLint("NewApi")
	private void setLayoutParams() {
		// set params
		LayoutParams thisP = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		setLayoutParams(thisP);

		this.addView(bg);
		this.addView(tv);

		LayoutParams bgP = new LayoutParams(W, H);
		bgP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bg.setLayoutParams(bgP);

		if (bgpic == null)
			if ( Doc.getImg() == null)
				bgpic = this.getResources().getDrawable(
						R.drawable.deadpool_profile_pic_test);
			else
				bgpic = Doc.getImg();

		Bitmap d = CUtils.drawableToBitmap(((Activity) this.getContext()),
				bgpic, W, H);

		bgpic = new BitmapDrawable(getResources(), d);

		bg.setBackground(bgpic);
		// never ever forget this line
		// d.recycle(); TODO :put this line in were ever we remove items

		LayoutParams tvP = new LayoutParams(W, LayoutParams.WRAP_CONTENT);
		tvP.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		tvP.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		tv.setLayoutParams(tvP);

		if (tvstr == null)
			if ( (Doc.getStatus() == null || Doc.getStatus().length() == 0))
				tvstr = "TEST STATUS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
			else
				tvstr = Doc.getStatus();
		tv.setText(tvstr);
		tv.setMaxLines(3);
		tv.setTextSize(CUtils.FONT_SIZE_SMALL);

		// set fonts and colors

		fixColors();

		tv.bringToFront();

		this.setPadding(10, 7, 7, 10);
		this.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FeedItem vx = (FeedItem) v;
		fixColors();
		// switch may not be needed
		switch (vx.type) {

		case CUtils.FACEBOOK_FEED:
			openFaceBook();
			break;
		case CUtils.TWITTER_FEED:
			openTwitter();
			break;
		case CUtils.INSTA_FEED:
			openInsta();
			break;
		case CUtils.PINTREST_FEED:
			openPintrest();
			break;
		}
		if (view != null) {
			MainActivity main = (MainActivity) this.getContext()
					.getApplicationContext();
			main.changeItemFragment(view);
		} else {
			MainActivity main = (MainActivity) this.getContext();
			// .getApplicationContext();
			main.changeItemFragment(new TestFragView(vx.type, this.getContext()));
		}
	}

	private void fixColors() {// not really needed
		// TODO: get new fonts
		switch (type) {
		case CUtils.FACEBOOK_FEED:
			tv.setBackgroundColor(CUtils.FACEBOOK_BLUE_CLEAR);
			break;
		case CUtils.TWITTER_FEED:
			tv.setBackgroundColor(CUtils.TWITTER_BLUE_CLEAR);
			tv.setTextColor(Color.BLACK);
			break;
		case CUtils.INSTA_FEED:
			tv.setBackgroundColor(CUtils.INSTA_BROWN_CLEAR);
			break;
		case CUtils.PINTREST_FEED:
			tv.setBackgroundColor(CUtils.PINTREST_RED_CLEAR);
		}

	}

	
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		FeedItem fi;
		try {
			fi = (FeedItem) o;
		}catch (Error e){
			return false;
		}
		
		return fi.getDoc().equals(Doc);
		
		//return super.equals(o);
	}

	private void openTwitter() {
		// TODO Auto-generated method stub
		System.out.println("twitter click");

	}

	private void openInsta() {
		// TODO Auto-generated method stub
		System.out.println("intsagram click");

	}

	private void openPintrest() {
		// TODO Auto-generated method stub
		System.out.println("pintrest click");

	}

	private void openFaceBook() {
		// TODO Auto-generated method stub
		System.out.println("facebook click");
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public boolean search(String toFind) {
		// TODO Come up with a good way to search through stuff
		if(Doc.getAuthor().contains(toFind))
			return true;
		if(Doc.getStatus().contains(toFind))
			return true;
		for(Pair<String, String> c :Doc.getComments()){
			if(c.first.contains(toFind))
				return true;
			if(c.second.contains(toFind))
				return true;
		}
		for(String l : Doc.getWhoLiked()){
			if(l.contains(toFind))
				return true;
		}
		for(String s : Doc.getWhoShared()){
			if(s.contains(toFind))
				return true;
		}
		
		
		return false;
	}

}
