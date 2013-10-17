package com.example.chf_mock_1_helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

import com.example.chf_mock_1.LoginActivity;
import com.example.chf_mock_1_data.PatientData;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.preference.PreferenceManager;


public class PatientHelper {
	public static String PATIENT_API_URL = "https://api.twitter.com/1/statuses/user_timeline.json?include_entities=true&include_rts=true&screen_name=ala_sl";
	
	public static ArrayList<PatientData> getTracks(String phone) throws ParseException {
		ArrayList<PatientData> retval = new ArrayList<PatientData>();

		// create an http client and a request object.
		HttpClient client = new DefaultHttpClient();
		
	
    	//String phoneNo = getSharedPreferences("phone");
    	
		PATIENT_API_URL=PATIENT_API_URL+"/"+phone;
		
		HttpGet request = new HttpGet(PATIENT_API_URL);

	    // execute the request
		HttpResponse response;
		try {
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
				
			   // retval will hold the parsed twitter data returned from this method. 
				JSONArray tweets = new JSONArray (strVal);
				
				for(int i=0; i<tweets.length(); i++) {
					phone = null;
					double weight=0;
					double BMI=0;
					double pressure=0;
					double glucose=0;
					double heartRate=0;
					
					String weightUnit="";
					String BMIUnit="";
					String pressureUnit="";
					String glucoseUnit="";
					String heartRateUnit="";
					
					
					
					JSONObject tweet = tweets.getJSONObject(i);
					String date = tweet.getString("created_at");
				    date=date.substring(0, 19)+" "+date.substring(26);
					String tweetText = tweet.getString("text");
					JSONObject userData = tweet.getJSONObject("user");
					String username = userData.getString("name");
					
				
					PatientData patientData = new PatientData(phone, weight, BMI, pressure, 
											glucose, heartRate ,weightUnit, BMIUnit, 
											pressureUnit, glucoseUnit, heartRateUnit);
					retval.add(patientData);
				}
				
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
		}

		return retval;
		
	}
	
	

}
