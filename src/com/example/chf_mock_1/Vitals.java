package com.example.chf_mock_1;

import java.util.ArrayList;

import com.example.chf_mock_1_data.PatientData;
import com.example.chf_mock_1_fetchdata.FetchData;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Vitals extends Fragment {
	
	
	private ArrayList<PatientData> patientData;
	private long lastTweetsUpdateTime = 0;
	private static final String TAG = "VF";

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.vitals,
				container, false);
		
		// cause the fragment to be retained on orientation so we don't need to reload data. 
				this.setRetainInstance(true);
				
				Context context = getActivity().getApplicationContext();
				SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
				String phone = (shared.getString("phone", ""));
												
				Log.d(TAG, "phone from preference : "+phone);
				
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastTweetsUpdateTime) > 60000) {
					// get the phone no from shared defaults

					new FetchData(Vitals.this,phone).execute("slavicfrost");
					lastTweetsUpdateTime = currentTime;
				}		
				
		
		TextView weight=(TextView)rootView.findViewById(R.id.weight_label);
//		weight.setBackgroundResource(R.drawable.customborder);
//		weight.append("\n"+"?");
		String weightValue="";
		if (weightValue=="") {
			
			
			CharSequence text = "Oops,,, you forgot to measure your weight!";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			//toast.show();
			
		}
		//textLayout
	//	weight.setLayoutParams(textLayout);
		
		// make weight clickable
		weight.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			      // TODO Auto-generated method stub
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
		if(patientData != null && patientData.size() > 0) {
			((MainActivity)activity).setLastPatientUpdateTime(System.currentTimeMillis());
		}
		super.onAttach(activity);
	}
	
	public void setPatientData(ArrayList<PatientData> patientData) 
	{
		this.patientData = patientData;
		
		// fill the textview with information
		//this.tweetList.setAdapter(new TweetAdapter(this.getActivity(),this.tweetData,this.layoutInflater,this.imageFetcher));
	}
	
	public static class PatientsHolder
	{
		public TextView weight;
		public TextView BMI;
		public TextView pressure;
		public TextView glucose;
		public TextView heartRate;
		public PatientData patientObject;
	}



}