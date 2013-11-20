package com.se.cronus.items;

import com.se.cronus.R;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class ItemFragmentCntrl  implements OnClickListener{

	public abstract boolean onLikeClick();
	public abstract boolean onCommentClick();
	
	
	public ItemFragmentCntrl() {
		// TODO Auto-generated constructor stub
		
		
	}

}
