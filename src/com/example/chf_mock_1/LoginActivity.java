package com.example.chf_mock_1;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.chf_mock_1_postrequest.MakeRequest;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	
	
	
	/**
	   * Set Project Number of your Google APIs Console Project.
	   */
	// the server URL for register product
	public static final String URL = "http://134.173.236.110/dashboard/patientreg.aspx";
	//  PROJECT_NUMBER from google console
	public static final String PROJECT_NUMBER = "68488464113";
	// variable to hold phone number from preference
    protected static String phoneNoFromPrefernce=null;
    // for debug purposes
    private static final String TAG = "LOGIN_ACTIVITY";
    // file name for storing patient number 
    private static String PREF_NAME="PatientPhone";
    // a resource to hold the settings file
	SharedPreferences settings; 
	
	private GoogleAnalytics myInstance;
	private Tracker tracker;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       
        // set the preference file
        final SharedPreferences shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

    	
            
        
        final Context context = getApplicationContext();
		phoneNoFromPrefernce = (shared.getString("phone", ""));
        
    
    	
		final Intent myIntent= new Intent(getBaseContext(),MainActivity.class);		
    	
		// check if there is Internet connection
		
		boolean checkConntection = new CheckInternetConnection().checkInternetConnection(context);
		
		if (checkConntection == true ) { 
		
	     // if the phone number already stored in the default then skip this activity
			if (phoneNoFromPrefernce.equals(null)
					|| phoneNoFromPrefernce.equals("")) {
			    		
			    	
				/*
				 * 
				 * register the device
				 * 
				 */
				
		        
		        GCMRegistrar.checkDevice(this);
		        GCMRegistrar.checkManifest(this);
		        
		        GCMRegistrar.register(LoginActivity.this,GCMIntentService.SENDER_ID);
		        // get the registeration id and send it o sever with phone
		        
				
				Button  submit=(Button) findViewById(R.id.phone_submit_button);
		        submit.setOnClickListener(new OnClickListener(){
		        	
		        	
		        	
		        	@Override
		        	public void onClick(View v) {
		        		// compare the phone to what we have
		        		// validate phone number from server
		        		
		        		
		        		 Tracker easyTracker = EasyTracker.getInstance(context);		        		
		        		easyTracker.send(MapBuilder
		        			      .createEvent("UX", "appstart", null, null)
		        			      .set(Fields.SESSION_CONTROL, "start")
		        			      .build()
		        			    );
		        		
		        		// save the phone number
		        		TextView phoneText=(TextView)findViewById(R.id.editText1);
		        		
		        		
		        		phoneNoFromPrefernce=phoneText.getText().toString();
		        		
		        		if (phoneNoFromPrefernce.isEmpty()==false){
			        		// get the registeration id
			        		Context context = getApplicationContext();
			        		SharedPreferences shared2 = context.getSharedPreferences("RegisterationID",0);
							String reg_id = (shared2.getString("registeration_id", ""));
					
			        		
			        		Map<String, String> map = new HashMap<String, String>();
			        		map.put("t", phoneNoFromPrefernce);
			        		map.put("reg_id", reg_id);
			        	
			        		
			        		
			        		try {
								MakeRequest mr= new MakeRequest();
								String result=mr.MakeRequest(URL, map);
								
								// test if the result true then continue otherwise exit with error message
								
							
								String[] r=result.split("-");
								
								if (r[0].toString().equals("False")) {
									
									CharSequence text = "Oops,,, something went wrong!";
									int duration = Toast.LENGTH_LONG;
	
									Toast toast = Toast.makeText(getApplicationContext(), text, duration);
									toast.show();
									//startActivity(myIntent);
								} else {
									
						    		// store the phone that user has entered
	
					        		SharedPreferences.Editor editor = shared.edit(); 
					        		editor.putString("phone", phoneNoFromPrefernce);
//				                    System.out.println("Here is the phone "+phoneNoFromPrefernce);
				                    editor.commit();
					        		
				                    startActivity(myIntent);
				                    finish();
								}
								
								
							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        		
		        		} else {
			        		
		        			CharSequence text = "Please enter your 10 digits phone number!";
							int duration = Toast.LENGTH_LONG;

							Toast toast = Toast.makeText(getApplicationContext(), text, duration);
							toast.show();
			        	}
		        		
		        	}
		        	
		        		
		        });
		        
			} else {
				
				 startActivity(myIntent);
				 finish();
			}
		}		
	
    	
    }

	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
