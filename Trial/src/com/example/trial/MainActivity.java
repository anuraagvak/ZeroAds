package com.example.trial;


import com.example.libraryfirst.BaseActivity;
import com.example.libraryfirst.UpdateService;
import com.example.libraryfirst.dummy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		Log.w("poda","naanga ellam appove appadi");
//		dummy.nothing(this);
		
		final Context con = this;
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
            	
                Intent intent = new Intent(con,nouse.class);
                startActivity(intent);
                // Perform action on click
            }
        });
	}
	/*
	@Override
	protected void onStop(){
		super.onStop();
		Log.w("rahul gandhi","likes pappu!!");
		//this.stopService(new Intent(this, UpdateService.class));
	}
	
	@Override
    public void onPause() {
        super.onPause();
        dummy.something(this);
        // Avoid pause notifications in destroy path.
     }

    @Override
    public void onResume() {
    	Log.w("first","time thatha");
        super.onResume();
        dummy.nothing(this);
        
        // Avoid resume notifications in startup path.
     }
     */

}