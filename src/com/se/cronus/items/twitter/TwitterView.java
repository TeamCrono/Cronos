package com.se.cronus.items.twitter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.se.cronus.MainActivity;
import com.se.cronus.R;
import com.se.cronus.items.ItemDoc;
import com.se.cronus.items.ItemFragmentView;
import com.se.cronus.utils.CUtils;

public class TwitterView extends ItemFragmentView {
	
	private ImageView sharedImg;
	private ScrollView contentScroll;
	private LinearLayout content;
	private LinearLayout imageOverlay; 
	private LinearLayout tweetLayout;
	private TextView tweet;
	private TextView author;
	//private ArrayList<LinearLayout> comments;
	private ImageView profilePic;
	public ImageView replyButton;
	public ImageView retweetButton;
	public ImageView favoriteButton;
	private Bitmap bimg;

	public TwitterView(ItemDoc d, Context c) {
		super(d, c);
		setOnClicks();
		pullContent();
		setStyles();
	}

	private void setStyles() {
		this.setBackgroundColor(CUtils.TWITTER_BLUE);
		tweetLayout.setBackgroundColor(CUtils.TWITTER_BLUE_LIGHT_CLEAR);
		replyButton.setBackgroundResource(R.drawable.twitter_reply);
		retweetButton.setBackgroundResource(R.drawable.twitter_retweet);
		favoriteButton.setBackgroundResource(R.drawable.twitter_fav_not);
		
		//content.setBackgroundColor(CUtils.TWITTER_BLUE);
		
		//this.setBackgroundColor(Color.rgb(82, 82, 82)); //twitter dark grey
		
//		sharedImg = new ImageView(this.getContext());
//		contentScroll = new ScrollView(this.getContext());
//		content.setBackgroundColor(Color.TRANSPARENT);
//		imageOverlay.setBackgroundColor(Color.CYAN);
		//tweetLayout.setBackgroundColor(Color.WHITE);
		
//		author = new TextView(this.getContext());
//		tweet = new TextView(this.getContext());
//		comments = new ArrayList<LinearLayout>();
//		profilePic = new ImageView(this.getContext());
//		replyButton = new ImageView(this.getContext());
//		retweetButton = new ImageView(this.getContext());
//		favoriteButton = new ImageView(this.getContext());
//		
	}

	@SuppressLint("NewApi")
	public ItemDoc pullContent() {
		sharedImg.setBackground(doc.getImg());
		bimg = CUtils.drawableToBitmap((Activity)this.getContext(), doc.getProfPic(), 100, 100);
		profilePic.setBackground(new BitmapDrawable(this.getResources(),bimg));
		author.setText(doc.getAuthor());
		author.setTypeface(null, Typeface.BOLD);
		tweet.setText(doc.getStatus());
		LinearLayout commentsLayout = new LinearLayout(this.getContext());
		commentsLayout.setOrientation(LinearLayout.VERTICAL);
		commentsLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		commentsLayout.setBackgroundColor(CUtils.TWITTER_BLUE_LIGHT);
		content.addView(commentsLayout);
		
		for(Pair<String, String> cur : doc.getComments())
		{
			LinearLayout tempComment = new LinearLayout(this.getContext());
			tempComment.setOrientation(LinearLayout.VERTICAL);
			tempComment.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			TextView tempAuthor = new TextView(this.getContext());
			tempAuthor.setTypeface(null, Typeface.BOLD);
			tempAuthor.setText(cur.first);
			TextView tempText = new TextView(this.getContext());
			tempText.setText(cur.second);
			tempText.setBackgroundColor(Color.WHITE);
			tempText.setPaddingRelative(10, 0, 0, 10);
			tempComment.addView(tempAuthor);
			tempComment.addView(tempText);
//			tempComment.setBackgroundColor(Color.WHITE);
			tempAuthor.setBackgroundColor(Color.WHITE);
			commentsLayout.addView(tempComment);
//			tempComment.setBackgroundColor(Color.WHITE);
			tempComment.setPadding(10, 10, 10, 10);
		}
		return this.getDoc();
	}

	private void setOnClicks() {
		clicklistener = new TwitterCntrl(this);
		replyButton.setOnClickListener(clicklistener);
		retweetButton.setOnClickListener(clicklistener);
		favoriteButton.setOnClickListener(clicklistener);
	}

	@Override
	protected void onCreate() {
		sharedImg = new ImageView(this.getContext());
		contentScroll = new ScrollView(this.getContext());
		content = new LinearLayout(this.getContext());
		imageOverlay = new LinearLayout(this.getContext());
		tweetLayout = new LinearLayout(this.getContext());
		author = new TextView(this.getContext());
		tweet = new TextView(this.getContext());
		//comments = new ArrayList<LinearLayout>();
		profilePic = new ImageView(this.getContext());
		replyButton = new ImageView(this.getContext());
		retweetButton = new ImageView(this.getContext());
		favoriteButton = new ImageView(this.getContext());
		author = new TextView(this.getContext());
	}

	@Override
	protected void setLayoutParams() {
		//params for image
		RelativeLayout.LayoutParams pImage = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getScreenHeight((MainActivity) this.getContext())/2);
		pImage.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pImage.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		sharedImg.setLayoutParams(pImage);
		
		//params for the scrolling content
		RelativeLayout.LayoutParams pContent = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		pContent.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pContent.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		contentScroll.setLayoutParams(pContent);
		
		//add top level views
		this.addView(sharedImg);
		this.addView(contentScroll);
		
		content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		content.setOrientation(LinearLayout.VERTICAL);
		contentScroll.addView(content);
		
		//now dealing with content
		imageOverlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getScreenHeight((MainActivity) this.getContext())/2));
		imageOverlay.setVisibility(View.INVISIBLE);
		tweetLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		tweetLayout.setOrientation(LinearLayout.HORIZONTAL);
		content.addView(imageOverlay);
		content.addView(tweetLayout);
		
		//tweetLayout stuff
		LinearLayout tweetInner = new LinearLayout(this.getContext());
		tweetInner.setOrientation(LinearLayout.VERTICAL);
		tweetInner.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		LinearLayout innerButtons = new LinearLayout(this.getContext());
		innerButtons.setOrientation(LinearLayout.HORIZONTAL);
		innerButtons.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
//		profilePic.setLayoutParams(new LayoutParams(100, 100));
		tweetLayout.addView(profilePic);
		tweetLayout.addView(tweetInner);
		tweetInner.addView(author);
		tweetInner.addView(tweet);
		tweetInner.addView(innerButtons);
		tweetLayout.setPadding(0, 0, 0, 5);
		
		//add buttons to tweetInner, innerButtons
		innerButtons.addView(replyButton);
		innerButtons.addView(retweetButton);
		innerButtons.addView(favoriteButton);
		
		
	}

	@Override
	public void destroy() {
//		bimg.recycle();
	}

}
