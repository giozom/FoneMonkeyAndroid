/*
 * CodeButtonsPanel.java
 * FoneMonkey Test
 * 
 * Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/06/11.
 * Copyright 2011 Gorilla Logic, Inc. All rights reserved.
*/
package com.gorillalogic.monkeytest;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.gesture.*;

public class CodeButtonsPanel extends Activity
				implements GestureOverlayView.OnGesturePerformedListener
{
	GestureLibrary gestureLib;

	@Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setGravity(Gravity.CENTER);

		ll.setBackgroundResource(R.drawable.candle);

		View.OnClickListener callback = new View.OnClickListener()
			{
				public void onClick(View button)
				{
					message((Button) button, false);
				}
			};

		View.OnLongClickListener longCallback = new View.OnLongClickListener()
			{
				public boolean onLongClick(View button)
				{
					message((Button) button, true);
					return true;
				}
			};

		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams llp2 = new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.WRAP_CONTENT);
		llp2.setMargins(0, 80, 0, 0);

		Button b = makeButton(R.string.CodeButton1);
		ll.addView(b);
		b.setLayoutParams(llp);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		b = makeButton(R.string.CodeButton2);
		ll.addView(b);
		b.setLayoutParams(llp2);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

//		b = makeButton(R.string.CodeButton3);
		b = makeButton("Code Button 3 (hardcoded)");
		ll.addView(b);
		b.setLayoutParams(llp2);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		b.setTag(R.id.MonkeyID, "CodeButton3-MonkeyID");

//		b = makeButton(R.string.CodeButton4);
		b = makeButton("Code Button 4 (hardcoded)");
		ll.addView(b);
		b.setLayoutParams(llp2);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
		gestureOverlayView.addView(ll);
		gestureOverlayView.addOnGesturePerformedListener(this);
		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!gestureLib.load())
		{
			Log.log("GESTURES LIB FAILED TO LOAD");
			finish();
		}

		setContentView(gestureOverlayView);
	}

	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
	{
		Prediction best = null;
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		for (Prediction prediction : predictions)
		{
			if (prediction.score > 0)
			{
				if (best == null || prediction.score > best.score) best = prediction;
			}
		}
		if (best != null)
		{
			Toast.makeText(this, "Gesture is " + best.name, Toast.LENGTH_SHORT).show();
			if (best.name.equals("TO_LEFT")) TabsActivity.getInstance().slideForward();
		}
	}

	void message(Button b, boolean isLong)
	{
		Toast.makeText(this, "Code Button " + (isLong ? "LONG" : "NORMAL") + " Press:\n" + b.getText(), Toast.LENGTH_SHORT).show();
	}

	Button makeButton(int resid)
	{
		return makeButton(getText(resid));
	}

	Button makeButton(CharSequence label)
	{
		Button b = new Button(this);
		b.setText(label);
		b.setTypeface(Typeface.DEFAULT_BOLD);
		b.setBackgroundResource(R.drawable.rounded_button_bg_violet);
		b.setTextSize(20);
		return b;
	}

	@Override
	public void onStart()
	{
		super.onStart();
	}

	@Override
    public void onPause()
    {
		super.onPause();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
}

