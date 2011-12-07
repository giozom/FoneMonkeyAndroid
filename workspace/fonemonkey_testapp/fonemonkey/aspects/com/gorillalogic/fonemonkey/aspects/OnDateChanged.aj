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

// OnDateChanged.aj
// Created Nov 6, 2011 by stu.stern@gorillalogic.com
// Copyright 2011 Gorilla Logic, Inc. All Rights Reserved


package com.gorillalogic.fonemonkey.aspects;
 
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;

import com.gorillalogic.fonemonkey.automators.AutomationManager;
 
aspect OnDateChanged
{
    pointcut captureOnDateChanged() : (execution(* onDateChanged(DatePicker, int, int, int)));

	// AspectJ compiler does not like a tab for indenting this line?!?!?!
	// (And presumably many others)
    before(): captureOnDateChanged()
	{
		DatePicker view = (DatePicker) thisJoinPoint.getArgs()[0];
		String year = ((Integer) thisJoinPoint.getArgs()[1]).toString();	
		String monthOfYear = ((Integer) thisJoinPoint.getArgs()[2]).toString();	
		String dayOfMonth = ((Integer) thisJoinPoint.getArgs()[3]).toString();	

		AutomationManager.record("EnterText", view, year + " " + monthOfYear + " " + dayOfMonth);
		
    }
}