package com.example.chf_mock_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import com.example.chf_mock_1_db.Contact;
import com.example.chf_mock_1_db.DatabaseHandler;
import com.example.chf_mock_adapter.DiscussArrayAdapter;
import com.example.chf_mock_adapter.OneComment;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

import de.svenjacobs.loremipsum.LoremIpsum;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class Messages extends Fragment {
	
	private DiscussArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_discuss,
				container, false);
//		random = new Random();
//		ipsum = new LoremIpsum();
		Fragment bf1=(Fragment) 
				 getFragmentManager().findFragmentByTag("Reminders");
//
        Log.d("before contact in messages", "FirstXXX");
//        getActivity().getSupportFragmentManager().beginTransaction().detach(bf1).commit();
//        Fragment currentFragment = getFragmentManager().findFragmentByTag("Reminders");
//        android.support.v4.app.FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
//        fragTransaction.detach(currentFragment);
//        fragTransaction.attach(currentFragment);
//        fragTransaction.commit();//
////		// cause the fragment to be retained on orientation so we don't need to reload data. 
////				this.setRetainInstance(true);
//		
//		lv = (ListView) rootView.findViewById(R.id.listView1);
		
//		Context context = getActivity().getApplicationContext();
		
//		adapter = new DiscussArrayAdapter(context, R.layout.listitem_discuss);
//
//		lv.setAdapter(adapter);
//
//		// get the notification message
//		
////		Intent i = getActivity().getIntent();
////		 
////        String message = i.getStringExtra("message");
//        
//       // Log.d("message",message);
//        // clear the intent to remove the extra 
////        i.removeExtra("message");
////        i=null;
//        // add the message to DB
//        DatabaseHandler db = new DatabaseHandler(context);
//        
//       
//        
//        /**
//         * CRUD Operations
//         * */
//        // Inserting Contacts
//        
//  
//        /**
//         * CRUD Operations
//         * */
//        // Inserting Contacts
////        if (message != "" && message != null) {
////        	
////        	String[] d=message.split(" ");
////        	if (d[0].equals("M")) {
////        		
////		        db.addContact(new Contact("message", message.substring(2)));
////        	}
////        }
//        	// remove all notifications
//        NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//             
//
// 
//        // Reading all contacts
//        List<com.example.chf_mock_1_db.Contact> contacts = db.getContact("message"); 
//        Collections.reverse(contacts);
//        
//        Log.d("before contact in messages", contacts.size()+"LLL");
//        for (Contact cn : contacts) {
//        	
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber()
//            		+ " ,notif id: " + cn.getNotifID();
//                // Writing Contacts to log
//            adapter.add(new OneComment(false, cn.getPhoneNumber()));
//            notifManager.cancel(cn.getNotifID());
////            Log.d("Messages tab: id for int in message",log);
//        
//        }
//////        
//   

        
		
//        Log.d("message", message);
		//editText1 = (EditText) rootView.findViewById(R.id.editText1);
      //============ 
		
      		// Initialize a tracker using a Google Analytics property ID.
        //================================
//        boolean checkConntection = new CheckInternetConnection().checkInternetConnection(context);
//		
//		if (checkConntection == true ) { 
//      		Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
//      		
//      		HashMap<String, String> hitParameters = new HashMap<String, String>();
//      		hitParameters.put(Fields.HIT_TYPE, "appview");
//      		hitParameters.put(Fields.SCREEN_NAME, "Messages");
//
//      		tracker.send(hitParameters);
//      		
//      		//============
//		}
		
		return rootView;
	}

	private void addItems() {
		
		adapter.add(new OneComment(false, "Hello bubbles!",""));

	}

	
	
	/**
	 * When orientation changes, we need to report in to the Activity. 
	 */
	@Override
	public void onAttach(Activity activity) {
		
	
		super.onAttach(activity);
	}
	
	
//
//	@Override
//	public void onResume() {
//	    super.onResume();
//		Log.d("LALA", "Mess");
//
//	}
	 
	
	
	
}



	



	