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
import android.text.TextWatcher;
import android.text.Editable;

import com.gorillalogic.fonemonkey.TextListener;

public class TextPanel extends Activity
{
	@Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		LinearLayout ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setPadding(10, 10, 10, 10);

		ll.setBackgroundResource(R.drawable.fantasy_land);

		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(300, 100);
		llp.setMargins(0, 20, 0, 0);

		EditText e = new EditText(this);
		e.setTag(R.id.MonkeyID, "Text1-MonkeyID");
		e.setLayoutParams(llp);
		e.setContentDescription("#1");
		ll.addView(e);

		e = new EditText(this);
		e.setContentDescription("#2");		
// Can use MonkeyID -- if not will default to ordinal position. EditText fields
// cannot be identified by text content like, for instance, buttons can because
// it doesn't really make sense to do so.
//		e.setTag(R.id.MonkeyID, "Text2-MonkeyID");
		e.setLayoutParams(llp);
		ll.addView(e);

		/* Uncomment this to verify that it is possible to have a client 
		 * TextWatcher as well as a FoneMonkey TextWatcher
		e.addTextChangedListener(new TextWatcher()
		{
			public void	afterTextChanged(Editable s) {}

			public void	beforeTextChanged(CharSequence s, int start, int count, int after) {}

			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				com.gorillalogic.droidmonkey.Log.log("TEXT CHANGED(" + start + "," + before + "," + count + ") " + s); 
			}
		});
		*/

		setContentView(ll);
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

