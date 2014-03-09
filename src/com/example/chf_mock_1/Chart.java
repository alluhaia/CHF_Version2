package com.example.chf_mock_1;

import java.util.HashMap;

import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class Chart extends Fragment {
	
	
	private WebView webView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.webview_chart,
				container, false);
		
		//get the patient phone number from reference
		Context context = getActivity().getApplicationContext();
		SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
		String phone = (shared.getString("phone", ""));
		
		webView = (WebView) rootView.findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		
		
		webView.loadUrl("http://134.173.236.110/dashboard/PhoneView.aspx?t="+phone);
		
		//============ 
		
				// Initialize a tracker using a Google Analytics property ID.
				Tracker tracker = GoogleAnalytics.getInstance(getActivity()).getTracker("UA-45989172-1");
				
				HashMap<String, String> hitParameters = new HashMap<String, String>();
				hitParameters.put(Fields.HIT_TYPE, "appview");
				hitParameters.put(Fields.SCREEN_NAME, "Chart");

				tracker.send(hitParameters);
				
				//============
				
		

		return rootView;
	}


}