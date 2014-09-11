package com.example.chf_mock_1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
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

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Symptoms extends Fragment {
	
	// for debug purposes
	private static final String TAG = "SF";
	private GoogleAnalytics myInstance;
	private Tracker tracker;
	SharedPreferences symptomsPreference;
	private static String PREF_SYMP="PatientPhone"; 
	private String Info;

	// server URL to send the symptoms
	public static String PATIENT_API_URL = "http://134.173.236.110/dashboard/PatientSymptoms.aspx";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.symptoms,
				container, false);
		// the info textview
		 
			 
		 
		 
		// chest pain seek bar +textview
         SeekBar sb = (SeekBar)rootView.findViewById(R.id.seekBar1);
         final TextView seekBarValue = (TextView)rootView.findViewById(R.id.chest_pain_text);
         
 
         
        // the maximum value 
		sb.setMax(10);
		


		sb.setProgress(1);

		 sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBarValue.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		
			// fatigue_text seek bar +textview

		 SeekBar sb1 = (SeekBar)rootView.findViewById(R.id.seekBar2);
         final TextView seekBar2Value = (TextView)rootView.findViewById(R.id.fatigue_text);
         
         sb1.setMax(10);


         sb1.setProgress(1);

         sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBar2Value.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		 
     	// breathe_pain_text  bar +textview

		 SeekBar sb2 = (SeekBar)rootView.findViewById(R.id.seekBar3);
         final TextView seekBar3Value = (TextView)rootView.findViewById(R.id.breathe_pain_text);
         
         sb2.setMax(10);


         sb2.setProgress(1);

         sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBar3Value.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
         
      // swallow_feet_text  bar +textview
         
         SeekBar sb4 = (SeekBar)rootView.findViewById(R.id.seekBar4);
         final TextView seekBar4Value = (TextView)rootView.findViewById(R.id.swallow_feet_text);
         
         sb4.setMax(10);


         sb4.setProgress(1);

         sb4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBar4Value.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		 
         
         // define a dialog box to inform the user if information was submitted correctly
         final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

         // Setting Dialog Title
         alertDialog.setTitle("Information");
      
         //breath
		 final RadioGroup rg = (RadioGroup)rootView.findViewById(R.id.radioBreathe);
		 // set the default 
		 rg.check(R.id.RadioButton02);
		 
         // medication
         final RadioGroup rgm = (RadioGroup)rootView.findViewById(R.id.radioMed);
         //set the default
       //breath
         rgm.check(R.id.RadioButton01);
      
       //============ 
 		
         //================================
         boolean checkConntection = new CheckInternetConnection().checkInternetConnection(getActivity());
 		
 		if (checkConntection == true ) { 
 		// Initialize a tracker using a Google Analytics property ID.
 		tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
 		
 		HashMap<String, String> hitParameters = new HashMap<String, String>();
 		hitParameters.put(Fields.HIT_TYPE, "appview");
 		hitParameters.put(Fields.SCREEN_NAME, "Symptoms");

 		tracker.send(hitParameters);
 		
 		//============
         
         Button submit = (Button)rootView.findViewById(R.id.symptoms_submit_button);
         submit.setOnClickListener(new OnClickListener(){
	        	
	        	
	        	
	        	@Override
	        	public void onClick(View v) {
	        		
	        		Context context = getActivity().getApplicationContext();
	        		SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
					String phone = (shared.getString("phone", ""));
//	        		// breathe
					String selectedRadioBreathe = ((RadioButton)rootView.findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
					// medication
					String selectedRadioMedication = ((RadioButton)rootView.findViewById(rgm.getCheckedRadioButtonId() )).getText().toString();
									
			         
	        		// put inputs in a mp and sent it to makerequest class
	        		Map<String, String> map = new HashMap<String, String>();
	        		map.put("phone", phone);
	        		map.put("chest_pain", seekBarValue.getText().toString());
	        		map.put("wake_up", selectedRadioBreathe);
	        		map.put("tired", seekBar2Value.getText().toString());
	        		map.put("breathe", seekBar3Value.getText().toString());
	        		map.put("swollen", seekBar4Value.getText().toString());
	        		map.put("take_medication", selectedRadioMedication);
	        		
//	        		//before sending make sure all questions has been answered 
        		
	        		if (seekBarValue.getText().toString().isEmpty()
	        				|| seekBar2Value.getText().toString().isEmpty()
	        				|| seekBar3Value.getText().toString().isEmpty()
	        				|| seekBar4Value.getText().toString().isEmpty()) {
	        			CharSequence text = "Oops,,, You forgot to answer all the questions!";
						int duration = Toast.LENGTH_LONG;
						
						Toast toast = Toast.makeText(getActivity(), text, duration);
						toast.show();
	        			
	        		} else {
	        			
	        			
	        			
	        			
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
								                   "Symptoms_input",   // Event label
								                   null)            // Event value
								      .build()
								  );
								 
								 
						            //alertDialog.setMessage("Thank you,,, we have received your information");

						            // set the date, time and info sent in sharred prefernce
						            Calendar c = Calendar.getInstance();
						            
						            
						            Log.d("date","Here date"+c.get(Calendar.MONTH));
						          
						            String sDate = c.get(Calendar.YEAR) + "-" 
						            + String.valueOf(c.get(Calendar.MONTH)+1)
						            + "-" + c.get(Calendar.DAY_OF_MONTH)
						            + " at " + c.get(Calendar.HOUR_OF_DAY) 
						            + ":" + c.get(Calendar.MINUTE);
						            // put that in textview
						            //infoTextView.
								 
						            
						           
					        		
					        		// display information on top
					        		 Info = "Thank you\nYour Symptoms were sent on "+ sDate;
					    			 
					    			 Info =Info + "\n Chest Pain: "+ seekBarValue.getText().toString();
					    			 Info =Info + "\n Waking up at night: "+ selectedRadioBreathe;
					    			 Info =Info + "\n Tired: "+ seekBar2Value.getText().toString();
					    			 Info =Info + "\n Short of Breathe: "+ seekBar3Value.getText().toString();
					    			 Info =Info + "\n Swollen feet: "+ seekBar4Value.getText().toString();
					    			 Info =Info + "\n Taking Medication: "+ selectedRadioMedication;
					    			 
							         alertDialog.setMessage(Info);

					    		
					        		
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
//	        	
//	        		
//	        		}
	        	
         });	
 		}
		return rootView;
	}	

}