package com.arsalan.firebasedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
	public static String mUsername;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		
		FragmentManager fm = getSupportFragmentManager();
		
		 
	    Fragment fragment=new NameFragment();
	    fm.beginTransaction()
	        .add(R.id.fragment_container, fragment)
	        .commit();
	        
	}
}
