package com.se.cronus.items;

import java.util.ArrayList;

import com.se.cronus.utils.CUtils;

import android.graphics.drawable.Drawable;
import android.util.Pair;
/***
 * DOCUMENT FOR POPULATING VIEWS IN THE ITEM FRAGMENT
 * @author dj
 *
 */
public class ItemDoc {

	/* This document should work regardless of feed type */
	private Drawable img;
	private Drawable profPic;
	private String Author;
	private String status;
	private ArrayList<String> whoLiked;
	private ArrayList<String> whoShared;
	private ArrayList<Pair<String, String>> Comments;
	private ArrayList<String> URL;
	private int type;

	
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		ItemDoc Io = (ItemDoc) o;
		//if someone posts a statsus saying the same thing we be like, not in this house!
		if(Io.Author.equals(Author) && Io.getStatus().equals(status) && Io.getType() == type){
			return true;
		}
		return false;
		
	}

	public ItemDoc() {
		// set defaults to null!!
		img = null;
		profPic = null;
		Author = new String();
		status = new String();
		whoLiked = new ArrayList<String>();
		whoShared =  new ArrayList<String>();
		Comments =  new ArrayList<Pair<String,String>>();
		URL =  new ArrayList<String>();
		type = CUtils.TEST_FEED;

	}

	public int getNumLikes() {
		if (whoLiked != null)
			return whoLiked.size();
		return 0;
	}

	public int getNumShares() {
		if (whoShared != null)
			return whoShared.size();
		return 0;
	}

	public int getNumComments() {
		if (Comments != null)
			return Comments.size();
		return 0;
	}

	public Drawable getImg() {
		if (img != null)
			return img;
		return profPic;
	}

	public void setImg(Drawable img) {
		this.img = img;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<String> getWhoLiked() {
		return whoLiked;
	}

	public void setWhoLiked(ArrayList<String> whoLiked) {
		this.whoLiked = whoLiked;
	}

	public void addWhoLiked(String who){
		this.whoLiked.add(who);
	}
	
	public ArrayList<String> getWhoShared() {
		return whoShared;
	}
	
	public void setWhoShared(ArrayList<String> whoShared) {
		this.whoShared = whoShared;
	}
	
	public void addWhoShared(String who){
		this.whoShared.add(who);
	}
	
	public ArrayList<Pair<String, String>> getComments() {
		return Comments;
	}

	public void setComments(ArrayList<Pair<String, String>> comments) {
		Comments = comments;
	}
	
	public void addComment(String usr, String comment){
		Comments.add(new Pair<String, String>(usr,comment));
	}
	
	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Drawable getProfPic() {
		return profPic;
	}

	public void setProfPic(Drawable profPic) {
		this.profPic = profPic;
	}

	public String getURL(int possition) {
		return URL.get(possition);
	}

	public void setURL(ArrayList<String> uRL) {
		URL = uRL;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
