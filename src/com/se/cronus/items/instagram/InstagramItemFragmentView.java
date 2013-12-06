package com.se.cronus.items.instagram;

import java.util.ArrayList;

import android.content.Context;
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
	private TextView author;
	private TextView description;
	private TextView likesText;
	private ImageView image;		//main image
	private ImageView likesHeart;
	private ImageView commentsImg;
	private ImageView profilePic;
	public ImageView likeButton;
	public ImageView commentButton;
	
	public InstagramItemFragmentView(ItemDoc d, Context c) {
		super(d, c);
	}

	@Override
	protected void onCreate() {
		mainScroll = new ScrollView(this.getContext());
		content = new LinearLayout(this.getContext());
		titleBar = new LinearLayout(this.getContext());
		titleOverlay = new LinearLayout(this.getContext());
		infoLayout = new LinearLayout(this.getContext());
		author = new TextView(this.getContext());
		description = new TextView(this.getContext());
		likesText = new TextView(this.getContext());
		image = new ImageView(this.getContext());
		likesHeart = new ImageView(this.getContext());
		profilePic = new ImageView(this.getContext());
		likeButton = new ImageView(this.getContext());
		commentButton = new ImageView(this.getContext());
	}

	@Override
	protected void setLayoutParams() {

		//params for title
		RelativeLayout.LayoutParams pTitle = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext()));//finish later TODO
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleBar.setLayoutParams(pTitle);
		
		//params for scrolling stuff
		RelativeLayout.LayoutParams pScroll = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		pScroll.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pScroll.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		mainScroll.setLayoutParams(pScroll);
		
		//add top level views
		this.addView(titleBar);
		this.addView(mainScroll);
		
		content.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		content.setOrientation(LinearLayout.VERTICAL);
		mainScroll.addView(content);
		
		//Layout for prettiness
		titleOverlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext())));
		titleOverlay.setVisibility(View.INVISIBLE);
		infoLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		infoLayout.setOrientation(LinearLayout.HORIZONTAL);
		content.addView(titleOverlay);
		content.addView(image);
		content.addView(infoLayout);
		
		//info layout
		infoLayout.addView(likesText);
		LinearLayout descriptionAuthor = new LinearLayout(this.getContext());
		descriptionAuthor.setOrientation(LinearLayout.VERTICAL);
		descriptionAuthor.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));
		infoLayout.addView(descriptionAuthor);
		descriptionAuthor.addView(profilePic);
		descriptionAuthor.addView(author);
		descriptionAuthor.addView(description);
		
		//buttons :)
		
	}

	@Override
	public boolean setDoc(ItemDoc d) {
		doc = d;
		return true;
	}

	@Override
	public ItemDoc getDoc() {
		return doc;
	}

	@Override
	public void destroy() {
		// TODO Maybe later
		
	}

}
