package com.example.libraryfirst;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;

public class UpdateService extends Service {
	static int a = 0;
	static int is_outside_once = 0;
	static int is_outside_once1 = 0;
	static int first = 0;
    BroadcastReceiver mReceiver = new ScreenReceiver();

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);
        //Log.w("service","created");
    }


    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
    	////Log.w("a = ",getString(a));
        if (!screenOn){
        	//Log.w("onstart   ",Integer.toString(is_outside_once));

        	if(a == 1 || is_outside_once == 1)
        	{
        		if(is_outside_once == 1){
                	//Log.w("Why does it not become","just this small number (..2..)");
                	is_outside_once1 = 1;
        			is_outside_once = 2;
        		}
	        	Intent I = new Intent(this,ad_activity.class);
	        	I.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        	this.startActivity(I);
        	}
        	if(is_outside_once == 0)
        		a = 1;
        }
    }

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
		
	 public void onDestroy(){
//	   unregisterReceiver(mReceiver);	 
	super.onDestroy();
	    }
	
}
