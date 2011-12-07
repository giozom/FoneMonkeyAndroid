/*
 * ImageScrollPanel.java
 * FoneMonkey Test
 * 
 * Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/15/11.
 * Copyright 2011 Gorilla Logic, Inc. All rights reserved.
*/
package com.gorillalogic.monkeytest;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.gorillalogic.fonemonkey.MultiplexedOnTouchListener;

public class ImageScrollPanel extends Activity
{
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		setContentView(R.layout.scroll);

		final ScrollView sv = (ScrollView) findViewById(R.id.scrollview);

		ImageView v = (ImageView) findViewById(R.id.eiffel_tower);

		v.setOnTouchListener(new MultiplexedOnTouchListener(new View.OnTouchListener()
			{
				public boolean onTouch(View v, MotionEvent m)
				{
					if (m.getAction() != MotionEvent.ACTION_DOWN) return false;

					Toast t = Toast.makeText(ImageScrollPanel.this, "Touched Image @ (" + ((int) m.getX()) + ", " + ((int) m.getY()) + ")", Toast.LENGTH_SHORT);
					t.setGravity(Gravity.TOP|Gravity.LEFT, (int)m.getX()-sv.getScrollX(), (int)m.getY()-sv.getScrollY());
					t.show();

					// Important we do not handle the event so it will propogate
					// to the enclosing ScrollView
					return false;
				}
			}));
	}
}

