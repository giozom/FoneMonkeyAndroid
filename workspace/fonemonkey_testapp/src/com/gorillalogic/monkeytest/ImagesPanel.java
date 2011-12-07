/*
 * ImagesPanel.java
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

public class ImagesPanel extends Activity
{
	@Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		setContentView(R.layout.images);

		View.OnClickListener callback = new View.OnClickListener()
			{
				public void onClick(View image)
				{
					message((ImageView) image);
				}
			};

		ImageView i = (ImageView) findViewById(R.id.bulb);
		i.setOnClickListener(callback);

		i = (ImageView) findViewById(R.id.sun_and_clouds);
		i.setOnClickListener(callback);

		i = (ImageView) findViewById(R.id.radar);
		i.setOnClickListener(callback);
	}

	void message(ImageView b)
	{
		Toast.makeText(this, "ImageView Pressed:\n" + getResources().getResourceName(b.getId()), Toast.LENGTH_SHORT).show();
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

