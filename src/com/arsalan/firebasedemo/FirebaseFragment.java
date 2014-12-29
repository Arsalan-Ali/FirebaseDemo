package com.arsalan.firebasedemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class FirebaseFragment extends Fragment {
	TextView username;
	EditText messageText;
	String user;
	String msg;
	
	ListView messageList;
    Button sendMessageBtn;
    Firebase myFirebaseRef;
    ArrayList<MsgBody> mList;
    MyAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        
    	
    	View view=inflater.inflate(R.layout.fragment_firebase, null);
        Firebase.setAndroidContext(getActivity());

        username=(TextView)view.findViewById(R.id.username);
        username.setText("Current User: "+MainActivity.mUsername);
        messageText=(EditText)view.findViewById(R.id.message);
        
        sendMessageBtn=(Button)view.findViewById(R.id.send_button);
        
        messageList=(ListView)view.findViewById(R.id.message_list);
        
        mList=new ArrayList<MsgBody>();

        mAdapter=new MyAdapter(mList);
        messageList.setAdapter(mAdapter);
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                
                if(messageText.getText().toString().length()>0){
                	
                	myFirebaseRef.child("user").setValue(MainActivity.mUsername);
                	myFirebaseRef.child("message").setValue(messageText.getText().toString());
                	
				}
                messageText.setText("");
                
            }
        });

        
        firebaseCommunication();
        return view;
    }

    private class MyAdapter extends ArrayAdapter<MsgBody>{
		
    	private TextView mMsg;
		private TextView mName;
		public MyAdapter(ArrayList<MsgBody> list) {
			super(getActivity(), android.R.layout.simple_list_item_1,list);
			
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position,View convertView,ViewGroup parent){
			if(convertView==null){
				convertView=getActivity().getLayoutInflater().inflate(R.layout.listitem, null);
			}
			mName=(TextView)convertView.findViewById(R.id.name);
			mMsg=(TextView)convertView.findViewById(R.id.msg);
			
			//System.out.println(getItem(position).getMsg());
			
			if(getItem(position).getMsg()!=null)
				mMsg.setText(getItem(position).getMsg());
			
			if(getItem(position).getName()!=null)
				if(getItem(position).getName().equals(MainActivity.mUsername))
					mName.setText("You");
				else
					mName.setText(getItem(position).getName());
			
			return convertView;
			
		}

	}
    

    private void firebaseCommunication(){
    	
    	

    	
    	

        myFirebaseRef = new Firebase("https://glaring-fire-9610.firebaseIO.com/");
        //myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        myFirebaseRef.child("message").removeValue();
    	myFirebaseRef.child("user").removeValue();
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
            	if(snapshot!=null){
            		if(snapshot.getValue()!=null){
            			msg=snapshot.getValue().toString();
            			mList.add(new MsgBody(user,msg));
                    	((MyAdapter)messageList.getAdapter()).notifyDataSetChanged();
    					
    					
		                System.out.println(snapshot.getValue());
            		}
            	}
            	//prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
        });
        myFirebaseRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
            	if(snapshot!=null){
            		if(snapshot.getValue()!=null){
            			
            			user=snapshot.getValue().toString();
    					
    					
    					
		                System.out.println(snapshot.getValue());
            		}
            	}
            	//prints "Do you have data? You'll love Firebase."
            }
            @Override public void onCancelled(FirebaseError error) { }
        });

    }
}
