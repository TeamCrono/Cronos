package com.se.cronus.items;

import com.se.cronus.utils.CUtils;

import android.content.Context;
import android.widget.TextView;

public class TestFragView extends ItemFragmentView {
	private TextView testviewtextlongnameeasytofind;
	public TestFragView(int t, Context c) {
		super(t, c);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void onCreate() {
		// TODO Auto-generated method stub
		testviewtextlongnameeasytofind = new TextView(this.getContext());
		testviewtextlongnameeasytofind.setText("This will show more information on any image you click to the left! \n\n Go ahead try it!  \n\n" + getType());
		testviewtextlongnameeasytofind.setTextSize(testviewtextlongnameeasytofind.getTextSize());
	
		this.addView(testviewtextlongnameeasytofind);
	
	}

	@Override
	protected void setLayoutParams() {
		// TODO Auto-generated method stub
		switch(getType()){
		case CUtils.FACEBOOK_FEED:
			this.setBackgroundColor(CUtils.FACEBOOK_BLUE);
			break;
		case CUtils.TWITTER_FEED:
			this.setBackgroundColor(CUtils.TWITTER_BLUE);
			break;
		case CUtils.INSTA_FEED:
			this.setBackgroundColor(CUtils.INSTA_BROWN);
			break;
		case CUtils.PINTREST_FEED:
			this.setBackgroundColor(CUtils.PINTREST_RED);
			break;
		}
	}

	@Override
	public boolean setDoc(ItemDoc d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemDoc getDoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
