package com.se.cronus.backend;

import java.util.ArrayList;

import com.se.cronus.MainActivity;
import com.se.cronus.Feeds.Feed;
import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CronusApp;

public class ItemGenerator implements Runnable{
	private DocGen docgen;
	private int numNeeded;
	private Feed thisF;
	private CronusApp app;
	
	
	public ItemGenerator(Feed f) {
		// TODO Auto-generated constructor stub
		docgen = new DocGen(f.getContext());
		numNeeded = 1;
		thisF = f;
		app = ((CronusApp)((MainActivity)thisF.getContext()).getApplication());
	}
	public void numItemsRequested(int i){
		numNeeded = i;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < numNeeded; i++)
			genItem();
		
		
		
		
		//last thing is to reset the numNeeded to 1
		numNeeded = 1;
	}
	private FeedItem genItem() {
		// TODO Auto-generated method stub
		FeedItem gened = new FeedItem(thisF.getContext(), thisF.type, docgen.getNewDoc(thisF.type),
				((CronusApp)((MainActivity)thisF.getContext()).getApplication()).feedIDgen++);
		post(gened);
		return gened;
	}
	
	private synchronized void post(final FeedItem gened) {
		// TODO Auto-generated method stub
		thisF.feeditemlist.post(new Runnable(){

			@Override
			public void run() {
				thisF.onItemRecieved(gened);
				((MainActivity)thisF.getContext()).feedAdapt.resetItems();
				
			}
			
		});
	}

}
