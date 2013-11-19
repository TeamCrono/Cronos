package com.se.cronus.items;

import com.se.cronus.R;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public abstract class ItemFragmentView extends /*View*/ FrameLayout {
	
	private ItemDoc doc;
	private int type;
	
	protected ItemFragmentCntrl clicklistener;
	
	
	// stuff
	Button add;
	public ItemFragmentView(int t, Context c){
		super(c);
		this.setType(t);
		onCreate();
		setLayoutParams();
		add = new Button(this.getContext());
		this.addView(add);
		
		
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	/*abstract methods to override*/
	
	protected abstract void onCreate();
	protected abstract void setLayoutParams();
	public abstract boolean setDoc(ItemDoc d);
	public abstract ItemDoc getDoc();

	public abstract void destroy();
}
