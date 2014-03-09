package com.example.chf_mock_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.google.analytics.tracking.android.EasyTracker;

import com.example.chf_mock_1_data.PatientData;
import com.example.chf_mock_1_postrequest.MakeGetRequest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



public class Vitals extends Fragment {
	
	
	//private ArrayList<PatientData> patientData;
	private long lastTweetsUpdateTime = 0;
	private ArrayList<PatientData> result;
	private static final String TAG = "VF";
	private GoogleAnalytics myInstance;
	private Tracker tracker;
	public static String PATIENT_API_URL = "http://134.173.236.110/dashboard/patientvital.aspx";
    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.vitals,
				container, false);

		//============ 
		
		// Initialize a tracker using a Google Analytics property ID.
		Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
		
		HashMap<String, String> hitParameters = new HashMap<String, String>();
		hitParameters.put(Fields.HIT_TYPE, "appview");
		hitParameters.put(Fields.SCREEN_NAME, "Vitals");

		tracker.send(hitParameters);
		
		//============
		
		
		// cause the fragment to be retained on orientation so we don't need to reload data. 
				this.setRetainInstance(true);
				
				final Context context = getActivity().getApplicationContext();
				SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
				String phone = (shared.getString("phone", ""));
												
				Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(context));

				long currentTime = System.currentTimeMillis();
				if((currentTime - lastTweetsUpdateTime) > 60000) {
					// get the phone no from shared defaults

					MakeGetRequest mgr= new MakeGetRequest();
					try {
						 result=mgr.MakeGetRequest(PATIENT_API_URL, phone, this.getActivity());

						 
					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						Toast.makeText(context, e1.getMessage(), Toast.LENGTH_SHORT);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						Toast.makeText(context, e1.getMessage(), Toast.LENGTH_SHORT);

					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						Toast.makeText(context, e1.getMessage(), Toast.LENGTH_SHORT);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						Toast.makeText(context, e1.getMessage(), Toast.LENGTH_SHORT);

					} catch (ExecutionException e1) {
						// TODO Auto-generated catch block
						Toast.makeText(context, e1.getMessage(), Toast.LENGTH_SHORT);
					}
					
											
					lastTweetsUpdateTime = currentTime;
				}		
				
		TextView weight=(TextView)rootView.findViewById(R.id.weight_label);
		TextView pressure=(TextView)rootView.findViewById(R.id.Blood_Pressure_label);
		TextView glucose=(TextView)rootView.findViewById(R.id.glucose);
		TextView heratRate=(TextView)rootView.findViewById(R.id.heart_rate);
		TextView bmi=(TextView)rootView.findViewById(R.id.bmi_label);

		weight.setText("Weight \n"+result.get(0).getWeight());

		pressure.setText("Blood Pressure \n"+result.get(0).getPressure());
		
		glucose.setText("Glucose \n"+result.get(0).getGlucose());
		
		heratRate.setText("Heart Rate \n"+result.get(0).getHeartRate());
		
		bmi.setText("BMI \n"+result.get(0).getBMI());
		
		if (result.get(0).getWeight()==null 
			|| result.get(0).getPressure()==null 
					|| result.get(0).getGlucose()==null
						|| result.get(0).getHeartRate()==null
							|| result.get(0).getBMI()==null) {
			
			CharSequence text = "Oops,,, you forgot to complete your measurement!";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			// if the weight was empty and user forgot to measure his/her weight
			if (result.get(0).getWeight()==null) {
				
				weight.setBackgroundResource(R.drawable.customborder);
				weight.append("\n"+"?");
				
			}
			
			if (result.get(0).getPressure()==null) {
				
				pressure.setBackgroundResource(R.drawable.customborder);
				pressure.append("\n"+"?");
				
			}
			
			if (result.get(0).getGlucose()==null) {
				
				glucose.setBackgroundResource(R.drawable.customborder);
				glucose.append("\n"+"?");
				
			}
			
			if (result.get(0).getHeartRate()==null) {
				
				heratRate.setBackgroundResource(R.drawable.customborder);
				heratRate.append("\n"+"?");
				
			}
			
			if (result.get(0).getBMI()==null) {
				
				bmi.setBackgroundResource(R.drawable.customborder);
				bmi.append("\n"+"?");
				
			}
			
		}
		//textLayout
	//	weight.setLayoutParams(textLayout);
		
		// make weight clickable
		weight.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			      
				   // send the phone 

				  
				   android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
				   
				   Chart wvf =  new Chart();
				   
				

	
				   ft.replace(R.id.pager, wvf);
//		
				   ft.commit();

				   
			   }
			});
		
		

		return rootView;
	}
	
	/**
	 * When orientation changes, we need to report in to the Activity. 
	 */
	@Override
	public void onAttach(Activity activity) {
		//((MainActivity)activity).setTweetsFrag(this);
//		if(patientData != null && patientData.size() > 0) {
//			((MainActivity)activity).setLastPatientUpdateTime(System.currentTimeMillis());
//		}
		super.onAttach(activity);
	}
	
//	public void setPatientData(ArrayList<PatientData> patientData) 
//	{
//		
//		System.out.println("in set patient data fun"+patientData);
//		//this.patientData = patientData;
//		
//		
//	}
//	


	  @Override
	  public void onStart() {
	    super.onStart();
	     // The rest of your onStart() code.
	    EasyTracker.getInstance(getActivity()).activityStart(getActivity());  // Add this method.
	  
	    
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	     // The rest of your onStop() code.
	    EasyTracker.getInstance(getActivity()).activityStop(getActivity());  // Add this method.
	  }
	
	

}