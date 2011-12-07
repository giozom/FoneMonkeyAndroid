/*
 * TabsActivity.java
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
import android.content.*;
import android.widget.*;
import android.content.pm.ActivityInfo;

import com.gorillalogic.fonemonkey.FoneMonkey;

public class TabsActivity extends TabActivity
{
	TabHost tabs;
	ViewAnimator animator;

	static TabsActivity me;

	static TabsActivity getInstance() { return me; }

	@Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		me = this;

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		tabs = getTabHost();

		TabHost.TabSpec t = tabs.newTabSpec("CodeButtons")
			.setContent(new Intent(this, CodeButtonsPanel.class))
			.setIndicator(getText(R.string.CodeButtons), getResources().getDrawable(R.drawable.sun_purple));
		tabs.addTab(t);

		t = tabs.newTabSpec("XMLButtons")
			.setContent(new Intent(this, XMLButtonsPanel.class))
			.setIndicator(getText(R.string.XMLButtons), getResources().getDrawable(R.drawable.sun_blue));
		tabs.addTab(t);

		t = tabs.newTabSpec("Images")
			.setContent(new Intent(this, ImagesPanel.class))
			.setIndicator(getText(R.string.Images), getResources().getDrawable(R.drawable.sun_yellow));
		tabs.addTab(t);

		t = tabs.newTabSpec("List")
			.setContent(new Intent(this, ListPanel.class))
			.setIndicator(getText(R.string.List), getResources().getDrawable(R.drawable.sun_red));
		tabs.addTab(t);

		t = tabs.newTabSpec("Text")
			.setContent(new Intent(this, TextPanel.class))
			.setIndicator(getText(R.string.Text), getResources().getDrawable(R.drawable.sun_green));
		tabs.addTab(t);

		t = tabs.newTabSpec("Scroll")
			.setContent(new Intent(this, ImageScrollPanel.class))
			.setIndicator(getText(R.string.Scroll), getResources().getDrawable(R.drawable.sun_orange));
		tabs.addTab(t);

		t = tabs.newTabSpec("Misc")
			.setContent(new Intent(this, MiscPanel.class))
			.setIndicator(getText(R.string.Misc), getResources().getDrawable(R.drawable.sun_yellow));
		tabs.addTab(t);

		((ViewGroup)tabs.getParent()).removeView(tabs);

		animator = new ViewAnimator(this);
		animator.addView(tabs);
		animator.setAnimateFirstView(true);
		setForwardAnimation();

		setContentView(animator);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		// If the client adds a listener like this AspectJ will pick it up
		// (see OnTabChanged.aj). If no listener has been added we add our
		// own FoneMonkey listener to capture tab change events (see
		// OnCreate.aj).
		/*
		tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener()
			{
				public void	onTabChanged(String tabId)
				{
				}
			});
		*/
	}

	void slideForward()
	{
		setForwardAnimation();

		if (animator.getDisplayedChild() == animator.getChildCount()-1)
		{
			ColorView c = new ColorView(this);
			if (animator.getDisplayedChild() > 0)
				c.initFrom((ColorView) animator.getChildAt(animator.getChildCount()-1));

			FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(
						FrameLayout.LayoutParams.FILL_PARENT,
						FrameLayout.LayoutParams.FILL_PARENT);
			c.setLayoutParams(flp);
			animator.addView(c);
		}

		animator.showNext();
	}

	void slideBackward()
	{
		setBackwardAnimation();

		animator.showPrevious();
	}

	void setForwardAnimation()
	{
		animator.setInAnimation(this, R.anim.push_left_in);
		animator.setOutAnimation(this, R.anim.push_left_out);
	}

	void setBackwardAnimation()
	{
		animator.setInAnimation(this, R.anim.push_right_in);
		animator.setOutAnimation(this, R.anim.push_right_out);
	}

	/*
	static List<MenuItem> startStops = new ArrayList<MenuItem>();
	static List<MenuItem> playbacks = new ArrayList<MenuItem>();
	*/

	public boolean onCreateOptionsMenu(Menu menu)
	{
			/*
		MenuItem m = menu.add(0, 1, 0, Recorder.isRecording() ? "Stop Recording" : "StartRecording");
		startStops.add(m);

		m = menu.add(0, 2, 0, "Playback");
		m.setEnabled(!Recorder.isRecording());
		playbacks.add(m);
		*/

		menu.add(0, 3, 0, "Test Button 1");
		menu.add(0, 4, 0, "Alert Dialog");
		menu.add(0, 5, 0, "Spinner");
		menu.add(0, 6, 0, "Quit");

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
			/*
		if (item.getItemId() == 1)
		{
			Recorder.toggleRecording();

			for (MenuItem i : startStops)
				i.setTitle(Recorder.isRecording() ? "Stop Recording" : "StartRecording");
			for (MenuItem i : playbacks)
				i.setEnabled(!Recorder.isRecording());
		}
		else if (item.getItemId() == 2)
		{
			Recorder.playback();
		}
		*/
		if (item.getItemId() == 3)
		{
			 Toast.makeText(this, "Menu Button 1 Pressed ", Toast.LENGTH_SHORT).show();
		}
		else if (item.getItemId() == 4)
		{
			AlertDialog.Builder adb = new AlertDialog.Builder(this)
				.setTitle("Test Dialog")
				.setMessage("A dialog message");

			adb.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int arg1)
					{
						Log.log("OK");
					}
				});

			AlertDialog ad = adb.create();
			ad.setCanceledOnTouchOutside(true);
			ad.show();

			FoneMonkey.register(ad);
		}
		else if (item.getItemId() == 5)
		{
			CharSequence[] opts = new CharSequence[50];
			for (int i=0; i < opts.length; ++i) opts[i] = "Option #" + i;

			Spinner s = new Spinner(this);
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, opts);

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			s.setAdapter(adapter);
			s.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		}
		else if (item.getItemId() == 6) finish();

		return true;
	}
	
	@Override
	public void onBackPressed()
	{
		finish();
	}

	/*
	@Override
	public void onBackPressed()
	{
		finish();
	}
	*/

	@Override
	public void onStart()
	{
		super.onStart();
		
		try
		{
			Class.forName("com.gorillalogic.droidmonkey.aspects.OnClick");
		}
		catch (Exception e)
		{
			com.gorillalogic.fonemonkey.Log.log("AspectJ Handlers Not Found");
			return;
		}
		com.gorillalogic.fonemonkey.Log.log("STARTED NORMALLY");
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

