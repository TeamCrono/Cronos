package com.se.cronus.items.instagram;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.se.cronus.MainActivity;
import com.se.cronus.items.ItemDoc;
import com.se.cronus.items.ItemFragmentView;
import com.se.cronus.utils.CUtils;

/**
 * 
 * @author Jeremiah Janney
 *
 */
public class InstagramItemFragmentView extends ItemFragmentView{

	private ScrollView mainScroll;
	private LinearLayout content;
	private LinearLayout titleBar;
	private LinearLayout titleOverlay;
	private LinearLayout infoLayout; //contains likes, author and description
	private LinearLayout infoPics;	//contains likes heart and comment pics
	private LinearLayout infoContainer; //contains the infoLayout and infoPics layouts
	private LinearLayout buttonHolder;
	private TextView author;
	private TextView iAuthor;
	private TextView description;
	private TextView likesText;
	private ImageView image;		//main image
	private ImageView likesHeart;
	private ImageView commentsImg;
	private ImageView profilePic;
	public ImageView likeButton;
	public ImageView commentButton;
	
	private Bitmap bImg;
	private float ratio;
	private int likes;
	
	public InstagramItemFragmentView(ItemDoc d, Context c) {
		super(d, c);
		pullContent();
		setStyles();
	}

	@SuppressLint("NewApi")
	private ItemDoc pullContent() {
		Drawable img = doc.getImg();
		ratio = img.getIntrinsicWidth() / img.getIntrinsicHeight();
		bImg = CUtils.drawableToBitmap((MainActivity) this.getContext(), img, CUtils.getScreenWidth((MainActivity) this.getContext()), ratio);
		img = new BitmapDrawable(getResources(), bImg);
		likes = doc.getNumLikes();
		image.setBackground(img);
		profilePic.setBackground(doc.getProfPic());
		author.setText(doc.getAuthor());
		iAuthor.setText(doc.getAuthor() + " ");
		likesText.setText(likes + "likes");
		description.setText(doc.getStatus());
		for(Pair<String, String> cur : doc.getComments()) {
			LinearLayout comment = new LinearLayout(this.getContext());
			TextView cAuthor = new TextView(this.getContext());
			cAuthor.setTextColor(Color.rgb(81, 127, 164));
			cAuthor.setTypeface(null, Typeface.BOLD);
			cAuthor.setText(cur.first + " ");
			TextView commentText = new TextView(this.getContext());
			commentText.setTextColor(Color.rgb(140, 140, 140));
			commentText.setText(cur.second);
			comment.addView(cAuthor);
			comment.addView(commentText);
			content.addView(comment);
		}
		return this.getDoc();
	}

	@Override
	protected void onCreate() {
		mainScroll = new ScrollView(this.getContext());
		content = new LinearLayout(this.getContext());
		titleBar = new LinearLayout(this.getContext());
		titleOverlay = new LinearLayout(this.getContext());
		infoContainer = new LinearLayout(this.getContext());
		infoLayout = new LinearLayout(this.getContext());
		infoPics = new LinearLayout(this.getContext());
		buttonHolder = new LinearLayout(this.getContext());
		author = new TextView(this.getContext());
		iAuthor = new TextView(this.getContext());
		description = new TextView(this.getContext());
		likesText = new TextView(this.getContext());
		image = new ImageView(this.getContext());
		likesHeart = new ImageView(this.getContext());
		profilePic = new ImageView(this.getContext());
		likeButton = new ImageView(this.getContext());
		commentButton = new ImageView(this.getContext());
		// TODO: create comment button, like button
	}
	
	private void setStyles() {
		titleBar.setBackgroundColor(CUtils.INSTA_BROWN_CLEAR);
		author.setTextColor(Color.rgb(81, 127, 164));
		author.setTypeface(null, Typeface.BOLD);
		iAuthor.setTextColor(Color.rgb(81, 127, 164));
		iAuthor.setTypeface(null, Typeface.BOLD);
		description.setTextColor(Color.rgb(140, 140, 140));
	}

	@Override
	protected void setLayoutParams() {

		//params for title
		RelativeLayout.LayoutParams pTitle = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext()));//finish later TODO
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleBar.setOrientation(LinearLayout.HORIZONTAL);
		titleBar.setLayoutParams(pTitle);
		
		//params for scrolling stuff
		RelativeLayout.LayoutParams pScroll = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		pScroll.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pScroll.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		mainScroll.setLayoutParams(pScroll);
		
		//add top level views
		this.addView(mainScroll);
		this.addView(titleBar);
		
		content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		content.setOrientation(LinearLayout.VERTICAL);
		mainScroll.addView(content);
		
		//titleBar stuff
		profilePic.setLayoutParams(new LayoutParams(75, 75));//TODO, acutally crop this photo
		titleBar.addView(profilePic);
		titleBar.addView(author);
		
		//Layout for prettiness
		titleOverlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext())));
		titleOverlay.setVisibility(View.INVISIBLE);
		infoContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		infoContainer.setOrientation(LinearLayout.VERTICAL);
		buttonHolder.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		buttonHolder.setOrientation(LinearLayout.HORIZONTAL);
		content.addView(titleOverlay);
		content.addView(image);
		content.addView(infoContainer);
		content.addView(buttonHolder);
		
		//Layout for infoContainer
		infoLayout.setLayoutParams(new LayoutParams((int) (CUtils.getScreenWidth((MainActivity) this.getContext()) * .95), LayoutParams.WRAP_CONTENT));
		infoLayout.setOrientation(LinearLayout.VERTICAL);
		infoPics.setLayoutParams(new LayoutParams((int) (CUtils.getScreenWidth((MainActivity) this.getContext()) * .05), LayoutParams.WRAP_CONTENT));
		infoPics.setOrientation(LinearLayout.VERTICAL);
		infoContainer.addView(infoPics);
		infoContainer.addView(infoLayout);
		
		//info layout
		if (likes > 0)
			infoLayout.addView(likesText);
		likesText.setTextColor(Color.rgb(140, 140, 140));
		LinearLayout descriptionAuthor = new LinearLayout(this.getContext());
		descriptionAuthor.setOrientation(LinearLayout.HORIZONTAL);
		descriptionAuthor.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));
		infoLayout.addView(descriptionAuthor);
		descriptionAuthor.addView(iAuthor);
		descriptionAuthor.addView(description);
		
		//info pics
//		if (likes > 0)
//			infoPics.addView(likesHeart);
//		infoPics.addView(commentsImg);
		
		//buttons :)
		likeButton.setClickable(true);
		likeButton.setOnClickListener(this.clicklistener);
		commentButton.setClickable(true);
		commentButton.setOnClickListener(this.clicklistener);
		buttonHolder.addView(likeButton);
		buttonHolder.addView(commentButton);
		
		
	}

	@Override
	public void destroy() {
		bImg.recycle();
		
	}

}
