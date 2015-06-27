package com.example.chf_mock_1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;


public class TestConnection {
	
	public boolean checkInternetConnection(Context context) {

			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    android.net.NetworkInfo wifi = cm
		            .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		    android.net.NetworkInfo datac = cm
		            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		    if ((wifi != null & datac != null)
		            && (wifi.isConnected() | datac.isConnected())) {
		        Toast toast = Toast.makeText(context, "there is  Internet Connection", Toast.LENGTH_LONG);
		        toast.show(); 
		        return true;
		             }else{
		            //no connection
		              Toast toast = Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG);
		              toast.show();  
		              return false;
	    }

	}
	
	
	
	

}
