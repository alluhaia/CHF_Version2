package com.example.chf_mock_1;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.example.chf_mock_1_db.Chat_DB;
import com.example.chf_mock_1_db.Contact;
import com.example.chf_mock_1_db.DatabaseHandler;
import com.example.chf_mock_1_db.DatabaseHandler_1;
import com.example.chf_mock_1_db.db;
import com.example.chf_mock_adapter.DiscussArrayAdapter;
import com.example.chf_mock_adapter.OneComment;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

import de.svenjacobs.loremipsum.LoremIpsum;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Chat extends Fragment {
	
	
	private DiscussArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;
	List<Chat_DB> contacts;
	int oldDBCount;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.chat,
				container, false);
		random = new Random();
		ipsum = new LoremIpsum();
	      Log.d("Chat tab:hrrrrr   First","XXXX");
	      
	      
	      
	 	
//		// cause the fragment to be retained on orientation so we don't need to reload data. 
//				this.setRetainInstance(true);
		
		lv = (ListView) rootView.findViewById(R.id.listView1);
//		
		Context context = getActivity().getApplicationContext();
////		
		adapter = new DiscussArrayAdapter(context, R.layout.listitem_chat);
////
		lv.setAdapter(adapter);
		
//	    adapter.notifyDataSetChanged();

		
//	 	 final TextView textView1 = (TextView)rootView.findViewById(R.id.dateInfo);


        final db db1 = new db(context);
//        
        
        final NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

//        /**
//         * CRUD Operations
//         * */
        final Date d = new Date();
	    final SimpleDateFormat form = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm");
//////        // Reading all contacts
        db1.addChat(new Chat_DB("user", "test message", form.format(d).toString(),1));

	    contacts = db1.getAllChat("");       
////// 
        Collections.reverse(contacts);
////        

	    
//        Log.d("before contact in chat", contacts.size()+"REW");
////
        for (Chat_DB cn : contacts) {
////            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getChat();
////                // Writing Contacts to log
////        
//////        	  
        	if (cn.getName().equals("user")) {
//        		
        		  addMItems(cn.getChat(), cn.getChatDate());
//        		
//        		
        	} else {
        		  addRItems(cn.getChat(), cn.getChatDate());
        		
        	}
//            
//////			  textView1.setText("Date here");
////              
//              System.out.println( " in get all contact chat"+cn.getChatDate());
////
////              
////        	  
//////            Log.d("Reminder tab: id for int in reminder",log);
////
        }
//       
        int i=1;
		 int max=6;
		 
		 while ( i< max) {
//			 
//
             addRItems("Provider: UUUU HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH", form.format(d).toString());
//             db1.addChat(new Chat_DB("user", "UUUU", "HHHH"));
             i++;
//
//			 
		 }
//		 
			lv.setTranscriptMode(lv.TRANSCRIPT_MODE_ALWAYS_SCROLL);

		 // define date TextView
        
        editText1 = (EditText)rootView.findViewById(R.id.editText1);
		editText1.setOnKeyListener(new OnKeyListener() {
			
			
			
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
//				lv.setStackFromBottom(true);
//				
				lv.smoothScrollToPosition(lv.getCount());
//				
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					// Perform action on key press
					// date
					
//					lv.setStackFromBottom(false);
					
					 addMItems("Me: "+editText1.getText().toString(),form.format(d).toString());	
					 
				        db1.addChat(new Chat_DB("user", "Me:"+ editText1.getText().toString(), form.format(d).toString(), 2));
			    		


					editText1.setText("");
					
					return true;
				}
				return false;
			}
		});
		
		


//
//        //================================
//        boolean checkConntection = new CheckInternetConnection().checkInternetConnection(context);
//		
//		if (checkConntection == true ) { 
//			Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
//	
//  		HashMap<String, String> hitParameters = new HashMap<String, String>();
//  		hitParameters.put(Fields.HIT_TYPE, "appview");
//  		hitParameters.put(Fields.SCREEN_NAME, "Reminders");
//
//  		tracker.send(hitParameters);
//		}
//        //================================
		
		// make the selecton on last item
		lv.setSelection(lv.getAdapter().getCount()-1);
		
		   // to check if there is new messages/reminders
//	    oldDBCount=db1.getContactsCount();
	    
//	    temp=db.getContactsCount();
		
	  
	    
	    final Handler handler = new Handler();
	    handler.postDelayed( new Runnable() {

	        @Override
	        public void run() {
	        	
	        	  

	        	System.out.println("start"+ db1.getContactsCount());
	        	
	        	
//	        	contacts = db.getLast5Contact();   
	        	
//	        	if (db1.getChatCount() > oldDBCount) {
////	        		
//	        		System.out.println("count the inserted"+ (db1.getContactsCount()-oldDBCount));
//	            	
////	        		
//
//	        		
//	        		  
//	        		
//	        		  contacts = db1.getLastInsertedChat(db1.getChatCount()-oldDBCount);
//	        		  oldDBCount=db1.getContactsCount();
//	        		  
//	        		  // fill the list again
//	        		  for (Chat_DB cn : contacts) {
//	        		        String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getChat();
//	        		            
//	        		        System.out.println("old db count inside");
//	        		        // Writing Contacts to log
//	        		      if (cn.getName().equals("Provider")) {
//	        		    	  
//	        		          addRItems(cn.getChat(), cn.getChatDate());
//
//	        		      } 
//	        		        notifManager.cancel(cn.getNotifID());
//
//
//	        		    }
//	        		  
//	        		  
//	        		  
//	        		  
////	        		 
////	        		  
//	        	}
//	        	
	        	
	        	
//	        	addRItems("testchange");
	        	
	            adapter.notifyDataSetChanged();
	            System.out.println("in timer");
	            

	            
//	            for (Contact cn : contacts2) {
//	                String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//	                    // Writing Contacts to log
//	                System.out.println(" log in timer"+ log);
	//
//	            }
//	            
	            handler.postDelayed( this, 20 * 1000 );
	        }
	    }, 20 * 1000 );
	    
		

		return rootView;
	}

	
	private void addRItems(String message, String date) {
			
			adapter.add(new OneComment(true, message,date));
	
		}
	
	
	
	private void addMItems(String message,  String date ) {
			
			adapter.add(new OneComment(false, message,date));
	
		}

}