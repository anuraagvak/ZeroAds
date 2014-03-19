package com.example.libraryfirst;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
	 
    private boolean screenOff;
 
    @Override
    public void onReceive(Context context, Intent intent) {
    	if(dummy.is_in_adactivity == 1)
    			return;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        	dummy.is_screen_off = 1;
        	//Log.w("this should","be screenoff!!");
            screenOff = true;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
 
        }
        Intent i = new Intent(context, UpdateService.class);
        i.putExtra("screen_state", screenOff);
        context.startService(i);
    }
 
}