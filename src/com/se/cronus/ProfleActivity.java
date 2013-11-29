package com.se.cronus;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.se.cronus.items.ItemFragmentView;
import com.se.cronus.utils.CronusApp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
//TODO: everything here
public class ProfleActivity extends AbstractCActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profle);
		this.profile.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		this.curAttatched.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onSearchClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void offSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onSearch(String toFind) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onOpenItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onOpenProfile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onOpenMain() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean changeItemFragment(ItemFragmentView testFragView) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void updateAllFeeds() {
		// TODO Auto-generated method stub
		
	}
	

}
