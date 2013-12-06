package com.se.cronus.items.instagram;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
	
	private ItemDoc doc;
	private ScrollView mainScroll;
	private LinearLayout content;
	private LinearLayout titleBar;
	private LinearLayout titleOverlay;
	private LinearLayout infoLayout; //contains likes, author and description
	private TextView author;
	private TextView iAuthor;
	private TextView description;
	private TextView likesText;
	private ImageView image;		//main image
	private ImageView likesHeart;
	private ImageView commentsImg;
	private ImageView profilePic;
	private ImageView iProfilePic;
	public ImageView likeButton;
	public ImageView commentButton;
	
	public InstagramItemFragmentView(ItemDoc d, Context c) {
		super(d, c);
		pullContent();
	}

	@SuppressLint("NewApi")
	private ItemDoc pullContent() {
		image.setBackground(doc.getImg());
		profilePic.setBackground(doc.getProfPic());
		iProfilePic.setBackground(doc.getProfPic());
		author.setText(doc.getAuthor());
		iAuthor.setText(doc.getAuthor());
		likesText.setText("#" + doc.getNumLikes() + "likes");
		description.setText(doc.getStatus());
		return this.getDoc();

	public InstagramItemFragmentView(ItemDoc d, Context c) {
		super(d, c);
		setDoc(d);
		// TODO Auto-generated constructor stub
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
		// TODO: create comment button, like button
	}

	@Override
	protected void setLayoutParams() {

		//params for title
		RelativeLayout.LayoutParams pTitle = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext()));//finish later TODO
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		pTitle.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		titleBar.setOrientation(LinearLayout.VERTICAL);
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
		
		//titleBar stuff
		profilePic.setLayoutParams(new LayoutParams(100, 100));//TODO, acutally crop this photo
		titleBar.addView(profilePic);
		titleBar.addView(author);
		
		//Layout for prettiness
		titleOverlay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CUtils.getActBarH(this.getContext())));
		titleOverlay.setVisibility(View.INVISIBLE);
		infoLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		infoLayout.setOrientation(LinearLayout.VERTICAL);
		content.addView(titleOverlay);
		content.addView(image);
		content.addView(infoLayout);
		
		//info layout
		infoLayout.addView(likesText);
		LinearLayout descriptionAuthor = new LinearLayout(this.getContext());
		descriptionAuthor.setOrientation(LinearLayout.HORIZONTAL);
		descriptionAuthor.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT));
		infoLayout.addView(descriptionAuthor);
//		iProfilePic.setLayoutParams(new LayoutParams(100, 100));//TODO, acutally crop this photo
		descriptionAuthor.addView(iProfilePic);
		descriptionAuthor.addView(iAuthor);
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
