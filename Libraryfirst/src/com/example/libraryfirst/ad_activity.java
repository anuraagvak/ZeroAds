package com.example.libraryfirst;


//import com.example.hope_v2.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.R.drawable;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ad_activity extends BaseActivity{
    static int rotate = -1;
    static String unique_id = null;
	Bitmap bmImg;
	private static final String Drawable = null;
	ImageView im;
	Context context_ad;
	StringBuilder str = new StringBuilder();

	public void onBackPressed(){
		super.onBackPressed();
    	dummy.is_in_adactivity = 0;		
	}
	
	public void onCreate(Bundle savedInstanceState){
		TelephonyManager telephonyManager;                                            
		telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		unique_id = telephonyManager.getDeviceId();
//    	Log.w("gur in oncreate",unique_id);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if(UpdateService.is_outside_once == 2)
			stopService(new Intent(this,UpdateService.class));

//		http://web.iiit.ac.in/~ramaguruguru.prakash/insert?app=<app-name>&id=<unique-identifier(may be phone number)>
		
		
		//Log.w("this bug","is entirely new!!!");
		//Log.w("this is entirely just for","checking"+Integer.toString(UpdateService.is_outside_once));
		dummy.is_in_adactivity = 1;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		super.onCreate(savedInstanceState);
		final Context a = this;
		context_ad = this;

		int temp_id = dummy.getResourseIdByName(this.getPackageName(),"layout","ads");//R.layout.ads;
		setContentView(temp_id);
		
		temp_id = dummy.getResourseIdByName(this.getPackageName(),"id","imageView1");//R.id.imageView1;
		im = (ImageView)findViewById(temp_id);
		new myClass().execute();

		////Log.w("thsi is the last statement","i think");


		SeekBar seekBar = (SeekBar) findViewById(dummy.getResourseIdByName(this.getPackageName(),"id","myseek")   );//R.id.myseek);
		SeekBar seekBar1 = (SeekBar) findViewById(dummy.getResourseIdByName(this.getPackageName(),"id","myseek1"));//R.id.myseek1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar) {

	            if (seekBar.getProgress() > 80) {
	            	// a url needs to be called here
	            	
	            	String pack = getPackageName();
	            	//String s[] = pack.split(".");
	            	//String k = s[3];
	
	            	
//	            	TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//	            	String mPhoneNumber = tMgr.getLine1Number();
/*	            	String u = "http://web.iiit.ac.in/~ramaguruguru.prakash/insert?app=trial&id="+unique_id;
	            	try {
	            		  URL url = new URL(u);
	            		  HttpURLConnection con = (HttpURLConnection) url
	            		    .openConnection();
            			  Log.w("thisis",con.getResponseMessage());
	            		  } catch (Exception e) {
	            		  e.printStackTrace();
	            		}
*/
	            	String u = "http://web.iiit.ac.in/~ramaguruguru.prakash/insert?app=trial&id="+unique_id;
	            	Analytics task = new Analytics();
	            	task.execute(u);//new String[] { "http://www.vogella.com" });
	            	
	            	
	            	seekBar.setProgress(0);	            	
	            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
	            	startActivity(browserIntent);
	            	dummy.flag_adactivity_leftorright = 1;
	            	dummy.is_in_adactivity = 0;
	            	finish();
	            	
	            	//setContentView(R.layout.);
	            } else {
	       //         seekBar.setThumb(getResources().getDrawable(R.drawable.ic_launcher));
	            	
	            	 seekBar.setProgress(0);
	            }
	        	
	        }
	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar) {
	        }
	        public void onProgressChanged(SeekBar seekBar, int progress,
	                boolean fromUser) {
	        /*    if(progress>80){
	                //seekBar.setThumb(getResources().getDrawable(R.drawable.ic_launcher));
	            	//Log.w("hello","world");
	            	//setContentView(R.layout.change);
	            }
	            else{
	            	//Log.w("pinapple",String.valueOf(progress));
	            	setContentView(R.layout.activity_main);
	            }*/

	        }
	    });
		
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar1) {

	            if (seekBar1.getProgress() < 20){
	            	seekBar1.setProgress(100);
	            	dummy.flag_adactivity_leftorright = 0;
	            	dummy.is_in_adactivity = 0;
	            	dummy.home = 0;
	            	finish();
	            } else {
	       //         seekBar.setThumb(getResources().getDrawable(R.drawable.ic_launcher));
	            	 seekBar1.setProgress(100);
	            }
	        	
	        }
	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar1) {
	        }
	        public void onProgressChanged(SeekBar seekBar1, int progress,
	                boolean fromUser) {
	        
	        }
	    });
	}

	
	  private class Analytics extends AsyncTask<String, Void, String> {
		  /*					try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://web.iiit.ac.in/~ramaguruguru.prakash/insert?app=trial&id=000000000000000");
			HttpResponse response = client.execute(request);
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		  
		    @Override
		    protected String doInBackground(String... urls) {
		    	String response = "";
		        DefaultHttpClient client = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet(urls[0]);
            	Log.w("gur ",urls[0]);
		        try {
		          HttpResponse execute = client.execute(httpGet);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      return response;
		    }

		    @Override
		    protected void onPostExecute(String result) {
//		      textView.setText(result);
		    }
		  }
	
	
	protected class myClass extends AsyncTask<String, Void , Bitmap>
	{
		
		private ProgressDialog simpleWaitDialog ;

	// Note that first param in the class definition matches the param passed to this method 
	// and the last param in the class definition matches the return type of this method
		protected Bitmap doInBackground(String... params) {
			
			
			rotate = (rotate+1)%5;
			String x = Integer.toString(rotate);
			String url = "http://web.iiit.ac.in/~ramaguruguru.prakash/images/";
			url = url + x;
			url = url + ".png";
			return downloadFile(url);
			
//			im.setImageBitmap(tbmImg);
		//	return tbmImg;
			// TODO Auto-generated method stub
			
		}
		
		
		@SuppressLint("NewApi")
		Bitmap downloadFile(String fileUrl){
			
/*
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet("http://web.iiit.ac.in/~ramaguruguru.prakash/insert?app=trial&id=000000000000000");
				HttpResponse response = client.execute(request);

				String html = "";
				InputStream in = response.getEntity().getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while((line = reader.readLine()) != null)
				{
				    str.append(line);
				}
				in.close();
				html = str.toString();
				
				Log.w("This is a new dungeon",html);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

*/

			URL myFileUrl =null; 
			try {
			myFileUrl= new URL(fileUrl);
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			try {
				HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				bmImg = BitmapFactory.decodeStream(is);		
			} catch (IOException e) {
				//Log.w("this is an","exception");
			// TODO Auto-generated catch block
			e.printStackTrace();
			}		
			return bmImg;
		}

		

	//when user issues a cancel on the operation.
	@Override
	protected void onCancelled() {
	// Do a cleanup on operation canceled
	// ReInitialise Data
	super.onCancelled();
	}

	// on successful completion of onPostExecute method is called

	// Called before doInBackground() 
	@Override
	protected void onPreExecute() {
        Log.i("Async-Example", "onPreExecute Called");
        //simpleWaitDialog = ProgressDialog.show(ad_activity.this,
          //      "Wait", "Downloading Image");

	super.onPreExecute();
	// do any initialization
	}
	@Override
	protected void onPostExecute(Bitmap result) {
		
	//showResults();
	//progressDialog.dismiss();
	//	simpleWaitDialog.dismiss();
		BitmapDrawable d = new BitmapDrawable(context_ad.getResources(),result);
		im.setImageBitmap(result);
	
//	super.onPostExecute(result);
	}
	
	// update the UI on complete of longrunningtask()
	protected void onProgressUpdate(Void... values) {
	super.onProgressUpdate(values);
	}
	
	}		
	
}
