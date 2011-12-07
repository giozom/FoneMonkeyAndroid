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
//  OnItemSelected.aj
//  FoneMonkey
//
//  Created by Shaun Terry (shaun.terry@gorillalogic.com) on 09/06/11.
//  Copyright 2011 Gorilla Logic, Inc. All rights reserved.
//
package com.gorillalogic.fonemonkey.aspects;
 
import java.util.ArrayList;

import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint;

import android.view.View;
import android.widget.*;

import com.gorillalogic.fonemonkey.*;
import com.gorillalogic.fonemonkey.automators.AutomationManager;
 
aspect OnItemSelected
{
    pointcut captureOnItemSelected() : (execution(* onItemSelected(AdapterView, View, int, long)));

	before(): captureOnItemSelected()
	{
		
		if (AutomationManager.record(Operation.Select.toString(), thisJoinPoint.getArgs())) {	
			return;
		}
		
		AdapterView parent = (AdapterView) thisJoinPoint.getArgs()[0];
		View view = (View) thisJoinPoint.getArgs()[1];
		int rowNum = (Integer) thisJoinPoint.getArgs()[2];
		long rowID = (Long) thisJoinPoint.getArgs()[3];

		
		if (Recorder.isPlayingBack()) return;


		Object target = thisJoinPoint.getTarget();

		Object obj = parent.getItemAtPosition(rowNum);

		if (!(obj instanceof String)) obj = null;

		WidgetReference wref = WidgetReference.create(view, parent, rowNum, (String) obj, rowID);
		wref.operation = Operation.Click;

		Recorder.addEvent(wref);

        Log.log("OnItemSelected: " + wref);
    }
}

