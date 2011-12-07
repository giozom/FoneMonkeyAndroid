/*
 * ColorView.java
 * FoneMonkey Test
 * 
 * Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/14/11.
 * Copyright 2011 Gorilla Logic, Inc. All rights reserved.
 */
package com.gorillalogic.monkeytest;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.gesture.*;
import android.graphics.Typeface;

public class ColorView extends GestureOverlayView
				implements GestureOverlayView.OnGesturePerformedListener
{
	int color;
	int idx;
	TextView text;
	GestureLibrary gestureLib;
	TabsActivity tabs;

    public ColorView(TabsActivity c)
    {
		super(c);

		tabs = c;

		setBackgroundColor(color = 0xff00ff00);

		addText("PANEL #" + idx);

		addOnGesturePerformedListener(this);
		gestureLib = GestureLibraries.fromRawResource(c, R.raw.gestures);
		gestureLib.load();
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
			Toast.makeText(getContext(), "Gesture is " + best.name, Toast.LENGTH_SHORT).show();
			if (best.name.equals("TO_LEFT")) tabs.slideForward();
			else if (best.name.equals("TO_RIGHT")) tabs.slideBackward();
		}
	}

	void initFrom(ColorView c)
	{
		setBackgroundColor(color = c.color + 100);
		idx = c.idx + 1;
		text.setText("PANEL #" + idx);
		text.setTextColor(idx == 3 || idx == 8 ? 0xffffffff : 0xff000000);
	}

	void addText(String s)
	{
		FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.WRAP_CONTENT,
						FrameLayout.LayoutParams.WRAP_CONTENT,
						Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);
		text = new TextView(getContext());
		text.setText(s);
		text.setTextSize(24);
		text.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
		text.setTextColor(0xff000000);
		text.setLayoutParams(flp);

		addView(text);
	}
}

