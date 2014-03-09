package com.example.chf_mock_1_postrequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.renderscript.Sampler.Value;
import android.util.Log;

public class MakeRequest {
	private String URL;
	
	public  String MakeRequest( String URL, Map params) throws ClientProtocolException, IOException, JSONException, InterruptedException, ExecutionException {
	
		this.URL=URL;
	   
	    String result= new MyAsyncTask().execute(params).get();
	    
	    return result;
	}
	
	
	private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

	    //all the passed parameters from the post request
	    //iterator used to loop through all the parameters
	    //passed in the post request
	    Iterator iter = params.entrySet().iterator();

	    //Stores JSON
	    JSONObject holder = new JSONObject();


	    //While there is another entry
	    while (iter.hasNext()) 
	    {
	        //gets an entry in the params
	        Map.Entry pairs = (Map.Entry)iter.next();

	    
	        //gets the value
	       
	        holder.put((String)pairs.getKey(), pairs.getValue());
	    }
	    return holder;
	}
	
	private class MyAsyncTask extends AsyncTask<Map, Integer, String>{
		 
		@Override
		protected String doInBackground(Map... params) {
			// TODO Auto-generated method stub
			String result="";
			
			try {
				result = postData(params[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
 
		protected void onPostExecute(Double result){
			//pb.setVisibility(View.GONE);
		}
		
		protected void onProgressUpdate(Integer... progress){
			//pb.setProgress(progress[0]);
		}
 
		public String postData(Map params) throws JSONException {
			// // result
			String result="";
			
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(URL);
 
			try {
				// Add your data
				//convert parameters into JSON object
			    JSONObject holder = getJsonObjectFromMap(params);
			    
			   
			    
//			    //passes the results to a string builder/entity
//			    StringEntity se = new StringEntity(holder.toString());
//			
//			    httppost.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
//			    se.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
//
//			    
//			    httppost.setEntity(se);
//			   
			    
			    httppost.setHeader("Content-Type",
	                    "application/x-www-form-urlencoded;charset=UTF-8");
			    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(params.size());
			    
			    Iterator iterator = params.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
//					System.out.println("The key is: " + mapEntry.getKey()
//						+ ",value is :" + mapEntry.getValue());
					
					
					nameValuePair.add(new BasicNameValuePair(mapEntry.getKey().toString(), mapEntry.getValue().toString()));
				}
			    
			    
				 
			    httppost.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));

//			    Log.d("gli",nameValuePair.toString() );
				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
	
				result = EntityUtils.toString(response.getEntity());
//				Log.d("response",result );
// 
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			
			return result;
		}
		
	}

}
