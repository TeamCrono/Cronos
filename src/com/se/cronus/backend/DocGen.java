package com.se.cronus.backend;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.util.Pair;

import com.se.cronus.R;
import com.se.cronus.items.ItemDoc;
import com.se.cronus.utils.CUtils;

public class DocGen {
	private Context mContext;
	//this is the part that actualy gets the info from the social media.
	public DocGen(Context c) {
		// TODO Auto-generated constructor stub
		mContext = c;
	}

	
	public ItemDoc getNewDoc(int type) {
		// TODO Auto-generated method stub
		switch(type){
		case CUtils.INSTA_FEED:
			return instaDoc(type);
		case CUtils.FACEBOOK_FEED:
			return faceDoc(type);
		case CUtils.PINTREST_FEED:
			return pinDoc(type);
		case CUtils.TWITTER_FEED:
			return twitDoc(type);
		case CUtils.TEST_FEED:
			return testDoc(type);
		}
		return null;
	}


	private ItemDoc testDoc(int t) {
		// TODO Auto-generated method stub
		ArrayList<Pair<String,String>> comments = new ArrayList<Pair<String,String>>();
		Random ran = new Random();
		ran.setSeed(System.currentTimeMillis());
		ItemDoc newDoc = new ItemDoc();
		newDoc.setAuthor("DJ Test"+ran.nextInt(100));
		for(int i = 0; i < ran.nextInt(10); i++)
			comments.add(new Pair<String,String>("Liz Test","My Comments"));
		newDoc.setComments(comments);
		
		newDoc.setImg(mContext.getResources().getDrawable(R.drawable.deadpool_profile_pic_test));
		newDoc.setProfPic(mContext.getResources().getDrawable(R.drawable.temp_cronos_logo));
		newDoc.setStatus("Lets just pretend this works for now!"+ran.nextInt(100));
		newDoc.setType(t);
		ArrayList<String> url = new ArrayList<String>();
		url.add("https://www.google.com");
		newDoc.setURL(url);
		ArrayList<String> whoLiked = new ArrayList<String>();
		whoLiked.add("Nate Test"+ran.nextInt(100));
		newDoc.setWhoLiked(whoLiked);
		ArrayList<String> whoShared = new ArrayList<String>();
		whoShared.add("Em Test"+ran.nextInt(100));
		newDoc.setWhoShared(whoShared);
		
		
		return newDoc;
	}


	private ItemDoc twitDoc(int t) {
		// TODO Auto-generated method stub
		return testDoc(t);
	}


	private ItemDoc pinDoc(int t) {
		// TODO Auto-generated method stub
		return testDoc(t);
	}


	private ItemDoc faceDoc(int t) {
		// TODO Auto-generated method stub
		return testDoc(t);
	}


	private ItemDoc instaDoc(int t) {
		// TODO Auto-generated method stub
		return testDoc(t);
	}

}
