package com.se.cronus.backend;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
		newDoc.setAuthor(testNAME());
		for(int i = 0; i < 10; i++)
			comments.add(new Pair<String,String>("Liz Test",testComment()));
		newDoc.setComments(comments);
		
		newDoc.setImg(testPicGen());
		newDoc.setProfPic(testPicGen());
		newDoc.setStatus(testComment());
		newDoc.setType(t);
		ArrayList<String> url = new ArrayList<String>();
		url.add("https://www.google.com");
		newDoc.setURL(url);
		ArrayList<String> whoLiked = new ArrayList<String>();
		whoLiked.add(testNAME());
		newDoc.setWhoLiked(whoLiked);
		ArrayList<String> whoShared = new ArrayList<String>();
		whoShared.add(testNAME());
		newDoc.setWhoShared(whoShared);
		
		
		return newDoc;
	}
	
	private String testComment(){
		Random ran = new Random();
		switch(ran.nextInt(12)){
		case 0:
			return "Dont do it!!!";
		case 1:
			return "THats SOOOOO RAD";
		case 2:
			return "More Power too ya!";
		case 3:
			return "Could be way worse, I think you lucked out";
		case 4:
			return "I'm so Gladd we passed 319!!";
		case 5:
			return "Dont Tell Him We totally pranked him Good";
		case 6:
			return "You spelt favorite wrong";
		case 7:
			return "SPAM: Click like to save puppies";
		case 8:
			return "THE ONE RING";
		case 9:
			return "Kendall Durkee REWLZ!";
		case 10:
			return "Once More Coulding Hurt";
		case 11:
			return "My New Favorite Band";
		};
		return "DJ TODD IS THE BEST!!!1";
	}
	
	private String testNAME(){
		Random ran = new Random();
		switch(ran.nextInt(12)){
		case 0:
			return "DJ Todd";
		case 1:
			return "Kendall Durkee";
		case 2:
			return "Mario Leuang";
		case 3:
			return "Zach Rasavanh";
		case 4:
			return "Jerry Jan";
		case 5:
			return "Dan Rust";
		case 6:
			return "Jordan Fitz";
		case 7:
			return "Don Gordo";
		case 8:
			return "Pete Scifert";
		case 9:
			return "Kendale Tannman";
		case 10:
			return "Iron Man";
		case 11:
			return "DEADPOOL";
		};
		return "Liz Todd";
	}
	
	private Drawable testPicGen(){
		Random ran = new Random();
		switch(ran.nextInt(12)){
		case 0:
			return mContext.getResources().getDrawable(R.drawable.test_pick_1);
		case 1:
			return mContext.getResources().getDrawable(R.drawable.test_pick_2);
		case 2:
			return mContext.getResources().getDrawable(R.drawable.test_pick_3);
		case 3:
			return mContext.getResources().getDrawable(R.drawable.test_pick_4);
		case 4:
			return mContext.getResources().getDrawable(R.drawable.test_pick_5);
		case 5:
			return mContext.getResources().getDrawable(R.drawable.test_pick_6);
		case 6:
			return mContext.getResources().getDrawable(R.drawable.test_pick_7);
		case 7:
			return mContext.getResources().getDrawable(R.drawable.test_pick_8);
		case 8:
			return mContext.getResources().getDrawable(R.drawable.test_pick_9);
		case 9:
			return mContext.getResources().getDrawable(R.drawable.test_pick_10);
		case 10:
			return mContext.getResources().getDrawable(R.drawable.test_pick_11);
		case 11:
			return mContext.getResources().getDrawable(R.drawable.test_pick_12);
		};
		return mContext.getResources().getDrawable(R.drawable.temp_cronos_logo);
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
