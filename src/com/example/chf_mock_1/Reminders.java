package com.example.chf_mock_1;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.chf_mock_1_db.Contact;
import com.example.chf_mock_1_db.DatabaseHandler;
import com.example.chf_mock_1_db.db;
import com.example.chf_mock_adapter.DiscussArrayAdapter;
import com.example.chf_mock_adapter.OneComment;
import com.google.analytics.tracking.android.Log;

import de.svenjacobs.loremipsum.LoremIpsum;

public class Reminders extends Fragment {
	private DiscussArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;
	List<Contact> contacts;
    int oldDBCount;
    int countNewNotif=0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_reminder,
				container, false);
//		random = new Random();
//		ipsum = new LoremIpsum();
////	      Log.d("Reminder tab:hrrrrr   First","XXXX");
//
////		// cause the fragment to be retained on orientation so we don't need to reload data. 
////				this.setRetainInstance(true);
//		
		lv = (ListView) rootView.findViewById(R.id.listView1);
////		
//		Context context = getActivity().getApplicationContext();
//////		
//		adapter = new DiscussArrayAdapter(context, R.layout.listitem_reminder);
//////
//		lv.setAdapter(adapter);
//
//        DatabaseHandler db = new DatabaseHandler(context);
////        
////        /**
////         * CRUD Operations
////         * */
//
//////        
//        NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//////
////////     
//////// 
////////        // Reading all contacts
//        List<Contact> contacts = db.getLast5Contact();       
//////// 
//        Collections.reverse(contacts);
//////        
//////        Log.d("before contact in reminders", contacts.size()+"REW");
//////
//        for (Contact cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//                // Writing Contacts to log
//          if (cn.getName().equals("reminder")) {
//        	  
//              addRItems(cn.getPhoneNumber());
//
//          } else {
//        	  
//              addMItems(cn.getPhoneNumber());
//
//        	  
//          }
//            notifManager.cancel(cn.getNotifID());
////            Log.d("Reminder tab: id for int in reminder",log);
//
//        }
        
//        lv.setSelection(totalItemCount - 9);
       
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

		
		return rootView;
	}

	private void addRItems(String message) {
		
		adapter.add(new OneComment(true, message,""));

	}
	
	
private void addMItems(String message) {
		
		adapter.add(new OneComment(false, message,""));

	}

@Override
public void onStart() {
    super.onStart();
    System.out.println("start");
    
    
    
	random = new Random();
	ipsum = new LoremIpsum();

	Context context = getActivity().getApplicationContext();

	adapter = new DiscussArrayAdapter(context, R.layout.listitem_reminder);

	lv.setAdapter(adapter);

    final db db1 = new db(context);

  

    
    final NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

   contacts = db1.getLast5Contact(); 
   
   System.out.println("start"+ db1.getContactsCount());

    Collections.reverse(contacts);

    for (Contact cn : contacts) {
        String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
	      if (cn.getName().equals("reminder")) {
	    	  
	          addRItems(cn.getPhoneNumber());
	
	      } else {
	    	  
	          addMItems(cn.getPhoneNumber());
	
	    	  
	      }
	        notifManager.cancel(cn.getNotifID());


    }
    
   // to check if there is new messages/reminders
    oldDBCount=db1.getContactsCount();
    
//    temp=db.getContactsCount();
	
  
    
    final Handler handler = new Handler();
    handler.postDelayed( new Runnable() {

        @Override
        public void run() {
        	
        	  
        	
        	System.out.println("start"+ db1.getContactsCount());
        	
        	
//        	contacts = db.getLast5Contact();   
        	
        	if (db1.getContactsCount() > oldDBCount) {
//        		
        		System.out.println("count the inserted"+ (db1.getContactsCount()-oldDBCount));
            	
//        		

        		
        		  
        		
        		  contacts = db1.getLastInsertedContact(db1.getContactsCount()-oldDBCount);
        		  oldDBCount=db1.getContactsCount();
        		  
        		  // fill the list again
        		  for (Contact cn : contacts) {
        		        String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
        		            
        		        System.out.println("old db count inside");
        		        // Writing Contacts to log
        		      if (cn.getName().equals("reminder")) {
        		    	  
        		          addRItems(cn.getPhoneNumber());

        		      } else {
        		    	  
        		          addMItems(cn.getPhoneNumber());

        		    	  
        		      }
        		        notifManager.cancel(cn.getNotifID());


        		    }
        		  
        		  
        		  
        		  
//        		 
//        		  
        	}
//        	
        	
        	
//        	addRItems("testchange");
        	
            adapter.notifyDataSetChanged();
            System.out.println("in timer");
            

            
//            for (Contact cn : contacts2) {
//                String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//                    // Writing Contacts to log
//                System.out.println(" log in timer"+ log);
//
//            }
//            
            handler.postDelayed( this, 20 * 1000 );
        }
    }, 20 * 1000 );
    

    
    
    
}

	@Override
	public void onResume() {
	    super.onResume();
	    System.out.println("Resume");
	    
//		Context context = getActivity().getApplicationContext();
//
//
//		DatabaseHandler db = new DatabaseHandler(context);
////    
////    /**
////     * CRUD Operations
////     * */
//
//////    
//		NotificationManager notifManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
////////    // Reading all contacts
//		List<Contact> contacts = db.getLast5Contact();       
//		Collections.reverse(contacts);
//////    
//////    Log.d("before contact in reminders", contacts.size()+"REW");
//	    for (Contact cn : contacts) {
//	        String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//	            // Writing Contacts to log
//		      if (cn.getName().equals("reminder")) {
//		    	  
//		          addRItems(cn.getPhoneNumber());
//		
//		      } else {
//		    	  
//		          addMItems(cn.getPhoneNumber());
//		
//		    	  
//		      }
//        notifManager.cancel(cn.getNotifID());
//        Log.d("Reminder tab: id for int in reminder",log);
//
//	    }
   
	    
	    

	 
	}


}
