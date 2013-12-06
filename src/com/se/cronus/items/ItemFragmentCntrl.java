package com.se.cronus.items;

import com.se.cronus.R;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class ItemFragmentCntrl  implements OnClickListener{

	public abstract boolean onLikeClick();
	public abstract boolean onCommentClick();
	private ItemFragmentView parent;
	
	public ItemFragmentCntrl( ItemFragmentView parent) {
		// TODO Auto-generated constructor stub
		this.parent = parent;
		
	}

}
