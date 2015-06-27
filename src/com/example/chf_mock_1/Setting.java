package com.example.chf_mock_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import com.example.chf_mock_1_db.Contact;
import com.example.chf_mock_1_db.DatabaseHandler;
import com.example.chf_mock_adapter.Device;
import com.example.chf_mock_adapter.DeviceSettingAdapter;
import com.example.chf_mock_adapter.DiscussArrayAdapter;
import com.example.chf_mock_adapter.OneComment;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

import de.svenjacobs.loremipsum.LoremIpsum;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class Setting extends Fragment {
		
	private ListView lv;
	private DeviceSettingAdapter adapter;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_device_setting,
				container, false);
		
		lv = (ListView) rootView.findViewById(R.id.listView1);

		// cause the fragment to be retained on orientation so we don't need to reload data. 
		this.setRetainInstance(true);
		
		Context context = getActivity().getApplicationContext();

		adapter = new DeviceSettingAdapter(context, R.layout.setting);

		lv.setAdapter(adapter);
		
		int m=2;
		int i=0;
		boolean deviceType= false;
		String name= "weight";
		for ( i=0 ; i<m ; i++) {
			
			if ( i == 0 ) {
				
				deviceType= true;
				
				
			} else {
				
				name="cuff";
				
			}
			
			addDevice(deviceType, name);
			
		}
		
//		ImageView img= (ImageView) rootView.findViewById(R.id.imageView1);
//		img.setImageResource(R.drawable.glucose_meter);
		

		
		return rootView;
	}



	
	
	/**
	 * When orientation changes, we need to report in to the Activity. 
	 */
	@Override
	public void onAttach(Activity activity) {
		
	
		super.onAttach(activity);
	}
	

	 
	private void addDevice(boolean available, String name) {
		
		adapter.add(new Device(available, name));

	}
	
	
}



	



	