package com.se.cronus.items;

import android.view.View.OnClickListener;

public abstract class ItemFragmentCntrl  implements OnClickListener{

	public abstract boolean onLikeClick();
	public abstract boolean onCommentClick();
	
	
	public ItemFragmentCntrl() {
		// TODO Auto-generated constructor stub
	}

}
