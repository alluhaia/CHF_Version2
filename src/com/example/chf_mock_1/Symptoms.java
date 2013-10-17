package com.example.chf_mock_1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.example.chf_mock_1_postrequest.MakeRequest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Symptoms extends Fragment {
	private static final String TAG = "SF";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.symptoms,
				container, false);
		// chest pain seek bar +textview
         SeekBar sb = (SeekBar)rootView.findViewById(R.id.seekBar1);
         final TextView seekBarValue = (TextView)rootView.findViewById(R.id.chest_pain_text);
         
		sb.setMax(10);


		sb.setProgress(1);

		 sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBarValue.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		
			// breathe seek bar +textview

		 SeekBar sb1 = (SeekBar)rootView.findViewById(R.id.seekBar2);
         final TextView seekBar2Value = (TextView)rootView.findViewById(R.id.breathe_pain_text);
         
         sb1.setMax(10);


         sb1.setProgress(1);

         sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBar2Value.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
		 
     	// swallow feet bar +textview

		 SeekBar sb2 = (SeekBar)rootView.findViewById(R.id.seekBar3);
         final TextView seekBar3Value = (TextView)rootView.findViewById(R.id.swallow_feet_text);
         
         sb2.setMax(10);


         sb2.setProgress(1);

         sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 

			   @Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
			    // TODO Auto-generated method stub 
				   seekBar3Value.setText(String.valueOf(progress)); 
			   } 

			   @Override 
			   public void onStartTrackingTouch(SeekBar seekBar) { 
			    
				   
			   } 

			   @Override 
			   public void onStopTrackingTouch(SeekBar seekBar) { 
			    // TODO Auto-generated method stub 
			   } 
			       }); 
         
         Button submit = (Button)rootView.findViewById(R.id.symptoms_submit_button);
         submit.setOnClickListener(new OnClickListener(){
	        	
	        	
	        	
	        	@Override
	        	public void onClick(View v) {
	        		
	        		Context context = getActivity().getApplicationContext();
	        		SharedPreferences shared = context.getSharedPreferences("PatientPhone",0);
					String phone = (shared.getString("phone", ""));
	        		// breathe
					 RadioGroup rg = (RadioGroup)rootView.findViewById(R.id.radioBreathe);
			         String selectedRadioBreathe = ((RadioButton)rootView.findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
								
			         // medication
			         RadioGroup rgm = (RadioGroup)rootView.findViewById(R.id.radioMed);
			         String selectedRadioMedication = ((RadioButton)rootView.findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
					
			         Log.d(TAG, selectedRadioBreathe );
			         
			         Log.d(TAG, selectedRadioMedication );
			         
	        		// put inputs in a mp and sent it to makerequest class
	        		Map<String, String> map = new HashMap<String, String>();
	        		map.put("phone", phone);
	        		map.put("chest_pain", seekBarValue.getText().toString());
	        		map.put("wake_up", selectedRadioBreathe);
	        		map.put("breathe", seekBar2Value.getText().toString());
	        		map.put("swollen", seekBar3Value.getText().toString());
	        		map.put("take_medication", selectedRadioMedication);
	        		
			         Log.d(TAG, "HHHHH" );

	        		try {
						MakeRequest mr= new MakeRequest(map);
						 Log.d(TAG, "After make request" );
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
	        		
	      
	        	}
	        		
	        });
		 
    
		return rootView;
	}	

}