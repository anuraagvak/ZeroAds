package com.example.libraryfirst;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	
	static int home_click = 0;
	static int back_pressed = 0;

	protected static final String TAG = BaseActivity.class.getName();

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public static boolean isAppWentToBg = true;

	public static boolean isWindowFocused = false;
 
	public static boolean isMenuOpened = false;

	public static boolean isBackPressed = false;

	@Override
	protected void onStart(){
		Log.w("Entered ", " onStart function" );
		Log.w("activity ",getClass().getName());
		back_pressed = 0;
		if(UpdateService.first == 0){
			applicationWillEnterForeground();
		}
		
		if(UpdateService.first == 1){
			UpdateService.first = 0;
		}
		super.onStart();
	}

	private void applicationWillEnterForeground() {

		Log.w("Entered ", " Foreground function " );
		Log.w("activity ",getClass().getName());
		if (isAppWentToBg){
		//	Log.w("Entered ", " Foreground123 function" );
				isAppWentToBg = false;

				if(UpdateService.is_outside_once != 2){
					Intent i = new Intent(this, UpdateService.class);
				    i.putExtra("screen_state", false);
				    this.startService(i);
				    ////Log.w("i d have run out of","comments"+Integer.toString(UpdateService.is_outside_once));
				}

				if(UpdateService.is_outside_once == 2){
					UpdateService.is_outside_once = 0;
					isAppWentToBg = true;
				}
				
				if(dummy.is_screen_off == 1){
					dummy.is_screen_off = 0;
					UpdateService.is_outside_once = 0;
				}
				else{
					UpdateService.a= 0;
				}

				/*if(dummy.is_screen_off != 1){
					//Log.w("log","dog");
					UpdateService.is_outside_once = 0;
					UpdateService.a= 0;
				}*/

			Toast.makeText(getApplicationContext(), "App is in foreground",
					Toast.LENGTH_SHORT).show();
			if(!(this instanceof ad_activity)){
				UpdateService.is_outside_once = 0;
			}
		}
	}

	@Override
	protected void onStop(){
		Log.w("Entered ", " onstop function" );
		Log.w("activity ",getClass().getName());
		super.onStop();
//		//Log.w("JUST THIS",Integer.toString(UpdateService.is_outside_once));
		if(home_click == 1){
			applicationdidenterbackground();
			home_click = 0;
		}
		else if(this instanceof ad_activity){
			//Log.w("raju","dantuluri"+Integer.toString(UpdateService.is_outside_once));
			//Log.w("guru",getClass().getName());
			if(dummy.flag_adactivity_leftorright == 1 || UpdateService.is_outside_once1 == 1){
				//Log.w("raju","redyy");
				applicationdidenterbackground();
			}
			dummy.flag_adactivity_leftorright = -1;
		}
		else{
			applicationdidenterbackground();
		}
	}

	public void applicationdidenterbackground(){
		Log.w("Entered ", " Background function");
		Log.w("activity ",getClass().getName());
		if(!isWindowFocused || home_click == 1){//|| UpdateService.is_outside_once == 2){
			Log.w("going to",Integer.toString(home_click));
			UpdateService.is_outside_once1 = 0;
			//Log.w("i just cant","believe this came!!");
			isAppWentToBg = true;
			Toast.makeText(getApplicationContext(),
					"App is Going to Background", Toast.LENGTH_SHORT).show();
			if(UpdateService.is_outside_once != 2){
				UpdateService.is_outside_once = 1;
				UpdateService.first = 1;
			}
/*			if(UpdateService.is_outside_once == 2)
				stopService(new Intent(this,UpdateService.class));*/
    		//Log.w("Went to ","Backgound ?"+Integer.toString(UpdateService.is_outside_once));
			UpdateService.a = 0;
		}
	}

/*	public void onUserLeaveHint(){
//		Intent intent = new Intent(this, BaseActivity.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		if(dummy.home == 1 && back_pressed == 0){
			onBackPressed();
		}
		else{
			dummy.home = 1;
		}
		Log.w("i pressedhome","really!!!");
	}
*/
	
	@Override
	public void onPause() {
		
	    if (isApplicationSentToBackground(this)){
	        // Do what you want to do on detecting Home Key being Pressed 
	    	Log.w("Home button ","detected!!");
	    	onBackPressed();
	    	
	    }
	    super.onPause();
	}
	public boolean isApplicationSentToBackground(final Context context) {
		Log.w("entered ","this");
	    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> tasks = am.getRunningTasks(1);
	    if (!tasks.isEmpty()) {
	        ComponentName topActivity = tasks.get(0).topActivity;
	        if (!topActivity.getPackageName().equals(context.getPackageName())) {
	            return true;
	        }
	    }
	    return false;
	}
	 
	   
	@Override
	public void onBackPressed() {
		Log.w("Entered ", " Backpressed function function" );
		Log.w("activity ",getClass().getName());
		if(back_pressed == 0){
			back_pressed = 1;
			Class ex = null;
			try {
				ex = Class.forName(getPackageName()+".MainActivity");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Context a = this;
			if (a.getClass() == ex) {			// i think it should be !(this instanceof ad_activity)
	//			UpdateService.is_outside_once = 1;
			} else {
				isBackPressed = true;
				 	
			}
		}
		Log.d(TAG,
				"onBackPressed " + isBackPressed + ""
						+ this.getLocalClassName());
		super.onBackPressed();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		Log.w("Entered ", " OnwindowfocusChanged function" );
		Log.w("activity ",getClass().getName());
		isWindowFocused = hasFocus;

		if (isBackPressed && !hasFocus) {
			isBackPressed = false;
			isWindowFocused = true;
		}

		super.onWindowFocusChanged(hasFocus);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.w("going to","home");
			Intent intent = new Intent(this, BaseActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			home_click = 1;
//			onBackPressed();
			break;
		}
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.w("going to","home");
			Intent intent = new Intent(this, BaseActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			home_click = 1;
//			onBackPressed();
			break;
		}
		return true;
	}

}