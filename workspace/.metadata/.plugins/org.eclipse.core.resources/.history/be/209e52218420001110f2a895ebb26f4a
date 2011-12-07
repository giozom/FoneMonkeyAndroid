/* This file is part of FoneMonkey.

    FoneMonkey is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FoneMonkey is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with FoneMonkey.  If not, see <http://www.gnu.org/licenses/>.  */
//
//  OnItemLongClick.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/08/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import android.view.View;
import android.widget.*;

import com.gorillalogic.fonemonkey.*;
 
aspect OnItemLongClick
{
    pointcut captureOnItemLongClick() : (execution(* onItemLongClick(AdapterView, View, int, long)));

	before(): captureOnItemLongClick()
	{
		if (Recorder.isPlayingBack()) return;

		AdapterView parent = (AdapterView) thisJoinPoint.getArgs()[0];
		View view = (View) thisJoinPoint.getArgs()[1];
		int rowNum = (Integer) thisJoinPoint.getArgs()[2];
		long rowID = (Long) thisJoinPoint.getArgs()[3];
		Object target = thisJoinPoint.getTarget();

		WidgetReference wref = WidgetReference.create(view, parent, rowNum, null, rowID);
		wref.operation = Operation.LongClick;

		Recorder.addEvent(wref);

        Log.log("OnItemLongClick: " + wref);
    }
}

