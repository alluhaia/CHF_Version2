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

public class Reminders extends Fragment {
	private DiscussArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_reminder,
				container, false);
		random = new Random();
		ipsum = new LoremIpsum();

		
//		// cause the fragment to be retained on orientation so we don't need to reload data. 
//				this.setRetainInstance(true);
		
		lv = (ListView) rootView.findViewById(R.id.listView1);
		
		Context context = getActivity().getApplicationContext();
		
		adapter = new DiscussArrayAdapter(context, R.layout.listitem_reminder);

		lv.setAdapter(adapter);

		Intent i = getActivity().getIntent();
		 
        String message = i.getStringExtra("message");
        i.removeExtra("message");
        DatabaseHandler db = new DatabaseHandler(context);
//        
//        /**
//         * CRUD Operations
//         * */
//        // Inserting Contacts
        if (message != "" && message != null) {
        	
        	String[] d=message.split(" ");
        	if (d[0].equals("R")) {
		        db.addContact(new Contact("reminder", message.substring(2)));
        	}
        }

//     
// 
//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getContact("reminder");       
// 
        Collections.reverse(contacts);
        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
                // Writing Contacts to log
          
            addItems(cn.getPhoneNumber());
        
        }
//        
// 
//        
//        Log.d("Reading: ", "Reading all contacts.."); 

        //================================
 		Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
  		
  		HashMap<String, String> hitParameters = new HashMap<String, String>();
  		hitParameters.put(Fields.HIT_TYPE, "appview");
  		hitParameters.put(Fields.SCREEN_NAME, "Reminders");

  		tracker.send(hitParameters);
		
        //================================

		
		return rootView;
	}

	private void addItems(String message) {
		
		adapter.add(new OneComment(true, message));

	}

	

}
