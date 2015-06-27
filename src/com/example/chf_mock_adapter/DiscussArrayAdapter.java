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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiscussArrayAdapter extends ArrayAdapter<OneComment> {

	private TextView countryName;
	private TextView datetimeMsg;
	private List<OneComment> countries = new ArrayList<OneComment>();
	private LinearLayout wrapper;

	@Override
	public void add(OneComment object) {
		countries.add(object);
		super.add(object);
	}

	public DiscussArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.countries.size();
	}

	public OneComment getItem(int index) {
		return this.countries.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_chat, parent, false);
		}

		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		OneComment coment = getItem(position);

		countryName = (TextView) row.findViewById(R.id.comment);

		countryName.setText(coment.comment);
		
		//set date
	
	    SimpleDateFormat form2 = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm");
//		
	    datetimeMsg = (TextView) row.findViewById(R.id.dateInfo);
//	    
	    System.out.println("test " + coment.comment+" /// "+coment.date);

	    
	    if (coment.date!=null) {
	    	
			datetimeMsg.setText(coment.date);
////		
	    }
		countryName.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);
		
		
	
		datetimeMsg.setGravity(Gravity.LEFT);
		
		return row;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}