/*
 * MiscPanel.java
 * FoneMonkey Test
 * 
 * Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/27/11.
 * Copyright 2011 Gorilla Logic, Inc. All rights reserved.
*/
package com.gorillalogic.monkeytest;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;

public class MiscPanel extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		setContentView(R.layout.misc);

		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this, R.array.planets_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

		Button toggleButton = (Button) findViewById(R.id.togglebutton);
		View.OnClickListener listener = new View.OnClickListener()
		{
			public void onClick(View view)
			{
				Log.log("Click " + view.getClass().getName());				
			}
		};
		toggleButton.setOnClickListener(listener);
		toggleButton = (Button) findViewById(R.id.togglebutton2);
		toggleButton.setOnClickListener(listener);

		RadioButton rb = (RadioButton) findViewById(R.id.radio1);
		rb.setOnClickListener(listener);
		rb = (RadioButton) findViewById(R.id.radio2);
		rb.setOnClickListener(listener);
		
		SeekBar bar = (SeekBar) findViewById(R.id.slider);
		bar.setOnSeekBarChangeListener(new MySeekListener());

		RatingBar rbar = (RatingBar) findViewById(R.id.rating);
		rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
			{
				public void	onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
				{
					Log.log("RATING " + rating);
				}
			});
	}

	public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener
	{
    	public void onItemSelected(AdapterView<?> parent,
				View view, int pos, long id)
		{
			Toast.makeText(parent.getContext(), "The planet is " +
				parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
    	}

		public void onNothingSelected(AdapterView parent)
		{
		}
	}

	public class MySeekListener implements SeekBar.OnSeekBarChangeListener
	{
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
		{
			// AspectJ will pick up this call
		}

		public void onStartTrackingTouch(SeekBar seekBar) {}

		public void onStopTrackingTouch(SeekBar seekBar) {}
	}

	// This callback is referenced in the layout XML file
	public void checked(View v)
	{
		Log.log("CHECKED");
	}

	// This callback is referenced in the layout XML file
	public void pickDate(View v)
	{
		MyPopup mp = new MyPopup(this);

		mp.showAtLocation(findViewById(R.id.misc_panel), Gravity.CENTER, 0, 0);
	}

	// This callback is referenced in the layout XML file
	/* Add back later
	public void showMap(View v)
	{
		Intent i = new Intent(this, MapPanel.class);
		startActivity(i);
	}
	*/

	static class MyPopup extends PopupWindow
	{
		MyPopup(final Activity context)
		{
			super(new LinearLayout(context));

			LinearLayout ll = (LinearLayout) getContentView();
			ll.setOrientation(LinearLayout.VERTICAL);
			ll.setBackgroundColor(0xC0CCCCCC);

			DatePicker dp = new DatePicker(context);
			dp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			dp.setContentDescription("End Date");
			dp.init(2012, 1, 1, new DatePicker.OnDateChangedListener()
			{
				public void	onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
				{
				}
			});

			ll.addView(dp);

			Button b = new Button(context);
			b.setText("Cancel");
			b.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

			// This is better way to identify the button since there may be
			// many other buttons named "Cancel" in the app
			b.setTag(R.id.MonkeyID, "PopupWindow-Cancel-MonkeyID");

			ll.addView(b);

			b.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View v)
				{
					dismiss();
				}
			});

			setOnDismissListener(new PopupWindow.OnDismissListener()
			{
				public void onDismiss()
				{
					Button b = (Button) context.findViewById(R.id.datebutton);
					b.setEnabled(true);
				}
			});

			b = (Button) context.findViewById(R.id.datebutton);
			b.setEnabled(false);

			setWidth(400);
			setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		}
	}
}

