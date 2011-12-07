/*
 * XMLButtonsPanel.java
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

public class XMLButtonsPanel extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

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

		setContentView(R.layout.buttons);

		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		b = (Button) findViewById(R.id.button2);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		b = (Button) findViewById(R.id.button3);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);

		b = (Button) findViewById(R.id.button4);
		b.setOnClickListener(callback);
		b.setLongClickable(true);
		b.setOnLongClickListener(longCallback);
	}

	void message(Button b, boolean isLong)
	{
		Toast.makeText(this, "XML Button " + (isLong ? "LONG" : "NORMAL") + " Press:\n" + b.getText(), Toast.LENGTH_SHORT).show();
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

