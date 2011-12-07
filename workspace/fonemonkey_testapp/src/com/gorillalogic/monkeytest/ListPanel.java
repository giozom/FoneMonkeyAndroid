/*
 * ListPanel.java
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

public class ListPanel extends ListActivity
{
	@Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

		ListView list = getListView();

		list.setTag(R.id.MonkeyID, "TestList-MonkeyID");

		list.setBackgroundColor(0xFFFFFF);
		list.setFocusable(false);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			public void	onItemClick(AdapterView parent, View view, int position, long id)
			{
				message((TextView)view, false);
 			}
		}); 
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
		{
			public boolean	onItemLongClick(AdapterView parent, View view, int position, long id)
			{
				message((TextView)view, true);
				return true;
 			}
		});

		// TBD: Handle selection listener

		setListAdapter(new MyAdapter());
	}

	void message(TextView t, boolean isLong)
	{
		Toast.makeText(this, "List Item " + (isLong ? "LONG" : "NORMAL") + " Press:\n" + t.getText(), Toast.LENGTH_SHORT).show();
	}

	class MyAdapter extends BaseAdapter implements ListAdapter
	{
		public int getCount() { return 100; }

		public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

		public View getView(int position, View convertView, ViewGroup parent)
		{
			TextView t;

			if (convertView != null) t = (TextView) convertView;
			else
			{
				t = new TextView(ListPanel.this);
				t.setTextSize(32);
				t.setTextColor(0xFF0000FF);
				t.setBackgroundColor(0xFFFFFFFF);
				t.setDrawingCacheBackgroundColor(0x00000000);
			}

			t.setText("List Item Row #" + position);

			return t;
		}

		public boolean areAllItemsSelectable() { return true; }

		public boolean isSelectable(int position) { return true; }
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

