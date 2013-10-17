package com.example.chf_mock_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Chart extends Fragment {
	
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.vitals,
				container, false);
		
		TextView weight=(TextView)rootView.findViewById(R.id.weight_label);
		ViewGroup.LayoutParams textLayout = weight.getLayoutParams();
		weight.append("\n"+"?");
		String weightValue="";
		if (weightValue=="") {
			
			Context context = getActivity().getApplicationContext();
			CharSequence text = "Oops,,, you forgot to measure your weight!";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
		}
		//textLayout
		weight.setLayoutParams(textLayout);

		return rootView;
	}


}