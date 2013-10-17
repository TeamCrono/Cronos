package com.se.cronus.utils;

import com.se.cronus.MainActivity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;

public class CUtils {
	private static final int CLEAR = 100;
	/* FEED ID */
	public static final int FACEBOOK_FEED = 0;
	public static final int PINTREST_FEED = 1;
	public static final int INSTA_FEED = 2;
	public static final int TWITTER_FEED = 3;
	
	

	/* COLORS */
	public static final int FACEBOOK_BLUE = Color.rgb(59, 89, 152);
	public static final int FACEBOOK_BLUE_CLEAR = Color
			.argb(CLEAR, 59, 89, 152);
	public static final int PINTREST_RED = Color.rgb(203, 32, 40);
	public static final int PINTREST_RED_CLEAR = Color
			.argb(CLEAR, 203, 32, 40);
	public static final int INSTA_BROWN = Color.rgb(75, 66, 57);
	public static final int INSTA_BROWN_CLEAR = Color
			.argb(CLEAR, 75, 66, 57);
	public static final int TWITTER_BLUE = Color.rgb(91,197,237);
	public static final int TWITTER_BLUE_CLEAR = Color
			.argb(CLEAR, 91,197,237);

	/* cronus colors!! */
	public static final int CRONUS_GREEN_LIGHT = Color.rgb(47, 255, 152);
	public static final int CRONUS_GREEN_DARK = Color.rgb(0, 204, 153);
	public static final int CRONUS_GREEN_BLACK = Color.rgb(7, 38, 23);
	public static final int CRONUS_BLUE_WHITE = Color.rgb(227, 251, 255);
	public static final int CRONUS_GREEN_LIGHT_CLEAR = Color.argb(CLEAR, 47,
			255, 152);

	/* FONTS AND LETTERING AND NUMBER STUFF */
	public static final float FONT_SIZE_SMALL = 12;

	
	/* static methods 888888888888888888888888888888888888888888888888888888888888888*/
	public static int getScreenWidth(MainActivity a) {

		int[]temp  = new int[2];
		getScreenSizePixels(temp, a);
		return temp[0];

	}

	public static int getScreenHeight(MainActivity a) {
		
		int[]temp  = new int[2];
		getScreenSizePixels(temp, a);
		return temp[1];

	}

	private static void getScreenSizePixels(int widthHeightInPixels[/*2*/], MainActivity a)
	{
	    Resources resources = a.getResources();
	    Configuration config = resources.getConfiguration();
	    DisplayMetrics dm = resources.getDisplayMetrics();
	    // Note, screenHeightDp isn't reliable
	    // (it seems to be too small by the height of the status bar),
	    // but we assume screenWidthDp is reliable.
	    // Note also, dm.widthPixels,dm.heightPixels aren't reliably pixels
	    // (they get confused when in screen compatibility mode, it seems),
	    // but we assume their ratio is correct.
	    
	    double screenWidthInPixels = (double)config.screenWidthDp * dm.density;
	    double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;
	    widthHeightInPixels[0] = (int)(screenWidthInPixels + .5);
	    widthHeightInPixels[1] = (int)(screenHeightInPixels + .5);
	}
	
	CUtils() {

	}

}
