package com.example.chf_mock_1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;




public class CheckInternetConnection {


	
	public boolean checkInternetConnection(Context context) {
		
		
		
	        final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
	       
	        
	        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
//	             
//	        	CharSequence text = conMgr.getActiveNetworkInfo().getState().toString();
//				int duration = Toast.LENGTH_LONG;
//
//				Toast toast = Toast.makeText(context, text, duration);
//				toast.show();
				
	        	return true;
	        } else {
	        	
	        	
	        	CharSequence text = "Internet Connection Not Present";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
	           
	            return false;
	        }
	 }

	
	
	
}
