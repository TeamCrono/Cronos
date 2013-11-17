package com.se.cronus.Feeds;

import java.util.ArrayList;

import com.se.cronus.MainActivity;
import com.se.cronus.items.FeedItem;
import com.se.cronus.utils.CUtils;
import com.se.cronus.utils.CronusApp;

import android.content.Context;
import android.widget.LinearLayout;

/***
 * 
 * @author dj
 * 
 */
public class Feed extends LinearLayout {

	public int type;
	public ArrayList<FeedItem> items;
	public boolean isInit;
	private ArrayList<FeedItem> rItems;

	// variables to handle search1
	private ArrayList<FeedItem> sItems;
	private boolean searchMode;// Activate!

	public Feed(Context context, int type) {
		super(context);
		// TODO Auto-generated constructor stub
		this.type = type;

		isInit = false;

		items = new ArrayList<FeedItem>();
		rItems = new ArrayList<FeedItem>();
		sItems = new ArrayList<FeedItem>();
		Integer feedids = ((CronusApp)((MainActivity)this.getContext()).getApplication()).feedIDgen;
		if (MainActivity.TESTING) {
			// for testing loop
			for (int i = 0; i < 4; i++) {
				items.add(new FeedItem(this.getContext(), type,feedids++));
				items.add(new FeedItem(this.getContext(), type,feedids++));
				items.add(new FeedItem(this.getContext(), type,feedids++));
				items.add(new FeedItem(this.getContext(), type,feedids++));
			}
		}
		((CronusApp)((MainActivity)this.getContext()).getApplication()).feedIDgen = feedids;
		searchMode = false;
		 
	}

	public boolean onItemRecieved(FeedItem item) {
		//TODO: still need to finish this!
		if (items.size() >= 15) {
			rItems.add(items.remove(items.size() - 1));
		}

		if (contains(item))
			return false;
		else
			return items.add(item);
	}

	private boolean contains(FeedItem item) {
		// TODO Done but not tested

		for (int i = 0; i < items.size(); i++) {
			if (item.equals(items.get(i))) {
				return true;
			}
		}
		for (int i = 0; i < rItems.size(); i++) {
			if (item.equals(rItems.get(i))) {
				return true;
			}
		}

		return false;
	}

	public void add(FeedItem item) {
		items.add(item);
	}

	// this handles the view when in searchy searchy mode!
	public void offSearch() {
		searchMode = false;

	}

	public void onSearch(String toFind) {
		searchMode = true;
		// Emptey all the items in search list
		for (int i = 0; i < sItems.size(); i++)
			sItems.remove(i);
		ArrayList<FeedItem> fullList = new ArrayList<FeedItem>();
		fullList.addAll(items);
		fullList.addAll(rItems);
		
		for(int i = 0; i < fullList.size(); i++){
			if(fullList.get(i).search(toFind))
				sItems.add(fullList.get(i));
		}
		
	}
	public ArrayList<FeedItem> getSearch(){
		return rItems;
	}
	public boolean isSearchMode() {
		return searchMode;
	}

}