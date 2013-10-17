package com.example.chf_mock_1;



import com.example.chf_mock_1_notification.NotificationReceiver;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {

	/**
	   * Set Project Number of your Google APIs Console Project.
	   */
	public static final String PROJECT_NUMBER = "68488464113";
    protected static final int REQUEST_TEXT = 1;
    protected static String phoneNoFromPrefernce=null;
    private static final String TAG = "LOGIN_ACTIVITY";
    private static String PREF_NAME="PatientPhone";
	SharedPreferences settings; 

    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        final SharedPreferences shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        
       // settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        
     // if the phone number already stored in the default then skip this activity
    	
        shared.edit().remove("phone").commit();
        phoneNoFromPrefernce = shared.getString("phone", null);
    	
    
    	
    	Log.d(TAG, "phone from preference : "+phoneNoFromPrefernce);
    	// notification test
    	Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
		// intent triggered, you can add other intent for other actions
		Intent intent = new Intent(LoginActivity.this, NotificationReceiver.class);
		PendingIntent pIntent = PendingIntent.getActivity(LoginActivity.this, 0, intent, 0);
		
		// this is it, we'll build the notification!
		// in the addAction method, if you don't want any icon, just set the first param to 0
		Notification mNotification = new Notification.Builder(this)			
			.setContentTitle("New Post!")
			.setContentText("Here's an awesome update for you!")
			.setSmallIcon(R.drawable.ninja)
			.setContentIntent(pIntent)
			.setSound(soundUri)
			.setStyle(new Notification.BigTextStyle().bigText("You're doing a great job!"))
			.addAction(R.drawable.ninja, "View", pIntent)
			.addAction(0, "Remind", pIntent)
			
			.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// If you want to hide the notification after it was selected, do the code below
		// myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		notificationManager.notify(0, mNotification);
    	
    	//
    	
		final Intent myIntent= new Intent(getBaseContext(),MainActivity.class);		
    	
        
    	//if (phoneNo==null) {
    		// store the phone that user has entered
    		
    		
    	
    	
        
    		Button  submit=(Button) findViewById(R.id.phone_submit_button);
	        submit.setOnClickListener(new OnClickListener(){
	        	
	        	
	        	
	        	@Override
	        	public void onClick(View v) {
	        		// compare the phone to what we have
	        		
	        		// save the phone number
	        		TextView phoneText=(TextView)findViewById(R.id.editText1);
	        		
	        		phoneNoFromPrefernce=phoneText.getText().toString();
	        		
	       //     	
	        		Log.d(TAG, "phone from preference : "+phoneNoFromPrefernce);
	        		SharedPreferences.Editor editor = shared.edit(); 
	        		editor.putString("phone", phoneNoFromPrefernce);
                    System.out.println("Here is the phone "+phoneNoFromPrefernce);
                    editor.commit();
	        		
	        		// call 
	        		
	        		//LoginActivity.this.startActivityForResult(myIntent, REQUEST_TEXT);
                    startActivity(myIntent);
	        	}
	        		
	        });
    	//}
    	
    	
    }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode == REQUEST_TEXT) {
    		if(resultCode == Activity.RESULT_OK) {
    			 
//    		        TextView tvName=(TextView) findViewById(R.id.name_text);
//    		        TextView tvAddress=(TextView) findViewById(R.id.address_text);
//    		        TextView tvCity=(TextView) findViewById(R.id.city_text);
//    		        TextView tvState=(TextView) findViewById(R.id.state_text);
//    		        TextView tvPhone=(TextView) findViewById(R.id.phone_text);
    		        
    		     
    		       // Bundle b = data.getExtras();
//    		        if(b!=null)
//    		        {
//    		        	tvName.setText(b.get("name").toString());
//    		        	tvAddress.setText(b.get("address").toString());
//    		        	tvCity.setText(b.get("city").toString());
//    		        	tvState.setText(b.get("state").toString());
//    		        	tvPhone.setText(b.get("phone").toString());
    		          
    		            
    		           
    		       // }
    		        
    			
    			
    			
    		}
    	}
    	
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
