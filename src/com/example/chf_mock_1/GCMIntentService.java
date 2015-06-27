package com.example.chf_mock_1;
 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
 
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
 
import com.example.chf_mock_1.R;
import com.example.chf_mock_1_db.Chat_DB;
import com.example.chf_mock_1_db.Contact;
import com.example.chf_mock_1_db.DatabaseHandler;
import com.example.chf_mock_1_db.db;
import com.example.chf_mock_1_postrequest.MakeRequest;
import com.google.android.gcm.GCMBaseIntentService;
 
public class GCMIntentService extends GCMBaseIntentService {
	
	// instantiate ArrayList to save the notifications and pass them without click to messages or reminders tab
	 ArrayList<String> list = new ArrayList<String>();
	
    private static final String TAG = "GCM Tutorial::Service";
    private static String PREF_NAME="RegisterationID";
    SharedPreferences settings; 
    // Use your PROJECT ID from Google API into SENDER_ID
    public static final String SENDER_ID = "68488464113";
 
    public GCMIntentService() {
        super(SENDER_ID);
    }
 
    @Override
    protected void onRegistered(Context context, String registrationId) {
 
        //save the regosteration id at defaults:
        final SharedPreferences shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        shared.edit().remove("registeration_id").commit();
        SharedPreferences.Editor editor = shared.edit(); 
		editor.putString("registeration_id", registrationId);
        editor.commit();
        // send the registeration id to server

    }
 
    @Override
    protected void onUnregistered(Context context, String registrationId) {
 
    }
 
    @Override
    protected void onMessage(Context context, Intent data) {
        String message;
        // Message from  server
        message = data.getStringExtra("message");

        Intent intent = new Intent(this, MainActivity.class);
        
        Date now = new Date();
        long uniqueId = now.getTime();//use date to generate an unique id to differentiate the notifications.
        
        
        
        // Pass data to the new activity
        intent.putExtra("message", message);
        // Starts the activity on notification click
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) uniqueId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // Create the notification with a notification builder
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("MyHeart Notification")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setStyle(new Notification.BigTextStyle()
                .bigText(message.substring(2)))
                .setContentText(message.substring(2))
                .setContentIntent(pIntent)
                .build();
        
        Log.d("MMM","Message recived");
//        list.add(message);
        
        // save the message in the DB
        db db1 = new db(context);
        String[] d=message.split(" ");
 
		  if (message != "" && message != null) {
		        	
		        	
		        	if (d[0].equals("M")) {
		        		
		        		Log.d("the message id","number MMM is"+(int) uniqueId+"MM"+message );
		        		
				        db1.addContact(new Contact("message", "Message: "+message.substring(2),(int) uniqueId));
		        	}
		        }
		
		        
		  if (message != "" && message != null) {
	        	
	        	if (d[0].equals("R")) {
	        		Log.d("the reminder id","number RRR is"+(int) uniqueId+"RR"+message);
			        db1.addContact(new Contact("reminder", "Reminder: "+message.substring(2),(int) uniqueId));
	        	}
	        }
		 
		  
		  if (message != "" && message != null) {
	        	
	        	if (d[0].equals("C")) {
	        	       final Date date = new Date();
	        		    final SimpleDateFormat form = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm");
	        		Log.d("the chat id","number RRR is"+(int) uniqueId+"CC"+message);
			        db1.addChat(new Chat_DB("Provider", "Provider: " + message.substring(2),form.format(date).toString(),(int) uniqueId ));
	        	}
	        }
		  
		  
		  
//		     db1.addChat(new Chat_DB("user", "", ""));
 
		  
        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
 
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //manager.notify(R.string.app_name, notification);
        manager.notify((int) uniqueId, notification);
        
        
        {
            // Wake Android Device when notification received
            PowerManager pm = (PowerManager) context
                    .getSystemService(Context.POWER_SERVICE);
            final PowerManager.WakeLock mWakelock = pm.newWakeLock(
                    PowerManager.FULL_WAKE_LOCK
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP, "GCM_PUSH");
            mWakelock.acquire();
 
            // Timer before putting Android Device to sleep mode.
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    mWakelock.release();
                }
            };
            timer.schedule(task, 5000);
        }
 
    }
 
    @Override
    protected void onError(Context arg0, String errorId) {
 
    }
 
}