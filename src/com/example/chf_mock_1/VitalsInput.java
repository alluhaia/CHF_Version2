package com.example.chf_mock_1;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.chf_mock_1_postrequest.MakeRequest;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VitalsInput extends Fragment {
	
	// for debug purposes
	private static final String TAG = "VIF";
	// server URL to send the symptoms
	public static String PATIENT_API_URL = "http://134.173.236.50/cguchf/PatientBG.aspx";
	private SharedPreferences GlucosePreference;
	private static String PREF_GLUCOSE="PatientPhone"; 
	private String Glucose_Info;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.input,
				container, false);
	



		
		
    
      
         Button submit = (Button)rootView.findViewById(R.id.vital_input_button);
         submit.setOnClickListener(new OnClickListener(){
	        	
	        	
	        	
	        	@Override
	        	public void onClick(View v) {
	        		
	        		
	        		
	        		Context context = getActivity().getApplicationContext();
	        		SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
					String phone = (shared.getString("phone", ""));
					//get the input
					
//					TextEdit glucose=rootView.findViewById(R.id.glucose_input).toString();
			        
					TextView glucoseText=(TextView) rootView.findViewById(R.id.glucose_input);
	        		
	        		
					String glucose=glucoseText.getText().toString();
					
	        		// put inputs in a mp and sent it to makerequest class
	        		Map<String, String> map = new HashMap<String, String>();
	        		map.put("phone", phone);
	        		
	        		map.put("glucose", glucose);
	        		
	        	
	        		  // define a dialog box to inform the user if information was submitted correctly
	                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

	                // Setting Dialog Title
	                alertDialog.setTitle("Information");
	        		
//	        		//before sending make sure all questions has been answered 
        		
	        		if ( glucose.equals("")) {
	        			CharSequence text = "Please enter one value!";
						int duration = Toast.LENGTH_LONG;
						
						Toast toast = Toast.makeText(getActivity(), text, duration);
						toast.show();
	        			
	        		} else {
	        			
	        			
	        			boolean checkConntection = new CheckInternetConnection().checkInternetConnection(getActivity());
	        			
	        			if (checkConntection == true ) { 
	        			
	        			
	        			try {
							MakeRequest mr= new MakeRequest();
							String result=mr.MakeRequest(PATIENT_API_URL, map);
							 
							 if (result.toString().equals("False")) {
								 
						         alertDialog.setMessage("Oops,,, Something went wrong,, Please try again!");


								 
								 
								 
								 
							 } else {
								 
								 
								 EasyTracker easyTracker = EasyTracker.getInstance(context);

								  // MapBuilder.createEvent().build() returns a Map of event fields and values
								  // that are set and sent with the hit.
								  easyTracker.send(MapBuilder
								      .createEvent("ui_action",     // Event category (required)
								                   "button_press",  // Event action (required)
								                   "Glucose_manual_input",   // Event label
								                   null)            // Event value
								      .build()
								  );
								 
						           // alertDialog.setMessage("Thank you,,, we have received your information");
						            
						            
						            
						            Calendar c = Calendar.getInstance();

						            String sDate = c.get(Calendar.YEAR) + "-" 
						            + String.valueOf(c.get(Calendar.MONTH)+1)
						            + "-" + c.get(Calendar.DAY_OF_MONTH) 
						            + " at " + c.get(Calendar.HOUR_OF_DAY) 
						            + ":" + c.get(Calendar.MINUTE);
						            // put that in textview
						            //infoTextView.
								 
						            
						          
					        		// display information on top
					        		Glucose_Info = "Thank you\n Your Glucose measurement: "
					   					 + glucose
					   					 +"\n sent on "+ sDate;
					   			 
							         alertDialog.setMessage(Glucose_Info);

					   	
					    			 

								 
							 }
							 
					         alertDialog.show();

							 
							 // Showing Alert Message
					        
							 
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
		        		
		      
		        	}
		        		
	        		}
			 
	        		}
//	        	
//	        		
//	        		}
	        	
         });	
   
		return rootView;
	}	

}