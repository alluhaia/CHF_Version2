package com.example.chf_mock_1_fetchdata;

import java.util.ArrayList;

import com.example.chf_mock_1.LoginActivity;
import com.example.chf_mock_1.R;
import com.example.chf_mock_1.Vitals;
import com.example.chf_mock_1_data.PatientData;
import com.example.chf_mock_1_helper.PatientHelper;




import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

public class FetchData extends AsyncTask<String, Void, ArrayList<PatientData>> {

	public Vitals vital;
	public String phone;
	private ProgressDialog progDialog;
	
	public FetchData(Vitals vital, String phone) 
	{
		this.vital = vital;
	}

	@Override
    protected void onPreExecute() {
        super.onPreExecute(); 
    	this.progDialog = ProgressDialog.show(this.vital.getActivity(), "Search", this.vital.getActivity().getResources().getString(R.string.looking_for_tracks) , true, false);
    }
	
	@Override
	protected ArrayList<PatientData> doInBackground(String... params) 
	{
	
		// get the phone no from shared defaults in loginactivity and bind it to URL
		
			return PatientHelper.getTracks(phone);
		
		
	}
	
	 @Override
	protected void onPostExecute(ArrayList<PatientData> result) 
	{
		 this.progDialog.dismiss();
		 this.vital.setPatientData(result);
	}

}
