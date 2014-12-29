package com.arsalan.firebasedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NameFragment extends Fragment{
	Button mEnterButton;
	EditText mNameField;
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	        
	    	
	    	View view=inflater.inflate(R.layout.fragment_name, null);
	    	mEnterButton=(Button)view.findViewById(R.id.name_button);
	    	mNameField=(EditText)view.findViewById(R.id.name);
	    	
	    	mEnterButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					MainActivity.mUsername=mNameField.getText().toString();
					if(MainActivity.mUsername.length()>0){
						FragmentManager fm = getActivity().getSupportFragmentManager();
						
						Fragment fragment = fm.findFragmentById(R.id.fragment_container);

					        if (fragment != null) {
					        	
					            fm.beginTransaction()
					                .remove(fragment)
					                .commit();
					        }
					        
					     fragment=new FirebaseFragment();
				         fm.beginTransaction()
				             .add(R.id.fragment_container, fragment)
				             .commit();
					}
				}
			});
	    	return view;
	}
}
