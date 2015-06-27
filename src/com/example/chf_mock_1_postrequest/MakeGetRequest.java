package com.example.chf_mock_1_postrequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;

import com.example.chf_mock_1.R;
import com.example.chf_mock_1.Vitals;
import com.example.chf_mock_1_data.PatientData;


public class MakeGetRequest {
	
	private ProgressDialog progDialog;
	public  String URL;
	public String phone;
	public Context context;
	public Activity activity;
	
	public  ArrayList<PatientData> MakeGetRequest( String URL, String phone, Activity activity) throws ClientProtocolException, IOException, JSONException, InterruptedException, ExecutionException {
	
		this.URL=URL;
		this.phone=phone;
		this.activity=activity;
		
		Log.d("check connection", "In log CheckInternetCionnection");
	    ArrayList<PatientData> result= new MyAsyncTask().execute().get();
	    
	    return result;
	}
	
	

	private class MyAsyncTask extends AsyncTask<String, Void, ArrayList<PatientData>> {

		public Vitals vital;
		private ProgressDialog progDialog;
		

		@Override
	    protected void onPreExecute() {
	        super.onPreExecute(); 
	    	this.progDialog = ProgressDialog.show(activity, "Search", activity.getResources().getString(R.string.looking_for_tracks) , true, false);
	    }
		
		@Override
		protected ArrayList<PatientData> doInBackground(String... params) 
		{
		
			// get the phone no from shared defaults in loginactivity and bind it to URL
			
				return getTracks();
			
			
		}
		
		 @Override
		protected void onPostExecute(ArrayList<PatientData> result) 
		{
			 this.progDialog.dismiss();
			 //System.out.println("On post execute"+result);
			// this.vital.setPatientData(result);
		}
		 
		 
		 public ArrayList<PatientData> getTracks() throws ParseException {
				ArrayList<PatientData> retval = new ArrayList<PatientData>();

				// create an http client and a request object.
				HttpClient client = new DefaultHttpClient();
				
			
				
				
				URL=URL+"?t="+phone;
			
				HttpGet request = new HttpGet(URL);

			    // execute the request
				HttpResponse response;
				try {
					
					// test internet connection
					
					
					
					
					
					response = client.execute(request);
					StatusLine status = response.getStatusLine();
					if (status.getStatusCode() == 200) {

						
						
						// process the content. 
						HttpEntity entity = response.getEntity();
						InputStream ist = entity.getContent();
						ByteArrayOutputStream content = new ByteArrayOutputStream();
						
						int readCount = 0;
						byte[] buff = new byte[1024];
						while ((readCount = ist.read(buff)) != -1) {
							content.write(buff, 0, readCount);
						}
						
						String strVal = new String (content.toByteArray());
						
						Log.d("array1", strVal);
					   // retval will hold the parsed twitter data returned from this method. 
						JSONArray patientDatas = new JSONArray (strVal);
//						Log.d("array", "KKKKK");
						Log.d("array", patientDatas.toString());
//						for(int i=0; i<patientDatas.length(); i++) {
							phone = null;
							Object weight="";
							Object BMI="";
							Object pressure="";
							Object glucose="";
							Object heartRate="";

							
							//Log.d("array", patientDatas.toString());
							
							JSONObject patientDataPoint = patientDatas.getJSONObject(0);
							
							
							weight=  patientDataPoint.get("weight");
							
							heartRate=  patientDataPoint.get("heartbeat");
						
						
							pressure= patientDataPoint.get("bp");
							
							glucose= patientDataPoint.get("bloodglucose");
							
							BMI= patientDataPoint.get("bmi");
							
							PatientData patientData = new PatientData(phone, weight, pressure, 
									glucose, heartRate, BMI);
							
							retval.add(patientData);
						}
						
//					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return retval;
				
			}
	}

}


