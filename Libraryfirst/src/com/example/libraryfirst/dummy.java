package com.example.libraryfirst;
//import com.testlib.adactivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class dummy {
	static int flag_adactivity_leftorright = -1;
	static int is_in_adactivity = 0;
	static int home = 1;
	//Context a = this;
	public static int is_screen_off = 0;
	
	public static void nothing(Context a)
	{
		Intent i = new Intent(a, UpdateService.class);
	    i.putExtra("screen_state", false);
	    a.startService(i);

	}
	
	public static int getResourseIdByName(String packageName, String className, String name) {
	    Class r = null;
	    int id = 0;
	    try {
	        r = Class.forName(packageName + ".R");

	        Class[] classes = r.getClasses();
	        Class desireClass = null;

	        for (int i = 0; i < classes.length; i++) {
	            if (classes[i].getName().split("\\$")[1].equals(className)) {
	                desireClass = classes[i];

	                break;
	            }
	        }

	        if (desireClass != null) {
	            id = desireClass.getField(name).getInt(desireClass);
	        }

	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    } catch (SecurityException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (NoSuchFieldException e) {
	        e.printStackTrace();
	    }

	    return id;
	}

}