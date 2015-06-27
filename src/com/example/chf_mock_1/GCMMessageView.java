package com.example.chf_mock_1;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
 
public class GCMMessageView extends Activity {
    String message;
    TextView txtmsg;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
		final Intent myIntent= new Intent(getBaseContext(),MainActivity.class);		
		startActivity(myIntent);

        
  
    }
}