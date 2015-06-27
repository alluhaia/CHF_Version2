package com.example.chf_mock_adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.chf_mock_1.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DeviceSettingAdapter extends ArrayAdapter<Device> {

	private ImageView img;
	private TextView deviceName;
	private TextView deviceAvailable;
	private List<Device> devices = new ArrayList<Device>();
	private LinearLayout wrapper;

	@Override
	public void add(Device object) {
		devices.add(object);
		super.add(object);
	}

	public DeviceSettingAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.devices.size();
	}

	public Device getItem(int index) {
		return this.devices.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.setting, parent, false);
		}


		Device coment = getItem(position);

		img = (ImageView) row.findViewById(R.id.imageView1);
//
		
		if (coment.name.equals("cuff")) {
			
			img.setImageResource(R.drawable.blood_cuff);
			
		} else {
			
			img.setImageResource(R.drawable.weight);

			
		}
		
	
	    deviceName = (TextView) row.findViewById(R.id.TextView01);
//	    
	    deviceName.setText(coment.name);
	    
	    deviceAvailable = (TextView) row.findViewById(R.id.textView1);
	    
	    deviceAvailable.setTextColor(Color.parseColor("#7CFC00"));
		
	    System.out.println("test " + coment.name+" /// ");

	    

		
		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}