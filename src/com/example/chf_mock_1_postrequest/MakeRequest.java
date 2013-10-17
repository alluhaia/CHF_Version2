package com.example.chf_mock_1_postrequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeRequest {
	public static String PATIENT_API_URL = "https://api.twitter.com/1/statuses/user_timeline.json?include_entities=true&include_rts=true&screen_name=ala_sl";

	
	public MakeRequest( Map params) throws ClientProtocolException, IOException, JSONException {
		//instantiates httpclient to make request
	    DefaultHttpClient httpclient = new DefaultHttpClient();

	    //url with the post data
	    HttpPost httpost = new HttpPost(PATIENT_API_URL);

	    //convert parameters into JSON object
	    JSONObject holder = getJsonObjectFromMap(params);

	    //passes the results to a string builder/entity
	    StringEntity se = new StringEntity(holder.toString());
	    
	    
	    
	    //sets the post request as the resulting string
	    httpost.setEntity(se);
	    //sets a request header so the page receving the request
	    //will know what to do with it
	    httpost.setHeader("Accept", "application/json");
	    httpost.setHeader("Content-type", "application/json");

	    //Handles what is returned from the page 
	    ResponseHandler responseHandler = new BasicResponseHandler();
	    //httpclient.execute(httpost, responseHandler);
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

}
